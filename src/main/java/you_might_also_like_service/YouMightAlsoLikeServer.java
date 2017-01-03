package you_might_also_like_service;

import you_might_also_like_service.controller.YouMightAlsoLikeAPIController;

import java.net.URISyntaxException;

import static spark.Spark.*;

public class YouMightAlsoLikeServer {
//    private YouMightAlsoLikeAPIController controller;

    public static void main(String[] args) {

        port(60002);

        // --- MAPPINGS ---
//        get("/api/preferences/save", application.controller::getAllEmail);
//        get("/api/preferences/select", application.controller::changeStatus);

        // --- EXCEPTION HANDLING ---
        exception(URISyntaxException.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("URI building error, maybe wrong format? : %s", exception.getMessage()));
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(String.format("Unexpected error occurred: %s", exception.getMessage()));
        });
    }
}
