package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.dto.UserLoginDTO;
import lk.ijse.hostel.service.custom.UserService;
import lk.ijse.hostel.service.custom.impl.UserServiceImpl;
import lk.ijse.hostel.view.tdm.UserTM;

import java.io.IOException;
import java.util.List;

public class LoginDetailFormController {
    public JFXTextField txtUID;
    public JFXTextField txtUserName;
    public JFXTextField txtUPassword;
    public AnchorPane paneLoginDetail;
    public JFXButton btnDelete;
    public TableView<UserTM> tblLogInDetail;
    public TableColumn colUserID;
    public TableColumn colUserName;
    public TableColumn colPassword;
    public JFXButton btnSave;

    UserService userService = new UserServiceImpl();

    public void initializer() {
        tblLogInDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userID"));
        tblLogInDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblLogInDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("password"));

        initUI();

        tblLogInDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnDelete.setDisable(false);

            if(newValue != null){
                txtUID.setText(newValue.getUserID());
                txtUserName.setText(newValue.getUserName());
                txtUPassword.setText(newValue.getPassword());


                txtUID.setDisable(false);
                txtUserName.setDisable(false);
                txtUPassword.setDisable(false);


                btnSave.setDisable(false);
            }
        });

        loadAllUsers();
    }
    private void loadAllUsers(){
        tblLogInDetail.getItems().clear();
        try {
            List<UserLoginDTO> userLoginDTOS = userService.getAllUser();
            for(UserLoginDTO u : userLoginDTOS) {
                tblLogInDetail.getItems().add(new UserTM(
                        u.getUserID(),
                        u.getUserName(),
                        u.getPassword()
                ));
            }
        } catch (Exception e) {
            // System.out.println(e);
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            e.printStackTrace();
        }
    }
    private void clearFields(){
        txtUID.clear();
        txtUserName.clear();
        txtUPassword.clear();

    }

    private void initUI() {
        txtUID.clear();
        txtUserName.clear();
        txtUPassword.clear();
        txtUID.setDisable(true);
        txtUserName.setDisable(true);
        txtUPassword.setDisable(true);
        txtUID.setEditable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }
    public void SaveNewLogDetailOnAction(ActionEvent actionEvent) throws IOException {
        String id = txtUID.getText();
        String name = txtUserName.getText();
        String password = txtUPassword.getText();

        if (!id.matches("^(U00)[0-9]{1,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
            txtUID.requestFocus();
            return;

        }else if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtUserName.requestFocus();
            return;
        } else if (!password.matches("^[A-z]{3,8}[0-9]{4}$")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtUPassword.requestFocus();
            return;
        }
        if (btnSave.getText().equalsIgnoreCase("Save")) {


            if (userService.saveUser(new UserLoginDTO(id, name, password)))
            {

                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                tblLogInDetail.getItems().add(new UserTM(id, name, password));
                loadAllUsers();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();

            }


        } else {
            try {
                if (userService.updateUser(new UserLoginDTO(id, name, password))){
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                    loadAllUsers();
                    clearFields();
                }
            } catch (Exception e) {
                // System.out.println("Exception 2");
                //System.out.println(e);
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            }
        }
    }

    public void DeleteOnAction(ActionEvent actionEvent) {

        String id=tblLogInDetail.getSelectionModel().getSelectedItem().getUserID();
        try {
            userService.deleteUser(id);
            tblLogInDetail.getItems().remove(tblLogInDetail.getSelectionModel().getSelectedItem());
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted..!").show();

            tblLogInDetail.getSelectionModel().clearSelection();
            clearFields();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something Happened.Try again Carefully..!").show();
        }
    }
}
