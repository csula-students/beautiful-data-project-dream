package edu.csula.datascience.acquisition;

import javastrava.api.v3.auth.AuthorisationService;
import javastrava.api.v3.auth.impl.retrofit.AuthorisationServiceImpl;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.service.Strava;
import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.engine.control.CompositeCacheManager;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jaime on 5/1/16.
 */
public class StravaAthleteCollector implements Collector<StravaAthlete, StravaAthlete> {
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    public StravaAthleteCollector() {
        // establish database connection to MongoDB
        mongoClient = new MongoClient();

        // select `bd-example` as testing database
        database = mongoClient.getDatabase("strava");

        // select collection by name `tweets`
        collection = database.getCollection("athletes");
    }

    @Override
    public Collection<StravaAthlete> mungee(Collection<StravaAthlete> src) {
        return src;
    }

    @Override
    public void save(Collection<StravaAthlete> athlete) {
        List<Document> documents = athlete.stream()
                .map(item -> new Document()
                        .append("id", item.getId())
                        .append("firstname", item.getFirstname())
                        .append("lastname", item.getLastname())
                        .append("city", item.getCity())
                        .append("state", item.getState())
                        .append("country", item.getCountry()))
                .collect(Collectors.toList());

        collection.insertMany(documents);
    }
}
