package com.bignippleboy.KenPano.application.impl;

import java.util.List;
import java.util.Set;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;
import com.bignippleboy.KenPano.application.PanoInfoApplication;
import com.bignippleboy.KenPano.core.domain.PanoInfo;

@Named
@Transactional
public class PanoInfoApplicationImpl implements PanoInfoApplication {

	public PanoInfo getPanoInfo(Long id) {
		return PanoInfo.get(PanoInfo.class, id);
	}
	
	public void creatPanoInfo(PanoInfo panoInfo) {
		panoInfo.save();
	}
	
	public void updatePanoInfo(PanoInfo panoInfo) {
		panoInfo .save();
	}
	
	public void removePanoInfo(PanoInfo panoInfo) {
		if(panoInfo != null){
			panoInfo.remove();
		}
	}
	
	public void removePanoInfos(Set<PanoInfo> panoInfos) {
		for (PanoInfo panoInfo : panoInfos) {
			panoInfo.remove();
		}
	}
	
	public List<PanoInfo> findAllPanoInfo() {
		return PanoInfo.findAll(PanoInfo.class);
	}
	
}
