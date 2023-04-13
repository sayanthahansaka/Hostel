package lk.ijse.hostel.dao.util;


import lk.ijse.hostel.dao.SuperDAO;
import lk.ijse.hostel.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hostel.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hostel.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hostel.dao.custom.impl.UserDAOimpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){

    }

    public static DaoFactory getDaoFactory(){
        if(daoFactory == null){
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }
    public static DaoFactory getInstance(){
        return daoFactory == null?(daoFactory = new DaoFactory()) : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DaoTypes types) {
        switch (types) {
            case STUDENT:
                return (T) new StudentDAOImpl();
            case ROOM:
                return (T) new RoomDAOImpl();
            case RESERVATION:
                return (T) new ReservationDAOImpl();
            case USER:
                return (T) new UserDAOimpl();
            default:
                return null;
        }
    }
}
/*


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
                return (T) new ResevationDAOImpl();
            case USER:
                return (T) new UserDAOimpl();
            default:
                return null;
        }
    }*/
