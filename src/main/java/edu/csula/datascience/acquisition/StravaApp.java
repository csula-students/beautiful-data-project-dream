package edu.csula.datascience.acquisition;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javastrava.api.v3.model.StravaAthlete;
import org.apache.commons.jcs.engine.control.CompositeCacheManager;
import org.bson.Document;

import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by jaime on 5/1/16.
 */
public class StravaApp {

    public static void main(String[] args) {
        initCache();
        StravaAthleteSource source = new StravaAthleteSource(6508576);
        StravaAthleteCollector collector = new StravaAthleteCollector();

        //while (source.hasNext()) {
            Collection<StravaAthlete> athletes = source.next();
            Collection<StravaAthlete> cleanedAthletes = collector.mungee(athletes);
            collector.save(cleanedAthletes);
        //}
    }

    public static void initCache() {
        CompositeCacheManager ccm = CompositeCacheManager.getUnconfiguredInstance();
        Properties props = new Properties();

        props.put("jcs.default", "");
        props.put("jcs.default.cacheattributes",
                "org.apache.commons.jcs.engine.CompositeCacheAttributes");
        props.put("jcs.default.cacheattributes.MaxObjects", "200001");
        props.put("jcs.default.cacheattributes.MemoryCacheName",
                "org.apache.commons.jcs.engine.memory.lru.LRUMemoryCache");
        props.put("jcs.default.cacheattributes.MemoryCacheName",
                "org.apache.commons.jcs.engine.memory.lru.LRUMemoryCache");
        props.put("jcs.default.cacheattributes.UseMemoryShrinker", "true");
        props.put("jcs.default.cacheattributes.MaxMemoryIdleTime", "3600");
        props.put("jcs.default.cacheattributes.ShrinkerInterval", "60");
        props.put("jcs.default.elementattributes",
                "org.apache.commons.jcs.engine.ElementAttributes");
        props.put("jcs.default.elementattributes.IsEternal", "false");
        props.put("jcs.default.elementattributes.MaxLife", "3600");
        props.put("jcs.default.elementattributes.IsSpool", "false");
        props.put("jcs.default.elementattributes.IsRemote", "false");
        props.put("jcs.default.elementattributes.IsLateral", "false");
        ccm.configure(props);
        //JCS sessionCache = JCS.getInstance("jcs.default");
    }
}
