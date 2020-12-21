package application.controller;

import application.MyFXMLLoader;
import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Controller {
    public ListView<StatusController> statiList;
    public ListView<Ticket> ticketListView;
    public AnchorPane contentPane;
    public TextField searchTextfield;
    public ComboBox<Status> filterstatiCombo;
    public ComboBox<Priority> filterpriorityCombo;
    private ArrayList<Ticket> allTickets;

    private TicketController active = null;

    public void initialize(){
        ticketListView.setItems(Ticket.loadFile("tickets.csv"));

        ObservableList<Status> statuslist  = Status.load("stati.csv");
        statuslist.add(0, new Status(-1,"Filter wählen"));
        filterstatiCombo.setItems(statuslist);
        filterstatiCombo.getSelectionModel().select(0);

        ObservableList<Priority> priorityList = Priority.loadFile("priorities.csv");
        priorityList.add(0,new Priority(-1,"Filter wählen"));
        filterpriorityCombo.getSelectionModel().select(0);

        allTickets = new ArrayList<>(ticketListView.getItems());

    }


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

        /**
         * //wenn ich links drücke möchte ich rechts ticket anzeigen
         *         MyFXMLLoader loader = new MyFXMLLoader();
         *         Parent root=loader.loadFXML("view/ticket.fxml");
         *
         *         contentPane.getChildren().add(root);
         *
         *         active = (TicketController) loader.getController();
         *         active.setTicket(ticketListView.getSelectionModel().getSelectedItem());
         */

        if(ticketListView.getSelectionModel().getSelectedItem() != null){
            MyFXMLLoader loader = new MyFXMLLoader();
            Parent root = loader.loadFXML("view/ticket.fxml");
            AnchorPane.setBottomAnchor(root,0.0);
            AnchorPane.setRightAnchor(root,0.0);
            AnchorPane.setTopAnchor(root,0.0);
            AnchorPane.setLeftAnchor(root,0.0);
            contentPane.getChildren().add(root);


            TicketController controller = (TicketController) loader.getController();
            controller.setTicket(ticketListView.getSelectionModel().getSelectedItem());

        }
    }

    public void filterChanged(){

    }

    public void filterTyped(KeyEvent keyEvent) {
        filterChanged(null);
    }

    public void newTicketClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticket.fxml");
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        contentPane.getChildren().add(root);

        active = (TicketController) loader.getController();
        active.setTicket(null);
    }

    public void deleteClicked(ActionEvent actionEvent) {
        /**
         * laden eines Tickets
         * entfernen aus Listview
         * Datei aktualisieren
         */
    }

    public void saveClicked(ActionEvent actionEvent) {
        /**
         * Wenn Ticket neu -> laden des Tickets und hinzufügen zur Liste!!
         * Datei  aktualisieren
         */
    }




//wichtig über git arbeite, gleich blauen pfeil drücken
}
