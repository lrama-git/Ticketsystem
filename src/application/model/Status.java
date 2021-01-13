package application.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static ObservableList<Priority> loadList() {
        ObservableList<Status> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM stati");

            while (result.next()) {
                Status s = new Status(result.getInt("status_id"), result.getString("name"));
                //Priority p = new Priority();
                //p.number = result.getString("priority_id");
                //p.name = result.getString("name");
                list.add(s);
            }
        } catch (SQLException throwables) {

        }
        return list;
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
    public static void fileWriter(ObservableList<Status> listo) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("stati.csv"));

            for (Status a : listo) {
                bw.write(a.newCSVLine());

            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
