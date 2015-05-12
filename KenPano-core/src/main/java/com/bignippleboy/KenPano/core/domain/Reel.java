package com.bignippleboy.KenPano.core.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name="REEL")
public class Reel extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2323685908902274513L;
	
	/*
	 * 使用的图片集的正则表达式，如：
	 * images: 'img/360/360rotation_#####.png'
	 */
	private String images;
	
	private String uuid;
	
	/*
	 * 图片帧数
	 */
	private String frames;
	
	private String speed = "0.4";

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getFrames() {
		return frames;
	}

	public void setFrames(String frames) {
		this.frames = frames;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
