package User.DAO;

import User.Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import Connection.DbConn;

public class UserDAOImpl implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email=?";
        return getUser(email, sql);
    }

    private User getUser(String email, String sql) {
        try (Connection myCon = DbConn.getConnection();
             PreparedStatement preparedStatement = myCon.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToUser(resultSet);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching user", ex);
        }
        return null;
    }

    @Override
    public User findByEmployeeId(String empId) {
        String sql = "SELECT * FROM user WHERE EmpID=?";
        return getUser(empId, sql);
    }

    @Override
    public User findByEmailOrEmployeeId(String emailOrEmpId) {
        String sql = "SELECT * FROM user WHERE email=? OR EmpID=?";
        try (Connection myCon = DbConn.getConnection();
             PreparedStatement preparedStatement = myCon.prepareStatement(sql)) {

            preparedStatement.setString(1, emailOrEmpId);
            preparedStatement.setString(2, emailOrEmpId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToUser(resultSet);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching user by email or employee ID", ex);
        }
        return null;
    }

    private User mapRowToUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .employeeID(resultSet.getString("EMPLOYEEID"))
                .firstname(resultSet.getString("FIRSTNAME"))
                .surname(resultSet.getString("SURNAME"))
                .email(resultSet.getString("EMAIL"))
                .password(resultSet.getString("PASSWORD"))
                .accessLevel(resultSet.getInt("ACCESS_LEVEL"))
                .empId(resultSet.getString("EmpID"))
                .PhoneNo(resultSet.getString("PhoneNo"))
                .store(resultSet.getInt("store_id"))
                .registrationDate(resultSet.getTimestamp("registration_date").toLocalDateTime())
                .build();
    }

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO user (EMPLOYEEID, FIRSTNAME, SURNAME, EMAIL, PASSWORD, ACCESS_LEVEL, EmpID, PhoneNo, store_id, registration_date ) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try (Connection myCon = DbConn.getConnection();
             PreparedStatement preparedStatement = myCon.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getEmployeeID());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getAccessLevel());
            preparedStatement.setString(7, user.getEmpId());
            preparedStatement.setString(8, user.getPhoneNo());
            preparedStatement.setInt(9, user.getStore());
            preparedStatement.setTimestamp(10, Timestamp.valueOf(user.getRegistrationDate()));
            preparedStatement.executeUpdate();
            logger.info("User created successfully.");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error creating user", ex);
        }
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE user SET FIRSTNAME=?, SURNAME=?, EMAIL=?, PASSWORD=?, ACCESS_LEVEL=?, PhoneNo=?, store_id=? WHERE EMPLOYEEID=?";
        try (Connection myCon = DbConn.getConnection();
             PreparedStatement preparedStatement = myCon.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getAccessLevel());
            preparedStatement.setString(6, user.getPhoneNo());
            preparedStatement.setInt(7, user.getStore());
            preparedStatement.setString(8, user.getEmployeeID());
            preparedStatement.executeUpdate();
            logger.info("User updated successfully.");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating user", ex);
        }
    }

    @Override
    public List<User> userList() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user ORDER BY FIRSTNAME";
        try (Connection myCon = DbConn.getConnection();
             PreparedStatement preparedStatement = myCon.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                users.add(mapRowToUser(resultSet));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching user list", ex);
        }
        return users;
    }

    @Override
    public void setUserStore(String store, String id) {
        String sql = "UPDATE user SET store_id=? WHERE EMPLOYEEID=?";
        try (Connection myCon = DbConn.getConnection();
             PreparedStatement preparedStatement = myCon.prepareStatement(sql)) {

            preparedStatement.setString(1, store);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
            logger.info("User store updated successfully.");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating user store", ex);
        }
    }
}
