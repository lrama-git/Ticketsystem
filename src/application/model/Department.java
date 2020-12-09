package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Department {
    public String number;
    public String name;

    @Override
    public String toString() {
        return number + " - " + name;
    }

    public String newCSVLine() {
        return number + "\";\"" + name + "\";\"";
    }
    //

    public static ObservableList<Department> load(String filename){
        ObservableList<Department> result = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Department a = new Department();

                    String[] words = s.split(";");
                    a.number = words[0];
                    a.name = words[1];



                    result.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        return result;
    }
}

