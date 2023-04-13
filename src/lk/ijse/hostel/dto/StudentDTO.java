package lk.ijse.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StudentDTO {
    private String studentID;
    private String studentName;
    private String address;
    private String contactNo;
    private LocalDate dob;
    private String gender;

}
