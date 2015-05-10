package com.bignippleboy.pano.application.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bignippleboy.KenPano.core.domain.Pano;
import com.bignippleboy.KenPano.core.domain.Vtour;
import com.bignippleboy.pano.application.PanoApplication;

@Named
@Transactional
public class PanoApplicationImpl implements PanoApplication {

	@Override
	public void createPano(Pano pano) {
		pano.save();
	}

	@Override
	public void createVtour(Vtour vtour) {
		// TODO Auto-generated method stub
		vtour.save();
	}

}
