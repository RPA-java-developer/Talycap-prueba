package com.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistics.model.GroundLogistics;

@Repository
public interface GroundLogisticsRepository extends JpaRepository<GroundLogistics, Integer>{

}
