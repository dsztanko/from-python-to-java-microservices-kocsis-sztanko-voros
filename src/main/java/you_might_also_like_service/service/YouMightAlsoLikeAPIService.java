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
        ArrayList<User> allUsers = userDao.selectByCartItems(accessToken, userId);
        User specUser = userDao.find(accessToken, userId);
        HashMap<String, Integer> uniqueItems = userDao.selectUniqueItems(specUser.getAccessToken(), specUser);
        for (User user : allUsers) {
            Integer power = -1;
            for (String item : user.getItems()) {
                if (specUser.getItems().contains(item)) {
                    power++;
                }
            }
            for (String item : user.getItems()) {
                if (!specUser.getItems().contains(item)) {
                    uniqueItems.put(item, (int) (uniqueItems.get(item) + Math.pow(BASE_INDEX, power)));
                }
            }
        }
        JSONObject json = new JSONObject(uniqueItems);
        return json;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> al;
        UserDaoMem a = UserDaoMem.getInstance();
        a.save("page1", "user1", "1");

        a.save("page2", "user1", "2");

        a.save("page1", "user1", "3");

        a.save("page1", "user2", "1");
        a.save("page1", "user2", "4");
        a.save("page1", "user2", "5");

        a.save("page1", "user3", "1");
        a.save("page1", "user3", "2");
        a.save("page1", "user3", "8");
        a.save("page1", "user3", "5");

        a.save("page1", "user4", "a");
        a.save("page1", "user4", "b");
        a.save("page1", "user4", "c");
        YouMightAlsoLikeAPIService b = new YouMightAlsoLikeAPIService();
        System.out.println(b.getRecommendations("page1", "user1"));
//        System.out.println(a.selectByCartItems("page1", "user1"));
    }
}
