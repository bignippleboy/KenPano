package com.bignippleboy.pano.facade;

import org.openkoala.koala.commons.InvokeResult;

import com.bignippleboy.pano.facade.dto.XmlEditLogDTO;

public interface XmlEditLogFacade {
	public InvokeResult saveLog(XmlEditLogDTO dto);
}
