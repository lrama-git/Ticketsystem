package application.controller;

import application.model.Priority;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class PriorityController {
    public ListView<Priority> priorityListView;
    public TextField priorityTextfield;
    ObservableList<Priority> list = FXCollections.observableArrayList();
    private int number = 0;

    private Priority selectedPriority = null;


    public void initialize() {
        priorityListView.setItems(Priority.loadFile("priorities.csv"));

    }

    public void listclicked(MouseEvent mouseEvent) {
        Priority selected = priorityListView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedPriority = selected;

            priorityTextfield.setText(selected.name);
        }
    }

    public void newClicked(ActionEvent actionEvent) {
        priorityTextfield.clear();

        // lösche die Variable, die den gewählten Artikel
        // beinhaltet
        this.selectedPriority = null;
    }

    public void saveClicked(ActionEvent actionEvent) {
        if (this.selectedPriority != null) {
            // Aktualisiere die Artikeldaten
            // (übernimm die aktuellen Daten von den Textfeldern)
            // und speichere alles in die Datei

            selectedPriority.name = priorityTextfield.getText();

            priorityListView.refresh();
        } else {
            Priority a = new Priority();

            a.name = priorityTextfield.getText();
            a.number = (number + 1);

            list.add(a);
            // erzeuge neuen Artikel, füge ihn in die ListView ein
            // und speichere alles in die Datei
        }

        Priority.fileWriter(list);
    }


    public void deleteClicked(ActionEvent actionEvent) {
        Priority selected = priorityListView.getSelectionModel().getSelectedItem();

        list.remove(selected);
        priorityListView.refresh();

        Priority.fileWriter(list);
    }

}
