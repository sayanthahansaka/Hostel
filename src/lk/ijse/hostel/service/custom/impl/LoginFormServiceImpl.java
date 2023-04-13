package lk.ijse.hostel.service.custom.impl;

import lk.ijse.hostel.dao.custom.UserDAO;
import lk.ijse.hostel.dao.util.DaoFactory;
import lk.ijse.hostel.dao.util.DaoTypes;
import lk.ijse.hostel.entity.UserLogin;
import lk.ijse.hostel.service.custom.LoginFormService;


import java.io.IOException;
import java.sql.SQLException;

public class LoginFormServiceImpl implements LoginFormService {
    UserDAO userDAO = DaoFactory.getInstance().getDAO(DaoTypes.USER);
    @Override
    public boolean signup(UserLogin user) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.save(user);
    }
}
