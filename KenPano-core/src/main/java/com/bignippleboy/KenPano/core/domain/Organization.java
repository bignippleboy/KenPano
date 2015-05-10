package com.bignippleboy.KenPano.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name="organizations")
public class Organization extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2896024251413045272L;
	
	@Column(name="org_name")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Column(name="serial_number")
	private String serialNumber;

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
