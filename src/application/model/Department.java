package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Department {
    public int number;
    public String name;

    public Department(int id, String name){
        number = id;
        this.name = name;
    }
    public Department(){
        number = 0;
        name ="";
    }

    @Override
    public String toString() {
        return number + " - " + name;
    }

    public String newCSVLine() {
        return number + "\";\"" + name + "\";\"";
    }


    public static ObservableList<Department> load(String filename) {
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
                    a.number = Integer.getInteger(words[0]);
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

    public static void fileWriter(ObservableList<Department> listo) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv"));

            for (Department a : listo) {
                bw.write(a.newCSVLine());

            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

