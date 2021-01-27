package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ticket {
    public int id = 0;
    public String name = "";
    public String description = "";
    public Status status = null;
    public Priority priority = null;

    public Ticket(int id, String name, String description, int status, int priority) {
        this.id = id;
        this.name = name;
        this.description = description;

        this.status = Status.getByid(status);
        this.priority = Priority.getByid(priority);

    }

    @Override
    public String toString() {
        return id + " - " + name;
    }

    public static ObservableList<Ticket> loadList() {
        ObservableList<Ticket> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM tickets");

            while (result.next()) {

                Ticket s = new Ticket(
                        result.getInt("status_id"),
                        result.getInt("priority_id"),
                        result.getInt("order_id"),
                        result.getString("name"),
                        result.getInt("ticket_id")

                );
                list.add(s);

            }
        } catch (SQLException throwables) {

        }
        return list;
    }

    public static ObservableList<Ticket> loadFile(String filename) {
        ObservableList<Ticket> result = FXCollections.observableArrayList();

        String s = null;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            try {
                while ((s = br.readLine()) != null) {
                    while ((s = br.readLine()) != null) {
                        // s enthält die gesamte Zeile
                        s = s.replace("\"", ""); // ersetze alle " in der Zeile
                        Ticket a = new Ticket();

                        //  1;Fehlerbehebung Bezahlvorgang;Beim Abschließen einer Bestellung kommt es zu einer Nullpointer-Exception.;1;4

                        String[] words = s.split(";");
                        a.id = Integer.getInteger(words[0]);
                        a.name = words[1];
                        a.description = words[2];
                        a.status = new Status(Integer.getInteger(words[3]), "");
                        a.priority = new Priority(Integer.getInteger(words[4]), "");


                        return result;

                    }
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }
        return result;
    }

    public String newCSVLine() {
        //1;Fehlerbehebung Bezahlvorgang;Beim Abschließen einer Bestellung kommt es zu einer Nullpointer-Exception.;1;4
        return id + "\";\"" + name + "\";\"" + description + "\";\"" + status.id + "\";\"" + priority.id + "\";\"";
    }
}
