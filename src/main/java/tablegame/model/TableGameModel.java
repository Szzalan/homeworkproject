package tablegame.model;

import java.util.ArrayList;

public class TableGameModel {

    private static final int BOARD_ROWS = 6;



    private static final int BOARD_COLS = 7;
    State[][] table = new State[BOARD_ROWS][BOARD_COLS];

    private State currentPlayer = State.BLUE;

    public TableGameModel() {
        fillTable();
    }
    public State[][] getTable() {
        return table;
    }
    public TableGameModel(State[][] table) {
        this.table = table;
    }
    private void fillTable(){
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++){
                setState(i,j);
            }
        }
    }

    public State getCurrentPlayer() {
        return currentPlayer;
    }

    public void changeCurrentPlayer() {
        if(currentPlayer == State.BLUE){
            currentPlayer = State.RED;
        } else {
            currentPlayer = State.BLUE;
        }
    }
    public State goalCheck(){

        if(noMorePieces(State.RED)){
            return State.BLUE;
        } else if(noMorePieces(State.BLUE)){
            return State.RED;
        } else if (noMovesLeft(State.BLUE)){
            return State.RED;
        } else if (noMovesLeft(State.RED)){
            return State.BLUE;
        }

        return State.NONE;
    }
    private boolean noMovesLeft(State player){
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
                if(table[i][j] == player){
                    if(getPossibleMoves(i,j).size() != 0)
                        return false;
                }
            }
        }
        return true;
    }
    private boolean noMorePieces(State player){
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
                if(table[i][j] == player)
                    return false;
            }
        }
        return true;
    }
    private void setState(int row,int col){
        if(row == 0) table[row][col] = State.RED;
        else if (row == 5) table[row][col] = State.BLUE;
        else if ( (row == 3 && col == 2) || (row == 2 && col == 4) ) table[row][col] = State.BLACK;
        else table[row][col] = State.NONE;

    }
    public ArrayList<Position> getPossibleMoves(int row, int col){
        ArrayList<Position> steps = new ArrayList<>();
        int direction = table[row][col] == State.RED ? 1 : -1;
        Position currentPosition = new Position(row,col);

        checkPosition(steps,currentPosition,new Position(row+direction,col));
        checkPosition(steps,currentPosition,new Position(row+direction,col+1));
        checkPosition(steps,currentPosition,new Position(row+direction,col-1));

        return steps;
    }
    public void move(Position start,Position target){
        table[target.row()][target.col()] = table[start.row()][start.col()];
        table[start.row()][start.col()] = State.NONE;
    }
    private void checkPosition(ArrayList<Position> steps,Position start,Position target) {
        if((target.row() >= 0 && target.row() < BOARD_ROWS) &&
        (target.col() >= 0 && target.col() < BOARD_COLS)) {
            State startState = table[start.row()][start.col()];
            State targetState = table[target.row()][target.col()];

            if(checkStatus(startState,targetState, start.col(), target.col())){
                steps.add(target);
            }
        }
    }
    private boolean checkStatus(State start,State target,int startCol,int targetCol) {
        if(target != start && target != State.BLACK){
            //előre és sréhen is
            return target == State.NONE || startCol != targetCol;
        }
        return false;
    }
}
