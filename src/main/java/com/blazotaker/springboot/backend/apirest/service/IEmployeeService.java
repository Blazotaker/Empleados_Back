package com.blazotaker.springboot.backend.apirest.service;

import java.util.List;

import com.blazotaker.springboot.backend.apirest.exception.ResourceNotFoundException;
import com.blazotaker.springboot.backend.apirest.model.Employee;


public interface IEmployeeService {
	
public List<Employee> findAll();
	
	public Employee findById (Long id) throws ResourceNotFoundException;
	
	public Employee save(Employee employee);
	
	public void deleteById(Long id);
}
