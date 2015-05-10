package com.bignippleboy.KenPano.application.impl;

import java.util.List;
import java.util.Set;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;
import com.bignippleboy.KenPano.application.VtourMapApplication;
import com.bignippleboy.KenPano.core.domain.VtourMap;

@Named
@Transactional
public class VtourMapApplicationImpl implements VtourMapApplication {

	public VtourMap getVtourMap(Long id) {
		return VtourMap.get(VtourMap.class, id);
	}
	
	public void creatVtourMap(VtourMap vtourMap) {
		vtourMap.save();
	}
	
	public void updateVtourMap(VtourMap vtourMap) {
		vtourMap .save();
	}
	
	public void removeVtourMap(VtourMap vtourMap) {
		if(vtourMap != null){
			vtourMap.remove();
		}
	}
	
	public void removeVtourMaps(Set<VtourMap> vtourMaps) {
		for (VtourMap vtourMap : vtourMaps) {
			vtourMap.remove();
		}
	}
	
	public List<VtourMap> findAllVtourMap() {
		return VtourMap.findAll(VtourMap.class);
	}
	
}
