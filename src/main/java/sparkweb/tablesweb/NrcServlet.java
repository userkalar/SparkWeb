package sparkweb.tablesweb;

import sparkweb.service.IUserService;
import sparkweb.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
@WebServlet(name = "nrcServlet", value = "/nrc")
public class NrcServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService nrcService;

    @Override
    public void init() throws ServletException {
        super.init();
        nrcService = new UserServiceImpl(); // Initialize the NrcService implementation
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Get distinct rooms from the service layer
            List<String> rooms = nrcService.getDistinctRooms();

            // Construct the data map
            Map<String, List<Double>> dataMap = new HashMap<>();
            for (String room : rooms) {
                List<Double> countList = nrcService.getCountListByRoom(room);
                dataMap.put(room, countList);
            }

            // Convert data to JSON format
            Gson gson = new Gson();
            String jsonData = gson.toJson(dataMap);

            // Return JSON data
            out.println(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception and return an error response if needed
        }
    }
}
