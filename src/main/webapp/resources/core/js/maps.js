function initMap() {

    // Coordenadas de origen y destino
    var mapElement = document.getElementById('mapa');
    var origen = mapElement.getAttribute('data-origen');
    var destino = mapElement.getAttribute('data-destino');

    var latitudOrigen = parseFloat(origen.split(',')[0]);
    var longitudOrigen = parseFloat(origen.split(',')[1]);
    var latitudDestino = parseFloat(destino.split(',')[0]);
    var longitudDestino = parseFloat(destino.split(',')[1]);

    var origenLatLong = new google.maps.LatLng(latitudOrigen, longitudOrigen);
    var destinoLatLong = new google.maps.LatLng(latitudDestino, longitudDestino);
    console.log(origenLatLong)
    console.log(destinoLatLong)


    var mapa = new google.maps.Map(document.getElementById('mapa'), {
        center: { lat: (origenLatLong[0] + destinoLatLong[0]) / 2, lng: (origenLatLong[1] + destinoLatLong[1]) / 2 },
        zoom: 8
    });

    var directionsService = new google.maps.DirectionsService();
    var directionsDisplay = new google.maps.DirectionsRenderer({ map: mapa });

    var request = {
        origin: origenLatLong,
        destination: destinoLatLong,
        travelMode: google.maps.TravelMode.DRIVING

    };

    directionsService.route(request, function(result, status) {
        if (status == 'OK') {
            directionsDisplay.setDirections(result);
        }
    });
    directionsService.route(request, function(result, status) {
        if (status == 'OK') {
            directionsDisplay.setDirections(result);


            var route = result.routes[0];
            var leg = route.legs[0];

            var distancia = leg.distance.text;
            var duracion = leg.duration.text;

            document.getElementById('distancia').textContent = distancia;
            document.getElementById('duracion').textContent = duracion;

            // Ahora puedes hacer lo que quieras con la distancia y la duración
            console.log('Distancia: ' + distancia);
            console.log('Duración: ' + duracion);

        }
    });
}
// Llama a initMap cuando la API de Google Maps esté cargada
function loadMap() {
    initMap();
}



