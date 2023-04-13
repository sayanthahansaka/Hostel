package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.entity.Room;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {
    public List getRoomIds() throws IOException;
}

