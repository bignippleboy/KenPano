package com.bignippleboy.pano.facade.impl.assembler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bignippleboy.KenPano.core.domain.Pano;
import com.bignippleboy.KenPano.core.domain.XmlEditLog;
import com.bignippleboy.pano.facade.dto.PanoDTO;
import com.bignippleboy.pano.facade.dto.XmlEditLogDTO;

public class XmlEditLogAssembler {

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

	public static XmlEditLog toEntity(XmlEditLogDTO dto) {
		if (dto == null)
			return null;
		XmlEditLog log = new XmlEditLog();
		log.setVtour_uuid(dto.getVtour_uuid());
		log.setOrginal_xml_str(dto.getOrginal_xml_str());
		log.setChanged_xml_str(dto.getChanged_xml_str());
		log.setChange_time(dto.getChange_time());

		return log;
	}
}
