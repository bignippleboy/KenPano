package com.bignippleboy.KenPano.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.bignippleboy.KenPano.facade.dto.*;
import com.bignippleboy.KenPano.core.domain.*;

public class ReelAssembler {
	
	public static ReelDTO  toDTO(Reel  reel){
		if (reel == null) {
			return null;
		}
    	ReelDTO result  = new ReelDTO();
	    	result.setId (reel.getId());
     	    	result.setVersion (reel.getVersion());
     	    	result.setImages (reel.getImages());
     	    	result.setFrames (reel.getFrames());
     	    	result.setUuid(reel.getUuid());
     	    return result;
	 }
	
	public static List<ReelDTO>  toDTOs(Collection<Reel>  reels){
		if (reels == null) {
			return null;
		}
		List<ReelDTO> results = new ArrayList<ReelDTO>();
		for (Reel each : reels) {
			results.add(toDTO(each));
		}
		return results;
	}
	
	 public static Reel  toEntity(ReelDTO  reelDTO){
	 	if (reelDTO == null) {
			return null;
		}
	 	Reel result  = new Reel();
        result.setId (reelDTO.getId());
         result.setVersion (reelDTO.getVersion());
         result.setImages (reelDTO.getImages());
         result.setFrames (reelDTO.getFrames());
         result.setUuid(reelDTO.getUuid());
 	  	return result;
	 }
	
	public static List<Reel> toEntities(Collection<ReelDTO> reelDTOs) {
		if (reelDTOs == null) {
			return null;
		}
		
		List<Reel> results = new ArrayList<Reel>();
		for (ReelDTO each : reelDTOs) {
			results.add(toEntity(each));
		}
		return results;
	}
}
