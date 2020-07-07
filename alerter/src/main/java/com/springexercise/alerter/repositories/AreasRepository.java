package com.springexercise.alerter.repositories;

import com.springexercise.alerter.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreasRepository extends JpaRepository<Area, Long> {
    Optional<Area> findByAreaName(String areaName);
}
