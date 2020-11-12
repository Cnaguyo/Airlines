package com.catherine.Airline.controllers;

import com.catherine.Airline.model.Airplane;
import com.catherine.Airline.model.Airport;
import com.catherine.Airline.model.Message;
import com.catherine.Airline.repository.AirPlaneRepository;
import com.catherine.Airline.repository.AirportRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/airports/")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirPlaneRepository airPlaneRepository;

    @GetMapping
    public Iterable<Airport> getAirports() {
        return airportRepository.findAll();
    }

    // get all airports
    @GetMapping({"/id"})
    public ResponseEntity<Airport> getAirport(@PathVariable long id) {
        Optional<Airport> airport = airportRepository.findById(id);
        if (airport.isPresent()) {
            return ResponseEntity.ok(airport.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        return ResponseEntity.ok(airportRepository.save(airport));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@RequestBody Airport airport) {
        return ResponseEntity.ok(airportRepository.save(airport));
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteAirport(@PathVariable long id) {
        Optional<Airport> airport = airportRepository.findById(id);
        for (Airplane airplane : airport.get().getAirplanes()) {
            airPlaneRepository.deleteById(airplane.getId());
        }
        airportRepository.deleteById(id);
    }
}
