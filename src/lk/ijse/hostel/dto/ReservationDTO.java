package lk.ijse.hostel.dto;

import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private String res_id;
    private LocalDate date;
    private Student studentID;
    private Room roomID;
    private double key_money;
    private String status;
    private int qty;

    public ReservationDTO(String resId,Student student, Room room, double keyMoney, String status) {
    }
}
