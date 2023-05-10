package tablegame.model;

import java.util.ArrayList;

public class TableGameModel {

    private static final int BOARD_HORIZONTAL = 7;
    private static final int BOARD_VERTICAL = 6;
    State[][] table = new State[BOARD_HORIZONTAL][BOARD_VERTICAL];

    public TableGameModel() {
        fillTable();
    }
    private void fillTable(){
        for (int i = 0;i < BOARD_HORIZONTAL;i++) {
            for (int j = 0;j < BOARD_VERTICAL;j++){
                setState(i,j);
            }
        }
    }
    private void setState(int row,int col){
        if(row == 0) table[row][col] = State.RED;
        else if (row == 7) table[row][col] = State.BLUE;
        else if ( (row == 4 && col == 2) || (row == 2 && col == 3) ) table[row][col] = State.BLACK;
        else table[row][col] = State.NONE;

    }


}
