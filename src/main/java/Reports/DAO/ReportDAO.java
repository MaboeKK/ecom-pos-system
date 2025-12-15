package Reports.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ReportDAO {

    // Retrieves the top stores based on total sales
    List<Map<String, Object>> getTopStores(int limit) throws SQLException;

    // Retrieves the top rated stores for a specific month and year
    List<Map<String, Object>> getTopRatedStores(int month, int limit) throws SQLException;

    // Retrieves monthly sales data for a specific store and date
    List<Map<String, Object>> getMonthlySales(int storeId, int year) throws SQLException;

    //Retrieve top selling employees across the company
    List<Map<String, Object>> getTopSellingEmployees(int limit) throws SQLException;

    //Retrieve Stores that achieved a particular target
    List<Map<String, Object>> getStoreTargets(int month, int year, int target) throws SQLException;

    //Show the most selling product
    List<Map<String, Object>> getTopSellingProducts(int limit) throws SQLException;

    //Show least perfoming store on intervals of 3 to 6 months.
    List<Map<String, Object>> getLeastPerformingStore() throws SQLException;

    //Sales Report for a chosen product
    List<Map<String, Object>> getProductSalesReport(int product_id) throws SQLException;

    //To get current daily sales
    List<Map<String, Object>> getCurrentDailySales(int storeId, double dailyTarget) throws SQLException;
}
