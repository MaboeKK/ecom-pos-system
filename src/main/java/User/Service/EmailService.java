package User.Service;

public interface EmailService {
    void sendEmailToEmp(String email, String firstname, String role, String storeName);

    //Send receipt to customer
    void sendEmailToPaidCustomer(String email, String firstname);

    //Done if there is a shortage of stock.
    void sendEmailToManager(String email, String firstname, String role);

    //Deleted or Updated Store
    void sendEmailToRegMan(String email, String firstname, String store_id);

}
