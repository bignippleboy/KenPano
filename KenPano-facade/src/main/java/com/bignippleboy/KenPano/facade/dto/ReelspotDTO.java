package com.bignippleboy.KenPano.facade.dto;

import java.io.Serializable;

public class ReelspotDTO implements Serializable {

	private Long id;

	private int version;

			
		private String atv;
		
				
		private String ath;
		
				
		private String name;
		
		private ReelDTO reelDTO;
		
		private String vtour_uuid;
		
			
	
	public void setAtv(String atv) { 
		this.atv = atv;
	}

	public String getAtv() {
		return this.atv;
	}
		
			
	
	public String getVtour_uuid() {
		return vtour_uuid;
	}

	public void setVtour_uuid(String vtour_uuid) {
		this.vtour_uuid = vtour_uuid;
	}

	public ReelDTO getReelDTO() {
		return reelDTO;
	}

	public void setReelDTO(ReelDTO reelDTO) {
		this.reelDTO = reelDTO;
	}

	public void setAth(String ath) { 
		this.ath = ath;
	}

	public String getAth() {
		return this.ath;
	}
		
			
	
	public void setName(String name) { 
		this.name = name;
	}

	public String getName() {
		return this.name;
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

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReelspotDTO other = (ReelspotDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}