package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.ServiceTypes;
import lk.ijse.hostel.service.custom.ReservationService;
import lk.ijse.hostel.view.tdm.KeyMoneyTM;

import java.io.IOException;
import java.sql.SQLException;

public class KeyMoneyFormController {
    public JFXTextField txtSName;
    public JFXTextField txtSID;
    public JFXTextField txtStatus;
    public AnchorPane paneKeyMoney;
    public JFXButton btnOkay;

    private ReservationService reservationService;
            //= ServiceFactory.getInstance().getService(ServiceTypes.RESERVATION);

    public void okayOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
       ObservableList<KeyMoneyTM> KeyMnyStudent = reservationService.getRemainKeyMnyStudent();
    }
}
