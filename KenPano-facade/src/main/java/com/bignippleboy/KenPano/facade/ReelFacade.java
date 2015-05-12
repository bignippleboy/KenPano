package com.bignippleboy.KenPano.facade;

import java.util.List;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import com.bignippleboy.KenPano.facade.dto.*;

public interface ReelFacade {

	public InvokeResult getReel(Long id);
	
	public InvokeResult creatReel(ReelDTO reel);
	
	public InvokeResult updateReel(ReelDTO reel);
	
	public InvokeResult removeReel(Long id);
	
	public InvokeResult removeReels(Long[] ids);
	
	public List<ReelDTO> findAllReel();
	
	public Page<ReelDTO> pageQueryReel(ReelDTO reel, int currentPage, int pageSize);
	

}

