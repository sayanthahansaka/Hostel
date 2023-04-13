package lk.ijse.hostel.service.custom.impl;

import lk.ijse.hostel.dto.UserLoginDTO;
import lk.ijse.hostel.entity.UserLogin;
import lk.ijse.hostel.service.custom.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {


    @Override
    public List<UserLoginDTO> getAllUser() throws IOException {
        return null;
    }

    @Override
    public boolean saveUser(UserLoginDTO dto) throws IOException {
        return false;
    }

    @Override
    public boolean updateUser(UserLoginDTO dto) throws IOException {
        return false;
    }

    @Override
    public boolean deleteUser(String id) throws IOException {
        return false;
    }

    @Override
    public UserLogin searchUser(String id) throws IOException, SQLException, ClassNotFoundException {
        return null;
    }
}
