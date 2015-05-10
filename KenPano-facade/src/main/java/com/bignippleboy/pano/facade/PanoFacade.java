package com.bignippleboy.pano.facade;

import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.bignippleboy.pano.facade.dto.PanoDTO;

public interface PanoFacade {
	public InvokeResult creatPano(PanoDTO pano);
	
	public Page<PanoDTO> pageQueryPano(PanoDTO pano, int currentPage, int pageSize);
}
