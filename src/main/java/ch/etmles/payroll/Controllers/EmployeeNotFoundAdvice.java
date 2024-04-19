package ch.etmles.payroll.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class EmployeeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MinorEmployeeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String employeeMinorHandler(MinorEmployeeException ex){
        return ex.getMessage();
    }
}
