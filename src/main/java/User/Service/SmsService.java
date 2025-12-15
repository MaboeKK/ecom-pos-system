package User.Service;

import java.io.IOException;

public interface SmsService {
    void sendSMS(String toPhoneNumber, String messageBody);
}
