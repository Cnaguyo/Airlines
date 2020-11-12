package com.catherine.Airline.service;

import com.catherine.Airline.model.Airplane;
import com.catherine.Airline.model.Airport;
import com.catherine.Airline.repository.AirPlaneRepository;
import com.catherine.Airline.repository.AirportRepository;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AirplaneService {

  @Autowired
  private AirPlaneRepository airPlaneRepository;
  @PostConstruct
  public void init(){

  }
}
