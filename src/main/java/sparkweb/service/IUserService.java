package sparkweb.service;

import sparkweb.domain.User;
import sparkweb.tables.*;
import java.util.List;

public interface IUserService {
    //获取全部用户信息
    User login(String username, String password);
    //获取Neighbourhood条件
    List<String> getDistinctNeighbourhoods();
    //获取Property条件
    List<String> getDistinctProperties();
    //获取Room条件
    List<String> getDistinctRooms();
    //获取Neighbourhood条件
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

    List<Double> getPrcCountListByRoom(String room);
    List<Double> getPrcPriceListByRoom(String room);


}
