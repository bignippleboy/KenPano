package com.bignippleboy.KenPano.facade.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.beanutils.BeanUtils;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.bignippleboy.KenPano.application.OrganizationApplication;
import com.bignippleboy.KenPano.core.domain.Organization;
import com.bignippleboy.KenPano.facade.OrganizationFacade;
import com.bignippleboy.KenPano.facade.dto.OrganizationDTO;
import com.bignippleboy.KenPano.facade.impl.assembler.OrganizationAssembler;

@Named
public class OrganizationFacadeImpl implements OrganizationFacade {

	@Inject
	private OrganizationApplication  application;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getOrganization(Long id) {
		return InvokeResult.success(OrganizationAssembler.toDTO(application.getOrganization(id)));
	}
	
	public InvokeResult creatOrganization(OrganizationDTO organizationDTO) {
		application.creatOrganization(OrganizationAssembler.toEntity(organizationDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updateOrganization(OrganizationDTO organizationDTO) {
		application.updateOrganization(OrganizationAssembler.toEntity(organizationDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeOrganization(Long id) {
		application.removeOrganization(application.getOrganization(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeOrganizations(Long[] ids) {
		Set<Organization> organizations= new HashSet<Organization>();
		for (Long id : ids) {
			organizations.add(application.getOrganization(id));
		}
		application.removeOrganizations(organizations);
		return InvokeResult.success();
	}
	
	public List<OrganizationDTO> findAllOrganization() {
		return OrganizationAssembler.toDTOs(application.findAllOrganization());
	}
	
	public Page<OrganizationDTO> pageQueryOrganization(OrganizationDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _organization from Organization _organization  where 1=1 ");
	   	if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
	   		jpql.append(" and _organization.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}		
	   	if (queryVo.getSerialNumber() != null && !"".equals(queryVo.getSerialNumber())) {
	   		jpql.append(" and _organization.serialNumber like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getSerialNumber()));
	   	}		
        Page<Organization> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<OrganizationDTO>(pages.getStart(), pages.getResultCount(),pageSize, OrganizationAssembler.toDTOs(pages.getData()));
	}
	
	public OrganizationDTO findOrgByOrganization(Long id) {
		String jpql = "select e from Organization o right join o.org e where o.id=?";
		Organization result = (Organization) getQueryChannelService().createJpqlQuery(jpql).setParameters(new Object[] { id }).singleResult();
		OrganizationDTO  dto = new OrganizationDTO();
		if (result != null) {
			try {
				BeanUtils.copyProperties(dto, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}

	
}
