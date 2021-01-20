package application.controller;

import application.model.Department;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;

public class DepartmentController {

    public TextField departmentTextfield;
    public ListView<Department> departmentListView;
    ObservableList<Department> list = FXCollections.observableArrayList();
    private int number = 0;

    private Department selectedDepartment = null;

    public void initialize() {
        departmentListView.setItems(Department.load());
    }

    public void deleClicked(ActionEvent actionEvent) {
        nameTextField.clear();
        elementListView.getItems().remove(selectedItem);

        selectedItem.delete();

    }

    public void saveClicked(ActionEvent actionEvent) {
        if(selectedItem != null) {
            selectedItem.name = nameTextField.getText();

            elementListView.refresh();

            selectedItem.update();

        }
    }

    public void newClicked(ActionEvent actionEvent) {
        departmentTextfield.clear();

        this.selectedDepartment = null;
    }

    public void listClicked(MouseEvent mouseEvent) {
        Department selected = departmentListView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedDepartment = selected;

            departmentTextfield.setText(selected.name);
        }
    }

    public void cancelClicked(ActionEvent actionEvent){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
    }
}
