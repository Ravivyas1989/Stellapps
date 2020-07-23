package com.stellapps.interview.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stellapps.interview.model.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

	@Query(value = "select * from employee where emp_org_id=:id", 
			  nativeQuery = true)
	Iterable<Employee> findAllByEmpOrgId(@Param("id") Long id);

}
