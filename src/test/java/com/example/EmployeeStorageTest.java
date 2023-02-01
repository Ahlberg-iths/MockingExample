package com.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeStorageTest {

    @Test
    void findAllShouldReturnListOf3ItemsIfThereAre3EmployeesInStorage() {
        var employee = new Employee("007", 1_000_000.00);
        var employee2 = new Employee("005", 200_000.00);
        var employee3 = new Employee("002", 100_000.00);
        var storage = new EmployeeStorage(List.of(employee, employee2, employee3));

        assertEquals(3, storage.findAll().size());
    }

    @Test
    void findAllShouldReturnAnEmptyListIfThereAreNoEmployeesInStorage() {
        var storage = new EmployeeStorage();

        assertTrue(storage.findAll().isEmpty());
    }

    @Test
    void findAllShouldReturnAListThatContainsTheEmployeesInStorage() {
        var employee = new Employee("007", 1_000_000.00);
        var employee2 = new Employee("005", 200_000.00);
        var storage = new EmployeeStorage(List.of(employee, employee2));

        var resultList = storage.findAll();

        assertTrue(resultList.contains(employee));
        assertTrue(resultList.contains(employee2));
    }

    @Test
    void saveShouldReturnTheElementThatWasSaved() {
        var employee = new Employee("007", 1_000_000.00);
        var storage = new EmployeeStorage();

        assertEquals(employee, storage.save(employee));
    }

    @Test
    void saveEmployeeWithAlreadyExistingIdShouldReplaceTheFormerEmployeeWithThatId() {
        var employee = new Employee("123", 41_000.00);
        var employeeSameID = new Employee("123", 55_000.00);
        var storage = new EmployeeStorage(List.of(employee));

        storage.save(employeeSameID);

        assertTrue(storage.findAll().contains(employeeSameID));
        assertFalse(storage.findAll().contains(employee));
    }
}
