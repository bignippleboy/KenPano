package com.bignippleboy.KenPano.facade;

import java.util.List;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import com.bignippleboy.KenPano.facade.dto.*;

public interface OrganizationFacade {

	public InvokeResult getOrganization(Long id);
	
	public InvokeResult creatOrganization(OrganizationDTO organization);
	
	public InvokeResult updateOrganization(OrganizationDTO organization);
	
	public InvokeResult removeOrganization(Long id);
	
	public InvokeResult removeOrganizations(Long[] ids);
	
	public List<OrganizationDTO> findAllOrganization();
	
	public Page<OrganizationDTO> pageQueryOrganization(OrganizationDTO organization, int currentPage, int pageSize);
	

}

