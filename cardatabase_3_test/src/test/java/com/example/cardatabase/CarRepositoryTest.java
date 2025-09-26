package com.example.cardatabase;

import com.example.cardatabase.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.linesOf;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Test
    @DisplayName("차량 저장 메서드 테스트")
    void saveCar() {
        // given - 제반 준비 사항
        // Car Entity를 확인해봤을 때 field로 Owner를 요구하기 때문에
        // 얘부터 먼저 만들고 -> ownerRepository에 저장
        Owner owner = new Owner("Gemini", "GPT");
        ownerRepository.save(owner);
        // 그리고 Car 객체를 만들겁니다.
        Car car = new Car("Ford", "Mustang", "Red", "ABCEDF", 2021, 567890, owner);
        // when - 테스트 실행
        // 저장이 됐는가를 확인하기 위한 부분
        carRepository.save(car);
        // then - 그 결과가 어떠할지
        assertThat(carRepository.findById(car.getId())).isPresent();  // 이건 그냥 결과값이 하나일테니까.
        assertThat(carRepository.findById(car.getId()).get().getBrand()).isEqualTo("Ford");
    }

    @Test
    @DisplayName("삭제 확인 테스트")
    void delCar() {
        // given
        Owner owner = new Owner("동차", "자");
        ownerRepository.save(owner);
        carRepository.save(new Car("브랜드","모델","컬러","1",1,1,owner));
        // when
        carRepository.deleteAll();
        // then
        assertThat(carRepository.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("브랜드 검색 메서드")
    void findByBrandShouldReturnCar() {
        //given
        Owner owner = new Owner("Gemini", "GPT");
        ownerRepository.save(owner);
        carRepository.save(new Car("Ford", "Mustang", "Red", "ABCEDF", 2021, 567891, owner));
        carRepository.save(new Car("Ford", "Mustang", "White", "ABCED", 2022, 567892, owner));
        carRepository.save(new Car("Four", "Mustang", "Black", "ABCE", 2023, 567893, owner));

        //when
        List<Car> carList = carRepository.findByBrand("Ford");
        //then
        for(Car car : carList) assertThat(car.getBrand()).isEqualTo("Ford");
        carList.forEach(car->assertThat(car.getBrand()).isEqualTo("Ford"));
    }


}
