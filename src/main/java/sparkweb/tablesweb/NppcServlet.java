package sparkweb.tablesweb;

import com.google.gson.Gson;
import sparkweb.tables.NppcData;
import sparkweb.service.IUserService;
import sparkweb.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet(name = "nppcServlet", value = "/nppc")
public class NppcServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService nppcService;

    @Override
    public void init() throws ServletException {
        super.init();
        nppcService = new UserServiceImpl(); // 初始化NppcService实现类
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // 获取前端传递的参数
            String requestedNeighbourhood = request.getParameter("neighbourhood");
            String requestedProperty = request.getParameter("property");

            // 查询不同的 neighbourhood
            List<String> neighbourhoods = nppcService.getDistinctNeighbourhoods();

            // 查询不同的 property
            List<String> properties = nppcService.getDistinctProperties();

            // 根据条件查询表 nppc 的数据
            List<NppcData> nppcDataList = nppcService.getNppcData(requestedNeighbourhood, requestedProperty);

            // 构建数据集合
            List<Double> priceList = new ArrayList<>();
            List<Double> countList = new ArrayList<>();

            for (NppcData nppcData : nppcDataList) {
                priceList.add(nppcData.getPrice());
                countList.add(nppcData.getCount());
            }

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
            // 处理异常并返回错误响应
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
