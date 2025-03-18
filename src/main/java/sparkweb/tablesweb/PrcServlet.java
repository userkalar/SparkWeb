package sparkweb.tablesweb;

import sparkweb.service.IUserService;
import sparkweb.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet(name = "prcServlet", value = "/prc")
public class PrcServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserServiceImpl pncService;

    @Override
    public void init() throws ServletException {
        super.init();
        pncService = new UserServiceImpl(); // 初始化PncService的实现类
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {

            List<String> neighbourhoods = pncService.getDistinctRooms();

            String requestedRoom = request.getParameter("room");

            // 构建数据集合
            List<Double> priceList = pncService.getPrcPriceListByRoom(requestedRoom);
            List<Double> countList = pncService.getPrcCountListByRoom(requestedRoom);

            // 构建数据集合
            Map<String, List<Double>> dataMap = new HashMap<>();
            dataMap.put("price", priceList);
            dataMap.put("count", countList);

            // 将数据转换为 JSON 格式
            Gson gson = new Gson();
            String jsonData = gson.toJson(dataMap);

            // 返回 JSON 数据
            out.println(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常并返回错误响应（如果需要）
        }
    }
}
