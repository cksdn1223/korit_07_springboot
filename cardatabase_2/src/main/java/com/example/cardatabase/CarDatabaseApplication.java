package com.example.cardatabase;

import com.example.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.cert.Extension;
import java.util.Arrays;

@SpringBootApplication
public class CarDatabaseApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(
			CarDatabaseApplication.class
	);
	
	// 여기에 생성자 주입부분 적겠습니다.
	private final CarRepository repository;
	private final OwnerRepository ownerRepository;
	private final AppUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

    public CarDatabaseApplication(CarRepository repository, OwnerRepository ownerRepository, AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
		SpringApplication.run(CarDatabaseApplication.class, args);
	}

	// CommandLineRunner 인터페이스의 추상메서드인 run()을 여기서 구현함
	@Override
	public void run(String... args) throws Exception {
		// 소유자 객체를 추가
		Owner owner1 = new Owner("일","김");
		Owner owner2 = new Owner("이","강");
		// 다수의 객체를 한번에 저장하는 메서드
		ownerRepository.saveAll(Arrays.asList(owner1,owner2));

		// 내부에서 CarRepository의 객체인 repository의 메서드를 호출할겁니다.
		repository.save(new Car("Kia", "Seltos", "Chacol", "370SU5690", 2020, 30000000, owner1));
		repository.save(new Car("Hyundai", "Sonata", "White", "123456", 2025, 25000000, owner2));
		repository.save(new Car("Honda", "CR-V", "Black-White", "987654", 2024, 45000000, owner2));
		// -> 이상의 코드는 testdb 내의 CAR 테이블 내에 3 개의 row를 추가하여 저장한다는 의미입니다.
		// Java 기준으로는 객체 세 개를 만들어서 저장했다고 볼 수 있겠네요.

		// 모든 자동차를 가져와서 Console에 로깅해보도록 하겠습니다.
		for(Car car : repository.findAll()){
			logger.info("brand : {}, model : {}", car.getBrand(), car.getModel());
		}

		// AppUser 더미 데이터를 추가
		userRepository.save(new AppUser("user",passwordEncoder.encode("user"),"USER"));
		userRepository.save(new AppUser("admin", "$2a$12$QLn0SiGpQdYvmaTbHMdQf.YijUGJYRAUiVW.MIVi0y.3ZCWR9TRCu", "ADMIN"));


	}


}
