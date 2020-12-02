package application.controller;

import application.model.Department;
import application.model.Status;
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
        load(new File("department.csv"));
    }

    private void load(File file) {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            try {
                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Department a = new Department();

                    String[] words = s.split(";");
                    a.number = words[0];
                    a.name = words[1];
                    number = Integer.parseInt(words[0]) ;

                    list.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        departmentListView.setItems(list);
    }

    public void deleClicked(ActionEvent actionEvent) {
        Department selected = departmentListView.getSelectionModel().getSelectedItem();

        list.remove(selected);
        departmentListView.refresh();

        fileWriter();

    }

    public void saveClicked(ActionEvent actionEvent) {
        if (this.selectedDepartment != null) {


            selectedDepartment.name = departmentTextfield.getText();

            departmentListView.refresh();
        } else {
            Department a = new Department();

            a.name = departmentTextfield.getText();
            a.number = Integer.toString(number + 1);

            list.add(a);

        }

        fileWriter();
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

    private void fileWriter() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("department.csv"));

            for (Department a : list) {
                bw.write(a.newCSVLine());

            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
