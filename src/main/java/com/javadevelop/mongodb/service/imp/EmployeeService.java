package com.javadevelop.mongodb.service.imp;

import com.javadevelop.mongodb.document.Employee;
import com.javadevelop.mongodb.repository.EmployeeRepository;
import com.javadevelop.mongodb.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        employees = employeeRepository.findAll();
        return employees;
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findByEmpNo(Integer empNo) {
        return employeeRepository.findByEmpNo(empNo);
    }

    public List<Employee> findByEmpNoAndName(Integer empNo, String name) {
        return employeeRepository.findByEmpNoAndFullName(empNo, name);
    }

    public List<Employee> findByEmpNoOrName(Integer empNo, String name) {
        return employeeRepository.findByEmpNoOrFullName(empNo, name);
    }

    @Override
    public List<Employee> findByRegexName(String regexName) {
        return employeeRepository.findByRegexName(regexName);
    }

    @Override
    public Integer findTop1ByOrderByEmpNoDesc() {
        // 1st way: use Optional
//        Optional<Employee> e  = employeeRepository.findTop1ByOrderByEmpNoDesc();
//        return (e.isPresent()) ? e.get().getEmpNo() + 1 : 1;

        // 2nd way: use Objects.isNull(objest)
        Employee e  = employeeRepository.findTop1ByOrderByEmpNoDesc();
        return (Objects.isNull(e)) ? 1 : e.getEmpNo() + 1 ;
    }

    @Override
    public List<Employee> findByEmpNoBetween(Integer from, Integer to) {
        return employeeRepository.findByEmpNoBetween(from, to);
    }
}
