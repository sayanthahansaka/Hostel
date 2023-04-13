package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.custom.StudentService;
import lk.ijse.hostel.service.custom.impl.StudentServiceImpl;
import lk.ijse.hostel.util.Regex;
import lk.ijse.hostel.util.TextFields;
import lk.ijse.hostel.view.tdm.StudentTM;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class StudentFormController {
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public AnchorPane paneStudent;
    public JFXComboBox<String> combGender;
    public JFXDatePicker txtDOB;
    public JFXButton btnDelete;
    public JFXButton btnUpdate;
    public JFXButton btnSearch;
    public JFXButton btnAdd;
    private StudentService studentService;

    public void initialize(){
        studentService = new StudentServiceImpl();
        combGender.getItems().addAll("Male","Female");
    }
    public void addOnAction(ActionEvent actionEvent) throws IOException {
        String id = txtID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact_no = txtContact.getText();
        LocalDate dob = txtDOB.getValue();
        String gender = combGender.getValue();
        Student student = new Student(id,name,address,contact_no,dob,gender);
        boolean isAdded = studentService.saveStudent(student);
        if (isAdded) {
            (new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!", new ButtonType[0])).show();
        } else {
            (new Alert(Alert.AlertType.WARNING, "Something Wrong!", new ButtonType[0])).show();
        }

        //        if (!id.matches("^(S00)[0-9]{1,5}$")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
//            txtStudentId.requestFocus();
//            return;
//
//        }else if (!name.matches("[A-Za-z ]+")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
//            txtStudentName.requestFocus();
//            return;
//        } else if (!address.matches("^[A-z0-9 ,/]{4,20}$")) {
//            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
//            txtAddress.requestFocus();
//            return;
//        }else if (!contact_no.matches("^07(7|6|8|1|2|5|0|4)-[0-9]{7}$")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid city").show();
//            txtConNo.requestFocus();
//            return;
//        }
        //==============================================
//        if (btnAdd.getText().equalsIgnoreCase("Save")) {
//
//            if (studentService.saveStudent(new StudentDTO(id, name, address, contact_no, dob, gender))) {
//
//                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
//            }else{
//                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
//
//            }
//        } else {
//            try {
//                if (studentService.updateStudent(new StudentDTO(id, name, address, contact_no, dob, gender))) {
//                    new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
//                }
//            } catch (Exception e) {
//                // System.out.println("Exception 2");
//                //System.out.println(e);
//                e.printStackTrace();
//                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
//            }
//        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String studentId = this.txtID.getText();

        StudentDTO studentDTO = studentService.searchStudent(studentId);
        if (studentDTO!=null){
            this.fillData(studentDTO);
        }
    }
    private void fillData(StudentDTO studentDTO){
        this.txtID.setText(studentDTO.getStudentID());
        this.txtName.setText(studentDTO.getStudentName());
        this.txtAddress.setText(studentDTO.getAddress());
        this.txtContact.setText(studentDTO.getContactNo());
    }

    public void updateOnAction(ActionEvent actionEvent) throws IOException {
        String iD = this.txtID.getText();
        String name = this.txtName.getText();
        String address = this.txtAddress.getText();
        String contact = this.txtContact.getText();
        LocalDate dob = txtDOB.getValue();
        String gender = combGender.getValue();
        StudentDTO studentDTO = new StudentDTO(iD, name, address, contact, dob, gender);

        boolean isUpdate = studentService.updateStudent(studentDTO);
        if (isUpdate){
            (new Alert(Alert.AlertType.CONFIRMATION, "Student Updated!", new ButtonType[0])).show();
        }else{
            (new Alert(Alert.AlertType.WARNING, "Something Wrong!", new ButtonType[0])).show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) throws IOException {
        boolean isDeleted=studentService.deleteStudent(txtID.getText());
        if(isDeleted){
            (new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted!", new ButtonType[0])).show();
        }else {
            (new Alert(Alert.AlertType.WARNING, "Something Wrong!", new ButtonType[0])).show();
        }
    }

    public void logoutOnAction(ActionEvent actionEvent) {
    }

    public void genderOnAction(ActionEvent actionEvent) {
    }
}
