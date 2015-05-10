package com.bignippleboy.KenPano.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.bignippleboy.KenPano.facade.dto.*;
import com.bignippleboy.KenPano.core.domain.*;

public class OrganizationAssembler {
	
	public static OrganizationDTO  toDTO(Organization  organization){
		if (organization == null) {
			return null;
		}
    	OrganizationDTO result  = new OrganizationDTO();
	    	result.setId (organization.getId());
     	    	result.setVersion (organization.getVersion());
     	    	result.setName (organization.getName());
     	    	result.setSerialNumber (organization.getSerialNumber());
     	    return result;
	 }
	
	public static List<OrganizationDTO>  toDTOs(Collection<Organization>  organizations){
		if (organizations == null) {
			return null;
		}
		List<OrganizationDTO> results = new ArrayList<OrganizationDTO>();
		for (Organization each : organizations) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static Organization  toEntity(OrganizationDTO  organizationDTO){
	 	if (organizationDTO == null) {
			return null;
		}
	 	Organization result  = new Organization();
        result.setId (organizationDTO.getId());
         result.setVersion (organizationDTO.getVersion());
         result.setName (organizationDTO.getName());
         result.setSerialNumber (organizationDTO.getSerialNumber());
 	  	return result;
	 }
	
	public static List<Organization> toEntities(Collection<OrganizationDTO> organizationDTOs) {
		if (organizationDTOs == null) {
			return null;
		}
		
		List<Organization> results = new ArrayList<Organization>();
		for (OrganizationDTO each : organizationDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
