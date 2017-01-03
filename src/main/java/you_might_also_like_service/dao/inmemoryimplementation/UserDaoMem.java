package you_might_also_like_service.dao.inmemoryimplementation;
import you_might_also_like_service.dao.UserDao;
import you_might_also_like_service.model.User;

import java.util.ArrayList;

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
    public void save(User user) {
        DATA.add(user);
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
