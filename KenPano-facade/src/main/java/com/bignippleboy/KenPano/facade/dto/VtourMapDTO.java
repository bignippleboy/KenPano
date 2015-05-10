package com.bignippleboy.KenPano.facade.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bignippleboy.pano.facade.dto.VtourDTO;

public class VtourMapDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3257355300768523983L;

	private Long id;

	private int version;

			
		private String name;
		
				
		private String position;
		
				
		private String mapUUID;
		
		private String aspectRadio;
		
		private String width;
		
		private String height;
		
		private VtourDTO vtourDTO;
		
//		private Set<MapspotDTO> mapspotDTOs = new HashSet<MapspotDTO>();
		
		private List<MapspotDTO> mapspotDTOs = new ArrayList<MapspotDTO>();
			
	
	public void setName(String name) { 
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
		
			
	
	public void setPosition(String position) { 
		this.position = position;
	}

	public String getPosition() {
		return this.position;
	}
		

	public String getMapUUID() {
		return mapUUID;
	}

	public void setMapUUID(String mapUUID) {
		this.mapUUID = mapUUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

    public String getAspectRadio() {
		return aspectRadio;
	}

	public void setAspectRadio(String aspectRadio) {
		this.aspectRadio = aspectRadio;
	}

//	public Set<MapspotDTO> getMapspotDTOs() {
//		return mapspotDTOs;
//	}
//
//	public void setMapspotDTOs(Set<MapspotDTO> mapspotDTOs) {
//		this.mapspotDTOs = mapspotDTOs;
//	}
	

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public List<MapspotDTO> getMapspotDTOs() {
		return mapspotDTOs;
	}

	public void setMapspotDTOs(List<MapspotDTO> mapspotDTOs) {
		this.mapspotDTOs = mapspotDTOs;
	}

	public VtourDTO getVtourDTO() {
		return vtourDTO;
	}

	public void setVtourDTO(VtourDTO vtourDTO) {
		this.vtourDTO = vtourDTO;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VtourMapDTO other = (VtourMapDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}