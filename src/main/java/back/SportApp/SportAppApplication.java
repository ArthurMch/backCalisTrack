package back.SportApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import back.SportApp.Printer;


@SpringBootApplication
public class SportAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportAppApplication.class, args);
		Printer.printMsg("Hello World");

	}

}


// 		spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
//		spring.datasource.username=your_user
//		spring.datasource.password=your_password