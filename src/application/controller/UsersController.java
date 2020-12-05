package application.controller;

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
    public ComboBox abteilung;
    ObservableList<User> list = FXCollections.observableArrayList();
    private int number = 0;

    private User selectedUser = null;

    public void initialize() {
        load(new File("users.csv"));
    }

    public void load(File file) {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            try {
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
                   //a.country = words[6];
                    //a.abteilungsnummer = words[7];
                    number = Integer.parseInt(words[0]);

                    list.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        userListView.setItems(list);
    }


    public void deleteclicked(ActionEvent actionEvent) {
        User selected = userListView.getSelectionModel().getSelectedItem();

        list.remove(selected);
        userListView.refresh();

        fileWriter();
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
            // selectedUser.abteilungsnummer = abteilung.getText();??????????????????????????????????????????????????


            userListView.refresh();
        } else {
            User a = new User();

            selectedUser.name = nameUser.getText();
            selectedUser.title = title.getText();
            selectedUser.ort = ort.getText();
            selectedUser.plz = plz.getText();
            selectedUser.street = street.getText();
            // selectedUser.abteilungsnummer = abteilung.getText();??????????????????????????????????????????????????
            a.number = Integer.toString(number + 1);

            list.add(a);
            // erzeuge neuen Artikel, füge ihn in die ListView ein
            // und speichere alles in die Datei
        }

        fileWriter();
    }

    private void fileWriter() {
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
             *     public ComboBox abteilung;
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
        //??????????????abteilung.se

        // lösche die Variable, die den gewählten Artikel
        // beinhaltet
        this.selectedUser = null;
    }
}
