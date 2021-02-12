package com.prajyot.employeemanager.service;

import com.prajyot.employeemanager.exception.UserNotFoundException;
import com.prajyot.employeemanager.model.Employee;
import com.prajyot.employeemanager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() ->new UserNotFoundException("The user by id" + id +" doesn't exist"));
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteEmployeeById(id); //spring creates the query by reading the function name
    }

}
