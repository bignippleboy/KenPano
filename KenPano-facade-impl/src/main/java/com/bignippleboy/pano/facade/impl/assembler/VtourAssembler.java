package com.bignippleboy.pano.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bignippleboy.KenPano.core.domain.Pano;
import com.bignippleboy.KenPano.core.domain.Vtour;
import com.bignippleboy.pano.facade.dto.PanoDTO;
import com.bignippleboy.pano.facade.dto.VtourDTO;

public class VtourAssembler {

	public static VtourDTO toDTO(Vtour vtour) {
		if (vtour == null) {
			return null;
		}
		VtourDTO result = new VtourDTO();
		result.setId(vtour.getId());
		result.setUuid(vtour.getUuid());
		result.setIsGyroscope(vtour.isGyroscope()?"true":"false");
		result.setIsMapOpen(vtour.isMapOpen()?"true":"false");
		result.setIsSkinOpen(vtour.isSkinOpen()?"true":"false");
		
		if(vtour.getPanos() != null) {
			for(Pano pano : vtour.getPanos()) {
				PanoDTO pDto = new PanoDTO();
				pDto.setId(pano.getId());
				pDto.setDestPanoImgPath(pano.getDestPanoImgName());
				pDto.setSrcPanoImgName(pano.getSrcPanoImgName());
				result.getPanoDTOs().add(pDto);
			}
		}
		
		return result;
	}

	public static List<VtourDTO> toDTOs(Collection<Vtour> Vtours) {
		if (Vtours == null) {
			return null;
		}
		List<VtourDTO> results = new ArrayList<VtourDTO>();
		for (Vtour each : Vtours) {
			results.add(toDTO(each));
		}
		return results;
	}

	public static Vtour toEntity(VtourDTO VtourDTO) {
		if (VtourDTO == null)
			return null;
		Vtour vtour = null;
		if(VtourDTO.getId() == null || Vtour.get(Vtour.class, VtourDTO.getId()) == null)
			vtour = new Vtour();
		else
			vtour = Vtour.get(Vtour.class, VtourDTO.getId());
		vtour.setUuid(VtourDTO.getUuid());
		vtour.setGyroscope("true".equals(VtourDTO.getIsGyroscope())?true:false);
		vtour.setMapOpen("true".equals(VtourDTO.getIsMapOpen())?true:false);
		vtour.setSkinOpen("true".equals(VtourDTO.getIsSkinOpen())?true:false);
		for(PanoDTO panoDTO : VtourDTO.getPanoDTOs()) {
			Pano pano = new Pano();
			pano.setDestPanoImgName(panoDTO.getDestPanoImgPath());
			pano.setSrcPanoImgName(panoDTO.getSrcPanoImgName());
			vtour.getPanos().add(pano);
		}

		return vtour;
	}
}
