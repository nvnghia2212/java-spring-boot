package com.javadevelop.mongodb.service;

import com.javadevelop.mongodb.document.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    Employee save(Employee employee);
    Employee findByEmpNo(Integer empNo);
    List<Employee> findByEmpNoAndName(Integer empNo, String fullName );
    List<Employee> findByEmpNoOrName(Integer empNo, String fullName );
    List<Employee> findByRegexName(String regexp);
    Integer findTop1ByOrderByEmpNoDesc();
    List<Employee> findByEmpNoBetween(Integer from, Integer to);
}
