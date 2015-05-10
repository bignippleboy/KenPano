package com.bignippleboy.pano.facade.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.openkoala.koala.commons.InvokeResult;

import com.bignippleboy.pano.application.XmlEditLogApplication;
import com.bignippleboy.pano.facade.XmlEditLogFacade;
import com.bignippleboy.pano.facade.dto.XmlEditLogDTO;
import com.bignippleboy.pano.facade.impl.assembler.XmlEditLogAssembler;

@Named
public class XmlEditLogFacadeImpl implements XmlEditLogFacade {
	
	@Inject
	private XmlEditLogApplication app;

	@Override
	public InvokeResult saveLog(XmlEditLogDTO dto) {
		app.saveLog(XmlEditLogAssembler.toEntity(dto));
		return InvokeResult.success();
	}

}
