package com.bignippleboy.KenPano.application.impl;

import java.util.List;
import java.util.Set;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;
import com.bignippleboy.KenPano.application.ReelspotApplication;
import com.bignippleboy.KenPano.core.domain.Reelspot;

@Named
@Transactional
public class ReelspotApplicationImpl implements ReelspotApplication {

	public Reelspot getReelspot(Long id) {
		return Reelspot.get(Reelspot.class, id);
	}
	
	public void creatReelspot(Reelspot reelspot) {
		reelspot.save();
	}
	
	public void updateReelspot(Reelspot reelspot) {
		reelspot .save();
	}
	
	public void removeReelspot(Reelspot reelspot) {
		if(reelspot != null){
			reelspot.remove();
		}
	}
	
	public void removeReelspots(Set<Reelspot> reelspots) {
		for (Reelspot reelspot : reelspots) {
			reelspot.remove();
		}
	}
	
	public List<Reelspot> findAllReelspot() {
		return Reelspot.findAll(Reelspot.class);
	}
	
}
