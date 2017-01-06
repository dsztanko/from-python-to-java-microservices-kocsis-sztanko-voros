package you_might_also_like_service.model;

import java.util.HashSet;

/* This class represents a User */

public class User {
    private String accessToken;
    private String userID;
    private HashSet<String> items;

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
