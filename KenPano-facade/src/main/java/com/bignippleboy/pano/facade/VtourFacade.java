package com.bignippleboy.pano.facade;

import java.util.List;

import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;

import com.bignippleboy.KenPano.facade.dto.VtourMapDTO;
import com.bignippleboy.pano.facade.dto.VtourDTO;

public interface VtourFacade {
	public InvokeResult creatVtour(VtourDTO vtour);
	
	public InvokeResult getVtour(Long id);
	
	public InvokeResult updateVtour(VtourDTO vtourDTO);
	
	public Page<VtourDTO> pageQueryVtour(VtourDTO vtour, int currentPage, int pageSize);
	
	public List<VtourDTO> getAllVtour() ;
	
	public VtourMapDTO findMapspotsByVtour(VtourDTO queryVo);
}
