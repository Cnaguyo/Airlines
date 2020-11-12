package com.catherine.Airline.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Airport implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String airportName;

  private int numOfMaxFlights;


  @JsonManagedReference
  @OneToMany(
      mappedBy = "airport")
  private Set<Airplane> airplanes;

  public Airport() {}

  public Airport(Long id, String airportName, int numOfMaxFlights, Set<Airplane> airplanes) {
    this.id = id;
    this.airportName = airportName;
    this.numOfMaxFlights = numOfMaxFlights;
    this.airplanes = airplanes;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAirportName() {
    return airportName;
  }

  public void setAirportName(String airportName) {
    this.airportName = airportName;
  }

  public int getNumOfMaxFlights() {
    return numOfMaxFlights;
  }

  public void setNumOfMaxFlights(final int numOfMaxFlights) {
    this.numOfMaxFlights = numOfMaxFlights;
  }

  public Set<Airplane> getAirplanes() {
    return airplanes;
  }

  public void setAirplanes(Set<Airplane> airplanes) {
    this.airplanes = airplanes;
  }
}
