package User.Service;

import User.DAO.UserDAO;
import User.Model.User;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDao;

    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public String loginUser(String emailOrEmpId, String password) {
        User user = userDao.findByEmailOrEmployeeId(emailOrEmpId);
        if (user != null && PasswordUtil.verifyPassword(password, user.getPassword())) {
            return "access_Granted";
        } else {
            return "Access_Denied";
        }
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewUsers(List<User> userlist) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(String emailOrEmpId) {
        return userDao.findByEmailOrEmployeeId(emailOrEmpId);
    }

    @Override
    public void createUser(User user) {
        if (user != null) {
            // Hash password before storing
            if (user.getPassword() != null && !PasswordUtil.isHashed(user.getPassword())) {
                user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
            }
            userDao.createUser(user);
        } else {
            throw new IllegalArgumentException("User cannot be null");
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User getUserByEmployeeId(String empId) {
        return userDao.findByEmployeeId(empId);
    }

    @Override
    public void setUserStore(String store, String user) {
        userDao.setUserStore(store,user);
    }

}
