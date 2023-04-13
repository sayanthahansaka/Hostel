package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.service.ServiceFactory;
import lk.ijse.hostel.service.ServiceTypes;
import lk.ijse.hostel.service.custom.RoomService;
import lk.ijse.hostel.service.custom.StudentService;
import lk.ijse.hostel.view.tdm.RoomTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RoomFormController {
    public AnchorPane paneRoom;
    public JFXTextField txtRQty;
    public JFXTextField txtRKeyMoney;
    public JFXComboBox<String> combxRType;
    public JFXComboBox<String> combxRID;
    public JFXButton btnAdd;
    public TableView<RoomTM> tblRoom;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colKeymap;
    public TableColumn colRoomQty;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    private RoomService roomService;


    public void initialize() throws IOException {

        combxRID.getItems().addAll("R-001", "R-002", "R-003", "R-004");
        combxRType.getItems().addAll("Non-AC", "Non-Ac/Food", "AC", "AC/Food");

        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomID"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomType"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("roomQty"));

        initUI();

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnAdd.setText(newValue != null ? "Update" : "Save");
            btnDelete.setDisable(false);

            if(newValue != null){
                combxRID.setValue(newValue.getRoomID());
                combxRType.setValue(newValue.getRoomType());
                txtRKeyMoney.setText(newValue.getKeyMoney());
                txtRQty.setText(String.valueOf(newValue.getRoomQty()));


                combxRID.setDisable(false);
                combxRType.setDisable(false);
                txtRKeyMoney.setDisable(false);
                txtRQty.setDisable(false);


                btnAdd.setDisable(false);
            }
        });

        loadAllRooms();
    }
    private void loadAllRooms(){
        tblRoom.getItems().clear();
        try {
            List<RoomDTO> allrooms = roomService.getAllRooms();
            for(RoomDTO r : allrooms) {
                tblRoom.getItems().add(new RoomTM(
                        r.getRoomID(),
                        r.getRoomType(),
                        r.getKeyMoney(),
                        r.getRoomQty()
                ));
            }
        } catch (Exception e) {
             System.out.println(e);
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }
    private void clearFields(){
          combxRID.setValue(null);
          combxRType.setValue(null);
        txtRKeyMoney.clear();
            txtRQty.clear();

    }

    private void initUI() {
          combxRID.setValue(null);
          combxRType.setValue(null);
        txtRKeyMoney.clear();
            txtRQty.clear();
        // txtDOB.clear();
          combxRID.setDisable(true);
          combxRType.setDisable(true);
        txtRKeyMoney.setDisable(true);
            txtRQty.setDisable(true);
        combxRID.setEditable(true);
        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void AddNewRoomOnAction(ActionEvent actionEvent) {
        combxRID.setDisable(false);
        combxRType.setDisable(false);
        txtRKeyMoney.setDisable(false);
        txtRQty.setDisable(false);
        combxRID.setValue(null);
        combxRType.setValue(null);
        txtRKeyMoney.clear();
        txtRQty.clear();
        //cmbRoomID.setValue(generateNewId());
        combxRID.requestFocus();
        btnAdd.setDisable(false);
        btnAdd.setText("Save");
        tblRoom.getSelectionModel().clearSelection();
    }
    public void addOnAction(ActionEvent actionEvent) throws IOException {
        String id = combxRID.getValue();
        String type = combxRType.getValue();
        String keyMoney =txtRKeyMoney.getText();
        int qty = Integer.valueOf(txtRQty.getText());

        if (!keyMoney.matches("^[0-9]{3,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Key_Money").show();
            txtRKeyMoney.requestFocus();
            return;
        } else if (!txtRQty.getText().matches("^[0-9]{1,5}$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty ").show();
            txtRQty.requestFocus();
            return;
        }
        if (btnAdd.getText().equalsIgnoreCase("Save")) {
            if (roomService.saveRoom(new RoomDTO(id, type, keyMoney, qty)))
            {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            }
        } else {
            try {
                if (roomService.updateRoom(new RoomDTO(id, type, keyMoney, qty))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            }
        }
    }
    private void fillData(RoomDTO roomDTO) {
        this.combxRID.setValue(roomDTO.getRoomID());
        this.combxRType.setValue(roomDTO.getRoomType());
        this.txtRKeyMoney.setText(String.valueOf(roomDTO.getKeyMoney()));
        this.txtRQty.setText(String.valueOf(roomDTO.getRoomQty()));
    }

    public void updateOnAction(ActionEvent actionEvent) throws IOException {
        String id = combxRID.getValue();
        String type = combxRType.getValue();
        String keyMoney =txtRKeyMoney.getText();
        int qty = Integer.valueOf(txtRQty.getText());
        RoomDTO roomDTO = new RoomDTO(id, type, keyMoney, qty);

        boolean isUpdate= roomService.updateRoom(roomDTO);
        if(isUpdate){
            (new Alert(Alert.AlertType.CONFIRMATION, "Room Updated!", new ButtonType[0])).show();
        }else{
            (new Alert(Alert.AlertType.WARNING, "Something Wrong!", new ButtonType[0])).show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id=tblRoom.getSelectionModel().getSelectedItem().getRoomID();
        try {
            roomService.deleteRoom(id);
            tblRoom.getItems().remove(tblRoom.getSelectionModel().getSelectedItem());
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted..!").show();

            tblRoom.getSelectionModel().clearSelection();
            clearFields();
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Something Happened.Try again Carefully..!").show();
        }
    }

    public void logoutOnAction(ActionEvent actionEvent) {

    }
}
