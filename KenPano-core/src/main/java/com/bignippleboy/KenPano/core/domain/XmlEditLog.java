package com.bignippleboy.KenPano.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

@Entity
@Table(name="XML_EDIT_LOG")
public class XmlEditLog extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 951700127076474165L;
	
	@Column(name="VTOUR_UUID")
	private String vtour_uuid;
	
	@Column(name="O_XML_STR", columnDefinition="BLOB")
	private String orginal_xml_str;
	
	@Column(name="C_XML_STR", columnDefinition="BLOB")
	private String changed_xml_str;
	
	@Column(name="CHANGE_TIME")
	private Date change_time;

	public String getVtour_uuid() {
		return vtour_uuid;
	}

	public void setVtour_uuid(String vtour_uuid) {
		this.vtour_uuid = vtour_uuid;
	}

	public String getOrginal_xml_str() {
		return orginal_xml_str;
	}

	public void setOrginal_xml_str(String orginal_xml_str) {
		this.orginal_xml_str = orginal_xml_str;
	}

	public String getChanged_xml_str() {
		return changed_xml_str;
	}

	public void setChanged_xml_str(String changed_xml_str) {
		this.changed_xml_str = changed_xml_str;
	}

	public Date getChange_time() {
		return change_time;
	}

	public void setChange_time(Date change_time) {
		this.change_time = change_time;
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
