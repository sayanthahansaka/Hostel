package lk.ijse.hostel.service.custom;

import javafx.collections.ObservableList;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.service.SuperService;
import lk.ijse.hostel.view.tdm.KeyMoneyTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationService extends SuperService {
    ArrayList<ReservationDTO> getAllReserveDetails() throws SQLException, ClassNotFoundException;

    public ArrayList<ReservationDTO> searchReserveDetails(String enteredText) throws SQLException, ClassNotFoundException;

    ObservableList<KeyMoneyTM> getRemainKeyMnyStudent() throws SQLException, ClassNotFoundException, IOException;

}
