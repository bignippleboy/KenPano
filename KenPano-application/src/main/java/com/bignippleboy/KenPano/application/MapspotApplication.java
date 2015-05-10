package com.bignippleboy.KenPano.application;


import java.util.List;
import java.util.Set;
import  com.bignippleboy.KenPano.core.domain.Mapspot;

public interface MapspotApplication {

	public Mapspot getMapspot(Long id);
	
	public void creatMapspot(Mapspot mapspot);
	
	public void updateMapspot(Mapspot mapspot);
	
	public void removeMapspot(Mapspot mapspot);
	
	public void removeMapspots(Set<Mapspot> mapspots);
	
	public List<Mapspot> findAllMapspot();
	
}

