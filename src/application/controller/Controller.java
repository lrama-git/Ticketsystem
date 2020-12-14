package application.controller;

import application.MyFXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {
    public ListView<StatusController> statiList;
    public ListView ticketListView;
    public AnchorPane contentPane;


    public void editStaticlicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/stati.fxml", "Stati bearbeiten");
    }

    public void editPrioritiesclicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/priorities.fxml" , "Prioritäten bearbeiten");
    }

    public void editDepartmentClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/department.fxml", "Department bearbeiten");
    }

    public void editUsersClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/users.fxml", "User bearbeiten");
    }

    public void ticketListViewClicked(MouseEvent mouseEvent) {
        //wenn ich links drücke möchte ich rechts ticket anzeigen
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root=loader.loadFXML("view/ticket.fxml");

        contentPane.getChildren().add(root);

        TicketController controller = (TicketController) loader.getController();

    }

//wichtig über git arbeite, gleich blaues pfeil drücken
}
