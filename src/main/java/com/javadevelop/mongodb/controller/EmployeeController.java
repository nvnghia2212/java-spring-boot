package com.javadevelop.mongodb.controller;

import com.javadevelop.dto.ProductDTO;
import com.javadevelop.mongodb.document.Employee;
import com.javadevelop.mongodb.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mongodb/employee")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;

    @GetMapping(produces = "application/json")
    public List<Employee> getAllEmployee() {
        return employeeService.findAll();
    }

    @PostMapping(produces = "application/json")
    public Employee createProduct(@RequestBody Employee employee) {

        employee.setEmpNo(employeeService.findTop1ByOrderByEmpNoDesc());
        return employeeService.save(employee);
    }

    @GetMapping(produces = "application/json", value = "/no")
    public Employee getEmployeeByEmpNo(@RequestBody Integer empNo) {
        return employeeService.findByEmpNo(empNo);
    }

    @GetMapping(produces = "application/json", value = "/noandname")
    public List<Employee> getEmployeeByEmpNoAndName(@RequestBody Employee employee) {
        return employeeService.findByEmpNoAndName(employee.getEmpNo(), employee.getFullName());
    }

    @GetMapping(produces = "application/json", value = "/noorname")
    public List<Employee> getEmployeeByEmpNoOrName(@RequestBody Employee employee) {
        return employeeService.findByEmpNoOrName(employee.getEmpNo(), employee.getFullName());
    }

    @GetMapping(produces = "application/json", value = "/regex")
    public List<Employee> getEmployeeByRegexName(@RequestBody String regexName) {
        return employeeService.findByRegexName(regexName);
    }

    @GetMapping(produces = "application/json", value = "/{from}/{to}")
    public List<Employee> getEmployeeBetweenEmpNo(@PathVariable int from, @PathVariable int to) {
        return employeeService.findByEmpNoBetween(from, to);
    }
}
