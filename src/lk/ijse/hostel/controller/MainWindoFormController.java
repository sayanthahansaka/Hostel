package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindoFormController {
    //1500 X 905
    public AnchorPane mainPane;
    public AnchorPane tablePane;

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.setTitle("LOGIN HERE");
    }

    public void btnRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(getClass().getResource("../view/StudentForm.fxml"));
        tablePane.getChildren().clear();
        tablePane.getChildren().add(node);

    }

    public void btnRoomOnAction(ActionEvent actionEvent) throws IOException {
        tablePane.getChildren().clear();
        Parent node = FXMLLoader.load(getClass().getResource("../view/RoomForm.fxml"));
        tablePane.getChildren().add(node);
    }

    public void btnKeyMoneyOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(getClass().getResource("../view/KeyMoneyForm.fxml"));
        tablePane.getChildren().clear();
        tablePane.getChildren().add(node);
    }

    public void btnLogInDetailOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(getClass().getResource("../view/LoginDetailForm.fxml"));
        tablePane.getChildren().clear();
        tablePane.getChildren().add(node);
    }

    public void btnReservationOnAction(ActionEvent actionEvent) throws IOException {
        Parent node = FXMLLoader.load(getClass().getResource("../view/ReservationForm.fxml"));
        tablePane.getChildren().clear();
        tablePane.getChildren().add(node);
    }
}
