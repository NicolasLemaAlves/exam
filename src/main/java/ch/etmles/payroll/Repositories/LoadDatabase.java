package ch.etmles.payroll.Repositories;

import ch.etmles.payroll.Entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository){
        return args->{
            log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar", LocalDate.of(1999, 10, 10))));
            log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief", LocalDate.of(1999, 10, 03))));
        };
    }
}
