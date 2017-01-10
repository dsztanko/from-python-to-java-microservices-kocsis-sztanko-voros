package you_might_also_like_service.dao.inmemoryimplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import you_might_also_like_service.dao.UserDao;
import you_might_also_like_service.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Implementation which stores in computer's memory.
 *
 * @author  dsztanko
 * @version 1.0
 * @since   2017-01-10
 * @see User
 * @see UserDao
 */
public class UserDaoMem implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoMem.class);
    private ArrayList<User> DATA;
    private static UserDaoMem ourInstance = new UserDaoMem();

    /**
     * Private constructor which is responsible for initializing
     * memory storage in shape of an ArrayList.
     */
    private UserDaoMem() {
        DATA = new ArrayList<>();
    }

    /**
     * Gets the only existing UserDaoMem object - serving singleton pattern.
     * @return only instance of UserDaoMem
     */
    public static UserDaoMem getInstance() {
        return ourInstance;
    }

    /**
     * Finds specUser in memory storage - User, whose cart is the benchmark
     * as opposed to the others'.
     *
     * @param accessToken
     * @param userId
     * @return User - found in memory storage by id.
     */
    @Override
    public User find(String accessToken, String userId) {
        logger.info(">>>>> Looking for a certain User in the storage.");
        logger.debug(">>>>> Credentials: Access Token: {}, User ID: {}", accessToken, userId);

        User user = null;
        for (User element : DATA) {
            if (element.getAccessToken().equals(accessToken) && element.getUserID().equals(userId)) {
                user = element;
                logger.info(">>>>> Following user found: {}", userId);
            }
        }
        return user;
    }

    /**
     * Saves user into memory storage.
     *
     * @param accessToken
     * @param userID
     * @param item
     */
    @Override
    public void save(String accessToken, String userID, String item) {
        logger.info(">>>>> Saving process just started.");

        User user = find(accessToken, userID);
        if (user == null) {
            logger.info(">>>>> New User added to storage.");
            user = new User(accessToken, userID, new HashSet<>(Arrays.asList(item)));
            DATA.add(user);
        } else {
            logger.info(">>>>> User already exists in storage. Updating cart with new item is done.");
            user.getItems().add(item);
        }
    }

    /**
     * Selects all the Users from memory storage
     * whose cart items
     * contains one of the specUser's (reference User) cartItems - at least once.
     *
     * @param accessToken
     * @param userId
     * @return
     */
    @Override
    public ArrayList<User> containsOneOfTheSpecItemsAtLeast(String accessToken, String userId) {
        logger.info(">>>>> Collecting all users, whose cart contains one of the specUser's items just started.");

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
            logger.warn("No access token and user ID matched during searching in storage.");
        }
        logger.debug("All users selected: {}", users);
        return users;
    }

    /**
     * Selects all items that are unique among Users
     * selected by containsOneOfTheSpecItemsAtLeast() method.
     *
     * @param accessToken
     * @param specUser refers to the reference User
     * @return
     */
    @Override
    public HashMap<String, Integer> selectUniqueItems(String accessToken, User specUser) {
        logger.info(">>>>> Selecting unique items just started.");

        HashSet<String> uniqueItemsSet = new HashSet<>();
        for (User user : containsOneOfTheSpecItemsAtLeast(accessToken, specUser.getUserID())) {
            if (user.getAccessToken().equals(accessToken)) {
                uniqueItemsSet.addAll(user.getItems());
            }
        }

        HashMap<String, Integer> uniqueItemsMap = new HashMap<>();
        for (String id : uniqueItemsSet) {
            if (!specUser.getItems().contains(id)) {
                uniqueItemsMap.put(id, 0);
            }
        }
        logger.debug(">>>>> Unique items and their importance: {}", uniqueItemsMap);
        return uniqueItemsMap;
    }
}
