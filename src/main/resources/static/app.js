document.addEventListener('DOMContentLoaded', function () {
  if (document.querySelectorAll('#map').length > 0)
  {
    if (document.querySelector('html').lang)
      lang = document.querySelector('html').lang;
    else
      lang = 'en';

    var js_file = document.createElement('script');
    js_file.type = 'text/javascript';
    js_file.src = 'https://maps.googleapis.com/maps/api/js?callback=initMap&signed_in=true&language=' + lang;
    document.getElementsByTagName('head')[0].appendChild(js_file);
  }
});

var map;

function initMap()
{
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -34.397, lng: 150.644},
    zoom: 8
  });

  fetch('https://raw.githubusercontent.com/jayshields/google-maps-api-template/master/markers.json')
    .then(function(response){return response.json()})
    .then(plotMarkers);
}
var markers;
var bounds;

function plotMarkers(m)
{
  markers = [];
  bounds = new google.maps.LatLngBounds();

  m.forEach(function (marker) {
    var position = new google.maps.LatLng(marker.lat, marker.lng);

    markers.push(
      new google.maps.Marker({
        position: position,
        map: map,
        animation: google.maps.Animation.DROP
      })
    );

    bounds.extend(position);
  });
  map.fitBounds(bounds);
}

$(document).ready(function () {
  $.get("countries/getAll", function (data) {
    $('#mainBody').html('');
    $('#confirmed').html('');
    $('#recovered').html('');
    $('#deaths').html('');
    let index = 0;
    $.map(data, (val) => {
      let tdc = '<td>' + val.confirmed + '</td>'
      let tdr = '<td>' + val.recovered +  '</td>'
      let tdd = '<td>' + val.deaths + '</td>'
      let th = '<th scope="row">' + val.country + '</th>'
      let tr = '<tr id="' + 'id' + index + '">' + th + tdd + tdc + tdr + '</tr>'
      $('#mainBody').append(tr);
      $('#id' + index).click(function (val) {
        let cnt = String(val.currentTarget.cells[0].innerText);
        $('#country').html(cnt);
        $('#confirmed').html(val.currentTarget.cells[1].innerText);
        $('#recovered').html(val.currentTarget.cells[2].innerText);
        $('#deaths').html(val.currentTarget.cells[3].innerText);
        $.get("countries/" + cnt, function (cities) {
          $('#cityBody').html('');
          $.map(cities, (val) => {
            let tdc2 = '<td>' + val.confirmed + '</td>'
            let tdr2 = '<td>' + val.recovered + '</td>'
            let tdd2 = '<td>' + val.deaths + '</td>'
            let th2 = '<th scope="row">' + val.country + '</th>'
            let tr2 = '<tr id="' + 'id' + index + '">' + th2 + tdd2 + tdc2 + tdr2 + '</tr>'
            $('#cityBody').append(tr2);
          });
        });
      });
      index += 1;
    })

  });

});