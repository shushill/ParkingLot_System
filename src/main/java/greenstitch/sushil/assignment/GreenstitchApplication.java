package greenstitch.sushil.assignment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Parking Lot System",
				description = "Parking Lot Service API documentation \n",
				version = "v1.0",
				contact = @Contact(
						name = "Sushil verma"
				)
		)
)
@SpringBootApplication
public class GreenstitchApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenstitchApplication.class, args);
	}

}
