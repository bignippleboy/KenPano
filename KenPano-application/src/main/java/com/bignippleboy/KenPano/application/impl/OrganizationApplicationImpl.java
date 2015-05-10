package com.bignippleboy.KenPano.application.impl;

import java.util.List;
import java.util.Set;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;
import com.bignippleboy.KenPano.application.OrganizationApplication;
import com.bignippleboy.KenPano.core.domain.Organization;

@Named
@Transactional
public class OrganizationApplicationImpl implements OrganizationApplication {

	public Organization getOrganization(Long id) {
		return Organization.get(Organization.class, id);
	}
	
	public void creatOrganization(Organization organization) {
		organization.save();
	}
	
	public void updateOrganization(Organization organization) {
		organization .save();
	}
	
	public void removeOrganization(Organization organization) {
		if(organization != null){
			organization.remove();
		}
	}
	
	public void removeOrganizations(Set<Organization> organizations) {
		for (Organization organization : organizations) {
			organization.remove();
		}
	}
	
	public List<Organization> findAllOrganization() {
		return Organization.findAll(Organization.class);
	}
	
}
