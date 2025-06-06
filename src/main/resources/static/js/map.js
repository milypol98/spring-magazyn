let map;
let markers = [];

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 22.2297, lng: 21.0122 },
        zoom: 3,
    });
}

function clearMarkers() {
    markers.forEach(m => m.setMap(null));
    markers = [];
}

function addMarkers(places) {
    clearMarkers();
    places.forEach(place => {
        const marker = new google.maps.Marker({
            position: { lat: place.latitude, lng: place.longitude },
            map: map,
            title: place.name,
        });
        const infoWindow = new google.maps.InfoWindow({
            content: `<h3>${place.name}</h3><p>${place.address}</p><p>${place.description}</p>`,
        });
        marker.addListener("click", () => infoWindow.open(map, marker));
        markers.push(marker);
    });

    if (places.length > 0) {
        map.setCenter({ lat: places[0].latitude, lng: places[0].longitude });
        map.setZoom(4);
    }
}

document.getElementById("showPlacesBtn").addEventListener("click", () => {
    // Pobieramy dane z atrybutu data-places
    const placesData = JSON.parse(document.getElementById("showPlacesBtn").getAttribute("data-places"));
    addMarkers(placesData);
});