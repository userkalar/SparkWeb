package sparkweb.tablesweb;

import com.google.gson.Gson;
import sparkweb.service.IUserService;
import sparkweb.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet(name = "nrpcServlet", value = "/nrpc")
public class NrpcServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService nrpcService;

    @Override
    public void init() throws ServletException {
        super.init();
        nrpcService = new UserServiceImpl(); // Initialize the NrpcService implementation
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Retrieve distinct neighbourhoods and rooms from the service layer
            List<String> neighbourhoods = nrpcService.getDistinctNeighbourhoods();
            List<String> rooms = nrpcService.getDistinctRooms();

            // Retrieve requested neighbourhood and room from the request parameters
            String requestedNeighbourhood = request.getParameter("neighbourhood");
            String requestedRoom = request.getParameter("room");

            // Query the data based on the requested neighbourhood and room
            List<Double> priceList = nrpcService.getPriceListByNeighbourhoodAndRoom(requestedNeighbourhood, requestedRoom);
            List<Double> countList = nrpcService.getCountListByNeighbourhoodAndRoom(requestedNeighbourhood, requestedRoom);

            // Construct the data map
            Map<String, List<Double>> dataMap = new HashMap<>();
            dataMap.put("price", priceList);
            dataMap.put("count", countList);

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
