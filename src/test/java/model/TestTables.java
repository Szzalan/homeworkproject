package model;

import tablegame.model.State;
import tablegame.model.TableGameModel;

public class TestTables {
    private static final State[][] testTableForGoalCheckWhenRedHasNoMoreMovesLeft =
            {
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.BLACK, State.BLUE, State.NONE},
                    {State.NONE, State.NONE, State.BLACK, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.RED, State.NONE, State.RED, State.NONE, State.NONE, State.NONE},
            };

    static State[][] getTestTableForGoalCheckWhenRedHasNoMoreMovesLeft() {
        return testTableForGoalCheckWhenRedHasNoMoreMovesLeft;
    }
    private static final State[][] testTableForGoalCheckWhenBueHasNoMorePiecesLeft =
            {
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.BLACK, State.NONE, State.NONE},
                    {State.NONE, State.NONE, State.BLACK, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.RED, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.NONE, State.NONE, State.RED, State.NONE, State.NONE, State.NONE},
            };
    static State[][] getTestTableForGoalCheckWhenBueHasNoMorePiecesLeft() {
        return testTableForGoalCheckWhenBueHasNoMorePiecesLeft;
    }
    private static final State[][] testTableForGetPossibleMovesWhenAllSpecialConditionsOccur =
            {
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.RED, State.NONE, State.NONE, State.BLACK, State.NONE, State.NONE},
                    {State.BLUE, State.BLUE, State.BLACK, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
            };
    static State[][] getTestTableForGetPossibleMovesWhenAllSpecialConditionsOccur() {
        return testTableForGetPossibleMovesWhenAllSpecialConditionsOccur;
    }
    private static final State[][] testTableForMoveWhenBlueGoesDiagonally =
            {
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.RED, State.NONE, State.NONE, State.BLACK, State.NONE, State.NONE},
                    {State.BLUE, State.BLUE, State.BLACK, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.RED, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
                    {State.NONE, State.BLUE, State.NONE, State.NONE, State.NONE, State.NONE, State.NONE},
            };
    static State[][] getTestTableForMoveWhenBlueGoesDiagonally() {
        return testTableForMoveWhenBlueGoesDiagonally;
    }

}
