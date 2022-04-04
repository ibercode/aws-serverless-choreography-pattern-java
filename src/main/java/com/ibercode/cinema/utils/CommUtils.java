package com.ibercode.cinema.utils;

import com.google.gson.Gson;
import com.ibercode.movies.Movie;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.util.Arrays;
import java.util.List;

public class CommUtils {

    private final LambdaClient awsLambda = LambdaClient.builder()
            .region(Region.of(System.getenv(SdkSystemSetting.AWS_REGION.environmentVariable())))
            .build();
    private final Gson gson = new Gson();

    public List<Movie> getMovies(List<String> movieIds, String functionName) {

        SdkBytes payload = SdkBytes.fromUtf8String(gson.toJson(movieIds)) ;
        InvokeRequest request = InvokeRequest.builder()
                .functionName(functionName)
                .payload(payload)
                .build();

        InvokeResponse response = awsLambda.invoke(request);
        String value = response.payload().asUtf8String() ;

        Movie[] movies = gson.fromJson(value, Movie[].class);

        return Arrays.asList(movies);
    }
}
