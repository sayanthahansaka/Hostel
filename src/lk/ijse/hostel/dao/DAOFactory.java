package lk.ijse.hostel.dao;

import lk.ijse.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hostel.dao.custom.impl.UserDAOimpl;
import lk.ijse.hostel.dto.ReservationDTO;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (null == daoFactory) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO>T getDAO(DAOType type){
        switch (type){
            case STUDENT:
                return (T) new StudentDAOImpl();
            case ROOM:
                return (T) new RoomDAOImpl();
            case RESERVATION:
                return (T) new ReservationDTO();
            case USER:
                return (T) new UserDAOimpl();
            default:
                return null;
        }
    }
}
