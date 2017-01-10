package you_might_also_like_service.model;

import java.util.HashSet;

/**
 * This class represents a User.
 *
 * @author  dsztanko
 * @version 1.0
 * @since   2017-01-10
 */
public class User {
    private String accessToken;
    private String userID;
    private HashSet<String> items;

    /**
     *
     * @param accessToken uniquely identifies a web-page
     * @param userID unique attribute which identifies a User
     * @param items represents the User's temporary storage, the cart in this case
     */
    public User(String accessToken, String userID, HashSet<String> items) {
        this.accessToken = accessToken;
        this.userID = userID;
        this.items = items;
    }

    public HashSet<String> getItems() {
        return items;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getUserID() {
        return userID;
    }
}
