package com.example.cardatabase4;

import com.example.cardatabase4.Entity.Car;
import com.example.cardatabase4.Entity.Owner;
import com.example.cardatabase4.Repository.CarRepository;
import com.example.cardatabase4.Repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@RequiredArgsConstructor
@SpringBootApplication
public class Cardatabase4Application{

	public static void main(String[] args) {
		SpringApplication.run(Cardatabase4Application.class, args);
	}


}
