package com.milypol.security.controller;

import com.milypol.security.place.Place;
import com.milypol.security.place.PlaceService;
import jdk.jfr.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/places")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }
    @GetMapping
    public String showPlacePage(Model model){
        model.addAttribute("places", placeService.getAllPlaces());
        return "place";
    }
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("place", new Place());
        return "places/edit";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model){
        model.addAttribute("place", placeService.getPlaceById(id));
        return "places/edit";
    }
    @PostMapping("/save")
    public String savePlace(@ModelAttribute Place place){
        placeService.savePlace(place);
        return "redirect:/places";
    }
    @PostMapping("/delete/{id}")
    public String deletePlace(@PathVariable Integer id){
        placeService.deletePlace(id);
        return "redirect:/places";
    }

}
