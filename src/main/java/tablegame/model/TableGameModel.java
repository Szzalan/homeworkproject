package tablegame.model;
import java.util.ArrayList;
/**
 *The TableGameModel contains business logic behind the project
 */
public class TableGameModel {

    private static final int BOARD_ROWS = 6;

    private int stepCounter = 0;

    private static final int BOARD_COLS = 7;
    State[][] table = new State[BOARD_ROWS][BOARD_COLS];

    private State currentPlayer = State.BLUE;

    /**
     *Creates a {@code Table} object that corresponds to the
     *initial state of the table game
     */
    public TableGameModel() {
        fillTable();
    }

    /**
     *
     * @return the game table
     */
    public State[][] getTable() {
        return table;
    }
    /**
     *Creates a {@code TableGameModel} object where the initial state is given
     * @param table table to be used
     */
    public TableGameModel(State[][] table) {
        this.table = table;
    }

    /**
     * Initializes the starting table
     */
    private void fillTable(){
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++){
                setState(i,j);
            }
        }
    }

    /**
     *
     * @return the number of steps made
     */
    public int getStepCounter() {
        return stepCounter;
    }

    /**
     *
     * @return the current player
     */
    public State getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Changes the current player after each turn
     */

    public void changeCurrentPlayer() {
        if(currentPlayer == State.BLUE){
            currentPlayer = State.RED;
        } else {
            currentPlayer = State.BLUE;
        }
    }

    /**
     * Checks whether the game is in an end state or not
     * @return red or blue if the game has ended otherwise none
     */
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

    /**
     * Determines if the given player has any moves left
     * @param player the player whose moves are to be checked
     * @return boolean value if the player has no moves left
     */
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

    /**
     * Determines if the given player has any pieces left
     * @param player the player whose pieces are to be checked
     * @return boolean value if the player has no moves left
     */
    private boolean noMorePieces(State player){
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
                if(table[i][j] == player)
                    return false;
            }
        }
        return true;
    }

    /**
     * Sets the state of a given cell indexed by row and col
     * @param row the row coordinate of the cell
     * @param col the column coordinate of the cell
     */
    private void setState(int row,int col){
        if(row == 0) table[row][col] = State.RED;
        else if (row == 5) table[row][col] = State.BLUE;
        else if ( (row == 3 && col == 2) || (row == 2 && col == 4) ) table[row][col] = State.BLACK;
        else table[row][col] = State.NONE;

    }

    /**
     * Returns the possible moves of the piece indexed by row and col
     * @param row the row coordinate of the piece
     * @param col the column coordinate of the piece
     * @return an arraylist of the proper Positions
     */
    public ArrayList<Position> getPossibleMoves(int row, int col){
        ArrayList<Position> steps = new ArrayList<>();
        int direction = table[row][col] == State.RED ? 1 : -1;
        Position currentPosition = new Position(row,col);

        checkPosition(steps,currentPosition,new Position(row+direction,col));
        checkPosition(steps,currentPosition,new Position(row+direction,col+1));
        checkPosition(steps,currentPosition,new Position(row+direction,col-1));

        return steps;
    }

    /**
     * Moves the given piece from start to target coordinate
     * @param start determines the starter position of a piece
     * @param target determines the target position of a piece
     */
    public void move(Position start,Position target){
        table[target.row()][target.col()] = table[start.row()][start.col()];
        table[start.row()][start.col()] = State.NONE;
        stepCounter++;
    }

    /**
     * If the given Position is a correct target cell adds it to steps
     * @param steps An arraylist of possible target Positions
     * @param start marks the start position of a piece
     * @param target marks the target position of a piece
     */
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

    /**
     * Checks if the state of the target cell is correct
     * @param start the state of start cell
     * @param target the state of target cell
     * @param startCol the column coordinate of the startcell
     * @param targetCol the column coordinate of the target cell
     * @return logical value whether the target cell is correct
     */
    private boolean checkStatus(State start,State target,int startCol,int targetCol) {
        if(target != start && target != State.BLACK){
            return target == State.NONE || startCol != targetCol;
        }
        return false;
    }
}
