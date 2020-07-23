package com.stellapps.interview.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stellapps.interview.dao.EmployeeRepo;
import com.stellapps.interview.dao.OrganizationRepo;
import com.stellapps.interview.model.Employee;
import com.stellapps.interview.model.Organization;

@RestController
public class StellappsController {

	@Autowired
	OrganizationRepo organizationRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	@PostMapping("/createEmployee")
	public String createEmployee(@RequestBody Employee emp) {
		employeeRepo.save(emp);
		return "successfully created new Employee";
	}

	@PostMapping("/createOrganization")
	public String createOrganization(@RequestBody Organization org) {
		organizationRepo.save(org);
		return "successfully created new Organization";
	}

	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees() {
		Iterable<Employee> iterable =  employeeRepo.findAll();
		List<Employee> result = 
				  StreamSupport.stream(iterable.spliterator(), false)
				    .collect(Collectors.toList());
		return result;
	}


	@GetMapping("/getEmployeesbyOrgId/{id}")
	public List<Employee> getEmployeesbyOrgId(@PathVariable Long id) {
		//Employee emp =  employeeRepo.findById(id).orElse(new Employee());
		Iterable<Employee> iterable =  employeeRepo.findAllByEmpOrgId(id);
		List<Employee> result = 
				  StreamSupport.stream(iterable.spliterator(), false)
				    .collect(Collectors.toList());
		return result;
	}

	@GetMapping("/getEmployeesByOrgId")
	public String getEmployeesByOrgId(@RequestBody Employee req) {
		Employee emp = new Employee();
		employeeRepo.save(emp);
		return "successfully created new Employee";
	}


	@GetMapping("/getEmployee/{id}")
	public Employee get(@PathVariable Integer id) {		
		return employeeRepo.findById(id).orElse(null);
	}
	
	@PutMapping("/update/{id}")
	public Employee update(@RequestBody Employee newEmployee, @PathVariable int id) {
		//Employee employee1 = employeeRepo.findById(id).orElse(null);
	    return employeeRepo.findById(id)
	    	      .map(employee -> {
	    	        employee.setEmployeeName(newEmployee.getEmployeeName());
	    	        employee.setEmpOrgId(newEmployee.getEmpOrgId());
	    	        return employeeRepo.save(employee);
	    	      })
	    	      .orElseGet(() -> {
	    	        newEmployee.setEmployeeId(id);
	    	        return employeeRepo.save(newEmployee);
	    	      });
	}
	
	//delete by  id
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
	public String deleteCourse(@PathVariable Integer id) {
		System.out.println("deleting" + id);
		employeeRepo.deleteById(id);
		return "deleted "+id;
	}

}
