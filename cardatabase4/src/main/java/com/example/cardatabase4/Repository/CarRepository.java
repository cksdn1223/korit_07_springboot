package com.example.cardatabase4.Repository;

import com.example.cardatabase4.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {

}
