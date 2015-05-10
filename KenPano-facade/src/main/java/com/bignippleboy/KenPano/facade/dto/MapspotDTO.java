package com.bignippleboy.KenPano.facade.dto;

import java.io.Serializable;

public class MapspotDTO implements Serializable {

	private Long id;

	private int version;

							
		private String x_percent;
		
								
		private String y_percent;
		
		private String sceneName;
		
							
	
	public void setX_percent(String x_percent) { 
		this.x_percent = x_percent;
	}

	public String getX_percent() {
		return this.x_percent;
	}
		
							
	
	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public void setY_percent(String y_percent) { 
		this.y_percent = y_percent;
	}

	public String getY_percent() {
		return this.y_percent;
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
		MapspotDTO other = (MapspotDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}