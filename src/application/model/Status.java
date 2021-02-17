package application.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;

public class Status {
    public int id = 0;
    public String name = "";


    public Status(int id, String name) {
        this.id = id;
        this.name = name;

    }

    @Override
    public String toString() {
        return id + " - " + name;
    }

    public String newCSVLine() {
        return id + "\";\"" + name + "\";\"";
    }

    public static Status getById(int id) {
        Status obj = null;
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM status WHERE id= " + id);

            if (result.next()) {
                obj = new Status(result.getInt("status_id"), result.getString("name"));
                //   obj.name= result.getString("name");
                // obj.id= result.getInt("department_id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;

    }

    public void update() {
        try {
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE priorities SET name = ? WHERE stati_id=?");
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
//
        }
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
                Status s = new Status(0, "");
                s.id = result.getInt("status_id");
                s.name = result.getString("name");
                list.add(s);
            }
        } catch (SQLException throwables) {

        }
        return list;
    }
}
