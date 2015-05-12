package com.bignippleboy.KenPano.application;


import java.util.List;
import java.util.Set;
import  com.bignippleboy.KenPano.core.domain.Reelspot;

public interface ReelspotApplication {

	public Reelspot getReelspot(Long id);
	
	public void creatReelspot(Reelspot reelspot);
	
	public void updateReelspot(Reelspot reelspot);
	
	public void removeReelspot(Reelspot reelspot);
	
	public void removeReelspots(Set<Reelspot> reelspots);
	
	public List<Reelspot> findAllReelspot();
	
}

