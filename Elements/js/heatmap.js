
      // This example requires the Visualization library. Include the libraries=visualization
      // parameter when you first load the API. For example:
      // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=visualization">

      var map, heatmap;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 12,
          center: {lat: 34.03339, lng:  -118.22927},
          mapTypeId: google.maps.MapTypeId.MAP
        });

        heatmap = new google.maps.visualization.HeatmapLayer({
          data: getPoints(),
          map: map
        });
      }

      function toggleHeatmap() {
        heatmap.setMap(heatmap.getMap() ? null : map);
      }

      function changeGradient() {
        var gradient = [
          'rgba(0, 255, 255, 0)',
          'rgba(0, 255, 255, 1)',
          'rgba(0, 191, 255, 1)',
          'rgba(0, 127, 255, 1)',
          'rgba(0, 63, 255, 1)',
          'rgba(0, 0, 255, 1)',
          'rgba(0, 0, 223, 1)',
          'rgba(0, 0, 191, 1)',
          'rgba(0, 0, 159, 1)',
          'rgba(0, 0, 127, 1)',
          'rgba(63, 0, 91, 1)',
          'rgba(127, 0, 63, 1)',
          'rgba(191, 0, 31, 1)',
          'rgba(255, 0, 0, 1)'
        ]
        heatmap.set('gradient', heatmap.get('gradient') ? null : gradient);
      }

      function changeRadius() {
        heatmap.set('radius', heatmap.get('radius') ? null : 20);
      }

      function changeOpacity() {
        heatmap.set('opacity', heatmap.get('opacity') ? null : 0.2);
      }

     var request = new XMLHttpRequest();
       request.open("GET", "activities.json", false);
       request.send(null)
       var jsonData = JSON.parse(request.responseText);

       var decodedPath = google.maps.geometry.encoding.decodePath(encodedPolyline);
var decodedLevels = decodeLevels(encodedLevels);

// Decode an encoded levels string into an array of levels.
function decodeLevels(encodedLevelsString) {
  var decodedLevels = [];

  for (var i = 0; i < jsonData.activities.length; i++) {
    var level = jsonData.activities[i];
    decodedLevels.push(level);
  }

  return decodedLevels;
}
      // Heatmap data: 500 Points
      function getPoints() {
      var heatmapData = [
  new google.maps.LatLng(34.03339, -118.22927)];
        for (var i = 0; i < jsonData.activities.length; i++) {
            var startLat = jsonData.activities[i].start_lat;
            var startLng = jsonData.activities[i].start_lng;
            heatmapData.push(new google.maps.LatLng( startLat, startLng));
            }
        for (var i = 0; i < jsonData.activities.length; i++) {
            var activities = jsonData.activities[i];
            var endLat = jsonData.activities[i].end_lat;
            var endLng = jsonData.activities[i].end_lng;
            heatmapData.push(new google.maps.LatLng( endLat, endLng));
            }
        return heatmapData;


      }


