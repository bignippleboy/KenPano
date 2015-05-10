package com.bignippleboy.KenPano.core.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name="employees")
public class Employee extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1767800175185922092L;
	
	private String name;
	
	private int age;
	
	private String gender;
	
	@ManyToOne
	@JoinColumn(name="org_id")
	private Organization org;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}
	
	public void assignTo(Organization org) {
		setOrg(org);
		save();
	}
	
	public static List<Employee> findByAgeRange(Integer from, Integer to) {
		return getRepository().createNamedQuery("findEmployeesByAgeRange").setParameters(new Object[]{from, to}).list();
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
