import json

print json.dumps({"a": 3, "b": 4})

with open('data.json') as json_file:
    json_data = json.load(json_file)
    print json_data
    print json_data['name']
