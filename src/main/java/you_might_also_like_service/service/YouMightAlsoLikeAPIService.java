package you_might_also_like_service.service;

import you_might_also_like_service.dao.UserDao;
import you_might_also_like_service.dao.inmemoryimplementation.UserDaoMem;
import you_might_also_like_service.model.User;

import java.util.ArrayList;

public class YouMightAlsoLikeAPIService {
    private static YouMightAlsoLikeAPIService ourInstance = new YouMightAlsoLikeAPIService();

    public static YouMightAlsoLikeAPIService getInstance() {
        return ourInstance;
    }

    private UserDao userDao;

    private YouMightAlsoLikeAPIService() {
        userDao = UserDaoMem.getInstance();
    }

    public void saveUser(User user) {
        userDao.save(user);
    }

    public ArrayList getAllByItems(ArrayList items) {
        return userDao.selectByCartItems(items);
    }
}