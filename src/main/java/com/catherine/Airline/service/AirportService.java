package com.catherine.Airline.service;

import com.catherine.Airline.model.Airport;
import com.catherine.Airline.repository.AirportRepository;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @PostConstruct
    public void init() {
        Airport airport1 = new Airport();
        airport1.setAirportName("Schipol");
        airport1.setNumOfMaxFlights(5);

        Airport airport2 = new Airport();
        airport2.setAirportName("Heathrow");
        airport2.setNumOfMaxFlights(5);

        Airport airport3 = new Airport();
        airport3.setAirportName("Paris Orly");
        airport3.setNumOfMaxFlights(5);

        Airport airport4 = new Airport();
        airport4.setAirportName("Stockholm");
        airport4.setNumOfMaxFlights(5);

        Airport airport5 = new Airport();
        airport5.setAirportName("Berlin-Tegel");
        airport5.setNumOfMaxFlights(5);

        this.airportRepository.save(airport1);
        this.airportRepository.save(airport2);
        this.airportRepository.save(airport3);
        this.airportRepository.save(airport4);
        this.airportRepository.save(airport5);
    }

}
