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
import com.bignippleboy.KenPano.facade.impl.assembler.PanoInfoAssembler;
import com.bignippleboy.KenPano.facade.PanoInfoFacade;
import com.bignippleboy.KenPano.application.PanoInfoApplication;

import com.bignippleboy.KenPano.core.domain.*;

@Named
public class PanoInfoFacadeImpl implements PanoInfoFacade {

	@Inject
	private PanoInfoApplication application;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getPanoInfo(Long id) {
		return InvokeResult.success(PanoInfoAssembler.toDTO(application.getPanoInfo(id)));
	}
	
	public InvokeResult creatPanoInfo(PanoInfoDTO panoInfoDTO) {
		application.creatPanoInfo(PanoInfoAssembler.toEntity(panoInfoDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updatePanoInfo(PanoInfoDTO panoInfoDTO) {
		application.updatePanoInfo(PanoInfoAssembler.toEntity(panoInfoDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removePanoInfo(Long id) {
		application.removePanoInfo(application.getPanoInfo(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removePanoInfos(Long[] ids) {
		Set<PanoInfo> panoInfos= new HashSet<PanoInfo>();
		for (Long id : ids) {
			panoInfos.add(application.getPanoInfo(id));
		}
		application.removePanoInfos(panoInfos);
		return InvokeResult.success();
	}
	
	public List<PanoInfoDTO> findAllPanoInfo() {
		return PanoInfoAssembler.toDTOs(application.findAllPanoInfo());
	}
	
	public Page<PanoInfoDTO> pageQueryPanoInfo(PanoInfoDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _panoInfo from PanoInfo _panoInfo   where 1=1 ");
	   	if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
	   		jpql.append(" and _panoInfo.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}		
	   	if (queryVo.getImg() != null && !"".equals(queryVo.getImg())) {
	   		jpql.append(" and _panoInfo.img like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getImg()));
	   	}		
	   	if (queryVo.getPath() != null && !"".equals(queryVo.getPath())) {
	   		jpql.append(" and _panoInfo.path like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getPath()));
	   	}		
        Page<PanoInfo> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<PanoInfoDTO>(pages.getStart(), pages.getResultCount(),pageSize, PanoInfoAssembler.toDTOs(pages.getData()));
	}
	
	
}
