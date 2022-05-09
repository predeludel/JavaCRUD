package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.User;
import com.example.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class CityController {
    @Autowired
    CityRepository cityRepository;

    @GetMapping("/cities")
    public Iterable<City> read() {
        return cityRepository.findAll();
    }

    @PostMapping("/cities")
    public City create(@RequestBody City city) {
        return cityRepository.save(city);
    }

    @DeleteMapping("/cities/{id}")
    public Boolean delete(@PathVariable Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    @PutMapping("/cities")
    public City update(@RequestBody City newCity){
        if (cityRepository.findById(newCity.getId()).isPresent()){
            City oldCity = cityRepository.findById(newCity.getId()).get();
            oldCity.setName(newCity.getName());
            return cityRepository.save(oldCity);
        } else{
            return cityRepository.save(newCity);
        }
    }
    @GetMapping("/cities/size/{size}")
    public Iterable<City> read(@PathVariable Integer size) {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false).limit(size).collect(Collectors.toList());
    }

}
