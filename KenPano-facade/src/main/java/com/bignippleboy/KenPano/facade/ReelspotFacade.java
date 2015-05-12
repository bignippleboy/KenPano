package com.bignippleboy.KenPano.facade;

import java.util.List;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import com.bignippleboy.KenPano.facade.dto.*;

public interface ReelspotFacade {

	public InvokeResult getReelspot(Long id);
	
	public InvokeResult creatReelspot(ReelspotDTO reelspot);
	
	public InvokeResult updateReelspot(ReelspotDTO reelspot);
	
	public InvokeResult removeReelspot(Long id);
	
	public InvokeResult removeReelspots(Long[] ids);
	
	public List<ReelspotDTO> findAllReelspot();
	
	public Page<ReelspotDTO> pageQueryReelspot(ReelspotDTO reelspot, int currentPage, int pageSize);
	

}

