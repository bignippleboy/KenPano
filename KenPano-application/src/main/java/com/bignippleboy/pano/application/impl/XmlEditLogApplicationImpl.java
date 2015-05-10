package com.bignippleboy.pano.application.impl;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bignippleboy.KenPano.core.domain.XmlEditLog;
import com.bignippleboy.pano.application.XmlEditLogApplication;

@Named
@Transactional
public class XmlEditLogApplicationImpl implements XmlEditLogApplication {

	@Override
	public void saveLog(XmlEditLog log) {
		log.save();
	}

}
