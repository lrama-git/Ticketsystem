package application.controller;


import application.model.Department;
import application.model.Status;
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

public class StatusController {

    public ListView<Status> statiListView;
    public TextField statiTextField;
    public Button cancelButton;
  //  ObservableList<Status> list = FXCollections.observableArrayList();
    private int number = 0;

    private Status selectedItem = null;

    public void initialize() {
        statiListView.setItems(Priority.loadList());
        number= statiListView.getItems().size();
    }

    public void listclicked(MouseEvent mouseEvent) {
        statiTextField.setText(statiListView.getSelectionModel().getSelectedItem().name());
        selectedItem= statiListView.getSelectionModel().getSelectedItem();
    }

    public void newClicked(ActionEvent actionEvent) {
        statiTextField.clear();

        // lösche die Variable, die den gewählten Artikel
        // beinhaltet
        this.selectedItem = null;
    }

    public void saveClicked(ActionEvent actionEvent) {
        if(selectedItem != null) {
            selectedItem.name = statiTextField.getText();

            statiListView.refresh();

            selectedItem.update();

        }
    }

    public void deleteClicked(ActionEvent actionEvent) {
        statiTextField.clear();
        statiListView.getItems().remove(selectedItem);

        selectedItem.delete();
    }

    public void cancelClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
