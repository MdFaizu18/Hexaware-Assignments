package test.java.com.payxpert.test;

import com.payxpert.dao.PayrollServiceImpl;
import com.payxpert.entity.Payroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PayrollServiceTest {
    private PayrollServiceImpl payrollService;
    private static final int EMPLOYEE_ID = 123;
    private static final double BASIC_SALARY = 5000.0;
    private static final double OVERTIME = 1000.0;
    private static final double DEDUCTIONS = 500.0;

    @BeforeEach
    void setUp() {
        payrollService = new PayrollServiceImpl();
    }

    @Test
    void CalculateGrossSalaryForEmployee() throws Exception {
        Payroll payroll = payrollService.generatePayroll(
                EMPLOYEE_ID,
                LocalDate.now(),
                LocalDate.now().plusDays(14),
                BASIC_SALARY,
                OVERTIME,
                DEDUCTIONS
        );

        double expectedGross = BASIC_SALARY + OVERTIME;
        assertEquals(expectedGross,
                payroll.getBasicSalary() + payroll.getOvertimePay(),
                "Gross salary calculation mismatch");
    }

    @Test
    void CalculateNetSalaryAfterDeductions() throws Exception {
        Payroll payroll = payrollService.generatePayroll(
                EMPLOYEE_ID,
                LocalDate.now(),
                LocalDate.now().plusDays(14),
                BASIC_SALARY,
                OVERTIME,
                DEDUCTIONS
        );

        double expectedNet = (BASIC_SALARY + OVERTIME) - DEDUCTIONS;
        assertEquals(expectedNet, payroll.getNetSalary(),
                0.001, 
                "Net salary calculation mismatch");
    }

    @Test
    @DisplayName("Integration Test: Process Payroll for Multiple Employees and Validate Persistence")
    void shouldProcessPayrollForMultipleEmployeesAndValidateInDB() throws Exception {
        int[] employeeIds = {123, 456, 1018};
        double[] basicSalaries = {5000, 6000, 7000};
        double[] overtimePays = {500, 600, 700};
        double[] deductions = {300, 400, 500};

        for (int i = 0; i < employeeIds.length; i++) {
            Payroll payroll = payrollService.generatePayroll(
                    employeeIds[i],
                    LocalDate.now(),
                    LocalDate.now().plusDays(14),
                    basicSalaries[i],
                    overtimePays[i],
                    deductions[i]
            );

         
            double expectedGross = basicSalaries[i] + overtimePays[i];
            double expectedNet = expectedGross - deductions[i];

            assertNotNull(payroll, "Payroll object should not be null");
            assertEquals(expectedGross, payroll.getBasicSalary() + payroll.getOvertimePay(), 0.001, "Gross salary mismatch");
            assertEquals(expectedNet, payroll.getNetSalary(), 0.001, "Net salary mismatch");

            Payroll persisted = payrollService.getPayrollById(payroll.getPayrollId());
            assertNotNull(persisted, "Persisted payroll should not be null");
            assertEquals(employeeIds[i], persisted.getEmployeeId(), "Employee ID mismatch");
        }
    }

}
