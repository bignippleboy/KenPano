package com.bignippleboy.KenPano.application;


import java.util.List;
import java.util.Set;
import  com.bignippleboy.KenPano.core.domain.Reel;

public interface ReelApplication {

	public Reel getReel(Long id);
	
	public void creatReel(Reel reel);
	
	public void updateReel(Reel reel);
	
	public void removeReel(Reel reel);
	
	public void removeReels(Set<Reel> reels);
	
	public List<Reel> findAllReel();
	
}

