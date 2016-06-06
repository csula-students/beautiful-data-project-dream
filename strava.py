#!/usr/bin/env python

from stravalib.client import Client
from stravalib import unithelper
from stravalib.util import limiter
from pymongo import MongoClient
from pymongo import errors
import requests_cache
import simplejson as json
import polyline

requests_cache.install_cache('strava_cache', backend='sqlite', expire_after=3600)

client = Client(access_token='5fc432d82f7568743ee57be5eef08c0e1eeb6182', rate_limiter=limiter.DefaultRateLimiter())
mongo = MongoClient()
db = mongo.strava

# Commands to setup mongo within the cli:
# use strava
# db.createCollection("activities")
# db.activities.createIndex( { id: 1 } )

#access_token = client.exchange_code_for_token(client_id=11245,
                                              #client_secret='752834d5dd91633194a8a97ad4af594594ea11e0',
                                              #code='33da4461dcb4e76c312b24f47c731a3956f1d469')

#client.access_token = access_token
# Angel City Brewery
#34.0411/-118.2467 34.0527/-118.2279
segments = client.explore_segments([34.0411, -118.2467, 34.0527, -118.2279])
for segment in segments:
    efforts = client.get_segment_efforts(segment_id=segment.id)
    for s in efforts:
        # Skip activities already in the mongo
        if (db.activities.find({ "id" : s.activity.id}).limit(1).count(True) == 1) :
            continue

        a = client.get_activity(s.activity.id)
        athlete = client.get_athlete(a.athlete.id)

        poly = polyline.decode(a.map.summary_polyline)

        data = {
            'athlete_id' : athlete.id,
            'athlete_name' : athlete.firstname + u' ' + athlete.lastname,
            'athlete_city' : athlete.city,
            'athlete_state' : athlete.state,
            'athlete_country' : athlete.country,
            'athlete_sex' : athlete.sex,
            'id' : a.id,
            'distance_miles' : float(unithelper.miles(a.distance)),
            'moving_time_s' : a.moving_time.seconds,
            'elapsed_time_s' : a.elapsed_time.seconds,
            'total_elevation_gain_f' : float(unithelper.feet(a.total_elevation_gain)),
            'type' : str(a.type),
            'start_date' : a.start_date.isoformat(),
            'timezone' : str(a.timezone),
            'achievement_count' : a.achievement_count,
            'kudos_count' : a.kudos_count,
            'comment_count' : a.comment_count,
            'athlete_count' : a.athlete_count,
            'photo_count' : a.photo_count,
            'commute' : a.commute,
            'manual' : a.manual,
            'average_speed_mpers' : float(a.average_speed),
            'max_speed_mpers' : float(a.max_speed),
            'start_lat' : poly[0][0],
            'start_lng' : poly[0][1],
            'end_lat' : poly[len(poly)-1][0],
            'end_lng' : poly[len(poly)-1][1],
            'map_id' : a.map.id,
            'map_summary_polyline' : a.map.summary_polyline
        }

        print data
        try:
            db.activities.insert(data)
        except errors.DuplicateKeyError, e:
            print e.message

#34.168758,-118.1607445 Pasadena: Craftsman Brewery
    #print("Start latlng: {start} Stop latlng: {stop}".format(start=activity.start_latlng, stop=activity.end_latlng))
#leaderboard = get_segment_leaderboard(segment.id, top_results_limit=segment.)
#print("Pasadena top segment: {name} {count}".format(name=segment.name, count=segment.effort_count))



#athlete

#    The associated stravalib.model.Athlete that performed this activity.
