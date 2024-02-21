package com.javadevelop.mongodb.repository;

import com.javadevelop.mongodb.document.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Employee findTop1ByOrderByEmpNoDesc();

    @Query("{'$and':[ {'empNo':?0}, {'fullName':?1} ]}")
    List<Employee> findByEmpNoAndFullName(Integer empNo, String fullName );

    @Query("{'$or':[ {'empNo':?0}, {'fullName':?1} ]}")
    List<Employee> findByEmpNoOrFullName(Integer empNo, String fullName );

    @Query("{ 'fullName' : { $regex: ?0 } }")
    List<Employee> findByRegexName(String regexp);

    @Query(value="{ 'empNo' : ?0 }", fields="{ 'id' : 0, 'empNo' : 1, 'fullName' : 1, 'hireDate' : 1}")
    Employee findByEmpNo(Integer empNo);

    @Query("{'EmpNo' : {$gte : ?0, $lte : ?1}}")
    List<Employee> findByEmpNoBetween(Integer from, Integer to);
}
