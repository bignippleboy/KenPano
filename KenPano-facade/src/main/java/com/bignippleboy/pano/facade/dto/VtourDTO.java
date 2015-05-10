package com.bignippleboy.pano.facade.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class VtourDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8746831741150120890L;
	
	private Long id;
	private String uuid;
	private String isGyroscope;
	private String isMapOpen;
	private String isSkinOpen;
	private Set<PanoDTO> panoDTOs = new HashSet<PanoDTO>();
	public Set<PanoDTO> getPanoDTOs() {
		return panoDTOs;
	}
	public void setPanoDTOs(Set<PanoDTO> panoDTOs) {
		this.panoDTOs = panoDTOs;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getIsGyroscope() {
		return isGyroscope;
	}
	public void setIsGyroscope(String isGyroscope) {
		this.isGyroscope = isGyroscope;
	}
	public String getIsMapOpen() {
		return isMapOpen;
	}
	public void setIsMapOpen(String isMapOpen) {
		this.isMapOpen = isMapOpen;
	}
	public String getIsSkinOpen() {
		return isSkinOpen;
	}
	public void setIsSkinOpen(String isSkinOpen) {
		this.isSkinOpen = isSkinOpen;
	}
	
}
