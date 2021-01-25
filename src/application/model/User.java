package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    public int id;
    public int zip;
    public Department department;
    //public String number;
    public String name;
    public String title;
    public String street;
    //public String zip;
    public String city;
    //public String land;
    //public String abteilung;

    public User(int id, String title, String name, String street, int zip, String city, int department) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.city = city;

        this.department = Department.getByid(department);
    }

    @Override
    public String toString() {
        return id + " - " + title + name;
    }

    // public String newCSVLine() {
    //   return number + "\";\"" + title + "\";\"" + name + "\";\"" + street + "\";\"" +
    //         plz + "\";\"" + ort + "\";\"" + abteilung.number + "\";\"";
    //  }


    public static ObservableList<User> load() {
        ObservableList<User> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");

            while (result.next()) {
                // Status s = new Status(result.getInt("status_id"), result.getString("name"));
                User s = new User(
                        result.getInt("user_id"),
                        result.getString("name"),
                        result.getString("title"),
                        result.getString("street"),
                        result.getInt("zip"),
                        result.getString("city"),
                        result.getInt("department_id")
                        );
                list.add(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return list;
    }

    //3;Dipl-Ing.;Heinz Schweiger;AC/DC Straße 1;666;Rockcity;1
    public static ObservableList<User> load(String filename) {
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
                        a.name = words[1];
                        a.title = words[2];
                        a.street = words[3];
                        a.plz = words[4];
                        a.ort = words[5];
                        //   a.abteilung = new Department(Integer.getInteger(words[6]), "");

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

    public static void fileWriter(ObservableList<User> listo) {
        ObservableList<User> result = FXCollections.observableArrayList();
        result = listo;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv"));

            for (User a : result) {
                // bw.write(a.newCSVLine());

            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

