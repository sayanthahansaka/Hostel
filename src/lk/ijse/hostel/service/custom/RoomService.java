package lk.ijse.hostel.service.custom;


import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.service.exception.DuplicateException;
import lk.ijse.hostel.service.exception.InUseException;
import lk.ijse.hostel.service.exception.NotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public interface RoomService {
    List<RoomDTO> getAllRooms() throws IOException;



    //boolean saveRoom(Room r) throws IOException;

    boolean saveRoom(RoomDTO r) throws IOException;

    boolean updateRoom(RoomDTO dto) throws IOException;

    boolean deleteRoom(String id) throws IOException;
}
