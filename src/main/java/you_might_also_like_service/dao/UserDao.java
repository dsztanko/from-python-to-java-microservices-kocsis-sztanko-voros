package you_might_also_like_service.dao;

import you_might_also_like_service.model.User;

import java.util.ArrayList;

public interface UserDao {
    public void save(String accessToken, String userID, String item);
    public ArrayList selectByCartItems(ArrayList<String> items);
}
