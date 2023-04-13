package lk.ijse.hostel.service.custom;

import lk.ijse.hostel.dto.UserLoginDTO;
import lk.ijse.hostel.entity.UserLogin;
import lk.ijse.hostel.service.SuperService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserService extends SuperService {
    List<UserLoginDTO> getAllUser() throws IOException;

    boolean saveUser(UserLoginDTO dto) throws IOException;

    boolean updateUser(UserLoginDTO dto) throws IOException;

    boolean deleteUser(String id) throws IOException;

    UserLogin searchUser(String id) throws IOException, SQLException, ClassNotFoundException;
}
