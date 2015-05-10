package com.bignippleboy.KenPano.facade;

import java.util.List;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import com.bignippleboy.KenPano.facade.dto.*;

public interface PanoInfoFacade {

	public InvokeResult getPanoInfo(Long id);
	
	public InvokeResult creatPanoInfo(PanoInfoDTO panoInfo);
	
	public InvokeResult updatePanoInfo(PanoInfoDTO panoInfo);
	
	public InvokeResult removePanoInfo(Long id);
	
	public InvokeResult removePanoInfos(Long[] ids);
	
	public List<PanoInfoDTO> findAllPanoInfo();
	
	public Page<PanoInfoDTO> pageQueryPanoInfo(PanoInfoDTO panoInfo, int currentPage, int pageSize);
	

}

