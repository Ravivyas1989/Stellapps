package com.stellapps.interview;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.stellapps.interview.controller.StellappsController;
import com.stellapps.interview.dao.EmployeeRepo;
import com.stellapps.interview.dao.OrganizationRepo;
import com.stellapps.interview.model.Employee;
import com.stellapps.interview.model.User;

public class StellappsControllerTest {
	
	@Autowired
	OrganizationRepo organizationRepo;

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	StellappsController stellappsController;

    @Test
    public void testCreateEmployee() {
    	Employee emp = Employee.builder().employeeName("Dharam").empOrgId(1).build();
    	stellappsController.createEmployee(emp);
    	
        // Verify the results
        assertEquals(stellappsController.createEmployee(emp), "successfully created new Employee");
    }
}
