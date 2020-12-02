package application.controller;

import application.MyFXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

public class Controller {
    public ListView<StatusController> statiList;

    public void editStaticlicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/stati.fxml", "Stati bearbeiten");
    }

    public void editPrioritiesclicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/priorities.fxml" , "Prioritäten bearbeiten");
    }

    public void editUserclicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/users.fxml", "Users bearbeiten");
    }

//wichtig über git arbeite, gleich blaues pfeil drücken
}
