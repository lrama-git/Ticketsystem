package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Priority {
   // public int id;
    public String name;
    public String number;

    //public Priority(int id, String name) {
      //  this.number = id;
    //    this.name = name;
  //  }

    //public Priority() {
      //  number = 0;
        //name = "";
    //}

    @Override
    public String toString() {
        return number + " - " + name;
    }

    public String newCSVLine() {
        return number + "\";\"" + name + "\";\"";
    }

    public static ObservableList<Priority> loadList() {
        ObservableList<Priority> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();//hole datenbank connection

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM priorities");//alle date von tabelle von priorities

            while (result.next()) {
               // Priority p = new Priority(result.getInt("priority_id"), result.getString("name"));
                //lade ich zwei felder (name, priority_id) und erzeuge ein objekt priority
                Priority p = new Priority();
                p.number = result.getString("priority_id");
                p.name = result.getString("name");

                list.add(p);
            }
        } catch (SQLException throwables) {

        }
        return list;
    }
}

   /**public static ObservableList<Priority> loadFile(String filename) {
        ObservableList<Priority> result = FXCollections.observableArrayList();

        String s = null;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Priority a = new Priority();

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

    public static void fileWriter(ObservableList<Priority> listo) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("priority.csv"));

            for (Priority a : listo) {
                bw.write(a.newCSVLine());

            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void test() {
    }


}
    */