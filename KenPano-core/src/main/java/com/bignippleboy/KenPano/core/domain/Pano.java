package com.bignippleboy.KenPano.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name="PANO")
public class Pano extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7939245474045147888L;

	@Column(name="SRC_PANO_IMG_NAME")
	private String srcPanoImgName;
	
	@Column(name="DEST_PANO_IMG_NAME")
	private String destPanoImgName;
	
	@Column(name="ISVALID")
	private boolean isValid;
	
	public String getSrcPanoImgName() {
		return srcPanoImgName;
	}

	public void setSrcPanoImgName(String srcPanoImgName) {
		this.srcPanoImgName = srcPanoImgName;
	}

	public String getDestPanoImgName() {
		return destPanoImgName;
	}

	public void setDestPanoImgName(String destPanoImgName) {
		this.destPanoImgName = destPanoImgName;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
