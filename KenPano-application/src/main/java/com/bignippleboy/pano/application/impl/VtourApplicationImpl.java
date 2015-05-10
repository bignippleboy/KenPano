package com.bignippleboy.pano.application.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bignippleboy.KenPano.core.domain.Vtour;
import com.bignippleboy.pano.application.VtourApplication;

@Named
@Transactional
public class VtourApplicationImpl implements VtourApplication {

	@Override
	public void createVtour(Vtour vtour) {
		vtour.save();
	}

	/**
	 * 指南针
	 * 让数据库操作和xml文件操作在同一个事务里处理
	 * @throws DocumentException 
	 */
	@Override
	public void updateVtour(Vtour vtour) {
		vtour.save();
	}

	@Override
	public Vtour getVtour(Long id) {
		return Vtour.get(Vtour.class, id);
	}

}
