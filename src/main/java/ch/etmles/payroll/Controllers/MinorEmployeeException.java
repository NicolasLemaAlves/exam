package ch.etmles.payroll.Controllers;

public class MinorEmployeeException extends RuntimeException {
    public MinorEmployeeException(String employeeName) {
        super("Hiring a minor employee is not legally possible : " + employeeName);
    }
}
