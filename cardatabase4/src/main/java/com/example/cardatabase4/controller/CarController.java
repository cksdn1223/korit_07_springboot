package com.example.cardatabase4.controller;

import com.example.cardatabase4.entity.Car;
import com.example.cardatabase4.repository.OwnerRepository;
import com.example.cardatabase4.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CarController {
    public final CarService carService;
    public final OwnerRepository ownerRepository;
    // 1. 모든 자동차 정보 조회 (GET /api/cars)
    @GetMapping("/cars")
    public List<Car> getCars() {
        return carService.getCars();
    }

    // 2. 차량 한 대 추가
    @PostMapping("/cars")
    public ResponseEntity<Car> addCars(@RequestBody Car car) {
        Car savedCar = carService.addCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    // 3. 차량 한 대 조회
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable Long id){
        return carService.getCarById(id)
                .map(car -> ResponseEntity.ok().body(car))
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. 차량 한 대 삭제
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        // 삭제 성공시 204 No Content return
        if(carService.deleteCar(id)) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();  // 대상이 없으면 404 Not Found
    }

    // 5. 차량 한 대 수정
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails){
        return carService.updateCar(id, carDetails)
                .map(updateCar -> ResponseEntity.ok().body(updateCar)) // 수정 성공시에는 200 OK
                .orElse(ResponseEntity.notFound().build()); // 수정할 차량 id 검색 실패시 404 not found
    }

}
