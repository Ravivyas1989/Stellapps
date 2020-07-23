package com.stellapps.interview.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stellapps.interview.model.Organization;

@Repository
public interface OrganizationRepo extends CrudRepository<Organization, Long> {

}
