package you_might_also_like_service.service;

import org.json.JSONObject;
import you_might_also_like_service.dao.UserDao;
import you_might_also_like_service.dao.inmemoryimplementation.UserDaoMem;
import you_might_also_like_service.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class YouMightAlsoLikeAPIService {
    private static YouMightAlsoLikeAPIService ourInstance = new YouMightAlsoLikeAPIService();
    private static final int BASE_INDEX = 3;

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

    public JSONObject getRecommendations(String accessToken, String userId) {
//        todo: in progress
        ArrayList<User> allUsers = userDao.selectByCartItems(accessToken, userId);
        User specUser = userDao.find(accessToken, userId);
        HashMap<String, Integer> uniqueItems = userDao.selectUniqueItems(specUser.getAccessToken());
        for (User user : allUsers) {
            Integer power = -1;
            for (String item : user.getItems()) {
                if (specUser.getItems().contains(item)) {
                    power++;
                }
            }
            for (String item : user.getItems()) {
                uniqueItems.put(item, (int) (uniqueItems.get(item) + Math.pow(BASE_INDEX, power)));
            }
        }
        return null;
    }
}
