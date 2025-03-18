package sparkweb.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(name = "testservlet", value = "/testservlet")
public class testservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 加载并注册 MySQL 驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 创建数据库连接
            String jdbcUrl = "jdbc:mysql://192.168.233.131:3306/spark";
            String username = "root";
            String password = "123456a";
            conn = DriverManager.getConnection(jdbcUrl, username, password);

            // 查询表数据并构建数据集合
            Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();

            // 查询表bnc的数据
            String bncSql = "SELECT * FROM bnc";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(bncSql);
            List<Map<String, Object>> bncDataList = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> bncData = new HashMap<>();
                bncData.put("beds", rs.getDouble("beds"));
                bncData.put("neighbourhood", rs.getString("neighbourhood"));
                bncData.put("count", rs.getDouble("count"));
                bncDataList.add(bncData);
            }
            dataMap.put("bnc", bncDataList);

            // 查询表bpc的数据
            String bpcSql = "SELECT * FROM bpc";
            rs = stmt.executeQuery(bpcSql);
            List<Map<String, Object>> bpcDataList = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> bpcData = new HashMap<>();
                bpcData.put("beds", rs.getDouble("beds"));
                bpcData.put("property", rs.getString("property"));
                bpcData.put("count", rs.getDouble("count"));
                bpcDataList.add(bpcData);
            }
            dataMap.put("bpc", bpcDataList);

            // 查询表nppc的数据
            String nppcSql = "SELECT * FROM nppc";
            rs = stmt.executeQuery(nppcSql);
            List<Map<String, Object>> nppcDataList = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> nppcData = new HashMap<>();
                nppcData.put("neighbourhood", rs.getString("neighbourhood"));
                nppcData.put("property", rs.getString("property"));
                nppcData.put("price", rs.getInt("price"));
                nppcData.put("count", rs.getInt("count"));
                nppcDataList.add(nppcData);
            }
            dataMap.put("nppc", nppcDataList);

            // 查询表nrpc的数据
            String nrpcSql = "SELECT * FROM nrpc";
            rs = stmt.executeQuery(nrpcSql);
            List<Map<String, Object>> nrpcDataList = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> nrpcData = new HashMap<>();
                nrpcData.put("neighbourhood", rs.getString("neighbourhood"));
                nrpcData.put("room", rs.getString("room"));
                nrpcData.put("price", rs.getInt("price"));
                nrpcData.put("count", rs.getInt("count"));
                nrpcDataList.add(nrpcData);
            }
            dataMap.put("nrpc", nrpcDataList);

            // 查询表pnc的数据
            String pncSql = "SELECT * FROM pnc";
            rs = stmt.executeQuery(pncSql);
            List<Map<String, Object>> pncDataList = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> pncData = new HashMap<>();
                pncData.put("price", rs.getInt("price"));
                pncData.put("neighbourhood", rs.getString("neighbourhood"));
                pncData.put("count", rs.getInt("count"));
                pncDataList.add(pncData);
            }
            dataMap.put("pnc", pncDataList);

            // 查询表prc的数据
            String prcSql = "SELECT * FROM prc";
            rs = stmt.executeQuery(prcSql);
            List<Map<String, Object>> prcDataList = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> prcData = new HashMap<>();
                prcData.put("price", rs.getInt("price"));
                prcData.put("room", rs.getString("room"));
                prcData.put("count", rs.getInt("count"));
                prcDataList.add(prcData);
            }
            dataMap.put("prc", prcDataList);

            // 查询表nrc的数据
            String nrcSql = "SELECT * FROM nrc";
            rs = stmt.executeQuery(nrcSql);
            List<Map<String, Object>> nrcDataList = new ArrayList<>();
            while (rs.next()) {
                Map<String, Object> nrcData = new HashMap<>();
                nrcData.put("neighbourhood", rs.getString("neighbourhood"));
                nrcData.put("room", rs.getString("room"));
                nrcData.put("count", rs.getInt("count"));
                nrcDataList.add(nrcData);
            }
            dataMap.put("nrc", nrcDataList);

            // 将数据转换为 JSON 格式
            Gson gson = new Gson();
            String jsonData = gson.toJson(dataMap);

            // 返回 JSON 数据
            out.println(jsonData);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接和资源
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
