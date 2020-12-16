package application.controller;

import application.MyFXMLLoader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {
    public ListView<StatusController> statiList;
    public ListView ticketListView;
    public AnchorPane contentPane;
    public TextField searchTextfield;
    public ComboBox statiCombo;
    public ComboBox priorityCombo;

    private TicketController active = null;

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
        if(ticketListView.getSelectionModel().getSelectedItem()!= null) {
            MyFXMLLoader loader = new MyFXMLLoader();
            Parent root = loader.loadFXML("view/ticket.fxml");
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            contentPane.getChildren().add(root);
            
            active= (TicketController) loader.getController();
            active.setTicket(ticketListView.getSelectionModel().getSelectedItem());
            active.setTicket(ticketListView.getSelectionModel().getSelectedItem());


           
        }
    }
    public void filterChanged(ActionEvent actionEvent){

    }
    

//wichtig über git arbeite, gleich blaues pfeil drücken
}
