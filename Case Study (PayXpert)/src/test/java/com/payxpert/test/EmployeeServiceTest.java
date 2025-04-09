package test.java.com.payxpert.test;

import com.payxpert.dao.EmployeeServiceImpl;
import com.payxpert.entity.Employee;
import com.payxpert.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Test
    void VerifyErrorHandlingForInvalidEmployeeData() {
        Employee invalidEmployee = new Employee(
                -1,
                "",
                "",
                LocalDate.now().plusYears(1),
                "X",
                "invalid-email",
                "123",
                "",
                "",
                LocalDate.now(),
                null
        );

        assertThrows(InvalidInputException.class, () ->
                        employeeService.addEmployee(invalidEmployee),
                "System should reject invalid employee data"
        );
    }

    @Test
    void ValidEmployeeCreation() throws Exception {
        Employee validEmployee = new Employee(
                555,
                "Nikil",
                "Sigh",
                LocalDate.of(2002, 9, 17),
                "male",
                "nikil@gmail.com",
                "9999988558",
                "Pune",
                "Developer",
                LocalDate.now(),
                null
        );

        assertDoesNotThrow(() ->
                        employeeService.addEmployee(validEmployee),
                "Valid employee should be created without errors"
        );
    }
}