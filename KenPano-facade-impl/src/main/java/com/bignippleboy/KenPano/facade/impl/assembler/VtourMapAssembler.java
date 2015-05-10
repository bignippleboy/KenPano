package com.bignippleboy.KenPano.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bignippleboy.KenPano.facade.dto.*;
import com.bignippleboy.KenPano.core.domain.*;
import com.bignippleboy.pano.facade.dto.PanoDTO;
import com.bignippleboy.pano.facade.dto.VtourDTO;

public class VtourMapAssembler {
	
	public static VtourMapDTO  toDTO(VtourMap  vtourMap){
		if (vtourMap == null) {
			return null;
		}
		VtourMapDTO result  = new VtourMapDTO();
		result.setId(vtourMap.getId());
		result.setMapUUID(vtourMap.getMapImg());
		result.setName(vtourMap.getName());
		result.setAspectRadio(vtourMap.getAspectRadio());
		result.setWidth(vtourMap.getWidth());
		result.setHeight(vtourMap.getHeight());
		if(vtourMap.getMapspots() != null) {
			for(Mapspot spot : vtourMap.getMapspots()) {
				MapspotDTO spotDto = new MapspotDTO();
				spotDto.setX_percent(spot.getX_percent());
				spotDto.setY_percent(spot.getY_percent());
				spotDto.setSceneName(spot.getSceneName());
				result.getMapspotDTOs().add(spotDto);
			}
		}
		
		Vtour vtour = vtourMap.getVtour();
		if(vtour != null) {
			VtourDTO vtourDTO = new VtourDTO();
			vtourDTO.setId(vtour.getId());
			vtourDTO.setIsGyroscope(vtour.isGyroscope()?"true":"false");
			vtourDTO.setUuid(vtour.getUuid());
			if(vtour.getPanos() != null) {
				for(Pano pano : vtour.getPanos()) {
					PanoDTO panoDTO = new PanoDTO();
					panoDTO.setId(pano.getId());
					panoDTO.setDestPanoImgPath(pano.getDestPanoImgName());
					panoDTO.setSrcPanoImgName(pano.getSrcPanoImgName());
					vtourDTO.getPanoDTOs().add(panoDTO);
				}
			}
			result.setVtourDTO(vtourDTO);
		}
    	    
		return result;
	 }
	
	public static List<VtourMapDTO>  toDTOs(Collection<VtourMap>  vtourMaps){
		if (vtourMaps == null) {
			return null;
		}
		List<VtourMapDTO> results = new ArrayList<VtourMapDTO>();
		for (VtourMap each : vtourMaps) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static VtourMap  toEntity(VtourMapDTO  vtourMapDTO){
	 	if (vtourMapDTO == null) {
			return null;
		}
		VtourMap result = null;
		if(vtourMapDTO.getId() == null || VtourMap.get(VtourMap.class, vtourMapDTO.getId()) == null) {
			result = new VtourMap();
		 	result.setMapImg(vtourMapDTO.getMapUUID());
		 	result.setName(vtourMapDTO.getName());
		 	result.setAspectRadio(vtourMapDTO.getAspectRadio());
		 	result.setWidth(vtourMapDTO.getWidth());
		 	result.setHeight(vtourMapDTO.getHeight());
		} else {
			result = VtourMap.get(VtourMap.class, vtourMapDTO.getId());
		}
		
		Vtour vtour = null;
		if(vtourMapDTO.getVtourDTO() != null && vtourMapDTO.getVtourDTO().getId() != null && (vtour = Vtour.get(Vtour.class, Long.valueOf(vtourMapDTO.getVtourDTO().getId()))) != null) {
			result.setVtour(vtour);
		}
		
	 	if(vtourMapDTO.getMapspotDTOs() != null) {
		 	for(MapspotDTO mapspotDTO : vtourMapDTO.getMapspotDTOs()) {
		 		Mapspot mapspot = new Mapspot();
		 		mapspot.setX_percent(mapspotDTO.getX_percent());
		 		mapspot.setY_percent(mapspotDTO.getY_percent());
		 		mapspot.setSceneName(mapspotDTO.getSceneName());
		 		result.getMapspots().add(mapspot);
		 	}
	 	}
	 	
 	  	return result;
	 }
	
	public static List<VtourMap> toEntities(Collection<VtourMapDTO> mapspotDTOs) {
		if (mapspotDTOs == null) {
			return null;
		}
		
		List<VtourMap> results = new ArrayList<VtourMap>();
		for (VtourMapDTO each : mapspotDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
