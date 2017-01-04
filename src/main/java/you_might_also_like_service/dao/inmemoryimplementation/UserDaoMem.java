package you_might_also_like_service.dao.inmemoryimplementation;
import you_might_also_like_service.dao.UserDao;
import you_might_also_like_service.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class UserDaoMem implements UserDao{
    private static UserDaoMem ourInstance = new UserDaoMem();

    public static UserDaoMem getInstance() {
        return ourInstance;
    }

    public ArrayList<User> getDATA() {
        return DATA;
    }

    private ArrayList<User> DATA;

    private UserDaoMem() {
        DATA = new ArrayList<User>();
    }

    @Override
    public void save(String accessToken, String userID, String item) {
        User user = find(accessToken, userID);
        if (user == null) {
            user = new User(accessToken, userID, new HashSet<>(Arrays.asList(item)));
            DATA.add(user);
        }
        else {
            user.getItems().add(item);
        }
    }

    @Override
    public ArrayList<User> selectByCartItems(String accessToken, String userId) throws NullPointerException{
        HashSet<String> items = find(accessToken, userId).getItems();
        ArrayList users = new ArrayList();
        for (User user : DATA) {
            for (String item: items) {
                if (user.getItems().contains(item)) {
                    users.add(user);
                    break;
                }
            }
        }
        return users;
    }

    @Override
    public User find(String accessToken, String userId) throws NullPointerException {
        User user = null;
        for (User element: DATA) {
            if (element.getAccessToken().equals(accessToken) && element.getUserID().equals(userId)) {
                user = element;
            }
        }
        return user;
    }

    @Override
    public HashMap<String, Integer> selectUniqueItems(String accessToken, User specUser) throws NullPointerException {
        HashSet <String> uniqueItemsSet= new HashSet<>();
        for (User user: selectByCartItems(accessToken, specUser.getUserID())) {
            if (user.getAccessToken().equals(accessToken)) {
                uniqueItemsSet.addAll(user.getItems());
            }
        }
        HashMap <String, Integer> uniqueItemsMap = new HashMap<>();
        for (String id: uniqueItemsSet) {
            if (!specUser.getItems().contains(id)) {
                uniqueItemsMap.put(id, 0);
            }
        }
        return uniqueItemsMap;
    }
}
