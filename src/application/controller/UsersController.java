package application.controller;

import application.model.Department;
import application.model.Priority;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class UsersController {
    public ListView<User> userListView;
    public TextField title;
    public TextField nameUser;
    public TextField street;
    public TextField plz;
    public TextField ort;
    public TextField country;
    public ComboBox<Department> abteilung;
    ObservableList<User> list = FXCollections.observableArrayList();
    private int number = 0;

    private User selectedUser = null;

    public void initialize() {
        userListView.setItems(User.load("users.csv"));
    }

    public void deleteclicked(ActionEvent actionEvent) {
        User selected = userListView.getSelectionModel().getSelectedItem();

        list.remove(selected);
        userListView.refresh();

        User.fileWriter(list);
    }

    public void saveclicked(ActionEvent actionEvent) {
        if (this.selectedUser != null) {
            // Aktualisiere die Artikeldaten
            // (übernimm die aktuellen Daten von den Textfeldern)
            // und speichere alles in die Datei

            selectedUser.name = nameUser.getText();
            selectedUser.title = title.getText();
            selectedUser.ort = ort.getText();
            selectedUser.plz = plz.getText();
            selectedUser.street = street.getText();
            selectedUser.abteilung = abteilung.getSelectionModel().getSelectedItem();
            // selectedUser.abteilungsnummer = abteilung.getText();??????????????????????????????????????????????????


            userListView.refresh();
        } else {
            User a = new User();

            selectedUser.name = nameUser.getText();
            selectedUser.title = title.getText();
            selectedUser.ort = ort.getText();
            selectedUser.plz = plz.getText();
            selectedUser.street = street.getText();
            selectedUser.abteilung = abteilung.getSelectionModel().getSelectedItem();
            // selectedUser.abteilungsnummer = abteilung.getText();??????????????????????????????????????????????????
            a.number = Integer.toString(number + 1);

            list.add(a);
            // erzeuge neuen Artikel, füge ihn in die ListView ein
            // und speichere alles in die Datei
        }
        //userListView.setItems(User.loadFile("users.csv")); ??
        //fileWriter();
    }

    /**
     * ---------------------------------------------------------------ist in user-------------------------------------
     * private void fileWriter() {
     try {
     BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv"));

     for (User a : list) {
     bw.write(a.newCSVLine());

     }
     bw.flush();
     bw.close();

     } catch (IOException e) {
     e.printStackTrace();
     }
     }
     */

    public void userClicked(MouseEvent mouseEvent) {
        User selected = userListView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedUser = selected;

            nameUser.setText(selected.name);
            title.setText(selected.title);
            street.setText(selected.street);
            plz.setText(selected.plz);
            ort.setText(selected.ort);
            // country.setText(selected.?);
            //   ??????????????????????????? abteilung.setSelectionModel(selected.abteilungsnummer);
            /**
             *  public TextField title;
             *     public TextField nameUser;
             *     public TextField street;
             *     public TextField plz;
             *     public TextField ort;
             *     public TextField country;
             *     public ComboBox<Department> abteilung;
             */
        }
    }

    public void abbrechenclicked(ActionEvent actionEvent) {
    }

    public void newclicked(ActionEvent actionEvent) {
        title.clear();
        nameUser.clear();
        street.clear();
        plz.clear();
        ort.clear();
        country.clear();
        abteilung.setItems(null);//glaub ich zumindest, kann auch was anderes sein?

        // lösche die Variable, die den gewählten User
        // beinhaltet
        this.selectedUser = null;
    }

}
