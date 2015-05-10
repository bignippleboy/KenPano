package com.bignippleboy.KenPano.facade;

import java.util.List;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import com.bignippleboy.KenPano.facade.dto.*;

public interface MapspotFacade {

	public InvokeResult getMapspot(Long id);
	
	public InvokeResult creatMapspot(MapspotDTO mapspot);
	
	public InvokeResult updateMapspot(MapspotDTO mapspot);
	
	public InvokeResult removeMapspot(Long id);
	
	public InvokeResult removeMapspots(Long[] ids);
	
	public List<MapspotDTO> findAllMapspot();
	
	public Page<MapspotDTO> pageQueryMapspot(MapspotDTO mapspot, int currentPage, int pageSize);
	

}

