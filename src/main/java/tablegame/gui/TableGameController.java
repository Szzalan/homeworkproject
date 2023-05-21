package tablegame.gui;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import tablegame.model.Position;
import tablegame.model.State;
import tablegame.model.TableGameModel;
import org.tinylog.Logger;

public class TableGameController {
    @FXML
    private GridPane table;

    private TableGameModel model = new TableGameModel();

    @FXML
    public void initialize() {
        System.out.println("asd");
        for (var i = 0; i < table.getRowCount(); i++) {
            for (var j = 0; j < table.getColumnCount(); j++) {
                Node state = createState(i, j);
                table.add(state, j, i);
            }
        }


    }
    private Node createState(int i, int j) {
        if( model.getTable()[i][j] == State.RED)
        {
            return new Circle(50,Color.RED);
        }
        else if( model.getTable()[i][j] == State.BLUE)
        {
            return new Circle(50,Color.BLUE);
        }
        else if( model.getTable()[i][j] == State.BLACK)
        {
            return new Rectangle(100,100,Color.BLACK);
        }
        else
            return new Circle(50,Color.TRANSPARENT);
    }

}


