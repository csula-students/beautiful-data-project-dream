package edu.csula.datascience.acquisition;

import com.google.common.collect.Lists;

import javastrava.api.v3.auth.AuthorisationService;
import javastrava.api.v3.auth.impl.retrofit.AuthorisationServiceImpl;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaAthlete;
import javastrava.api.v3.service.Strava;
import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.engine.control.CompositeCacheManager;
import java.util.Properties;

import java.util.Collection;
import java.util.List;

/**
 * An example of Source implementation using Twitter4j api to grab tweets
 */
public class StravaAthleteSource implements Source<StravaAthlete> {
    private Integer athleteID;

    public StravaAthleteSource(Integer id) { athleteID = id;}

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Collection<StravaAthlete> next() {
        List<StravaAthlete> list = Lists.newArrayList();

        AuthorisationService service = new AuthorisationServiceImpl();
        // Authorize through
        Token token = service.tokenExchange(11245, "752834d5dd91633194a8a97ad4af594594ea11e0", "33da4461dcb4e76c312b24f47c731a3956f1d469");
        Strava strava = new Strava(token);

        StravaAthlete athlete = strava.getAthlete(athleteID);
        list.add(athlete);

        return list;
    }
}
