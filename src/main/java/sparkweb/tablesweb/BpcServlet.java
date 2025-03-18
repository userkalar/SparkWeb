package sparkweb.tablesweb;

import com.google.gson.Gson;
import sparkweb.tables.BpcData;

import sparkweb.service.IUserService;
import sparkweb.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;


@WebServlet(name = "bpcServlet", value = "/bpc")
public class BpcServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService bpcService;

    @Override
    public void init() throws ServletException {
        super.init();
        bpcService = new UserServiceImpl(); // Initialize the BpcService implementation
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Get the requested property from the request parameter
            String requestedProperty = request.getParameter("property");

            // Retrieve bpc data from the service layer
            List<BpcData> bpcDataList = bpcService.getBpcDataByProperty(requestedProperty);

            // Construct the data map
            Map<String, List<Double>> dataMap = new HashMap<>();
            List<Double> bedsList = new ArrayList<>();
            List<Double> countList = new ArrayList<>();
            for (BpcData bpcData : bpcDataList) {
                bedsList.add(bpcData.getBeds());
                countList.add(bpcData.getCount());
            }
            dataMap.put("beds", bedsList);
            dataMap.put("count", countList);

            Gson gson = new Gson();
            String jsonData = gson.toJson(dataMap);

            // Return JSON data
            out.println(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception and return an error response if needed
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
