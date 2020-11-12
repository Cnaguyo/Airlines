package com.catherine.Airline.repository;

import com.catherine.Airline.model.Airport;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {

  public List<Airport> findAllByAirportName(String airportName);

}
