package com.blazotaker.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blazotaker.springboot.backend.apirest.model.Employee;

public interface IEmployeeDao extends JpaRepository<Employee, Long> {

}
