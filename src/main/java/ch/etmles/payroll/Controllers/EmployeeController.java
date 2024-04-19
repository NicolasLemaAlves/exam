package ch.etmles.payroll.Controllers;

import ch.etmles.payroll.Entities.Employee;
import ch.etmles.payroll.Repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }

    /* curl sample :
    curl -i localhost:8080/employees
    */
    @GetMapping("/employees")
    List<Employee> all(){
        return repository.findAll();
    }

    /* curl sample :
    curl -i -X POST localhost:8080/employees ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Russel George\", \"role\": \"gardener\"}"
    */

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee){
        if (isMinor(newEmployee.getDateOfBirth())) {
            throw new MinorEmployeeException(newEmployee.getName());
        }

        return repository.save(newEmployee);
    }

    // Vérifie si la date de naissance indique que l'employé est mineur
    private boolean isMinor(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        LocalDate eighteenYearsAgo = currentDate.minusYears(18);
        return dateOfBirth.isAfter(eighteenYearsAgo);
    }
    /* curl sample :
    curl -i localhost:8080/employees/1
    */
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/employees/2 ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"Samwise Bing\", \"role\": \"peer-to-peer\"}"
     */
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    employee.setDateOfBirth(newEmployee.getDateOfBirth());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/employees/2
    */
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }
}
