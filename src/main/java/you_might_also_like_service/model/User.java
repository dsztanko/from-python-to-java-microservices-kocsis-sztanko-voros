package you_might_also_like_service.model;

import java.util.ArrayList;

public class User {
    private String accessToken, userID;
    private ArrayList<String> items;

    public User(String accessToken, String userID, ArrayList<String> items) {
        this.accessToken = accessToken;
        this.userID = userID;
        this.items = items;
    }

    public ArrayList getItems() {
        return items;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getUserID() {
        return userID;
    }
}
