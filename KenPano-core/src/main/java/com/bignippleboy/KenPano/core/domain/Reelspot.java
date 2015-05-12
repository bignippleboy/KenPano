package com.bignippleboy.KenPano.core.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name="REEL_SPOT")
public class Reelspot extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1841610388543889646L;
	
	private String name;
	
	private String ath;
	
	private String atv;
	
	@OneToOne()
	private Reel reel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Reel getReel() {
		return reel;
	}

	public void setReel(Reel reel) {
		this.reel = reel;
	}

	public String getAth() {
		return ath;
	}

	public void setAth(String ath) {
		this.ath = ath;
	}

	public String getAtv() {
		return atv;
	}

	public void setAtv(String atv) {
		this.atv = atv;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
