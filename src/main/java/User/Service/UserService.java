package User.Service;

import User.Model.User;
import java.util.List;

public interface UserService {

    void createUser(User user);
    String loginUser(String email, String password);
    User getUser(String emailOrEmpId);
    void update(User user);
    void viewUsers(List<User> userlist);
    User getUserByEmail(String email);
    User getUserByEmployeeId(String empId);
    void setUserStore(String store, String id);
}