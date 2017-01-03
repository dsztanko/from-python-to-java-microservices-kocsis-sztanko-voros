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
        Boolean addBoolean = true;
        for (User user: DATA) {
            if (user.getAccessToken().equals(accessToken) && user.getUserID().equals(userID)) {
                user.getItems().add(item);
                addBoolean = false;
            }
        }
        if (addBoolean) {
            User user = new User(accessToken, userID, new ArrayList<>(Arrays.asList(item)));
            DATA.add(user);
        }
    }

    @Override
    public ArrayList selectByCartItems(ArrayList<String> items) {
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
}
