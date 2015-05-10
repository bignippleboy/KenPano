package com.bignippleboy.KenPano.application;


import java.util.List;
import java.util.Set;
import  com.bignippleboy.KenPano.core.domain.PanoInfo;

public interface PanoInfoApplication {

	public PanoInfo getPanoInfo(Long id);
	
	public void creatPanoInfo(PanoInfo panoInfo);
	
	public void updatePanoInfo(PanoInfo panoInfo);
	
	public void removePanoInfo(PanoInfo panoInfo);
	
	public void removePanoInfos(Set<PanoInfo> panoInfos);
	
	public List<PanoInfo> findAllPanoInfo();
	
}

