package com.blazotaker.springboot.backend.apirest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blazotaker.springboot.backend.apirest.dao.IEmployeeDao;
import com.blazotaker.springboot.backend.apirest.exception.ResourceNotFoundException;
import com.blazotaker.springboot.backend.apirest.model.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findAll() {
		return (List<Employee>) employeeDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findById(Long id) throws ResourceNotFoundException {
		Employee employee = employeeDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		return employee;
	}

	@Override
	@Transactional
	public Employee save(Employee employee) {
		return employeeDao.save(employee);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		employeeDao.deleteById(id);

	}

}
