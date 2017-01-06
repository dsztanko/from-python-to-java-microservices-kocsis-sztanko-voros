package you_might_also_like_service.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import you_might_also_like_service.model.User;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

abstract class UserDaoTest {
    protected UserDao userDao;

    private HashSet<String> cart = new HashSet<>(Arrays.asList("1", "2"));
    protected User testUser = new User("accessToken", "1", cart);

    @Test
    public void saveNewUser() throws Exception {
    }

    @Test
    public void updateAlreadyExistingUser() throws Exception {
    }

    @Test
    public void testContainsOneOfTheSpecItemsAtLeast() throws Exception {

    }

    @Test
    public void testFind() throws Exception {

    }

    @Test
    public void testSelectUniqueItems() throws Exception {

    }

}