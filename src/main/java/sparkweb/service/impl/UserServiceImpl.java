package sparkweb.service.impl;

import sparkweb.dao.IUserDao;
import sparkweb.dao.impl.UserDaoImpl;
import sparkweb.domain.User;
import sparkweb.service.IUserService;
import sparkweb.tables.*;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private IUserDao dao = new UserDaoImpl();

    //获取全部用户信息
    public User login(String username, String password) {
        return dao.queryByUsernameAndPassword(username, password);
    }
    //获取Neighbourhood条件
    @Override
    public List<String> getDistinctNeighbourhoods() {
        return dao.getDistinctNeighbourhoods();
    }
    //获取Property条件
    @Override
    public List<String> getDistinctProperties() {
        return dao.getDistinctProperties();
    }
    //获取Room条件
    @Override
    public List<String> getDistinctRooms() {
        return dao.getDistinctRooms();
    }

    @Override
    public List<String> getNeighbourhoods() {
        return dao.getNeighbourhoods();
    }

    @Override
    public List<BncData> getBncDataByNeighbourhood(String neighbourhood) {
        return dao.getBncDataByNeighbourhood(neighbourhood);
    }
    //获取bpc数据
    @Override
    public List<BpcData> getBpcDataByProperty(String property) {
        return dao.getBpcDataByProperty(property);
    }


    @Override
    public List<NppcData> getNppcData(String neighbourhood, String property) {
        return dao.getNppcData(neighbourhood, property);
    }


    @Override
    public List<Double> getCountListByRoom(String room) {
        return dao.getCountListByRoom(room);
    }

    @Override
    public List<Double> getPriceListByNeighbourhood(String neighbourhood) {
        return dao.getPriceListByNeighbourhood(neighbourhood);
    }

    @Override
    public List<Double> getPriceListByNeighbourhoodAndRoom(String neighbourhood, String room) {
        return dao.getPriceListByNeighbourhoodAndRoom(neighbourhood, room);
    }

    @Override
    public List<Double> getCountListByNeighbourhoodAndRoom(String neighbourhood, String room) {
        return dao.getCountListByNeighbourhoodAndRoom(neighbourhood, room);
    }

    @Override
    public List<Double> getPncCountListByNeighbourhood(String neighbourhood) {
        return dao.getPncCountListByNeighbourhood(neighbourhood);
    }

    @Override
    public List<Double> getPncPriceListByNeighbourhood(String neighbourhood) {
        return dao.getPncPriceListByNeighbourhood(neighbourhood);
    }

    @Override
    public List<Double> getPrcCountListByRoom(String room) {
        return dao.getPrcCountListByRoom(room);
    }

    @Override
    public List<Double> getPrcPriceListByRoom(String room) {
        return dao.getPrcPriceListByRoom(room);
    }

}
