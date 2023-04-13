package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import org.hibernate.exception.ConstraintViolationException;

import java.io.IOException;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student,String> {

    public List<String> getStudentIds() throws IOException;
}
