package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.sql.*;

public class Priority {
   public int id=0;
    public String name="";


    public Priority(int id, String name) {
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
    public static Priority getById(int id){
        Priority obj = null;
        try {
            Connection connection = AccessDb.getConnection();

            //
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM status WHERE id= "+id);

            if(result.next()){
                obj= new Priority(result.getInt("priority_id"), result.getString("name"));
                //   obj.name= result.getString("name");
                // obj.id= result.getInt("department_id");
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return obj;

    }


    public void delete(){
        try{
            Connection connection = AccessDb.getConnection();

            Statement statement= null;
            statement= connection.createStatement();
            statement.executeUpdate("DELETE FROM priorities WHERE priority_id= "+id);

        }catch (SQLException throwables){

        }

    }
    public void update(){
        try {
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement= null;
            statement = connection.prepareStatement("UPDATE priorities SET name = ? WHERE priority_id=?");
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();

        }catch (SQLException throwables){
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
                Priority p = new Priority(0,"");
                p.id = result.getInt("priority_id");
                p.name = result.getString("name");

                list.add(p);
            }
        } catch (SQLException throwables) {

        }
        return list;
    }
}




