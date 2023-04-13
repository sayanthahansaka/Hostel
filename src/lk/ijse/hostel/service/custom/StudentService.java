package lk.ijse.hostel.service.custom;


import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.service.SuperService;
import lk.ijse.hostel.service.exception.DuplicateException;
import lk.ijse.hostel.service.exception.InUseException;
import lk.ijse.hostel.service.exception.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface StudentService extends SuperService {
    List<StudentDTO> getAllStudent() throws IOException;

    boolean saveStudent(Student dto) throws IOException;

    boolean updateStudent(StudentDTO dto) throws IOException;

    boolean deleteStudent(String id) throws IOException;

    StudentDTO searchStudent(String studentId);
}
