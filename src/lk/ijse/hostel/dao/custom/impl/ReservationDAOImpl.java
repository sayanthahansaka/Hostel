package lk.ijse.hostel.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.dao.exception.ConstraintViolationException;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.SuperEntity;
import lk.ijse.hostel.util.FactoryConfiguration;
import lk.ijse.hostel.view.tdm.KeyMoneyTM;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean update(Reservation entity) {
        Session session = null;
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) {
        Session session = null;
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        Reservation reservation = session.load(Reservation.class, s);

        session.delete(reservation);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Reservation search(String ID) throws SQLException, ClassNotFoundException {
        Session session = null;
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation reservation= session.get(Reservation.class,ID);



        transaction.commit();
        session.close();
        return reservation;
    }

    @Override
    public boolean find(String s) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }
    @Override
    public String generateNewID() throws IOException  {
        Session session = FactoryConfiguration.getInstance().getSession();

        Query query = session.createQuery("SELECT res_id FROM Reservation ORDER BY res_id DESC").setMaxResults(1);
        List list = query.list();
        session.close();

        String newUserId = "";

        String lastUserId = list.toString();
        String[] split = lastUserId.split("[A-z]");
        if(split.length==0){
            return "R001";
        }else{
            Integer integer = Integer.valueOf(split[2]);
            ++integer;

            if(!list.isEmpty()){
                if (integer>=100) {
                    newUserId = "R" + String.valueOf(integer) ;
                }else if(integer>=10){
                    newUserId = "R0" + String.valueOf(integer);
                }else{
                    newUserId = "R00" + String.valueOf(integer);
                }
                return newUserId;

            }else{
                return "R001";
            }

        }

    }

    @Override
    public ArrayList getAll()
    {
        return null;
    }

    @Override
    public boolean save(Reservation entity) throws IOException {
        Session session = null;
        session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Reservation> searchReservation(String enteredText) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ObservableList<KeyMoneyTM> getRemainKeyMoney() throws SQLException, ClassNotFoundException, IOException {
        ObservableList<KeyMoneyTM> students = FXCollections.observableArrayList();

        Session session = FactoryConfiguration.getInstance().getSession();
        List <Reservation>list = session.createQuery("FROM Reservation WHERE status = 'Paid Later'").list();

        for(Reservation reserve : list){
            String studentId = reserve.getStudent().getStudentID();
            String name = reserve.getStudent().getStudentName();
            String status = reserve.getStatus();

            students.add(new KeyMoneyTM(studentId,name,status));
        }
        return students;

    }
}
