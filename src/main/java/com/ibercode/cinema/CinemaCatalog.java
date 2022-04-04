package com.ibercode.cinema;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.ibercode.cinema.model.Cinema;
import com.ibercode.cinema.model.CinemaResponse;
import com.ibercode.cinema.utils.CommUtils;
import com.ibercode.cinema.utils.DDBUtils;
import com.ibercode.movies.Movie;

import java.util.List;

public class CinemaCatalog implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final CommUtils commUtils = new CommUtils();
    private final DDBUtils ddbUtils = new DDBUtils();
    private final Gson gson = new Gson();
    private final String CINEMAS_TABLE = System.getenv("CINEMAS_TABLE");
    private final String CINEMA_MS = System.getenv("CINEMA_MS");

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {

        APIGatewayProxyResponseEvent apiResponse = new APIGatewayProxyResponseEvent();

        String cinemaId = event.getPathParameters().get("cinemaId");

        Cinema cinema = ddbUtils.getCinemaById(cinemaId, CINEMAS_TABLE);

        // Calling Movies Microservices
        List<Movie> movies = commUtils.getMovies(cinema.getMovies(), CINEMA_MS);

        CinemaResponse response = new CinemaResponse(cinema.getName(), movies);
        apiResponse.setBody(gson.toJson(response));

        return apiResponse;
    }
}
