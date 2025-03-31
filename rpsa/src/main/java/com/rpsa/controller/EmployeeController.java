//package com.rpsa.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.rpsa.entity.Employee;
//import com.rpsa.service.EmployeeService;
//
//
//@RestController
//@RequestMapping("/api/employees")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    // GET API to retrieve all employees
//    @GetMapping
//    public List<Employee> getEmployees() {
//        return employeeService.getAllEmployees();
//    }
//
//    // POST API to add a new employee
//    @PostMapping
//    public Employee createEmployee(@RequestBody Employee employee) {
//        return employeeService.addEmployee(employee);
//    }
//}