package you_might_also_like_service.dao;

import you_might_also_like_service.model.User;

import java.util.ArrayList;

public interface UserDao {
    public void save(User user);
    public ArrayList selectByCartItems(ArrayList<String> items);
}
