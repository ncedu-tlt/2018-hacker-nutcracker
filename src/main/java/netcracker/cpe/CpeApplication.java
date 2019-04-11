package netcracker.cpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Locale;

@EnableScheduling
@SpringBootApplication
public class CpeApplication {

	public static void main (String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		SpringApplication.run(CpeApplication.class, args);
	}

}
