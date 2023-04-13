package lk.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Room implements SuperEntity{
    @Id
    private String roomID;
    @Column(nullable = false)
    private String roomType;
    @Column(nullable = false)
    private String keyMoney;
    @Column(nullable = false)
    private int roomQty;

    @OneToMany(mappedBy = "room")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<Reservation> roomDetails=new ArrayList<>();

    public Room(String roomID, String roomType, String keyMoney, int roomQty) {
        this.roomID=roomID;
        this.roomType=roomType;
        this.keyMoney=keyMoney;
        this.roomQty=roomQty;
    }
}
