package com.bignippleboy.KenPano.application.impl;

import java.util.List;
import java.util.Set;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;
import com.bignippleboy.KenPano.application.ReelApplication;
import com.bignippleboy.KenPano.core.domain.Reel;

@Named
@Transactional
public class ReelApplicationImpl implements ReelApplication {

	public Reel getReel(Long id) {
		return Reel.get(Reel.class, id);
	}
	
	public void creatReel(Reel reel) {
		reel.save();
	}
	
	public void updateReel(Reel reel) {
		reel .save();
	}
	
	public void removeReel(Reel reel) {
		if(reel != null){
			reel.remove();
		}
	}
	
	public void removeReels(Set<Reel> reels) {
		for (Reel reel : reels) {
			reel.remove();
		}
	}
	
	public List<Reel> findAllReel() {
		return Reel.findAll(Reel.class);
	}
	
}
