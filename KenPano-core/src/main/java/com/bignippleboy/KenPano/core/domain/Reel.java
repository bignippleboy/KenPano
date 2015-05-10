package com.bignippleboy.KenPano.core.domain;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

public class Reel extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2323685908902274513L;
	
	private String image;
	
	private String[] frames;
	
	private String initFrame;
	
	private String footage;
	
	private String speed;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String[] getFrames() {
		return frames;
	}

	public void setFrames(String[] frames) {
		this.frames = frames;
	}

	public String getInitFrame() {
		return initFrame;
	}

	public void setInitFrame(String initFrame) {
		this.initFrame = initFrame;
	}

	public String getFootage() {
		return footage;
	}

	public void setFootage(String footage) {
		this.footage = footage;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}
	
	public void upload() {
		
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
