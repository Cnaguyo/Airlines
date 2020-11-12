package com.catherine.Airline.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Airplane implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long Id;
  private String planeName;
  private String Location;
  private int CurrentFuel;

  @JsonBackReference
  @ManyToOne
  private Airport airport;

  public Airplane() {
  }

  public long getId() {
    return Id;
  }

  public void setId(long id) {
    Id = id;
  }

  public String getPlaneName() {
    return planeName;
  }

  public void setPlaneName(String planeName) {
    this.planeName = planeName;
  }

  public String getLocation() {
    return Location;
  }

  public void setLocation(String location) {
    Location = location;
  }

  public int getCurrentFuel() {
    return CurrentFuel;
  }

  public void setCurrentFuel(int currentFuel) {
    CurrentFuel = currentFuel;
  }

  public Airport getAirport() {
    return airport;
  }

  public void setAirport(Airport airport) {
    this.airport = airport;
  }
}
