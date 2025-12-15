package Reports.Controller;

import Reports.DAO.ReportDAOImpl;
import Reports.Service.ReportService;
import Reports.Service.ReportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
    private ReportService reportService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize ReportService with an instance of ReportDAOImpl
        this.reportService = new ReportServiceImpl(new ReportDAOImpl());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reportType = request.getParameter("reportType");
        List<Map<String, Object>> reportData = null;

        try {
            switch (reportType) {
                case "topAchievingStores":
                    int limitTAS = Integer.parseInt(request.getParameter("limit"));
                    reportData = reportService.TopAchievingStores(limitTAS);
                    break;
                case "topRatedStores":
                    int monthTRS = Integer.parseInt(request.getParameter("month"));
                    int limitTRS = Integer.parseInt(request.getParameter("limit"));
                    reportData = reportService.TopRatedStores(monthTRS, limitTRS);
                    break;

                case "monthlySales":
                    int storeId = Integer.parseInt(request.getParameter("storeId"));
                    int year = Integer.parseInt(request.getParameter("year"));
                    reportData = reportService.MonthlySales(storeId, year);

                    break;
                case "topSellingEmployees":
                    int limitTSE = Integer.parseInt(request.getParameter("limit"));
                    reportData = reportService.TopSellingEmployees(limitTSE);
                    break;

                case "storeTargets":
                    int monthST = Integer.parseInt(request.getParameter("month"));
                    int yearST = Integer.parseInt(request.getParameter("year"));
                    int targetST = Integer.parseInt(request.getParameter("target"));
                    //double targetST = Double.parseDouble(request.getParameter("target"));
                    reportData = reportService.StoreTargets(monthST, yearST, targetST);
                    break;

                case "topSellingProducts":
                    int limitTSP = Integer.parseInt(request.getParameter("limit"));
                    reportData = reportService.TopSellingProducts(limitTSP);
                    break;

                case "leastPerformingStores":
                    reportData = reportService.LeastPerformingStore();
                    break;

                case "productSalesReport":
                    int product_id = Integer.parseInt(request.getParameter("product_id"));
                    reportData = reportService.productSalesReport(product_id);
                    break;

                case "dailySales":
                    int storeIdDS = Integer.parseInt(request.getParameter("storeId"));
                    double targetDS = Double.parseDouble(request.getParameter("target"));
                    reportData =  reportService.CurrentDailySales(storeIdDS, targetDS);


                // Add cases for other report types
            }

            if (reportData != null) {
                request.setAttribute("reportData", reportData);
            } else {
                request.setAttribute("reportData", "No data available for the selected report type.");
            }
            request.setAttribute("reportType", reportType);
            request.getRequestDispatcher("displayReport.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
