package com.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
