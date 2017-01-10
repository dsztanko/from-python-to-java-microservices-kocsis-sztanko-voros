package you_might_also_like_service.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import you_might_also_like_service.dao.UserDao;
import you_might_also_like_service.dao.inmemoryimplementation.UserDaoMem;
import you_might_also_like_service.model.User;

import java.util.*;

/**
 * This class contains mainly the logic
 * of YouMightAlsoLikeAPIService.
 *
 * @author dsztanko
 * @version 1.0
 * @since 2017-01-10
 * @see you_might_also_like_service.YouMightAlsoLikeServer
 * @see you_might_also_like_service.controller.YouMightAlsoLikeAPIController
 */
public class YouMightAlsoLikeAPIService {
    private static final Logger logger = LoggerFactory.getLogger(YouMightAlsoLikeAPIService.class);

    private static YouMightAlsoLikeAPIService ourInstance = new YouMightAlsoLikeAPIService();
    private UserDao userDao;

    private static final int BASE_INDEX = 3;
    private static final int RECOMMENDATION_RANGE = 4;

    /**
     * Gets the only existing YouMightAlsoLikeAPIService object - serving singleton pattern.
     * @return only instance of YouMightAlsoLikeAPIService
     */
    public static YouMightAlsoLikeAPIService getInstance() {
        return ourInstance;
    }

    private YouMightAlsoLikeAPIService() {
        userDao = UserDaoMem.getInstance();
    }

    /**
     * Saving User object into storage.
     *
     * @param accessToken
     * @param userID
     * @param item ID given as newly added item/product.
     */
    public void saveUser(String accessToken, String userID, String item) {
        userDao.save(accessToken, userID, item);
        logger.debug(">>>>> User with the following credentials saved: Access Token: {}, User ID: {}, Item: {}", accessToken, userID, item);
        logger.info(">>>>> User saved");
    }

    /**
     * Main purpose of the whole microservice placed here.
     * Recommendations are handed over as an answer to the
     * request.
     *
     * @param accessToken
     * @param userId
     * @return JSON with keyword 'recommendation'.
     * Example:
     *      {
     *          "recommendations": ["5", "8", "2"]
     *      }
     * Result is sorted.
     * The most important item is the first one.
     * Number of sent-back items are maximized in 3.
     */
    public JSONObject getRecommendations(String accessToken, String userId) {
        logger.info(">>>>> Recommendation requested");

        JSONObject json;
        User specUser = userDao.find(accessToken, userId);
        logger.debug(">>>>> SpecUser found: {}", userDao.find(accessToken, userId));
        ArrayList<User> allUsers = userDao.containsOneOfTheSpecItemsAtLeast(accessToken, userId);
        logger.debug(">>>>> Users with carts containing specUser's items at leans once in their own pocket.");
        HashMap<String, Integer> uniqueItems = userDao.selectUniqueItems(specUser.getAccessToken(), specUser);
        logger.debug(">>>>> Unique items selected in HashMap: {}", uniqueItems);

        logger.info(">>>>> Calculation of recommendations just started.");
        for (User user : allUsers) {
            Integer power = -1;
            for (String item : user.getItems()) {
                if (specUser.getItems().contains(item)) {
                    power++;
                }
            }
            for (String item : user.getItems()) {
                if (!specUser.getItems().contains(item)) {
                    uniqueItems.put(item, (int) (uniqueItems.get(item) + Math.pow(BASE_INDEX, power)));
                }
            }
        }

        Map<String, Integer> resultHM = new LinkedHashMap<>();
        logger.debug(">>>>> Unsorted resultSet: {}", resultHM);

        uniqueItems.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> resultHM.put(x.getKey(), x.getValue()));
        logger.debug(">>>>> Sorted resultSet returned: {}", resultHM);

        Set resultS = resultHM.keySet();
        Integer range = (resultS.size() < RECOMMENDATION_RANGE) ? resultS.size() : RECOMMENDATION_RANGE;
        json = new JSONObject().put("recommendations", new ArrayList<>(resultS).subList(0, range));
        logger.debug(">>>>> Following JSON returned: {}", json);
        logger.info(">>>>> Recommendation returned.");
        return json;
    }
}
