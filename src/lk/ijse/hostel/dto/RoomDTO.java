package lk.ijse.hostel.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class RoomDTO {
    private String roomID;
    private String roomType;
    private String keyMoney;
    private int roomQty;

}
