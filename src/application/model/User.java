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
    public String name;
    public String title;
    public String street;
    public String city;


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
                        String[] split = s.split(";");
                        User a = new User(Integer.parseInt(split[0]),split[1],split[2],split[3], Integer.parseInt(split[4]),split[5], Integer.parseInt(split[6]));
                        result.add(a);


                        return result;
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

