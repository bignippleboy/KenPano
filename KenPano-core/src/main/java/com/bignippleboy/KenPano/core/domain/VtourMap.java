package com.bignippleboy.KenPano.core.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name="MAP")
public class VtourMap extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6916365618984462083L;
	
	@Column(name="NAME")
	private String name;
	
	/*
	 * 保存在uploads/maps文件夹
	 */
	@Column(name="MAP_IMG")
	private String mapImg;
	
	/*
	 * 左下、居中等，默认左下
	 */
	@Column(name="POSITION")
	private String position;
	
	/*
	 * 图片长宽比
	 */
	@Column(name="ASPECT_RADIO")
	private String AspectRadio;
	
	@Column(name="WIDTH")
	private String width;
	
	@Column(name="HEIGHT")
	private String height;
	
	@OneToOne()
	@JoinColumn(name="vtour_ID")
	private Vtour vtour;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="mapid")
	private Set<Mapspot> mapspots = new HashSet<Mapspot>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMapImg() {
		return mapImg;
	}

	public void setMapImg(String mapImg) {
		this.mapImg = mapImg;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Set<Mapspot> getMapspots() {
		return mapspots;
	}

	public void setMapspots(Set<Mapspot> mapspots) {
		this.mapspots = mapspots;
	}

	public String getAspectRadio() {
		return AspectRadio;
	}

	public void setAspectRadio(String aspectRadio) {
		AspectRadio = aspectRadio;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Vtour getVtour() {
		return vtour;
	}

	public void setVtour(Vtour vtour) {
		this.vtour = vtour;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
