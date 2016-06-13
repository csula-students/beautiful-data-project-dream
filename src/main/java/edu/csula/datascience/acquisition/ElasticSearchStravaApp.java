package edu.csula.datascience.acquisition;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.fieldstats.FieldStats;
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
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("strava");
        MongoCollection<Document> collection = database.getCollection("activities");

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

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {

                Document obj = cursor.next();

                Activities act = new Activities(
                        obj.getInteger("id"),
                        obj.getInteger("athlete_id"),
                        obj.getString("athlete_name"),
                        obj.getString("athlete_city"),
                        obj.getString("athlete_state"),
                        obj.getString("athlete_country"),
                        obj.getString("athlete_sex"),
                        obj.getDouble("distance_miles"),
                        obj.getInteger("moving_time_s"),
                        obj.getInteger("elapsed_time_s"),
                        obj.getDouble("total_elevation_gain_f"),
                        obj.getString("type"),
                        obj.getString("start_date"),
                        obj.getString("timezone"),
                        obj.getInteger("achievement_count"),
                        obj.getInteger("kudos_count"),
                        obj.getInteger("comment_count"),
                        obj.getInteger("athlete_count"),
                        obj.getInteger("photo_count"),
                        obj.getBoolean("commute"),
                        obj.getBoolean("manual"),
                        obj.getDouble("average_speed_mpers"),
                        obj.getDouble("max_speed_mpers"),
                        obj.getDouble("start_lat"),
                        obj.getDouble("start_lng"),
                        obj.getDouble("end_lat"),
                        obj.getDouble("end_lng"),
                        (List<Double>)obj.get("start_latlng"),
                        (List<Double>)obj.get("end_latlng"),
                        obj.getString("map_id"),
                        obj.getString("map_summary_polyline"));


                bulkProcessor.add(new IndexRequest(indexName, typeName)
                        .source(gson.toJson(act))
                );
            }
        }
    }

    static class Activities {
        final Integer id;
        final Integer athlete_id;
        final String athlete_name;
        final String athlete_city;
        final String athlete_state;
        final String athlete_country;
        final String athlete_sex;
        final double distance_miles;
        final Integer moving_time_s;
        final Integer elapsed_time_s;
        final double total_elevation_gain_f;
        final String type;
        final String start_date;
        final String timezone;
        final Integer achievement_count;
        final Integer kudos_count;
        final Integer comment_count;
        final Integer athlete_count;
        final Integer photo_count;
        final Boolean commute;
        final Boolean manual;
        final double average_speed_mpers;
        final double max_speed_mpers;
        final double start_lat;
        final double start_lng;
        final double end_lat;
        final double end_lng;
        final List<Double> start_latlng;
        final List<Double> end_latlng;
        final String map_id;
        final String map_summary_polyline;

        public Activities(Integer id, Integer athlete_id,
                          String athlete_name,
                          String athlete_city,
                          String athlete_state,
                          String athlete_country,
                          String athlete_sex,
                          double distance_miles,
                          Integer moving_time_s,
                          Integer elapsed_time_s,
                          double total_elevation_gain_f,
                          String type,
                          String start_date,
                          String timezone,
                          Integer achievement_count,
                          Integer kudos_count,
                          Integer comment_count,
                          Integer athlete_count,
                          Integer photo_count,
                          Boolean commute,
                          Boolean manual,
                          double average_speed_mpers,
                          double max_speed_mpers,
                          double start_lat,
                          double start_lng,
                          double end_lat,
                          double end_lng,
                          List<Double> start_latlng,
                          List<Double> end_latlng,
                          String map_id,
                          String map_summary_polyline) {
            this.id = id;
            this.athlete_id = athlete_id;
            this.athlete_name = athlete_name;
            this.athlete_city = athlete_city;
            this.athlete_state = athlete_state;
            this.athlete_country = athlete_country;
            this.athlete_sex = athlete_sex;
            this.distance_miles = distance_miles;
            this.moving_time_s = moving_time_s;
            this.elapsed_time_s = elapsed_time_s;
            this.total_elevation_gain_f = total_elevation_gain_f;
            this.type = type;
            this.start_date = start_date;
            this.timezone = timezone;
            this.achievement_count = achievement_count;
            this.kudos_count = kudos_count;
            this.comment_count = comment_count;
            this.athlete_count = athlete_count;
            this.photo_count = photo_count;
            this.commute = commute;
            this.manual = manual;
            this.average_speed_mpers = average_speed_mpers;
            this.max_speed_mpers = max_speed_mpers;
            this.start_lat = start_lat;
            this.start_lng = start_lng;
            this.end_lat = end_lat;
            this.end_lng = end_lng;
            this.start_latlng = start_latlng;
            this.end_latlng = end_latlng;
            this.map_id = map_id;
            this.map_summary_polyline = map_summary_polyline;
        }

        public Integer getId() {
            return id;
        }

        public Integer getAthlete_id() {
            return athlete_id;
        }

        public String getAthlete_name() {
            return athlete_name;
        }

        public String getAthlete_city() {
            return athlete_city;
        }

        public String getAthlete_state() {
            return athlete_state;
        }

        public String getAthlete_country() {
            return athlete_country;
        }

        public String getAthlete_sex() {
            return athlete_sex;
        }

        public double getDistance_miles() {
            return distance_miles;
        }

        public Integer getMoving_time_s() {
            return moving_time_s;
        }

        public Integer getElapsed_time_s() {
            return elapsed_time_s;
        }

        public double getTotal_elevation_gain_f() {
            return total_elevation_gain_f;
        }

        public String getType() {
            return type;
        }

        public String getStart_date() {
            return start_date;
        }

        public String getTimezone() {
            return timezone;
        }

        public Integer getAchievement_count() {
            return achievement_count;
        }

        public Integer getKudos_count() {
            return kudos_count;
        }

        public Integer getComment_count() {
            return comment_count;
        }

        public Integer getAthlete_count() {
            return athlete_count;
        }

        public Integer getPhoto_count() {
            return photo_count;
        }

        public Boolean getCommute() {
            return commute;
        }

        public Boolean getManual() {
            return manual;
        }

        public double getAverage_speed_mpers() {
            return average_speed_mpers;
        }

        public double getMax_speed_mpers() {
            return max_speed_mpers;
        }

        public double getStart_lat() {
            return start_lat;
        }

        public double getStart_lng() {
            return start_lng;
        }

        public double getEnd_lat() {
            return end_lat;
        }

        public double getEnd_lng() {
            return end_lng;
        }

        public String getMap_id() {
            return map_id;
        }

        public String getMap_summary_polyline() {
            return map_summary_polyline;
        }
    }
}