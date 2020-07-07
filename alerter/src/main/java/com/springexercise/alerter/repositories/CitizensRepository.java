package com.springexercise.alerter.repositories;

import com.springexercise.alerter.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitizensRepository extends JpaRepository<Citizen, Long> {
    List<Citizen> findAllByCity(String city);
    Optional<Citizen> findByCitizenID(int citizenID);
}
