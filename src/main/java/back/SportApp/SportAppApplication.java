package back.SportApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class SportAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportAppApplication.class, args);
		Printer.printMsg("Hello World");
	}
	@EventListener(ApplicationReadyEvent.class)
	public void logMemoryUsage() {
		long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("Mémoire utilisée au démarrage: " + (used / 1024 / 1024) + " MB");
	}

}


// 		spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
//		spring.datasource.username=your_user
//		spring.datasource.password=your_password
