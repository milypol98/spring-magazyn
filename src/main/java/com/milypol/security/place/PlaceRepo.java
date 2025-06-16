package com.milypol.security.place;

import com.milypol.security.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepo extends JpaRepository<Place, Integer> {
}
