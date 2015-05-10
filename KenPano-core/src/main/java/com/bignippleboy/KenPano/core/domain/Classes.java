package com.bignippleboy.KenPano.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name="classes")
public class Classes extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6860617998073823837L;
	
	@Column(name="class_name")
	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
