package lk.ijse.hostel.util;

import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.entity.UserLogin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    public static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration= new Configuration().configure()
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(UserLogin.class);
        sessionFactory=configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return factoryConfiguration==null? factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}
//    public static FactoryConfiguration factoryConfiguration;
//    private SessionFactory sessionFactory;
//
//    private FactoryConfiguration() throws IOException {
//        Configuration configuration = new Configuration();
//        Properties p = new Properties();
//        p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
//        configuration.setProperties(p);
//
//
//
//        sessionFactory = configuration.buildSessionFactory();
//    }
//
//    public static FactoryConfiguration getInstance() throws IOException {
//        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration()
//                : factoryConfiguration;
//    }
//
//    public Session getSession(){
//        return sessionFactory.openSession();
//    }
//}
