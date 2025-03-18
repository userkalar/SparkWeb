package sparkweb.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import sparkweb.dao.IUserDao;
import sparkweb.domain.User;
import sparkweb.tables.*;
import sparkweb.utils.*;

import java.sql.*;
import java.util.*;

public class UserDaoImpl implements IUserDao {
    private QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

    @Override//获取全部用户信息
    public User queryByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM userdata WHERE username=? AND `password`=?";
        try {
            // 2、通过runner执行SQL语句
            User user = runner.query(sql, new BeanHandler<>
                    (User.class), username, password);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("登录出现异常", e);
        }
    }

    @Override//获取Room条件
    public List<String> getDistinctRooms() {
        List<String> rooms = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection(); // Get database connection

            String sql = "SELECT DISTINCT room FROM nrc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String room = rs.getString("room");
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs); // Close database resources
        }

        return rooms;
    }

    @Override//获取Neighbourhood条件
    public List<String> getDistinctNeighbourhoods() {
        List<String> neighbourhoods = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection(); // 获取数据库连接

            String sql = "SELECT DISTINCT neighbourhood FROM nppc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String neighbourhood = rs.getString("neighbourhood");
                neighbourhoods.add(neighbourhood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs);
        }

        return neighbourhoods;
    }

    @Override//获取Property条件
    public List<String> getDistinctProperties() {
        List<String> properties = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection(); // 获取数据库连接

            String sql = "SELECT DISTINCT property FROM nppc";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String property = rs.getString("property");
                properties.add(property);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs);
        }

        return properties;
    }

    @Override//获取Neighbourhood
    public List<String> getNeighbourhoods() {
        List<String> neighbourhoods = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection();

            String neighbourhoodSql = "SELECT DISTINCT neighbourhood FROM bnc";
            pstmt = conn.prepareStatement(neighbourhoodSql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String neighbourhood = rs.getString("neighbourhood");
                neighbourhoods.add(neighbourhood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs);
        }

        return neighbourhoods;
    }

    @Override
    public List<BncData> getBncDataByNeighbourhood(String neighbourhood) {
        List<BncData> bncDataList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection();

            String bncSql = "SELECT beds, count FROM bnc WHERE neighbourhood = ?";
            pstmt = conn.prepareStatement(bncSql);
            pstmt.setString(1, neighbourhood);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BncData bncData = new BncData();
                bncData.setBeds(rs.getDouble("beds"));
                bncData.setCount(rs.getDouble("count"));
                bncData.setNeighbourhood(neighbourhood);

                bncDataList.add(bncData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs);
        }

        return bncDataList;
    }
    //获取bpc数据
    @Override
    public List<BpcData> getBpcDataByProperty(String property) {
        List<BpcData> bpcDataList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection();

            String bpcSql = "SELECT beds, count FROM bpc WHERE property = ?";
            pstmt = conn.prepareStatement(bpcSql);
            pstmt.setString(1, property);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BpcData bpcData = new BpcData();
                bpcData.setBeds(rs.getDouble("beds"));
                bpcData.setCount(rs.getDouble("count"));

                bpcDataList.add(bpcData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs);
        }

        return bpcDataList;
    }

    @Override
    public List<NppcData> getNppcData(String neighbourhood, String property) {
        List<NppcData> nppcDataList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection(); // 获取数据库连接

            String sql = "SELECT price, count FROM nppc WHERE neighbourhood = ? AND property = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, neighbourhood);
            pstmt.setString(2, property);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                double price = rs.getDouble("price");
                double count = rs.getDouble("count");

                NppcData nppcData = new NppcData();
                nppcData.setPrice(price);
                nppcData.setCount(count);

                nppcDataList.add(nppcData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs);
        }

        return nppcDataList;
    }


    @Override
    public List<Double> getCountListByRoom(String room) {
        List<Double> countList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection(); // Get database connection

            String sql = "SELECT count FROM nrc WHERE room = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, room);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                double count = rs.getDouble("count");
                countList.add(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs); // Close database resources
        }

        return countList;
    }

    @Override
    public List<Double> getPriceListByNeighbourhood(String neighbourhood) {
        List<Double> priceList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection(); // Get database connection

            String sql = "SELECT price FROM nrpc WHERE neighbourhood = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, neighbourhood);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                double price = rs.getDouble("price");
                priceList.add(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs); // Close database resources
        }

        return priceList;
    }

    @Override
    public List<Double> getPriceListByNeighbourhoodAndRoom(String neighbourhood, String room) {
        List<Double> priceList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection(); // Get database connection

            String sql = "SELECT price FROM nrpc WHERE neighbourhood = ? AND room = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, neighbourhood);
            pstmt.setString(2, room);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                double price = rs.getDouble("price");
                priceList.add(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs); // Close database resources
        }

        return priceList;
    }

    @Override
    public List<Double> getCountListByNeighbourhoodAndRoom(String neighbourhood, String room) {
        List<Double> countList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection(); // Get database connection

            String sql = "SELECT count FROM nrpc WHERE neighbourhood = ? AND room = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, neighbourhood);
            pstmt.setString(2, room);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                double count = rs.getDouble("count");
                countList.add(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs); // Close database resources
        }

        return countList;
    }

    @Override
    public List<Double> getPncCountListByNeighbourhood(String neighbourhood) {
        List<Double> countList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection(); // Get database connection

            String sql = "SELECT count FROM pnc WHERE neighbourhood = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, neighbourhood);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                double count = rs.getDouble("count");
                countList.add(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs); // Close database resources
        }

        return countList;
    }

    @Override
    public List<Double> getPncPriceListByNeighbourhood(String neighbourhood) {
        List<Double> priceList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection(); // Get database connection

            String sql = "SELECT price FROM pnc WHERE neighbourhood = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, neighbourhood);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                double price = rs.getDouble("price");
                priceList.add(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs); // Close database resources
        }

        return priceList;
    }

    @Override
    public List<Double> getPrcCountListByRoom(String room) {
        List<Double> countList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection(); // Get database connection

            String sql = "SELECT count FROM prc WHERE Room = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, room);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                double count = rs.getDouble("count");
                countList.add(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs); // Close database resources
        }

        return countList;
    }

    @Override
    public List<Double> getPrcPriceListByRoom(String room) {
        List<Double> priceList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection(); // Get database connection

            String sql = "SELECT price FROM prc WHERE Room = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, room);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                double price = rs.getDouble("price");
                priceList.add(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.closeResources(conn, pstmt, rs); // Close database resources
        }

        return priceList;
    }
}
