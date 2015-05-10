package com.bignippleboy.KenPano.application.impl;

import java.util.List;
import java.util.Set;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;
import com.bignippleboy.KenPano.application.MapspotApplication;
import com.bignippleboy.KenPano.core.domain.Mapspot;

@Named
@Transactional
public class MapspotApplicationImpl implements MapspotApplication {

	public Mapspot getMapspot(Long id) {
		return Mapspot.get(Mapspot.class, id);
	}
	
	public void creatMapspot(Mapspot mapspot) {
		mapspot.save();
	}
	
	public void updateMapspot(Mapspot mapspot) {
		mapspot .save();
	}
	
	public void removeMapspot(Mapspot mapspot) {
		if(mapspot != null){
			mapspot.remove();
		}
	}
	
	public void removeMapspots(Set<Mapspot> mapspots) {
		for (Mapspot mapspot : mapspots) {
			mapspot.remove();
		}
	}
	
	public List<Mapspot> findAllMapspot() {
		return Mapspot.findAll(Mapspot.class);
	}
	
}
