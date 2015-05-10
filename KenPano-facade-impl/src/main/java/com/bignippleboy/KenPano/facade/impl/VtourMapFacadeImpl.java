package com.bignippleboy.KenPano.facade.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.bignippleboy.KenPano.application.VtourMapApplication;
import com.bignippleboy.KenPano.core.domain.VtourMap;
import com.bignippleboy.KenPano.facade.VtourMapFacade;
import com.bignippleboy.KenPano.facade.dto.MapspotDTO;
import com.bignippleboy.KenPano.facade.dto.VtourMapDTO;
import com.bignippleboy.KenPano.facade.impl.assembler.VtourMapAssembler;

@Named
public class VtourMapFacadeImpl implements VtourMapFacade {

	@Inject
	private VtourMapApplication  application;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getVtourMap(Long id) {
		return InvokeResult.success(VtourMapAssembler.toDTO(application.getVtourMap(id)));
	}
	
	public InvokeResult creatVtourMap(VtourMapDTO vtourMapDTO) {
		application.creatVtourMap(VtourMapAssembler.toEntity(vtourMapDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updateVtourMap(VtourMapDTO vtourMapDTO) {
		application.updateVtourMap(VtourMapAssembler.toEntity(vtourMapDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeVtourMap(Long id) {
		application.removeVtourMap(application.getVtourMap(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeVtourMaps(Long[] ids) {
		Set<VtourMap> vtourMaps= new HashSet<VtourMap>();
		for (Long id : ids) {
			vtourMaps.add(application.getVtourMap(id));
		}
		application.removeVtourMaps(vtourMaps);
		return InvokeResult.success();
	}
	
	public List<VtourMapDTO> findAllVtourMap() {
		return VtourMapAssembler.toDTOs(application.findAllVtourMap());
	}
	
	public Page<VtourMapDTO> pageQueryVtourMap(VtourMapDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _vtourMap from VtourMap _vtourMap   where 1=1 ");
	   	if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
	   		jpql.append(" and _vtourMap.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}		
//	   	if (queryVo.getMapImg() != null && !"".equals(queryVo.getMapImg())) {
//	   		jpql.append(" and _vtourMap.mapImg like ?");
//	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getMapImg()));
//	   	}		
	   	if (queryVo.getPosition() != null && !"".equals(queryVo.getPosition())) {
	   		jpql.append(" and _vtourMap.position like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getPosition()));
	   	}		
        Page<VtourMap> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<VtourMapDTO>(pages.getStart(), pages.getResultCount(),pageSize, VtourMapAssembler.toDTOs(pages.getData()));
	}

	@Override
	public List<MapspotDTO> findMapspotsByVtourMap(Long id) {
		StringBuilder jpql = new StringBuilder("select _vtourMap from VtourMap _vtourMap   where 1=1 ");
		
		return getQueryChannelService()
				   .createJpqlQuery(jpql.toString())
				   .list();
	}
	
	
}
