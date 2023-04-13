package lk.ijse.hostel.service.custom;



import lk.ijse.hostel.entity.UserLogin;
import lk.ijse.hostel.service.SuperService;

import java.io.IOException;
import java.sql.SQLException;

public interface LoginFormService extends SuperService {
    boolean signup(UserLogin user) throws SQLException, ClassNotFoundException, IOException;
}
