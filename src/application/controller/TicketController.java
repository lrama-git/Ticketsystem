package application.controller;

        import application.model.Priority;
        import application.model.Status;
        import application.model.Ticket;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.ListView;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.AnchorPane;

public class TicketController {
    private Ticket ticket;
    public TextField nameTextfield;
    public TextArea descriptionTextfield;
    public ComboBox<Status> statusComboBox;
    public ComboBox<Priority> priotityComboBox;
    public ComboBox orderComboBox;

    public void setTicket(Ticket t) {

        this.ticket= t;

        nameTextfield.setText(t.name);
        descriptionTextfield.setText(t.description);
        statusComboBox.setItems(Status.load("stati.csv"));
        priotityComboBox.setItems(Priority.loadFile("priorities.csv"));

        for (Status s : statusComboBox.getItems()) {
            if (s.id == t.status.id) {
                statusComboBox.getSelectionModel().select(s);
                break;
            }
        }

        for (Priority p : priotityComboBox.getItems()) {
            if (p.id == t.priority.id) {
                priotityComboBox.getSelectionModel().select(p);
                break;
            }

        }

        }
        public Ticket getTicket(){
        ticket.name = nameTextfield.getText();

        return ticket;
        }
    }
