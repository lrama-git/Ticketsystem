package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.io.*;

public class User {
    public String number;
    public String name;
    public String title;
    public String street;
    public String plz;
    public String ort;
    public Department abteilung;

    @Override
    public String toString() { return number + " - " + title + name;}

    public String newCSVLine (){
        return number + "\";\"" + title + "\";\""+ name + "\";\"" + street + "\";\"" +
                plz + "\";\"" + ort + "\";\"" + abteilung.number + "\";\"";
    }
//3;Dipl-Ing.;Heinz Schweiger;AC/DC Straße 1;666;Rockcity;1
public static ObservableList<User> loadFile(String filename) {
    ObservableList<User> result = FXCollections.observableArrayList();

    String s = null;
    BufferedReader br = null;

    try {
        br = new BufferedReader(new FileReader(filename));
        try {
            while ((s = br.readLine()) != null) {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    User a = new User();

                    String[] words = s.split(";");
                    a.number = words[0];
                    a.title = words[1];
                    a.name = words[2];
                    a.street = words[3];
                    a.plz = words[4];
                    a.ort = words[5];
                    a.abteilung.number = words[7];

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
    public static ObservableList<User> loadFile(String filename) {
        ObservableList<User> result = FXCollections.observableArrayList();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv"));

            for (User a : result) {
                bw.write(a.newCSVLine());

            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

