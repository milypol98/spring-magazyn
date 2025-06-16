package com.milypol.security.place;

import java.util.List;

public interface PlaceService {
    List<Place> getAllPlaces();
    Place getPlaceById(Integer id);
    Place savePlace(Place place);
    void deletePlace(Integer id);
}
