package com.example.cardatabase4.repository;

import com.example.cardatabase4.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {

}
