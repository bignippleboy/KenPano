package com.bignippleboy.KenPano.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name="users")
public class Users extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4887227100364991239L;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Classes classes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
