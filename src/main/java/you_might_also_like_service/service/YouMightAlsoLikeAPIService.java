package you_might_also_like_service.service;

import org.json.JSONObject;
import you_might_also_like_service.dao.UserDao;
import you_might_also_like_service.dao.inmemoryimplementation.UserDaoMem;
import you_might_also_like_service.model.User;

import java.util.ArrayList;

public class YouMightAlsoLikeAPIService {
    private static YouMightAlsoLikeAPIService ourInstance = new YouMightAlsoLikeAPIService();

    public static YouMightAlsoLikeAPIService getInstance() {
        return ourInstance;
    }

    private UserDao userDao;

    private YouMightAlsoLikeAPIService() {
        userDao = UserDaoMem.getInstance();
    }

    public void saveUser(String accessToken, String userID, String item) {
        userDao.save(accessToken, userID, item);
    }

    public JSONObject getAllByItems(String accessToken, String userId) {
        ArrayList allusers = userDao.selectByCartItems(accessToken, userId);
        User user = userDao.find(accessToken, userId);
        return null;
    }
}
