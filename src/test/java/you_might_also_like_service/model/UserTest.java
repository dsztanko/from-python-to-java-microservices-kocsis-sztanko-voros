package you_might_also_like_service.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private User testUser;
    private HashSet<String> cart;
    private String accessToken = "access token";
    private String userId = "1234";


    @Before
    public void setUp() throws Exception {
        cart = new HashSet<>();
        cart.add("1");
        cart.add("2");
        cart.add("3");

        testUser = new User(accessToken, userId, cart);
    }

    @Test
    public void testGetItems() throws Exception {
        assertEquals(3, testUser.getItems().size());
    }

    @Test
    public void testGetAccessToken() throws Exception {
        assertEquals(accessToken, testUser.getAccessToken());
    }

    @Test
    public void testGetUserID() throws Exception {
        assertEquals(userId, testUser.getUserID());
    }

    @After
    public void tearDown() throws Exception {
        testUser = null;
        cart = null;
        accessToken = null;
        userId = null;
    }
}