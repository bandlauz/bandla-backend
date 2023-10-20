package uz.bandla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BandlaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BandlaApplication.class, args);
	}

}
