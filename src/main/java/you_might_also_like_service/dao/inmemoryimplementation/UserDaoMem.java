package you_might_also_like_service.dao.inmemoryimplementation;

public class UserDaoMem2 {
    private static UserDaoMem2 ourInstance = new UserDaoMem2();

    public static UserDaoMem2 getInstance() {
        return ourInstance;
    }

    private UserDaoMem2() {
    }
}
