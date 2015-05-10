package com.bignippleboy.KenPano.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.bignippleboy.KenPano.facade.dto.*;
import com.bignippleboy.KenPano.core.domain.*;

public class MapspotAssembler {
	
	public static MapspotDTO  toDTO(Mapspot  mapspot){
		if (mapspot == null) {
			return null;
		}
    	MapspotDTO result  = new MapspotDTO();
    	result.setId(mapspot.getId());
    	result.setSceneName(mapspot.getSceneName());
    	result.setX_percent(mapspot.getX_percent());
    	result.setY_percent(mapspot.getY_percent());
//	    	result.setId (vtourMap.getId());
//     	    	result.setVersion (vtourMap.getVersion());
//     	    	result.setName (vtourMap.getName());
//     	    	result.setMapImg (vtourMap.getMapImg());
//     	    	result.setPosition (vtourMap.getPosition());
//     	    	result.setMapspots (vtourMap.getMapspots());
     	    return result;
	 }
	
	public static List<MapspotDTO>  toDTOs(Collection<Mapspot>  vtourMaps){
		if (vtourMaps == null) {
			return null;
		}
		List<MapspotDTO> results = new ArrayList<MapspotDTO>();
		for (Mapspot each : vtourMaps) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static Mapspot  toEntity(MapspotDTO  mapspotDTO){
	 	if (mapspotDTO == null) {
			return null;
		}
	 	Mapspot result  = new Mapspot();
//        result.setId (mapspotDTO.getId());
//         result.setVersion (mapspotDTO.getVersion());
//         result.setName (mapspotDTO.getName());
//         result.setMapImg (mapspotDTO.getMapImg());
//         result.setPosition (mapspotDTO.getPosition());
//         result.setMapspots (mapspotDTO.getMapspots());
 	  	return result;
	 }
	
	public static List<Mapspot> toEntities(Collection<MapspotDTO> mapspotDTOs) {
		if (mapspotDTOs == null) {
			return null;
		}
		
		List<Mapspot> results = new ArrayList<Mapspot>();
		for (MapspotDTO each : mapspotDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
