package test.java.com.payxpert.test;

import com.payxpert.dao.TaxServiceImpl;
import com.payxpert.entity.Tax;
import com.payxpert.exception.TaxCalculationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;
class TaxServiceTest {
    private TaxServiceImpl taxService;
    private static final int HIGH_INCOME_EMPLOYEE_ID = 123;

    @BeforeEach
    void setUp() {
        taxService = new TaxServiceImpl();
    }

    @Test
    void VerifyTaxCalculationForHighIncomeEmployee() throws Exception {
    
        double highTaxableIncome = 250_000.0;
        int currentYear = Year.now().getValue();
        // calculation:
        // First 100,000 @25% = 25,000
        // Next 100,000 @30% = 30,000
        // Remaining 50,000 @35% = 17,500
        // Total = 25,000 + 30,000 + 17,500 = 72,500
        double expectedTax = 72_500.0;

        Tax tax = taxService.calculateTax(
                HIGH_INCOME_EMPLOYEE_ID,
                currentYear,
                highTaxableIncome
        );

        assertEquals(expectedTax, tax.getTaxAmount(), 0.001,
                "High income tax calculation mismatch");
    }
}
