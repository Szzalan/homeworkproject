package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tablegame.model.Position;
import tablegame.model.State;
import tablegame.model.TableGameModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TableGameModelTest {
    @Test
    void testChangeCurrentPlayer(){
        TableGameModel table = new TableGameModel();

        table.changeCurrentPlayer();
        assertEquals(State.RED,table.getCurrentPlayer());

        table.changeCurrentPlayer();
        assertEquals(State.BLUE,table.getCurrentPlayer());
    }

    @Test
    void testGoalCheckWhenTheGameIsNotInGoalState(){
        TableGameModel table = new TableGameModel();

        State expectedResult = State.NONE;
        State actualResult = table.goalCheck();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGoalCheckWhenRedHasNoMoreMovesLeft(){
        TableGameModel table = new TableGameModel(TestTables
                .getTestTableForGoalCheckWhenRedHasNoMoreMovesLeft());

        State expectedResult = State.BLUE;
        State actualResult = table.goalCheck();

        assertEquals(expectedResult, actualResult);
    }
    @Test
    void testGoalCheckWhenBueHasNoMorePiecesLeft(){
        TableGameModel table = new TableGameModel(TestTables
                .getTestTableForGoalCheckWhenBueHasNoMorePiecesLeft());

        State expectedResult = State.RED;
        State actualResult = table.goalCheck();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGetPossibleMovesWhenAllTargetsAreFree() {
        TableGameModel table = new TableGameModel();

        ArrayList<Position> actualResult = table.getPossibleMoves(5, 1);
        ArrayList<Position> expectedResult = new ArrayList<>();

        expectedResult.add(new Position(4, 1));
        expectedResult.add(new Position(4, 0));
        expectedResult.add(new Position(4, 2));

        assertTrue(equalLists(expectedResult, actualResult));
    }

    @Test
    void testGetPossibleMovesWhenAllSpecialConditionsOccur() {
        TableGameModel table = new TableGameModel(TestTables
                .getTestTableForGetPossibleMovesWhenAllSpecialConditionsOccur());

        ArrayList<Position> actualResult = table.getPossibleMoves(2, 1);
        ArrayList<Position> expectedResult = new ArrayList<>();

        expectedResult.add(new Position(3, 0));

        assertTrue(equalLists(expectedResult, actualResult));
    }

    @Test
    void testMoveWhenRedGoesStraight() {
        TableGameModel table = new TableGameModel();

        Position startPosition = new Position(0,1);
        Position targetPosition = new Position(1,1);

        table.move(startPosition,targetPosition);

        assertEquals(State.NONE,
                table.getTable()[startPosition.row()][startPosition.col()]);
        assertEquals(State.RED,
                table.getTable()[targetPosition.row()][targetPosition.col()]);
    }

    @Test
    void testMoveWhenBlueGoesDiagonally() {
        TableGameModel table = new TableGameModel(TestTables
                .getTestTableForMoveWhenBlueGoesDiagonally());

        Position startPosition = new Position(5,1);
        Position targetPosition = new Position(4,0);

        assertEquals(State.RED,
                table.getTable()[targetPosition.row()][targetPosition.col()]);

        table.move(startPosition,targetPosition);

        assertEquals(State.NONE,
                table.getTable()[startPosition.row()][startPosition.col()]);
        assertEquals(State.BLUE,
                table.getTable()[targetPosition.row()][targetPosition.col()]);
    }
    private boolean equalLists(ArrayList<Position> list1, ArrayList<Position> list2) {
        if(list1.size() != list2.size()) {
            return false;
        }

        for (Position x : list1) {
            if (!list2.contains(x)) {
                return false;
            };
        }

        return true;
    }
}