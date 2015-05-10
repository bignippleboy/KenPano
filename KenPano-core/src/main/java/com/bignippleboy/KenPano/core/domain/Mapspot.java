package com.bignippleboy.KenPano.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name="MAPSPOT")
public class Mapspot extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4882359871426948591L;
	
	@Column(name="X_PERCENT")
	private String x_percent;
	
	@Column(name="Y_PERCENT")
	private String y_percent;
	
	@Column(name="SCENE_NAME")
	private String sceneName;

	public String getX_percent() {
		return x_percent;
	}

	public void setX_percent(String x_percent) {
		this.x_percent = x_percent;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public String getY_percent() {
		return y_percent;
	}

	public void setY_percent(String y_percent) {
		this.y_percent = y_percent;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
