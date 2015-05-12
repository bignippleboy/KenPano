package com.bignippleboy.KenPano.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bignippleboy.KenPano.facade.dto.*;
import com.bignippleboy.KenPano.core.domain.*;

public class ReelspotAssembler {
	
	public static ReelspotDTO  toDTO(Reelspot  reelspot){
		if (reelspot == null) {
			return null;
		}
    	ReelspotDTO result  = new ReelspotDTO();
	    	result.setId (reelspot.getId());
     	    	result.setVersion (reelspot.getVersion());
     	    	result.setName (reelspot.getName());
     	    	result.setAth (reelspot.getAth());
     	    	result.setAtv (reelspot.getAtv());
     	    return result;
	 }
	
	public static List<ReelspotDTO>  toDTOs(Collection<Reelspot>  reelspots){
		if (reelspots == null) {
			return null;
		}
		List<ReelspotDTO> results = new ArrayList<ReelspotDTO>();
		for (Reelspot each : reelspots) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static Reelspot  toEntity(ReelspotDTO  reelspotDTO){
	 	if (reelspotDTO == null) {
			return null;
		}
	 	Reelspot result = null;
		if(reelspotDTO.getId() == null || Reelspot.get(Reelspot.class, reelspotDTO.getId()) == null) {
			result = new Reelspot();
	         result.setName (reelspotDTO.getName());
	         result.setAth (reelspotDTO.getAth());
	         result.setAtv (reelspotDTO.getAtv());
		} else {
			result = Reelspot.get(Reelspot.class, reelspotDTO.getId());
		}
		
		Reel reel = null;
		if(reelspotDTO.getReelDTO() != null && reelspotDTO.getReelDTO().getId() != null && (reel = Reel.get(Reel.class, Long.valueOf(reelspotDTO.getReelDTO().getId()))) != null) {
			result.setReel(reel);
		}
		
 	  	return result;
	 }
	
	public static List<Reelspot> toEntities(Collection<ReelspotDTO> reelspotDTOs) {
		if (reelspotDTOs == null) {
			return null;
		}
		
		List<Reelspot> results = new ArrayList<Reelspot>();
		for (ReelspotDTO each : reelspotDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
