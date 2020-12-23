package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ticket {
    public int nummer;
    public String name;
    public String description;
    public Status status;
    public Priority priority;

    public Ticket(int id, String name,String description, int status, int priority){
        this.nummer = id;
        this.name = name;
        this.status = new Status(status, "");
        this.priority = new Priority(priority, "");
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
                        a.nummer = Integer.getInteger(words[0]);
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
        return nummer + "\";\"" + name + "\";\"" + description +  "\";\"" + status.number +  "\";\"" + priority.number +  "\";\"";
    }
}
