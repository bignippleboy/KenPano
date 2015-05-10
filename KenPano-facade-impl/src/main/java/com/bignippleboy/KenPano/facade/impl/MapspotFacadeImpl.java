package com.bignippleboy.KenPano.facade.impl;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.text.MessageFormat;
import javax.inject.Inject;
import javax.inject.Named;
import org.dayatang.domain.InstanceFactory;
import org.dayatang.utils.Page;
import org.dayatang.querychannel.QueryChannelService;
import org.openkoala.koala.commons.InvokeResult;
import com.bignippleboy.KenPano.facade.dto.*;
import com.bignippleboy.KenPano.facade.impl.assembler.MapspotAssembler;
import com.bignippleboy.KenPano.facade.MapspotFacade;
import com.bignippleboy.KenPano.application.MapspotApplication;

import com.bignippleboy.KenPano.core.domain.*;

@Named
public class MapspotFacadeImpl implements MapspotFacade {

	@Inject
	private MapspotApplication  application;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getMapspot(Long id) {
		return InvokeResult.success(MapspotAssembler.toDTO(application.getMapspot(id)));
	}
	
	public InvokeResult creatMapspot(MapspotDTO mapspotDTO) {
		application.creatMapspot(MapspotAssembler.toEntity(mapspotDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updateMapspot(MapspotDTO mapspotDTO) {
		application.updateMapspot(MapspotAssembler.toEntity(mapspotDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeMapspot(Long id) {
		application.removeMapspot(application.getMapspot(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeMapspots(Long[] ids) {
		Set<Mapspot> mapspots= new HashSet<Mapspot>();
		for (Long id : ids) {
			mapspots.add(application.getMapspot(id));
		}
		application.removeMapspots(mapspots);
		return InvokeResult.success();
	}
	
	public List<MapspotDTO> findAllMapspot() {
		return MapspotAssembler.toDTOs(application.findAllMapspot());
	}
	
	public Page<MapspotDTO> pageQueryMapspot(MapspotDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _mapspot from Mapspot _mapspot   where 1=1 ");
	   	if (queryVo.getX_percent() != null) {
	   		jpql.append(" and _mapspot.x_percent=?");
	   		conditionVals.add(queryVo.getX_percent());
	   	}	
	   	if (queryVo.getY_percent() != null) {
	   		jpql.append(" and _mapspot.y_percent=?");
	   		conditionVals.add(queryVo.getY_percent());
	   	}	
        Page<Mapspot> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<MapspotDTO>(pages.getStart(), pages.getResultCount(),pageSize, MapspotAssembler.toDTOs(pages.getData()));
	}
	
	
}
