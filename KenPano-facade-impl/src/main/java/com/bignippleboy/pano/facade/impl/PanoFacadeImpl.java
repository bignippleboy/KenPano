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

import com.bignippleboy.KenPano.core.domain.Pano;
import com.bignippleboy.pano.application.PanoApplication;
import com.bignippleboy.pano.facade.PanoFacade;
import com.bignippleboy.pano.facade.dto.PanoDTO;
import com.bignippleboy.pano.facade.impl.assembler.PanoAssembler;

@Named
public class PanoFacadeImpl implements PanoFacade {
	
	@Inject
	private PanoApplication panoApplication;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }

	@Override
	public InvokeResult creatPano(PanoDTO panoDTO) {
		Pano pano = PanoAssembler.toEntity(panoDTO);
//		Vtour vtour = VtourAssembler.toEntity(vtourDTO);
		panoApplication.createPano(pano);
		return InvokeResult.success();
	}

	@Override
	public Page<PanoDTO> pageQueryPano(PanoDTO queryVo, int currentPage,
			int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _pano from Pano _pano   where 1=1 ");
	   	if (queryVo.getDestPanoImgPath() != null && !"".equals(queryVo.getDestPanoImgPath())) {
	   		jpql.append(" and _pano.srcPanoImgName like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getSrcPanoImgName()));
	   	}		
	   	if (queryVo.getDestPanoImgPath() != null && !"".equals(queryVo.getDestPanoImgPath())) {
	   		jpql.append(" and _pano.destPanoImgPath like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getDestPanoImgPath()));
	   	}		
        Page<Pano> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<PanoDTO>(pages.getStart(), pages.getResultCount(),pageSize, PanoAssembler.toDTOs(pages.getData()));
	}

}
