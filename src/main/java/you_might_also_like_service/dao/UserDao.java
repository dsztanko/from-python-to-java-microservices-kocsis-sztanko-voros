package you_might_also_like_service.dao;

import you_might_also_like_service.model.User;

import java.util.ArrayList;

public interface UserDao {
    void save(String accessToken, String userID, String item);
    ArrayList selectByCartItems(String accessToken, String userId);
    User find(String accessToken, String userId);
}
