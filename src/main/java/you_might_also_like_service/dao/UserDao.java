package you_might_also_like_service.dao;

import you_might_also_like_service.model.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This interface is used to simplify User storage logic.
 * One implementation is available: saving in memory
 * Optional: JDBC (saving in database)
 *
 * @author  dsztanko
 * @version 1.0
 * @since   2017-01-10
 * @see User
 * @see you_might_also_like_service.dao.inmemoryimplementation.UserDaoMem
 * @see you_might_also_like_service.dao.JDBCimplementation.UserDaoJDBC

 */
public interface UserDao {
    /**
     *
     * @param accessToken
     * @param userID
     * @param item
     */
    void save(String accessToken, String userID, String item);

    /**
     *
     * @param accessToken
     * @param userId
     * @return
     */
    ArrayList containsOneOfTheSpecItemsAtLeast(String accessToken, String userId);

    /**
     *
     * @param accessToken
     * @param userId
     * @return
     */
    User find(String accessToken, String userId);

    /**
     *
     * @param accessToken
     * @param specUser refers to the reference User
     * @return
     */
    HashMap<String, Integer> selectUniqueItems(String accessToken, User specUser);
}
