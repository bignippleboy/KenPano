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
import com.bignippleboy.KenPano.facade.impl.assembler.ReelAssembler;
import com.bignippleboy.KenPano.facade.ReelFacade;
import com.bignippleboy.KenPano.application.ReelApplication;

import com.bignippleboy.KenPano.core.domain.*;

@Named
public class ReelFacadeImpl implements ReelFacade {

	@Inject
	private ReelApplication  application;

	private QueryChannelService queryChannel;

    private QueryChannelService getQueryChannelService(){
       if(queryChannel==null){
          queryChannel = InstanceFactory.getInstance(QueryChannelService.class,"queryChannel");
       }
     return queryChannel;
    }
	
	public InvokeResult getReel(Long id) {
		return InvokeResult.success(ReelAssembler.toDTO(application.getReel(id)));
	}
	
	public InvokeResult creatReel(ReelDTO reelDTO) {
		application.creatReel(ReelAssembler.toEntity(reelDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult updateReel(ReelDTO reelDTO) {
		application.updateReel(ReelAssembler.toEntity(reelDTO));
		return InvokeResult.success();
	}
	
	public InvokeResult removeReel(Long id) {
		application.removeReel(application.getReel(id));
		return InvokeResult.success();
	}
	
	public InvokeResult removeReels(Long[] ids) {
		Set<Reel> reels= new HashSet<Reel>();
		for (Long id : ids) {
			reels.add(application.getReel(id));
		}
		application.removeReels(reels);
		return InvokeResult.success();
	}
	
	public List<ReelDTO> findAllReel() {
		return ReelAssembler.toDTOs(application.findAllReel());
	}
	
	public Page<ReelDTO> pageQueryReel(ReelDTO queryVo, int currentPage, int pageSize) {
		List<Object> conditionVals = new ArrayList<Object>();
	   	StringBuilder jpql = new StringBuilder("select _reel from Reel _reel   where 1=1 ");
	   	if (queryVo.getImages() != null && !"".equals(queryVo.getImages())) {
	   		jpql.append(" and _reel.images like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getImages()));
	   	}		
	   	if (queryVo.getFrames() != null && !"".equals(queryVo.getFrames())) {
	   		jpql.append(" and _reel.frames like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getFrames()));
	   	}		
	   	if (queryVo.getSpeed() != null && !"".equals(queryVo.getSpeed())) {
	   		jpql.append(" and _reel.speed like ?");
	   		conditionVals.add(MessageFormat.format("%{0}%", queryVo.getSpeed()));
	   	}		
        Page<Reel> pages = getQueryChannelService()
		   .createJpqlQuery(jpql.toString())
		   .setParameters(conditionVals)
		   .setPage(currentPage, pageSize)
		   .pagedList();
		   
        return new Page<ReelDTO>(pages.getStart(), pages.getResultCount(),pageSize, ReelAssembler.toDTOs(pages.getData()));
	}
	
	
}
