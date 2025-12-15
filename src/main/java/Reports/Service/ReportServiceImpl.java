package Reports.Service;

import Reports.DAO.ReportDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ReportServiceImpl implements ReportService {

    private final ReportDAO reportDAO;

    public ReportServiceImpl(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @Override
    public List<Map<String, Object>> TopAchievingStores(int limit) throws SQLException {
        return reportDAO.getTopStores(limit);
    }

    @Override
    public List<Map<String, Object>> TopRatedStores(int month, int limit) throws SQLException {
        return reportDAO.getTopRatedStores(month, limit);
    }

    @Override
    public List<Map<String, Object>> MonthlySales(int storeId, int year) throws SQLException {
        return reportDAO.getMonthlySales(storeId, year);
    }

    @Override
    public List<Map<String, Object>> TopSellingEmployees(int limit) throws SQLException {
        return reportDAO.getTopSellingEmployees(limit);
    }

    @Override
    public List<Map<String, Object>> StoreTargets(int month, int year, int target) throws SQLException {
        return reportDAO.getStoreTargets(month, year, target);
    }

    @Override
    public List<Map<String, Object>> TopSellingProducts(int limit) throws SQLException {
        return reportDAO.getTopSellingProducts(limit);
    }

    @Override
    public List<Map<String, Object>> LeastPerformingStore() throws SQLException {
        return reportDAO.getLeastPerformingStore();
    }

    @Override
    public List<Map<String, Object>> productSalesReport(int product_id) throws SQLException {
        return reportDAO.getProductSalesReport(product_id);
    }

    @Override
    //List<Object[]> CurrentDailySales(int storeId, double dailyTarget) throws SQLException
    public List<Map<String, Object>> CurrentDailySales(int storeId, double dailyTarget) throws SQLException {
        return reportDAO.getCurrentDailySales(storeId, dailyTarget);
    }
}
