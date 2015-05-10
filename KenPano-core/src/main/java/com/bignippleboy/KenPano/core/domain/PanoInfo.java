package com.bignippleboy.KenPano.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name="PANO2")
public class PanoInfo extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4904191665347529419L;
	
	//全景图名称
	@Column(name = "name", nullable=false)
	String name;
	
	//全景图img
	@Column(name = "img")
	String img;
	
	//全景图存储相对路径
	@Column(name="path")
	String path;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
