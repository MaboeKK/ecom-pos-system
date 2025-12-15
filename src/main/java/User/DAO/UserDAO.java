package User.DAO;

import User.Model.User;
import java.util.List;

public interface UserDAO {
    //User getUser(String email);
    void updateUser(User user);
    List<User> userList();
    void createUser(User user);
    User findByEmployeeId(String empId);
    User findByEmailOrEmployeeId(String emailOrEmpId);
    User findByEmail(String email);
    void setUserStore(String store, String id);
}
