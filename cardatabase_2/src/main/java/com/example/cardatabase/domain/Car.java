package com.example.cardatabase.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand, model, color, registrationNumber;
    private int modelYear, price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    // JPA는 기본생성자가 필수적으로 요구됩니다.
    public Car(String brand, String model, String color, String registrationNumber, int modelYear, int price, Owner owner) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.modelYear = modelYear;
        this.price = price;
        this.owner = owner;
    }
}