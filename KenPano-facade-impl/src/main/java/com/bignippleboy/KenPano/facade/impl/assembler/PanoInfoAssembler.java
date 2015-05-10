package com.bignippleboy.KenPano.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.bignippleboy.KenPano.facade.dto.*;
import com.bignippleboy.KenPano.core.domain.*;

public class PanoInfoAssembler {
	
	public static PanoInfoDTO  toDTO(PanoInfo  panoInfo){
		if (panoInfo == null) {
			return null;
		}
    	PanoInfoDTO result  = new PanoInfoDTO();
	    	result.setId (panoInfo.getId());
     	    	result.setVersion (panoInfo.getVersion());
     	    	result.setName (panoInfo.getName());
     	    	result.setImg (panoInfo.getImg());
     	    	result.setPath (panoInfo.getPath());
     	    return result;
	 }
	
	public static List<PanoInfoDTO>  toDTOs(Collection<PanoInfo>  panoInfos){
		if (panoInfos == null) {
			return null;
		}
		List<PanoInfoDTO> results = new ArrayList<PanoInfoDTO>();
		for (PanoInfo each : panoInfos) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static PanoInfo  toEntity(PanoInfoDTO  panoInfoDTO){
	 	if (panoInfoDTO == null) {
			return null;
		}
	 	PanoInfo result  = new PanoInfo();
        result.setId (panoInfoDTO.getId());
         result.setVersion (panoInfoDTO.getVersion());
         result.setName (panoInfoDTO.getName());
         result.setImg (panoInfoDTO.getImg());
         result.setPath (panoInfoDTO.getPath());
 	  	return result;
	 }
	
	public static List<PanoInfo> toEntities(Collection<PanoInfoDTO> panoInfoDTOs) {
		if (panoInfoDTOs == null) {
			return null;
		}
		
		List<PanoInfo> results = new ArrayList<PanoInfo>();
		for (PanoInfoDTO each : panoInfoDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
