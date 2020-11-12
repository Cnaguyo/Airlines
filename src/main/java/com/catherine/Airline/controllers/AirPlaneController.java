package com.catherine.Airline.controllers;

import com.catherine.Airline.model.Airplane;
import com.catherine.Airline.model.Airport;
import com.catherine.Airline.model.Message;
import com.catherine.Airline.repository.AirPlaneRepository;
import com.catherine.Airline.repository.AirportRepository;
import com.catherine.Airline.service.AirportService;
import java.util.List;
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
@RequestMapping("api/airplanes/")
public class AirPlaneController {

  @Autowired
  private AirPlaneRepository airPlaneRepository;
  @Autowired
  private AirportRepository airportRepository;

  @RequestMapping( method = RequestMethod.GET)
  public Iterable<Airplane> getAirplanes() {
    return airPlaneRepository.findAll();
  }

  @RequestMapping( method = RequestMethod.POST)
  public ResponseEntity<?> save(@RequestBody Airplane airplaneToSave) {
    if (airplaneToSave.getCurrentFuel() > 5) {
      return ResponseEntity.badRequest().body(new Message("Plane max fuel is 5."));
    }
    List<Airport> airport=this.airportRepository.findAllByAirportName(airplaneToSave.getLocation());
    if (airport.size() > 0) {
      if(airport.get(0).getAirplanes().size()>=airport.get(0).getNumOfMaxFlights()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("maximum number of flight reached"));
      }
      airplaneToSave.setAirport(airport.get(0));
      airPlaneRepository.save(airplaneToSave);
      return ResponseEntity.ok(HttpStatus.OK);
    }
    else{
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(" airport does not exist."));

    }
  }

  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public void deleteAirPlanes(@PathVariable Long id) {
    airPlaneRepository.deleteById(id);
  }


  @RequestMapping(value = "flight/{id}", method = RequestMethod.PUT)
  public ResponseEntity<?> flyToAirport(@PathVariable long id) {
    Airplane plane = this.airPlaneRepository.findById(id).get();
    List<Airport> airport=this.airportRepository.findAllByAirportName(plane.getLocation());
    if(plane!=null&&airport!=null&&airport.size()>0){
      if (plane.getCurrentFuel() < 2) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Plane has not enough fuel left."));
      }
      else {
        plane.setCurrentFuel(plane.getCurrentFuel() - 2);
        plane.setAirport(airport.get(0));
        return ResponseEntity.ok(this.airPlaneRepository.save(plane));
      }
    }
    else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Either the given plane or airport does not exist."));
    }
  }

  @PutMapping("/refuel/{id}")
  public ResponseEntity<?> refuelPlane(@PathVariable long id) {
    System.out.println("refuel plane");
    Optional<Airplane> result = airPlaneRepository.findById(id);
    if (result.isPresent()) {
      Airplane airplane = result.get();
      if (airplane.getCurrentFuel() >= 5) {
        return ResponseEntity.badRequest().body(new Message("Plane is already full of fuel "));
      } else {
        airplane.setCurrentFuel(5);
        return ResponseEntity.ok(airPlaneRepository.save(airplane));
      }
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("plane not found"));
    }
  }
}
