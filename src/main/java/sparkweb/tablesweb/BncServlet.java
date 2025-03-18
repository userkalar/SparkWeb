package sparkweb.tablesweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import com.google.gson.Gson;
import sparkweb.service.IUserService;
import sparkweb.service.impl.UserServiceImpl;
import sparkweb.tables.BncData;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet(name = "bncServlet", value = "/bnc")
public class BncServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IUserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // 获取前端传递的参数 neighborhood
            String requestedNeighbourhood = request.getParameter("neighbourhood");

            // 调用Service层方法查询数据
            List<BncData> bncDataList = userService.getBncDataByNeighbourhood(requestedNeighbourhood);

            // 构建数据集合
            double[] bedsArray = new double[bncDataList.size()];
            double[] countArray = new double[bncDataList.size()];

            for (int i = 0; i < bncDataList.size(); i++) {
                BncData bncData = bncDataList.get(i);
                bedsArray[i] = bncData.getBeds();
                countArray[i] = bncData.getCount();
            }

            // 构建数据集合
            Map<String, double[]> dataMap = new HashMap<>();
            dataMap.put("beds", bedsArray);
            dataMap.put("count", countArray);

            // 将数据转换为 JSON 格式
            Gson gson = new Gson();
            String jsonData = gson.toJson(dataMap);

            // 返回 JSON 数据
            out.println(jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
