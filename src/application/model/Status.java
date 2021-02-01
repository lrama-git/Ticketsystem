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
    public int id = 0;
    public String name = "";


    public Status(int id, String name) {
     this.id = id;
      this.name = name;



    }
    //public Status(){
    // number = 0;
    //name = "";
    //}


    @Override
    public String toString() {
        return id + " - " + name;
    }

    public String newCSVLine() {
        return id + "\";\"" + name + "\";\"";
    }

    public static Status getByid(int id){
        Status obj = null;
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM status WHERE id= "+id);

            if(result.next()){
                obj= new Status(result.getInt("status_id"), result.getString("name"));
                //   obj.name= result.getString("name");
                // obj.id= result.getInt("department_id");
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return obj;

    }

    public static ObservableList<Status> loadList() {
        ObservableList<Status> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM stati");

            while (result.next()) {
                // Status s = new Status(result.getInt("status_id"), result.getString("name"));
                Status s = new Status(0,"");
                s.id = result.getInt("status_id");
                s.name = result.getString("name");
                list.add(s);
            }
        } catch (SQLException throwables) {

        }
        return list;
    }
}
/**
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
 */
