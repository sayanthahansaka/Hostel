package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.ServiceTypes;
import lk.ijse.hostel.service.custom.PurchaseReserveService;
import lk.ijse.hostel.service.custom.RoomService;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReservationFormController {
    public AnchorPane paneReservation;
    public JFXTextField txtReQTY;
    public JFXTextField txtStudentID;
    public JFXTextField txtStudentName;
    public JFXTextField txtRoomID;
    public JFXTextField txtRoomQty;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomType;
    public JFXTextField txtStatus;
    public JFXButton btnAdd;
    public Label lblReseID;
    public JFXComboBox combStudentID;
    public JFXComboBox combRoomID;
    public Text lblRoomId;
    public Text lblRoomType;
    public Label lblRoomQty;
    String reservationId;

    PurchaseReserveService purchaseReserveService = ServiceFactory.getInstance().getService(ServiceTypes.PERCHASE_RESERVE);
    RoomService roomService;

    public void initialize() {
        combStudentID.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                StudentDTO studentDTO = purchaseReserveService.searchStudent((String) newValue);
                txtStudentName.setText(studentDTO.getStudentName());
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        combRoomID.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                RoomDTO roomDTO = purchaseReserveService.searchRooms((String) newValue);
                lblReseID.setText(roomDTO.getRoomID());
                lblRoomType.setText(roomDTO.getRoomType());
                lblRoomQty.setText(String.valueOf(roomDTO.getRoomQty()));

                txtRoomType.setText(roomDTO.getRoomType());
                txtRoomQty.setText((String.valueOf(roomDTO.getRoomQty())));
                txtKeyMoney.setText(roomDTO.getKeyMoney());
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

    }
    private  void RF(){

        reservationId=generateNewOrderId();
        lblReseID.setText(reservationId);

    }
    private void clearFields(){
        combRoomID.setValue(null);
        txtStudentName.clear();
        combRoomID.setValue(null);
        txtRoomType.clear();
        txtKeyMoney.clear();
        txtRoomQty.clear();
        txtStatus.clear();
    }


    public void updateOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        String res_id = lblReseID.getText();
        //LocalDate date = DashBoardFormController.date;
        StudentDTO studentDTO = purchaseReserveService.searchStudent((String) combStudentID.getValue());
        Student student = new Student(studentDTO.getStudentID(), studentDTO.getStudentName(), studentDTO.getAddress(), studentDTO.getContactNo(), studentDTO.getDob(), studentDTO.getGender());
        RoomDTO roomDTO = purchaseReserveService.searchRooms((String) combRoomID.getValue());
        Room room = new Room(roomDTO.getRoomID(), roomDTO.getRoomType(), roomDTO.getKeyMoney(), roomDTO.getRoomQty());
        double key_money = Double.parseDouble(txtKeyMoney.getText());
        String status = txtStatus.getText();
        //int qty = Integer.parseInt(txtStudentQty.getText());


        ReservationDTO reservationDTO = new ReservationDTO(res_id, student, room, key_money, status);

        if(purchaseReserveService.UpdateReservation(reservationDTO)){

            int b=-Integer.parseInt(txtRoomQty.getText());



            RoomDTO roomDTO1=new RoomDTO(room.getRoomID(),room.getRoomType(),room.getKeyMoney(),b);

            roomService.updateRoom(roomDTO1);


            new Alert(Alert.AlertType.CONFIRMATION,"Updated.......").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Try Again.......").show();
        }
    }


    public String generateNewOrderId() {

        try {
            return purchaseReserveService.generateNewOrderID();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();

        return "R001";
    }

    public void ReserveOnAction(ActionEvent actionEvent) {
        String res_id = lblRoomId.getText();
        StudentDTO studentDTO = null;
        try {
            studentDTO = purchaseReserveService.searchStudent((String) combStudentID.getValue());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Student student = new Student(studentDTO.getStudentID(), studentDTO.getStudentName(), studentDTO.getAddress(), studentDTO.getContactNo(), studentDTO.getDob(), studentDTO.getGender());
        RoomDTO roomDTO = null;
        try {
            roomDTO = purchaseReserveService.searchRooms((String) combRoomID.getValue());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Room room = new Room(roomDTO.getRoomID(), roomDTO.getRoomType(), roomDTO.getKeyMoney(), roomDTO.getRoomQty());
        double key_money = Double.parseDouble(txtKeyMoney.getText());
        String status = txtStatus.getText();



        ReservationDTO reservationDTO = new ReservationDTO(res_id,  student, room, key_money, status);
        try {
            if(purchaseReserveService.purchaseReserveSave(reservationDTO)){
                updateRoomQty((String) combRoomID.getValue());
                RF();
                new Alert(Alert.AlertType.CONFIRMATION,"Saved.......").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Try Again.......").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void AddToRemainOnAction(ActionEvent actionEvent) {

    }
    public void updateRoomQty(String id) throws SQLException, IOException, ClassNotFoundException {
        RoomDTO roomDTO = purchaseReserveService.searchRooms(id);
        //int newqty=roomDTO.getRoomQty()-Integer.parseInt(txtStudentQty.getText());
//
//        roomDTO.setRoomQty(newqty);
//        roomBO.updateRoom(roomDTO);
    }
}
