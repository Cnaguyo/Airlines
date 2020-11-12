package com.catherine.Airline.repository;

import com.catherine.Airline.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirPlaneRepository extends CrudRepository<Airplane, Long> {

}
