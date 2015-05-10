package com.bignippleboy.pano.facade.dto;

import java.io.Serializable;
import java.util.Date;

public class XmlEditLogDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1043211385028663314L;
	
	private String vtour_uuid;
	
	private String orginal_xml_str;
	
	private String changed_xml_str;
	
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

}
