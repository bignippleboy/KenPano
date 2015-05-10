package com.bignippleboy.KenPano.application;


import java.util.List;
import java.util.Set;
import  com.bignippleboy.KenPano.core.domain.VtourMap;

public interface VtourMapApplication {

	public VtourMap getVtourMap(Long id);
	
	public void creatVtourMap(VtourMap vtourMap);
	
	public void updateVtourMap(VtourMap vtourMap);
	
	public void removeVtourMap(VtourMap vtourMap);
	
	public void removeVtourMaps(Set<VtourMap> vtourMaps);
	
	public List<VtourMap> findAllVtourMap();
	
}

