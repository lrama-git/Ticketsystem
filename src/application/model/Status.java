package application.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Status {
    public int number;
    public String name;

    public Status(int id, String name) {
        this.number = id;
        this.name = name;

    }
    public Status(){
        number = 0;
        name = "";
    }


    @Override
    public String toString() {
        return number + " - " +  name;

    }

    public String newCSVLine (){
        return number + "\";\"" + name + "\";\"";
    }


    public static ObservableList<Status> load(String filename) {
        ObservableList<Status> result = FXCollections.observableArrayList();

        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Status a = new Status();

                    String[] words = s.split(";");
                    a.number = Integer.getInteger(words[0]);
                    a.name = words[1];


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
