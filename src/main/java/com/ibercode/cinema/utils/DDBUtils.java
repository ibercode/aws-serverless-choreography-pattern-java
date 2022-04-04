package com.ibercode.cinema.utils;

import com.ibercode.cinema.model.Cinema;
import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DDBUtils {

    private final DynamoDbClient ddb = DynamoDbClient.builder()
            .region(Region.of(System.getenv(SdkSystemSetting.AWS_REGION.environmentVariable())))
            .build();
    private final DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(ddb)
            .build();

    public Cinema getCinemaById(String cinemaId, String tableName) {

        DynamoDbTable<Cinema> mappedTable = enhancedClient.table(tableName, TableSchema.fromBean(Cinema.class));
        Key key = Key.builder().partitionValue(cinemaId).build();
        Cinema cinema = mappedTable.getItem(r->r.key(key));

        return cinema;
    }
}
