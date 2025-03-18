package sparkweb.dao;

import sparkweb.domain.User;
import sparkweb.tables.*;

import java.util.List;

public interface IUserDao {
    //获取全部用户信息
    User queryByUsernameAndPassword(String usernamr, String password);
    //获取Neighbourhood条件
    List<String> getDistinctNeighbourhoods();
    //获取Property条件
    List<String> getDistinctProperties();
    //获取Room条件
    List<String> getDistinctRooms();

    List<String> getNeighbourhoods();

    List<BncData> getBncDataByNeighbourhood(String neighbourhood);
    //获取bpc数据
    List<BpcData> getBpcDataByProperty(String property);

    List<NppcData> getNppcData(String neighbourhood, String property);

    List<Double> getCountListByRoom(String room);

    List<Double> getPriceListByNeighbourhood(String neighbourhood);

    List<Double> getPriceListByNeighbourhoodAndRoom(String neighbourhood, String room);

    List<Double> getCountListByNeighbourhoodAndRoom(String neighbourhood, String room);

    List<Double> getPncCountListByNeighbourhood(String neighbourhood);

    List<Double> getPncPriceListByNeighbourhood(String neighbourhood);

    List<Double> getPrcCountListByRoom(String Room);

    List<Double> getPrcPriceListByRoom(String Room);

}

