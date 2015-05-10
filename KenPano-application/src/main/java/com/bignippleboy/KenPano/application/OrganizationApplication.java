package com.bignippleboy.KenPano.application;


import java.util.List;
import java.util.Set;
import  com.bignippleboy.KenPano.core.domain.Organization;

public interface OrganizationApplication {

	public Organization getOrganization(Long id);
	
	public void creatOrganization(Organization organization);
	
	public void updateOrganization(Organization organization);
	
	public void removeOrganization(Organization organization);
	
	public void removeOrganizations(Set<Organization> organizations);
	
	public List<Organization> findAllOrganization();
	
}

