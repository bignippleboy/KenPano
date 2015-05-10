package com.bignippleboy.pano.facade.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.dayatang.domain.InstanceFactory;
import org.dayatang.querychannel.QueryChannelService;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.bignippleboy.KenPano.core.domain.Vtour;
import com.bignippleboy.KenPano.core.domain.VtourMap;
import com.bignippleboy.KenPano.facade.dto.VtourMapDTO;
import com.bignippleboy.KenPano.facade.impl.assembler.VtourMapAssembler;
import com.bignippleboy.pano.application.VtourApplication;
import com.bignippleboy.pano.facade.VtourFacade;
import com.bignippleboy.pano.facade.dto.VtourDTO;
import com.bignippleboy.pano.facade.impl.assembler.VtourAssembler;

@Named
public class VtourFacadeImpl implements VtourFacade {
	
	@Inject
	private VtourApplication VtourApplication;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }

	@Override
	public InvokeResult creatVtour(VtourDTO VtourDTO) {
		Vtour Vtour = VtourAssembler.toEntity(VtourDTO);
		VtourApplication.createVtour(Vtour);
		return InvokeResult.success();
	}

	@Override
	public Page<VtourDTO> pageQueryVtour(VtourDTO queryVo, int currentPage,
			int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _Vtour from Vtour _Vtour   where 1=1 ");
	   	if (queryVo.getUuid() != null && !"".equals(queryVo.getUuid())) {
	   		jpql.append(" and _Vtour.srcVtourImgName like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getUuid()));
	   	}		
        Page<Vtour> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<VtourDTO>(pages.getStart(), pages.getResultCount(),pageSize, VtourAssembler.toDTOs(pages.getData()));
	}
	
	public VtourMapDTO findMapspotsByVtour(VtourDTO queryVo) {
//		List<Object> conditionVals = new ArrayList<Object>();
//	   	StringBuilder jpql = new StringBuilder("select _Vtour.vtourMap.mapspots from Vtour _Vtour   where 1=1 ");
//	   	if (queryVo.getUuid() != null && !"".equals(queryVo.getUuid())) {
//	   		jpql.append(" and _Vtour.id = ?");
//	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getId()));
//	   	}		
//	   	List<Mapspot> mapspots = getQueryChannelService()
//	   			.createJpqlQuery(jpql.toString())
//	   			.list();
		VtourMap vtourMap = Vtour.get(Vtour.class, queryVo.getId()).getVtourMap();
	   	return VtourMapAssembler.toDTO(vtourMap);
	}
	
	public List<VtourDTO> getAllVtour() {
		StringBuilder jpql = new StringBuilder("select _Vtour from Vtour _Vtour   where 1=1 ");
		List<Vtour> vtours = getQueryChannelService()
				.createJpqlQuery(jpql.toString())
				.list();
		return VtourAssembler.toDTOs(vtours);
	}

	@Override
	public InvokeResult updateVtour(VtourDTO vtourDTO) {
		VtourApplication.updateVtour(VtourAssembler.toEntity(vtourDTO));
		return InvokeResult.success();
	}

	@Override
	public InvokeResult getVtour(Long id) {
		return InvokeResult.success(VtourAssembler.toDTO(VtourApplication.getVtour(id)));
	}

}
