package application.controller;

import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TicketController {
    public TextField ticketNameTextfield;
    public TextField descriptionTextfield;
    public ComboBox<Status> statiCombo;
    public ComboBox<Priority> priorityCombo;
    private Ticket ticket = null;
    /**
     * darstellen der Daten des Tickets
     */
    public void setTicket(Ticket t){
        ticketNameTextfield.setText(t.name);
        descriptionTextfield.setText(t.description);
        statiCombo.setItems(Status.load("stati.csv"));
        priorityCombo.setItems(Priority.loadFile("priorities.csv"));

        for (Status s : statiCombo.getItems()){
            if(s.number == t.status.number){
                statiCombo.getSelectionModel().select(s);
                break;
            }
        }

        for (Priority p : priorityCombo.getItems()){
            if (p.number == t.priority.number){
                priorityCombo.getSelectionModel().select(p);
                break;
            }
        }

    }
    public Ticket getTicket(){
        /**
         * aktualisieren der Ticket-Daten
         */
        ticket.name = ticketNameTextfield.getText();
        //...
        return ticket;
    }
}
