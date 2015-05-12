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
import com.bignippleboy.KenPano.facade.impl.assembler.ReelspotAssembler;
import com.bignippleboy.KenPano.facade.ReelspotFacade;
import com.bignippleboy.KenPano.application.ReelspotApplication;

import com.bignippleboy.KenPano.core.domain.*;

@Named
public class ReelspotFacadeImpl implements ReelspotFacade {

	@Inject
	private ReelspotApplication  application;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getReelspot(Long id) {
		return InvokeResult.success(ReelspotAssembler.toDTO(application.getReelspot(id)));
	}
	
	public InvokeResult creatReelspot(ReelspotDTO reelspotDTO) {
		application.creatReelspot(ReelspotAssembler.toEntity(reelspotDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updateReelspot(ReelspotDTO reelspotDTO) {
		application.updateReelspot(ReelspotAssembler.toEntity(reelspotDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeReelspot(Long id) {
		application.removeReelspot(application.getReelspot(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeReelspots(Long[] ids) {
		Set<Reelspot> reelspots= new HashSet<Reelspot>();
		for (Long id : ids) {
			reelspots.add(application.getReelspot(id));
		}
		application.removeReelspots(reelspots);
		return InvokeResult.success();
	}
	
	public List<ReelspotDTO> findAllReelspot() {
		return ReelspotAssembler.toDTOs(application.findAllReelspot());
	}
	
	public Page<ReelspotDTO> pageQueryReelspot(ReelspotDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _reelspot from Reelspot _reelspot   where 1=1 ");
	   	if (queryVo.getName() != null && !"".equals(queryVo.getName())) {
	   		jpql.append(" and _reelspot.name like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getName()));
	   	}		
	   	if (queryVo.getAth() != null && !"".equals(queryVo.getAth())) {
	   		jpql.append(" and _reelspot.ath like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getAth()));
	   	}		
	   	if (queryVo.getAtv() != null && !"".equals(queryVo.getAtv())) {
	   		jpql.append(" and _reelspot.atv like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getAtv()));
	   	}		
        Page<Reelspot> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<ReelspotDTO>(pages.getStart(), pages.getResultCount(),pageSize, ReelspotAssembler.toDTOs(pages.getData()));
	}
	
	
}
