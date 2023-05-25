package tablegame.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tablegame.json.JsonResult;
import tablegame.json.JsonManager;

import java.io.IOException;

public class MatchHistoryController {
    @FXML
    private TableView<JsonResult> historytable;
    @FXML
    private TableColumn<JsonResult,String> winnercol;
    @FXML
    private TableColumn<JsonResult,Integer> stepcol;
    @FXML
    private Button backtomenu;
    private JsonManager jsonManager = new JsonManager(new JsonResult());
    @FXML
    private void initialize() throws IOException {
        winnercol.setCellValueFactory(new PropertyValueFactory<>("winner"));
        stepcol.setCellValueFactory(new PropertyValueFactory<>("stepCounter"));
        ObservableList<JsonResult> observableList = FXCollections.observableArrayList();
        observableList.addAll(jsonManager.load());
        historytable.setItems(observableList);
    }
    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/menu.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
