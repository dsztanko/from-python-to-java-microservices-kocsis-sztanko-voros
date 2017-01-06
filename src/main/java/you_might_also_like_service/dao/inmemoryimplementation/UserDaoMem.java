package you_might_also_like_service.dao.inmemoryimplementation;
import you_might_also_like_service.dao.UserDao;
import you_might_also_like_service.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/* Implementation which stores in computer's memory */

public class UserDaoMem implements UserDao{
    private static UserDaoMem ourInstance = new UserDaoMem();

    public static UserDaoMem getInstance() {
        return ourInstance;
    }

    private ArrayList<User> DATA;

    private UserDaoMem() {
        DATA = new ArrayList<User>();
    }

    public ArrayList<User> getDATA() {
        return DATA;
    }

    // finds specUser - person, whose cart is the benchmark as opposed to the others' - in storage
    @Override
    public User find(String accessToken, String userId) {
        User user = null;
        for (User element: DATA) {
            if (element.getAccessToken().equals(accessToken) && element.getUserID().equals(userId)) {
                user = element;
            }
        }
        return user;
    }

    // saves user
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

    // selects all the users whose cart items contains one of the specUser's cartItem
    @Override
    public ArrayList<User> containsOneOfTheSpecItemsAtLeast(String accessToken, String userId) {
        ArrayList users = new ArrayList();
        try {
            HashSet<String> items = find(accessToken, userId).getItems();
            for (User user : DATA) {
                for (String item : items) {
                    if (user.getItems().contains(item)) {
                        users.add(user);
                        break;
                    }
                }
            }
        } catch (NullPointerException e) {
//            No access token and user ID matched.
        }
        return users;
    }

    // selects all items that are unique among users selected by containsOneOfTheSpecItemsAtLeast() method
    @Override
    public HashMap<String, Integer> selectUniqueItems(String accessToken, User specUser) {
        HashSet <String> uniqueItemsSet= new HashSet<>();
        for (User user: containsOneOfTheSpecItemsAtLeast(accessToken, specUser.getUserID())) {
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
