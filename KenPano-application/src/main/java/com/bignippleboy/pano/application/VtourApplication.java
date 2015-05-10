package com.bignippleboy.pano.application;

import com.bignippleboy.KenPano.core.domain.Vtour;

public interface VtourApplication {
	public void createVtour(Vtour vtour);
	
	public Vtour getVtour(Long id);
	
	public void updateVtour(Vtour vtour);
}
