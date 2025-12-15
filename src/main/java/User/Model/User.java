package User.Model;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private String employeeID;
    private String firstname;
    private String surname;
    private String email;
    private int accessLevel;
    private String password;
    private String empId;
    private String PhoneNo;
    private int store;
    private LocalDateTime registrationDate;
}
