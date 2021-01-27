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
    public int id = 0;
    public String name = "";


    public Priority(int id, String name) {
        this.id = id;
        this.name = name;
    }


    // public Priority(int id, String name) {
    //   this.number = Integer.toString(id);
    // this.name = name;

    //  }
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
        return id + " - " + name;
    }

    public String newCSVLine() {
        return id + "\";\"" + name + "\";\"";
    }

    public static Priority getByid(int id) {
        Priority obj = null;
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM priority WHERE id= " + id);

            if (result.next()) {
                obj = new Priority(result.getInt("priority_id"), result.getString("name"));
                //   obj.name= result.getString("name");
                // obj.id= result.getInt("department_id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;

    }


    public void delete() {
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM priorities WHERE priority_id= " + id);

        } catch (SQLException throwables) {

        }
    }

    public void update() {
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.prepareStatement("UPDATE priorities SET name = ? WHERE priority_id=?");
            statement.setString(1, name);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
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
                p.id = result.getInt("priority_id");
                p.name = result.getString("name");

                list.add(p);

            }
        } catch (SQLException throwables) {

        }
        return list;
    }
}

/**
 * public static ObservableList<Priority> loadFile(String filename) {
 * ObservableList<Priority> result = FXCollections.observableArrayList();
 * <p>
 * String s = null;
 * BufferedReader br = null;
 * <p>
 * try {
 * br = new BufferedReader(new FileReader(filename));
 * try {
 * while ((s = br.readLine()) != null) {
 * // s enthält die gesamte Zeile
 * s = s.replace("\"", ""); // ersetze alle " in der Zeile
 * Priority a = new Priority();
 * <p>
 * String[] words = s.split(";");
 * a.number = Integer.getInteger(words[0]);
 * a.name = words[1];
 * <p>
 * result.add(a); // füge Artikel zur Liste hinzu
 * }
 * } finally {
 * br.close();
 * }
 * } catch (IOException io) {
 * }
 * return result;
 * }
 * <p>
 * public static void fileWriter(ObservableList<Priority> listo) {
 * <p>
 * try {
 * BufferedWriter bw = new BufferedWriter(new FileWriter("priority.csv"));
 * <p>
 * for (Priority a : listo) {
 * bw.write(a.newCSVLine());
 * <p>
 * }
 * bw.flush();
 * bw.close();
 * <p>
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * <p>
 * }
 * <p>
 * public void test() {
 * }
 * <p>
 * <p>
 * }
 */