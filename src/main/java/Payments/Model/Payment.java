package Payments.Model;

import lombok.Data;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private int paymentId;
    private int orderId;
    private int storeId;
    private int employeeId;
    private int productId;
    private double amount;
    private String status;
    private LocalDateTime paymentDate;
    private LocalDateTime updatedDate;
}
