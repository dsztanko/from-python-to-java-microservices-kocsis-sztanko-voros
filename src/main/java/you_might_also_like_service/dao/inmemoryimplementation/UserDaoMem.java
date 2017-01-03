package you_might_also_like_service.dao.inmemoryimplementation;
import you_might_also_like_service.dao.UserDao;
import you_might_also_like_service.model.User;

import java.util.ArrayList;
import java.util.Arrays;

public class UserDaoMem implements UserDao{
    private static UserDaoMem ourInstance = new UserDaoMem();

    public static UserDaoMem getInstance() {
        return ourInstance;
    }

    private ArrayList<User> DATA;

    private UserDaoMem() {
        DATA = new ArrayList<User>();
    }

    @Override
    public void save(String accessToken, String userID, String item) {
        User user = find(accessToken, userID);
        if (user == null) {
            user = new User(accessToken, userID, new ArrayList<>(Arrays.asList(item)));
            DATA.add(user);
        }
        else {
            user.getItems().add(item);
        }
    }

    @Override
    public ArrayList selectByCartItems(String accessToken, String userId) {
        ArrayList<String> items = find(accessToken, userId).getItems();
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
    public User find(String accessToken, String userId) {
        User user = null;
        for (User element: DATA) {
            if (element.getAccessToken().equals(accessToken) && element.getUserID().equals(userId)) {
                user = element;
            }
        }
        return user;
    }
}
