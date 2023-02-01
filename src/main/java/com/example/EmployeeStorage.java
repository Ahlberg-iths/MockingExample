package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeStorage implements EmployeeRepository {

    Map<String, Employee> employees;

    public EmployeeStorage() {
        this.employees = new HashMap<>();
    }

    public EmployeeStorage(List<Employee> employees) {
        this.employees = new HashMap<>();
        initializeStorage(this.employees, employees);
    }

    private static void initializeStorage(Map<String, Employee> storage, List<Employee> employees) {
        employees.forEach(e -> storage.put(e.getId(), e));
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public Employee save(Employee e) {
        employees.put(e.getId(), e);
        return e;
    }
}
