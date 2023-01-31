package com.example;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    List<Employee> employees;

    public EmployeeRepositoryImpl() {
        this.employees = new ArrayList<>();
    }

    public EmployeeRepositoryImpl(List<Employee> employees) {
        this.employees = new ArrayList<>(employees);
    }

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public Employee save(Employee e) {
        employees.add(e);
        return e;
    }
}
