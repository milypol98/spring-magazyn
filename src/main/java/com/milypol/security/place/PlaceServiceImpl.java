package com.milypol.security.place;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService{
    private final PlaceRepo placeRepo;

    public PlaceServiceImpl(PlaceRepo placeRepo) {
        this.placeRepo = placeRepo;
    }

    @Override
    public List<Place> getAllPlaces() {
        return placeRepo.findAll();
    }

    @Override
    public Place getPlaceById(Integer id) {
        return placeRepo.findById(id).orElseThrow(() -> new RuntimeException("Place not found"));
    }

    @Override
    public Place savePlace(Place place) {
        return placeRepo.save(place);
    }

    @Override
    public void deletePlace(Integer id) {
        placeRepo.deleteById(id);
    }
}
