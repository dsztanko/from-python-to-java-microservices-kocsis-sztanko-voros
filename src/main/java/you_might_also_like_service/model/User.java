package you_might_also_like_service.model;

import java.util.HashSet;

/**
 * This class represents a User.
 *
 * @author dsztanko
 * @version 1.0
 * @since 2017-01-10
 */
public class User {
    private String accessToken;
    private String userID;
    private HashSet<String> items;

    /**
     *
     * @param accessToken uniquely identifies a web-page.
     * @param userID unique attribute which identifies a User.
     * @param items represents the User's temporary storage, the cart in case of the web-shop project.
     */
    public User(String accessToken, String userID, HashSet<String> items) {
        this.accessToken = accessToken;
        this.userID = userID;
        this.items = items;
    }

    /**
     * Gets the cart of this User.
     * @return this User's name.
     */
    public HashSet<String> getItems() {
        return items;
    }

    /**
     * Gets the accessToken (web-page identifier) of this User.
     * @return this User's accessToken.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Gets the ID of this User.
     * @return this User's ID.
     */
    public String getUserID() {
        return userID;
    }
}
