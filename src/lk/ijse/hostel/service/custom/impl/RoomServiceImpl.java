package lk.ijse.hostel.service.custom.impl;


import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.DAOType;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.service.custom.RoomService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomServiceImpl implements RoomService {
    RoomDAO roomDAO = DAOFactory.getInstance().getDAO(DAOType.ROOM);

        @Override
        public List<RoomDTO> getAllRooms() throws IOException {
            List<Room> all = roomDAO.getAll();
            ArrayList<RoomDTO> allRooms = new ArrayList<>();

            for(Room r: all){
                allRooms.add(new RoomDTO(
                        r.getRoomID(),
                        r.getRoomType(),
                        r.getKeyMoney(),
                        r.getRoomQty()

                ));
            }

            return allRooms;
    }

    @Override
    public boolean saveRoom(RoomDTO r) throws IOException {
        return roomDAO.save(new Room(
           r.getRoomID(),
           r.getRoomType(),
           r.getKeyMoney(),
           r.getRoomQty()
        ));
    }


    @Override
    public boolean updateRoom(RoomDTO r) throws IOException {
        return roomDAO.update(new Room(
                r.getRoomID(),
                r.getRoomType(),
                r.getKeyMoney(),
                r.getRoomQty()

        ));
    }

    @Override
    public boolean deleteRoom(String id) throws IOException {
        return roomDAO.delete(id);
    }
}
