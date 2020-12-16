package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ticket {
    public int id;
    public String name;
    public String description;
    public Priority priority;
    public Status status;

    public Ticket(int id, String name, String description, int status, int priority){
        this.id = id;
        this.name= name;
        this.description= description;
        this.status= new Status(status, "");
        this.priority= new Priority(priority, "");
    }

    @Override
    public String toString() {
        return name;
    }
    public static ObservableList<Ticket> load(String filename) {
        ObservableList<Ticket> result = FXCollections.observableArrayList();

        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile


                    String[] words = s.split(";");
                    Ticket a = new Ticket(Integer.parseInt(words[0]), words[1], words[2], Integer.parseInt(words[3]), Integer.parseInt(words[4]));



                    result.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }

        return result;
    }

}
