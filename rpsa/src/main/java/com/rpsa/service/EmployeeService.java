//package com.rpsa.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.rpsa.entity.Employee;
//import com.rpsa.repository.EmployeeRepository;
//
//@Service
//public class EmployeeService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAll();
//    }
//
//    public Employee addEmployee(Employee employee) {
//        return employeeRepository.save(employee);
//    }
//}