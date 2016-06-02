package edu.csula.datascience.acquisition;

import com.google.gson.Gson;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import com.google.common.collect.Lists;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Sorts.descending;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * A quick elastic search example app
 *
 * It will parse the csv file from the resource folder under main and send these
 * data to elastic search instance running locally
 *
 * After that we will be using elastic search to do full text search
 *
 * gradle command to run this app `gradle stravaES`
 */
public class ElasticSearchStravaApp {
    private final static String indexName = "strava-data";
    private final static String typeName = "activities";

    public static void main(String[] args) throws URISyntaxException, IOException {
        Node node = nodeBuilder().settings(Settings.builder()
                .put("cluster.name", "jaime")
                .put("path.home", "elasticsearch-data")).node();
        Client client = node.client();

        /**
         *
         *
         * INSERT data to elastic search
         */

        // create bulk processor
        BulkProcessor bulkProcessor = BulkProcessor.builder(
                client,
                new BulkProcessor.Listener() {
                    @Override
                    public void beforeBulk(long executionId,
                                           BulkRequest request) {
                    }

                    @Override
                    public void afterBulk(long executionId,
                                          BulkRequest request,
                                          BulkResponse response) {
                    }

                    @Override
                    public void afterBulk(long executionId,
                                          BulkRequest request,
                                          Throwable failure) {
                        System.out.println("Facing error while importing data to elastic search");
                        failure.printStackTrace();
                    }
                })
                .setBulkActions(10000)
                .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                .setConcurrentRequests(1)
                .setBackoffPolicy(
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();

        // Gson library for sending json to elastic search
        Gson gson = new Gson();

        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("strava");
        MongoCollection<Document> collection = database.getCollection("activities");

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                bulkProcessor.add(new IndexRequest(indexName, typeName)
                        .source(cursor.next().toJson())
                );
            }
        }


        /**
         * Structured search
         */

        // simple search by field name "state" and find Washington
//        SearchResponse response = client.prepareSearch(indexName)
//            .setTypes(typeName)
//            .setSearchType(SearchType.DEFAULT)
//            .setQuery(QueryBuilders.matchQuery("state", "Washington"))   // Query
//            .setScroll(new TimeValue(60000))
//            .setSize(60).setExplain(true)
//            .execute()
//            .actionGet();
//
//        //Scroll until no hits are returned
//        while (true) {
//
//            for (SearchHit hit : response.getHits().getHits()) {
//                System.out.println(hit.sourceAsString());
//            }
//            response = client
//                .prepareSearchScroll(response.getScrollId())
//                .setScroll(new TimeValue(60000))
//                .execute()
//                .actionGet();
//            //Break condition: No hits are returned
//            if (response.getHits().getHits().length == 0) {
//                break;
//            }
//        }
//
//        /**
//         * AGGREGATION
//         */
//        SearchResponse sr = node.client().prepareSearch(indexName)
//            .setTypes(typeName)
//            .setQuery(QueryBuilders.matchAllQuery())
//            .addAggregation(
//                AggregationBuilders.terms("stateAgg").field("state")
//                    .size(Integer.MAX_VALUE)
//            )
//            .execute().actionGet();
//
//        // Get your facet results
//        Terms agg1 = sr.getAggregations().get("stateAgg");
//
//        for (Terms.Bucket bucket: agg1.getBuckets()) {
//            System.out.println(bucket.getKey() + ": " + bucket.getDocCount());
//        }
    }

    static class Activities {

        final String date;
        final double averageTemperature;
        final String state;
        final String country;

        public Temperature(String date, double averageTemperature, String state, String country) {
            this.date = date;
            this.averageTemperature = averageTemperature;
            this.state = state;
            this.country = country;
        }
    }
}