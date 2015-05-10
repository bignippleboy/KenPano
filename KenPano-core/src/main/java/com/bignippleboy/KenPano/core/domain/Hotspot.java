package com.bignippleboy.KenPano.core.domain;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

public class Hotspot extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4255035051285013409L;
	
	private Vtour vtour;
	
	private Reel reel;
	
	/**
	 * 判断是手工创建的热点交互，还是系统自带的导航箭头
	 */
	private boolean isCustomOrSystem;

	public Vtour getVtour() {
		return vtour;
	}

	public void setVtour(Vtour vtour) {
		this.vtour = vtour;
	}

	public Reel getReel() {
		return reel;
	}

	public void setReel(Reel reel) {
		this.reel = reel;
	}

	public boolean isCustomOrSystem() {
		return isCustomOrSystem;
	}

	public void setCustomOrSystem(boolean isCustomOrSystem) {
		this.isCustomOrSystem = isCustomOrSystem;
	}
	
	public void linkToReel() {
		
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
