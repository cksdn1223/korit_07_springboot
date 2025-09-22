package com.example.cardatabase;

import com.example.cardatabase.domain.Car;
import com.example.cardatabase.domain.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarDatabaseApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(
			CarDatabaseApplication.class
	);
	
	// 여기에 생성자 주입부분 적겠습니다.
	private final CarRepository repository;

    public CarDatabaseApplication(CarRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
		SpringApplication.run(CarDatabaseApplication.class, args);
	}

	// CommandLineRunner 인터페이스의 추상메서드인 run()을 여기서 구현함
	@Override
	public void run(String... args) throws Exception {
		// 내부에서 CarRepository의 객체인 repository의 메서드를 호출할겁니다.
		repository.save(new Car("Kia", "Seltos", "Chacol", "370SU5690", 2020, 30000000));
		repository.save(new Car("Hyundai", "Sonata", "White", "123456", 2025, 25000000));
		repository.save(new Car("Honda", "CR-V", "Black-White", "987654", 2024, 45000000));
		repository.save(new Car("Hyundai", "Grandeur", "Black", "258AB9012", 2023, 40000000));
		repository.save(new Car("Genesis", "G80", "Silver", "741GH3698", 2022, 65000000));
		repository.save(new Car("Kia", "K5", "Blue", "159MN2478", 2024, 32000000));
		repository.save(new Car("Ssangyong", "Torres", "Red", "357PQ8642", 2023, 31000000));
		repository.save(new Car("Hyundai", "Tucson", "Gray", "963RS5821", 2025, 34000000));
		repository.save(new Car("Toyota", "Camry", "White", "852TU1479", 2021, 38000000));
		repository.save(new Car("Kia", "Sorento", "Black", "753VW9513", 2024, 48000000));
		repository.save(new Car("Tesla", "Model 3", "White", "842XY6315", 2023, 58000000));
		repository.save(new Car("Ford", "Explorer", "Burgundy", "975ZA4268", 2020, 50000000));
		repository.save(new Car("Mercedes-Benz", "E-Class", "Black", "102BC5894", 2024, 85000000));
		repository.save(new Car("BMW", "5 Series", "White", "305DE7412", 2023, 78000000));
		repository.save(new Car("Audi", "A6", "Silver", "608FG9531", 2022, 72000000));
		repository.save(new Car("Kia", "Carnival", "Gray", "407HI8642", 2024, 42000000));
		repository.save(new Car("Genesis", "GV70", "Green", "516JK7539", 2025, 59000000));
		repository.save(new Car("Volvo", "XC60", "Blue", "819LM4567", 2023, 63000000));
		repository.save(new Car("Chevrolet", "Malibu", "White", "224NO9876", 2021, 28000000));
		repository.save(new Car("Kia", "Seltos", "Chacol", "370SU5690", 2020, 30000000));
		repository.save(new Car("Porsche", "911", "Red", "111PO2222", 2024, 180000000));
		repository.save(new Car("Hyundai", "Kona", "Yellow", "333QR3333", 2025, 27000000));
		repository.save(new Car("Kia", "Morning", "Pink", "444ST4444", 2022, 13000000));
		repository.save(new Car("Nissan", "Rogue", "Gray", "555UV5555", 2023, 35000000));
		repository.save(new Car("Hyundai", "Sonata", "White", "123456", 2025, 25000000));
		repository.save(new Car("Kia", "Ray", "Mint", "666WX6666", 2024, 15000000));
		repository.save(new Car("Jeep", "Wrangler", "Sand", "777YZ7777", 2021, 60000000));
		repository.save(new Car("Genesis", "G90", "Black", "888AB8888", 2025, 120000000));
		repository.save(new Car("Toyota", "Prius", "Silver", "999CD9999", 2023, 36000000));
		repository.save(new Car("Volkswagen", "Golf", "Blue", "100EF1000", 2022, 33000000));
		repository.save(new Car("Kia", "K8", "White", "200GH2000", 2024, 45000000));
		repository.save(new Car("Hyundai", "Santa Fe", "Burgundy", "300IJ3000", 2025, 48000000));
		repository.save(new Car("Mercedes-Benz", "C-Class", "White", "400KL4000", 2023, 65000000));
		repository.save(new Car("BMW", "3 Series", "Black", "500MN5000", 2024, 58000000));
		repository.save(new Car("Genesis", "GV80", "Gray", "600OP6000", 2023, 75000000));
		repository.save(new Car("Lexus", "ES", "White", "700QR7000", 2022, 60000000));
		repository.save(new Car("Tesla", "Model Y", "Red", "800ST8000", 2025, 62000000));
		repository.save(new Car("Honda", "Pilot", "Black", "900UV9000", 2024, 52000000));
		repository.save(new Car("Hyundai", "Palisade", "Blue", "110WX1100", 2025, 55000000));
		repository.save(new Car("Kia", "Carnival", "Black-White", "220YZ2200", 2024, 42000000));
		repository.save(new Car("Ssangyong", "Rexton", "Silver", "330AB3300", 2023, 44000000));
		repository.save(new Car("Ford", "Mustang", "Red", "440CD4400", 2022, 68000000));
		repository.save(new Car("Volkswagen", "Passat", "Black", "550EF5500", 2021, 39000000));
		repository.save(new Car("Audi", "A4", "White", "660GH6600", 2023, 56000000));
		repository.save(new Car("Chevrolet", "Equinox", "Gray", "770IJ7700", 2024, 38000000));
		repository.save(new Car("Volvo", "S90", "Black", "880KL8800", 2022, 70000000));
		repository.save(new Car("Hyundai", "Sonata", "Gray", "990MN9900", 2025, 29000000));
		repository.save(new Car("Kia", "Seltos", "Blue", "115OP1150", 2020, 28000000));
		repository.save(new Car("Toyota", "RAV4", "White", "225QR2250", 2023, 41000000));
		repository.save(new Car("Porsche", "Cayenne", "Black", "335ST3350", 2024, 150000000));
		// -> 이상의 코드는 testdb 내의 CAR 테이블 내에 3 개의 row를 추가하여 저장한다는 의미입니다.
		// Java 기준으로는 객체 세 개를 만들어서 저장했다고 볼 수 있겠네요.

		// 모든 자동차를 가져와서 Console에 로깅해보도록 하겠습니다.
		for(Car car : repository.findAll()){
			logger.info("brand : {}, model : {}", car.getBrand(), car.getModel());
		}

	}
}
