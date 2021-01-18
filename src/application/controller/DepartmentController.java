package application.controller;

import application.model.Department;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
        Department selected = departmentListView.getSelectionModel().getSelectedItem();

        list.remove(selected);
        departmentListView.refresh();

        Department.fileWriter(list);
    }

    public void saveClicked(ActionEvent actionEvent) {
        if (this.selectedDepartment != null) {
            selectedDepartment.name = departmentTextfield.getText();

            departmentListView.refresh();
        } else {
            Department a = new Department();

            a.name = departmentTextfield.getText();
            //a.number = number + 1;

            list.add(a);
        }
        Department.fileWriter(list);
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
}
