package com.bignippleboy.KenPano.facade;

import java.util.List;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import com.bignippleboy.KenPano.facade.dto.*;

public interface VtourMapFacade {

	public InvokeResult getVtourMap(Long id);
	
	public InvokeResult creatVtourMap(VtourMapDTO vtourMap);
	
	public InvokeResult updateVtourMap(VtourMapDTO vtourMap);
	
	public InvokeResult removeVtourMap(Long id);
	
	public InvokeResult removeVtourMaps(Long[] ids);
	
	public List<VtourMapDTO> findAllVtourMap();
	
	public Page<VtourMapDTO> pageQueryVtourMap(VtourMapDTO vtourMap, int currentPage, int pageSize);
	


			
	public List<MapspotDTO> findMapspotsByVtourMap(Long id);		
	
}

