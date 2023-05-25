package tablegame.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tablegame.json.JsonResult;
import tablegame.json.JsonManager;
import tablegame.model.Position;
import tablegame.model.State;
import tablegame.model.TableGameModel;
import org.tinylog.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TableGameController {
    @FXML
    private GridPane table;

    private TableGameModel model = new TableGameModel();

    private int stepProgress = 1;
    private int startX;
    private int startY;

    @FXML
    public void initialize() {
        for (var i = 0; i < table.getRowCount(); i++) {
            for (var j = 0; j < table.getColumnCount(); j++) {
                Node state = createState(i, j);
                table.add(state, j, i);
                state.setOnMouseClicked(this::handleMouseClick);
            }
        }


    }
    private StackPane createState(int i, int j) {
        StackPane pane = new StackPane();

        if( model.getTable()[i][j] == State.RED)
        {
            pane.getChildren().add(new Circle(50, Color.RED));
        }
        else if( model.getTable()[i][j] == State.BLUE)
        {
            pane.getChildren().add(new Circle(50, Color.BLUE));
        }
        else if( model.getTable()[i][j] == State.BLACK)
        {
            pane.getChildren().add(new Circle(50,Color.BLACK));
            pane.setStyle("-fx-background-color: black;");
        }
        else
        {
            pane.getChildren().add(new Circle(50, Color.TRANSPARENT));
        }

        return pane;
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var cell = (StackPane) event.getSource();
        var shape = cell.getChildren().get(0);
        var row = GridPane.getRowIndex(cell);
        var col = GridPane.getColumnIndex(cell);
        Logger.info("Clicked: {},{} whose state is: {}",row,col,model.getTable()[row][col]);
        if(stepProgress == 1)
            stepProgressOne((Circle) shape,row,col);

        else
            stepProgressTwo(cell,row,col);
    }
    private void stepProgressOne(Circle shape,int row, int col) {
        if(isValidPiece(shape)){
            ArrayList<Position> steps = model.getPossibleMoves(row,col);

            for(Position position:steps){
                StackPane currentCell = getNodeByCoordinate(position.row(), position.col());

                currentCell.setStyle("-fx-border-color: lime; -fx-border-width: 2;");
            }

            stepProgress = 2;
            startX = row;
            startY = col;
        }
    }
    private void stepProgressTwo(StackPane cell,int row,int col) {
        if(cell.getStyle().contains("-fx-border-color: lime;")){
            model.move(new Position(startX,startY),new Position(row,col));
            model.changeCurrentPlayer();
            handlePieceMovement(cell);
            if(model.goalCheck() != State.NONE)
                handleVictory(model.goalCheck());

        }
        removeBorders();
        stepProgress = 1;
    }
    private void handlePieceMovement(StackPane targetCell) {
        StackPane startCell = getNodeByCoordinate(startX,startY);
        targetCell.getChildren().remove(0);

        Color color = (Color) ((Circle) startCell.getChildren().get(0)).getFill();
        targetCell.getChildren().add(new Circle(50,color));
        startCell.getChildren().remove(0);
        startCell.getChildren().add(new Circle(50, Color.TRANSPARENT));
    }
    private void removeBorders() {
        for (Node node : table.getChildren()) {

            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);

            if (r != null && c != null) {
                    StackPane current = (StackPane) node;
                    if(current.getStyle().contains("-fx-border-color: lime;")){
                        current.setStyle("");
                    }
                }
            }
        }
    private boolean isValidPiece(Circle shape) {
        Color color = (Color) shape.getFill();

        return (model.getCurrentPlayer() == State.BLUE && color == Color.BLUE) ||
                (model.getCurrentPlayer() == State.RED && color == Color.RED);
    }
    private StackPane getNodeByCoordinate(Integer row, Integer column) {
        for (Node node : table.getChildren()) {

            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);

            if (r != null && c != null) {

                if (Objects.equals(r, row) && Objects.equals(c, column)) {
                    return (StackPane) node;
                }
            }
        }
        return null;
    }
    private  void handleVictory(State winner) {
        Logger.info("winner: {}",winner);
        String wincol = winner == State.RED ? "RED" : "BLUE";
        saveResult(wincol,model.getStepCounter());
        showMessageBox(wincol);
    }
    private  void showMessageBox(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Victory");
        alert.setHeaderText("Congratulations you won!");
        alert.setContentText("The winner is: " + winner);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                Stage win = (Stage) table.getParent().getScene().getWindow();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Scene scene = new Scene(root);
                win.setScene(scene);
                win.show();
            }
        });

    }
    private void saveResult(String winner,int stepCounter) {
        JsonResult result = new JsonResult(winner,stepCounter);
        JsonManager saveHandler = new JsonManager(result);
        saveHandler.save();
    }
}





