package lk.ijse.hostel.service.custom.impl;


import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.DAOType;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.custom.StudentService;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOType.STUDENT);
    @Override
    public List<StudentDTO> getAllStudent() throws IOException {
        List<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudents = new ArrayList<>();

        for(Student s: all){
            allStudents.add(new StudentDTO(
                    s.getStudentID(),
                    s.getStudentName(),
                    s.getAddress(),
                    s.getContactNo(),
                    s.getDob(),
                    s.getGender()));
        }

        return allStudents;
    }

    @Override
    public boolean saveStudent(Student dto) throws IOException {
        return studentDAO.save(new Student(
                dto.getStudentID(),
                dto.getStudentName(),
                dto.getAddress(),
                dto.getContactNo(),
                dto.getDob(),
                dto.getGender()
        ));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws IOException {
        return false;
    }

    @Override
    public boolean deleteStudent(String id) throws IOException {
        return studentDAO.delete(id);
    }

    @Override
    public StudentDTO searchStudent(String studentId) {
        return null;
    }
}
