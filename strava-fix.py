#!/usr/bin/env python

from stravalib.client import Client
from stravalib import unithelper
from stravalib.util import limiter
from pymongo import MongoClient
from pymongo import errors
import requests_cache
import simplejson as json
import polyline

requests_cache.install_cache('strava_cache', backend='sqlite', expire_after=600)

client = Client(access_token='5fc432d82f7568743ee57be5eef08c0e1eeb6182', rate_limiter=limiter.DefaultRateLimiter())
mongo = MongoClient()
db = mongo.strava


# Update activities with missing map polylines
# cursor = db.activities.find({ "map_id" : { "$exists" : False} })
#
# for document in cursor:
#     a = client.get_activity(document.get("id"))
#
#     #try:
#     result = db.activities.update_one(
#         {"id" : document.get("id")},
#         {"$set" : {
#              "map_id" : a.map.id,
#              "map_summary_polyline" : a.map.summary_polyline}
#         }
#     )
#
#     print result.modified_count
#
# # Update start and end latlng with more accurate polyline points
cursor = db.activities.find({ "map_id" : { "$exists" : True} })
#
# for document in cursor:
#     poly = document.get("map_summary_polyline")
#     poly = polyline.decode(poly)
#
#     result = db.activities.update_one(
#         {"id" : document.get("id")},
#         {"$set" : {
#             "start_lat" : poly[0][0],
#             "start_lng" : poly[0][1],
#             "end_lat" : poly[len(poly)-1][0],
#             "end_lng" : poly[len(poly)-1][1]
#         }}
#     )
#
#     print result.raw_result

for document in cursor:
    poly = document.get("map_summary_polyline")
    poly = polyline.decode(poly)

    result = db.activities.update_one(
        {"id" : document.get("id")},
        {"$set" : {
            "start_latlng" : [poly[0][1], poly[0][0]],
            "stop_latlng" : [poly[len(poly)-1][1], poly[len(poly)-1][0]]
        }}
    )

    print result.raw_result
