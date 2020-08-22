package com.blazotaker.springboot.backend.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blazotaker.springboot.backend.apirest.exception.ResourceNotFoundException;
import com.blazotaker.springboot.backend.apirest.model.Employee;
import com.blazotaker.springboot.backend.apirest.service.IEmployeeService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	private final IEmployeeService employeeSerivice;
	
	@Autowired
	EmployeeController(IEmployeeService E) {
		this.employeeSerivice = E;	
	}
	
	@GetMapping("/status")
	public String status() {
		return "hola wey";
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
        return employeeSerivice.findAll();
    }
	
	@GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException{    
        return ResponseEntity.ok().body(employeeSerivice.findById(employeeId));
    }
	
	
	@PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeSerivice.save(employee);
    }
	
	@PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
        @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeSerivice.findById(employeeId);
        employee.setEmail(employeeDetails.getEmail());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeSerivice.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
	
	@DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
        throws ResourceNotFoundException {
        Employee employee = employeeSerivice.findById(employeeId);
        employeeSerivice.deleteById(employee.getId());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
