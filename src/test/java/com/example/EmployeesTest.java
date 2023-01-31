package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EmployeesTest {

    @Test
    void payEmployeesShouldReturn0WhenThereAreNoEmployeesToPay() {
        var employees = new Employees(new EmployeeRepositoryImpl(), Mockito.mock(BankService.class));

        assertEquals(0, employees.payEmployees());
    }

    @Test
    void payEmployeesShouldReturn2WhenThereAreTwoEmployeesToPay() {
        var employees2 = new Employees(new EmployeeRepositoryImpl(List.of(new Employee("44", 44_000d), new Employee("534", 32_000d))), Mockito.mock(BankService.class));

        assertEquals(2, employees2.payEmployees());
    }

    @Test
    void unsuccessfulPaymentDoesNotSetEmployeeToPaid() {
        var bankServiceMock = Mockito.mock(BankService.class);
        var employee = new Employee("534", 47_000.99);
        var employees = new Employees(new EmployeeRepositoryImpl(List.of(employee)), bankServiceMock);
        Mockito.doThrow(RuntimeException.class).when(bankServiceMock).pay(employee.getId(), employee.getSalary());

        employees.payEmployees();

        assertFalse(employee.isPaid());
    }

    @Test
    void successfulPaymentDoesSetEmployeeToPaid() {
        var bankServiceMock = Mockito.mock(BankService.class);
        var employee = new Employee("555", 53_000.50);
        var employees = new Employees(new EmployeeRepositoryImpl(List.of(employee)), bankServiceMock);

        employees.payEmployees();

        assertTrue(employee.isPaid());
    }
}
