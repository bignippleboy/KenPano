package com.bignippleboy.pano.domain;

import org.junit.Test;
import org.openkoala.koala.util.KoalaBaseSpringTestCase;

import com.bignippleboy.KenPano.core.domain.Pano;
import com.bignippleboy.KenPano.core.domain.Vtour;

public class PanoTest extends KoalaBaseSpringTestCase {
	@Test
	public void testMany2one() {
		Vtour vtour = new Vtour();
		vtour.setUuid("uuid");
//		vtour.save();
		
//		Pano pano = new Pano();
//		pano.setSrcPanoImgName("name");
//		pano.setVtour(vtour);
//		pano.save();
//		
//		Pano pano2 = new Pano();
//		pano2.setSrcPanoImgName("name");
//		pano2.setVtour(vtour);
//		pano2.save();
	}
	
	@Test
	public void testSaveVtour() {
		Vtour vtour = new Vtour();
		vtour.setUuid("uuid");
		vtour.save();
	}
	
	@Test
	public void testOne2many() {
		Pano p1 = new Pano();
		p1.setSrcPanoImgName("n1");
		Pano p2 = new Pano();
		p2.setSrcPanoImgName("n2");
		
		Vtour vtour = new Vtour();
		vtour.setUuid("uuid1");
		vtour.getPanos().add(p1);
		vtour.getPanos().add(p2);
		vtour.save();
	}
}
