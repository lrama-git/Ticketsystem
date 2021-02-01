package application.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.*;

public class PriorityController {
    public ListView<Priority> priorityListView;
    public TextField priorityTextfield;
    public Button cancelButton;
   // ObservableList<Priority> list = FXCollections.observableArrayList();
    private int number = 0;


    private Priority selectedItem = null;

    public void initialize() {
        priorityListView.setItems(Priority.loadList());
        number= priorityListView.getItems().size();
    }

    public void listclicked(MouseEvent mouseEvent) {
        priorityTextfield.setText(priorityListView.getSelectionModel().getSelectedItem().name());
        selectedItem= priorityListView.getSelectionModel().getSelectedItem();

    }

    public void newClicked(ActionEvent actionEvent) {
        priorityTextfield.clear();

        // lösche die Variable, die den gewählten Artikel
        // beinhaltet
        this.selectedItem = null;
    }

    public void saveClicked(ActionEvent actionEvent) {
        if(selectedItem != null) {
            selectedItem.name = priorityTextfield.getText();

            priorityListView.refresh();

            selectedItem.update();

        }
    }

    public void deleteClicked(ActionEvent actionEvent) {

        priorityTextfield.clear();
        priorityListView.getItems().remove(selectedItem);

        selectedItem.delete();

    }

    public void cancelClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
