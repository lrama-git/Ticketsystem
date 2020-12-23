package application.controller;

import application.MyFXMLLoader;
import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    public ListView<StatusController> statiList;
    public ListView<Ticket> ticketListView;
    public AnchorPane contentPane;
    public TextField searchTextfield;
    public ComboBox<Status> statiCombo;
    public ComboBox<Priority> priorityCombo;
    private ArrayList<Ticket> allTickets;
    private ObservableList<Ticket> list;


    private TicketController active = null;
    private Ticket selectedTicket = null;

    public void initialize() {
        ticketListView.setItems(Ticket.loadFile("tickets.csv"));

        ObservableList<Status> statuslist = Status.load("stati.csv");
        statuslist.add(0, new Status(-1, "Filter wählen"));
        statiCombo.setItems(statuslist);
        statiCombo.getSelectionModel().select(0);

        ObservableList<Priority> priorityList = Priority.loadFile("priorities.csv");
        priorityList.add(0, new Priority(-1, "Filter wählen"));
        priorityCombo.getSelectionModel().select(0);

        allTickets = new ArrayList<>(ticketListView.getItems());

    }


    public void editStaticlicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/stati.fxml", "Stati bearbeiten");
    }

    public void editPrioritiesclicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/priorities.fxml", "Prioritäten bearbeiten");
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

        Ticket selected = ticketListView.getSelectionModel().getSelectedItem();
        this.selectedTicket = selected;
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

        if (ticketListView.getSelectionModel().getSelectedItem() != null) {
            MyFXMLLoader loader = new MyFXMLLoader();
            Parent root = loader.loadFXML("view/ticket.fxml");
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            contentPane.getChildren().add(root);


            TicketController controller = (TicketController) loader.getController();
            controller.setTicket(ticketListView.getSelectionModel().getSelectedItem());


        }
    }

    public void filterChanged() {

    }

    public void filterTyped(KeyEvent keyEvent) {
        filterChanged();
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
        Ticket selected = ticketListView.getSelectionModel().getSelectedItem();

        list.remove(selected);
        ticketListView.refresh();

        fileWriter();


    }

    private void fileWriter() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("tickets.csv"));

            for (Ticket a : list) {
                bw.write(a.newCSVLine());

            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveClicked(ActionEvent actionEvent) {
        /**
         * Wenn Ticket neu -> laden des Tickets und hinzufügen zur Liste!!
         * Datei  aktualisieren


         if (this.selectedTicket != null) {
         // Aktualisiere die Artikeldaten
         // (übernimm die aktuellen Daten von den Textfeldern)
         // und speichere alles in die Datei

         selectedTicket.artikelNummer = articleNumberTextField.getText();
         selectedTicket.name = articleNameTextField.getText();
         selectedTicket.lagerplatz = articleDepotTextField.getText();
         selectedTicket.preis = Double.parseDouble(articlePriceTextField.getText());

         articleList.refresh();
         } else {
         Artikel a = new Artikel();

         a.artikelNummer = articleNumberTextField.getText();
         a.name = articleNameTextField.getText();
         a.lagerplatz = articleDepotTextField.getText();
         a.preis = Double.parseDouble(articlePriceTextField.getText());

         list.add(a);
         // erzeuge neuen Artikel, füge ihn in die ListView ein
         // und speichere alles in die Datei
         }

         fileWriter();
         */

    }


//wichtig über git arbeite, gleich blauen pfeil drücken
}
