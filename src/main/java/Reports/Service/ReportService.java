package Reports.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ReportService {

   // Retrieves the top achieving stores based on the number of results specified
   List<Map<String, Object>> TopAchievingStores(int limit) throws SQLException;

   // Retrieves the top rated stores for a specific month and year
   List<Map<String, Object>> TopRatedStores(int month, int limit) throws SQLException;

   // Retrieves monthly sales data for a specific store, year, and month
   List<Map<String, Object>> MonthlySales(int storeId, int year) throws SQLException;

   //Retrieve top selling employees across the company
   List<Map<String, Object>> TopSellingEmployees(int limit) throws SQLException;

   //Retrieve Stores that reaxhed a particular target
   List<Map<String, Object>> StoreTargets(int month, int year, int target) throws SQLException;

   //Shows Top Selling products
   List<Map<String, Object>> TopSellingProducts(int limit) throws SQLException;

   //Show least perfoming store on intervals of 3 to 6 months.
   List<Map<String, Object>> LeastPerformingStore() throws SQLException;

   //Shows Sales Report For A Specific Product
   List<Map<String, Object>> productSalesReport(int product_id) throws SQLException;

   //Retrieves Current Daily Sales With Inputted Target
   List<Map<String, Object>> CurrentDailySales(int storeId, double dailyTarget) throws SQLException;
}
