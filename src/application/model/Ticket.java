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
    public int nummer;
    public String name;
    public String description;
    public Status status;
    public Priority priority;

    public Ticket(int id, String name,String description, int status, int priority){
        this.nummer = id;
        this.name = name;
        this.status = Status.getById(status);
        this.priority = Priority.getById(priority);
    }
    public Ticket(){
        this.nummer = 0;
        name = "";
        description = "";
        status = null;
        priority = null;
    }

    //  1;Fehlerbehebung Bezahlvorgang;Beim Abschließen einer Bestellung kommt es zu einer Nullpointer-Exception.;1;4

    @Override
    public String toString() {
        return nummer + " - " + name ;
    }
    public static ObservableList<Ticket> loadList() {
        ObservableList<Ticket> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM tickets");

            while (result.next()) {
                // Status s = new Status(result.getInt("status_id"), result.getString("name"));
                Ticket t = new Ticket();
                t.nummer = Integer.getInteger(String.valueOf(result));
                t.name = result.getString("name");
                t.priority = new Priority(result.getInt("priority_id"), "");
                t.status = new Status(result.getInt("status_id"), "");
                t.description = Integer.toString(String.valueOf(result));
                list.add(t);
            }
        } catch (SQLException throwables) {

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
                        // s enthält die gesamte Zeile
                        s = s.replace("\"", ""); // ersetze alle " in der Zeile
                        Ticket a = new Ticket();

                        //  1;Fehlerbehebung Bezahlvorgang;Beim Abschließen einer Bestellung kommt es zu einer Nullpointer-Exception.;1;4

                        String[] words = s.split(";");
                        a.nummer = Integer.getInteger(words[0]);
                        a.name = words[1];
                        a.description = words[2];
                        a.status = new Status(Integer.getInteger(words[3]), "");
                        a.priority = new Priority(Integer.getInteger(words[4]), "");


                        return result;

                    }

            } finally {
                br.close();
            }
        } catch (IOException io) {
        }
        return result;
    }

    public String newCSVLine() {

        return nummer + "\";\"" + name + "\";\"" + description +  "\";\"" + status.id +  "\";\"" + priority.id +  "\";\"";
    }
}
