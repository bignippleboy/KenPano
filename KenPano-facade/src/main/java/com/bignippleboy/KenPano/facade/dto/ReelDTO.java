package com.bignippleboy.KenPano.facade.dto;

import java.io.Serializable;

public class ReelDTO implements Serializable {

	private Long id;

	private int version;

			
		private String images;
		
				
		private String frames;
		
		private String uuid;
		
				
		private String speed;
		
			
	
	public void setImages(String images) { 
		this.images = images;
	}

	public String getImages() {
		return this.images;
	}
		
			
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setFrames(String frames) { 
		this.frames = frames;
	}

	public String getFrames() {
		return this.frames;
	}
		
			
	
	public void setSpeed(String speed) { 
		this.speed = speed;
	}

	public String getSpeed() {
		return this.speed;
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
		ReelDTO other = (ReelDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}