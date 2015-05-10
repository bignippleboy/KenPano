package com.bignippleboy.pano.facade.dto;

import java.io.Serializable;

public class PanoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8746831741150120890L;
	
	private Long id;
	private String srcPanoImgName;
	private String destPanoImgPath;
	private VtourDTO vDto;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSrcPanoImgName() {
		return srcPanoImgName;
	}
	public void setSrcPanoImgName(String srcPanoImgName) {
		this.srcPanoImgName = srcPanoImgName;
	}
	public String getDestPanoImgPath() {
		return destPanoImgPath;
	}
	public void setDestPanoImgPath(String destPanoImgPath) {
		this.destPanoImgPath = destPanoImgPath;
	}
	public VtourDTO getvDto() {
		return vDto;
	}
	public void setvDto(VtourDTO vDto) {
		this.vDto = vDto;
	}
}
