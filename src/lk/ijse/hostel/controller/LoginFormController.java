package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.ServiceTypes;
import lk.ijse.hostel.service.custom.LoginFormService;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginPane;
    public JFXTextField txtUserName;
    public Label lblErro;
    public Button btnLogIn;
    public JFXPasswordField txtPassword;

    private LoginFormService loginFormService;

    public void initialize(){
        loginFormService = ServiceFactory.getInstance().getService(ServiceTypes.LOGINFORM);
    }
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnLogIn){
            String userName = txtUserName.getText();
            String password = txtPassword.getText();

            if (userName.equalsIgnoreCase("sa") && (password.equalsIgnoreCase("as"))){
                Stage stage = (Stage) loginPane.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MainWindoForm.fxml"))));
                stage.setTitle("Main Window");
            } else if (txtUserName.getText().isEmpty() || (txtPassword.getText().isEmpty())) {
                lblErro.setText("Please Enter Your Login Details");
            }else {
                txtUserName.setUnFocusColor(Paint.valueOf("RED"));
                txtPassword.setUnFocusColor(Paint.valueOf("RED"));
                lblErro.setText("Wrong user name or password ");
            }
        }
    }
}
