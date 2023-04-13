package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Student implements SuperEntity {
    @Id
    private String studentID;
    @Column(nullable = false)
    private String studentName;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String address;
    @Column(nullable = false)
    private String contactNo;
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate dob;
    @Column(nullable = false)
    private String gender;

    @OneToMany(mappedBy = "student")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<Reservation> reservations = new ArrayList<>();



    public Student(String studentID, String studentName, String address, String contactNo, LocalDate dob, String gender) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.address = address;
        this.contactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
    }

}
