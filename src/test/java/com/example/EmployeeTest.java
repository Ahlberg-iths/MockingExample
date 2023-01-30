package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    Employee testEmployee;

    @BeforeEach
    void setUp() {
        testEmployee = new Employee("007", 1_000_000.00);
    }

    @Test
    @Disabled("Code not implemented")
    void anEmployeeCreatedWithNullIdShouldThrowAnIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Employee(null, 23_000.00));
    }

    @Test
    void getIdShouldReturnTheCorrectId() {
        assertEquals("007", testEmployee.getId());
    }

    @Test
    void setIdShouldSetANewId() {
        String newId = "10";

        testEmployee.setId(newId);

        assertEquals("10", testEmployee.getId());
    }

    @Test
    void setSalaryShouldSetANewSalary() {
        double newSalary = 1234.56;

        testEmployee.setSalary(newSalary);

        assertEquals(1234.56, testEmployee.getSalary());
    }

    @Test
    void isPaidShouldReturnFalseAsDefault() {
        assertFalse(testEmployee.isPaid());
    }

    @Test
    void setPaidToTrueShouldSetIsPaidToTrue() {
        testEmployee.setPaid(true);

        assertTrue(testEmployee.isPaid());
    }

    @Test
    void toStringShouldReturnFormattedStringRepresentation() {

        var result = testEmployee.toString();

        assertEquals("Employee [id=007, salary=1000000.0]", result);

    }
}
