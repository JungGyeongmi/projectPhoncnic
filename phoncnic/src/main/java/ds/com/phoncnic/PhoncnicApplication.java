package ds.com.phoncnic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PhoncnicApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoncnicApplication.class, args);
	}

}
