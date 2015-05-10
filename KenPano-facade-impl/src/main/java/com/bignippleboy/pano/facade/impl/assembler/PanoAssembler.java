package com.bignippleboy.pano.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bignippleboy.KenPano.core.domain.Pano;
import com.bignippleboy.pano.facade.dto.PanoDTO;

public class PanoAssembler {

	public static PanoDTO toDTO(Pano pano) {
		if (pano == null) {
			return null;
		}
		PanoDTO result = new PanoDTO();
		result.setId(pano.getId());
		result.setSrcPanoImgName(pano.getSrcPanoImgName());
		result.setDestPanoImgPath(pano.getDestPanoImgName());
		return result;
	}

	public static List<PanoDTO> toDTOs(Collection<Pano> panos) {
		if (panos == null) {
			return null;
		}
		List<PanoDTO> results = new ArrayList<PanoDTO>();
		for (Pano each : panos) {
			results.add(toDTO(each));
		}
		return results;
	}

	public static Pano toEntity(PanoDTO panoDTO) {
		if (panoDTO == null)
			return null;
		Pano pano = new Pano();
		pano.setSrcPanoImgName(panoDTO.getSrcPanoImgName());
		pano.setDestPanoImgName(panoDTO.getDestPanoImgPath());
//		pano.setVtour(vtour);panoDTO.getvDto();

		return pano;
	}
}
