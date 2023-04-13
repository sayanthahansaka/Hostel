package lk.ijse.hostel.service.custom.impl;

import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.DAOType;
import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.custom.PurchaseReserveService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class PurchaseReserveServiceImpl implements PurchaseReserveService {
    RoomDAO roomDAO = DAOFactory.getInstance().getDAO(DAOType.ROOM);
    StudentDAO studentDAO=DAOFactory.getInstance().getDAO(DAOType.STUDENT);
    ReservationDAO reservationDAO=DAOFactory.getInstance().getDAO(DAOType.RESERVATION);

    @Override
    public boolean purchaseReserveSave(ReservationDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.save(new Reservation(

                dto.getRes_id(),
                dto.getDate(),
                dto.getKey_money(),
                dto.getQty(),
                dto.getStatus(),
                dto.getRoomID(),
                dto.getStudentID()
        ));
    }

    @Override
    public boolean UpdateReservation(ReservationDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.update(new Reservation(
                dto.getRes_id(),
                dto.getDate(),
                dto.getKey_money(),
                dto.getQty(),
                dto.getStatus(),
                dto.getRoomID(),
                dto.getStudentID()
        ));
    }

    @Override
    public boolean deleteReservation(String id) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.delete(id);
    }

    @Override
    public RoomDTO searchRooms(String id) throws SQLException, ClassNotFoundException, IOException {
         Room room = roomDAO.search(id);
        return new RoomDTO(room.getRoomID(),room.getRoomType(),room.getKeyMoney(), room.getRoomQty());
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException, IOException {
        Student s = studentDAO.search(id);

        return new StudentDTO(s.getStudentID(),s.getStudentName(),s.getAddress(),s.getContactNo(),s.getDob(),s.getGender());
    }

    @Override
    public ReservationDTO searchReservation(String id) throws SQLException, ClassNotFoundException, IOException {
        Reservation reservation=reservationDAO.search(id);

        return new ReservationDTO(reservation.getRes_id(), reservation.getDate(),reservation.getStudent(), reservation.getRoom(), reservation.getKey_money(), reservation.getStatus(), reservation.getQty());
    }

    @Override
    public boolean checkRoomIsAvailable(String id) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.find(id);
    }

    @Override
    public boolean checkStudentIsAvailable(String id) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.find(id);
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.generateNewID();
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        return null;
    }

    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
        return null;
    }

    @Override
    public List<ReservationDTO> getAllReservation() throws Exception {
        return null;
    }

    @Override
    public List getStudentIds() throws IOException {
        return Collections.singletonList(studentDAO.getStudentIds());
    }

    @Override
    public List getRoomIds() throws IOException {
        return Collections.singletonList(roomDAO.getRoomIds());
    }
}
