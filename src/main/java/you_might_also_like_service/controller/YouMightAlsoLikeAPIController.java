package you_might_also_like_service.controller;

import spark.Request;
import spark.Response;
import you_might_also_like_service.model.User;
import you_might_also_like_service.service.YouMightAlsoLikeAPIService;

public class YouMightAlsoLikeAPIController {
    public static final String ACCESS_TOKEN_PARAM_KEY = "accessToken";
    public static final String USER_ID_PARAM_KEY = "userId";
    public static final String CART_ITEM_ID_PARAM_KEY = "cartItemId";

    private final YouMightAlsoLikeAPIService apiService;

    public YouMightAlsoLikeAPIController(YouMightAlsoLikeAPIService apiService) {
        this.apiService = apiService;
    }

    public String status(Request request, Response response) {
        return "ok";
    }

    public String saveToDao(Request request, Response response) {
        String accessToken = request.queryParams(ACCESS_TOKEN_PARAM_KEY);
        String userId = request.queryParams(USER_ID_PARAM_KEY);
        String cartItemId = request.queryParams(CART_ITEM_ID_PARAM_KEY);
        return null;
//        return apiService.saveUser(accessToken, userId, cartItemId);
    }

    public String selectByCartItemsFromDao(Request request, Response response) {
        return "select by";
    }
}
