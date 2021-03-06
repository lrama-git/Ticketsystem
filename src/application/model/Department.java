package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;

public class Department {
    public int id=0;
    public String name="";
//
    public Department(int id, String name){
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
    public static Department getByid(int id){
        Department obj = null;
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM departments WHERE department_id= "+id);

            if(result.next()){
                obj= new Department(result.getInt("department_id"), result.getString("name"));
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
            statement.executeUpdate("DELETE FROM priorities WHERE departments= "+id);

        }catch (SQLException throwables){

        }

    }
    public void update(){
        try {
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE departments SET name = ? WHERE department_id=?");
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }


    public static ObservableList<Department> load() {
        ObservableList<Department> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM departments");

            while (result.next()) {
                // Status s = new Status(result.getInt("status_id"), result.getString("name"));
                Department s = new Department(0, "");

                s.id = result.getInt("department");
                s.name = result.getString("name");
                list.add(s);
                list.add(s);
            }
        } catch (SQLException throwables) {

        }
        return list;
    }


    public static ObservableList<Department> load(String filename) {
        ObservableList<Department> result = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            try {
                while ((s = br.readLine()) != null) {
                    String[] split = s.split(";");
                    Department a = new Department(Integer.parseInt(split[0]),split[1]);
                    result.add(a);
                    // s enthält die gesamte Zeile
                 //   s = s.replace("\"", ""); // ersetze alle " in der Zeile


                   // String[] words = s.split(";");
                    //a.id= Integer.getInteger(words[0]);
                    //a.name = words[1];



                    return result;
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

