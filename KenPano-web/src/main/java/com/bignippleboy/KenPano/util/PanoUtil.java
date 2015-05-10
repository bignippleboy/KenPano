package com.bignippleboy.KenPano.util;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.bignippleboy.KenPano.facade.dto.MapspotDTO;

public class PanoUtil {
	public static void constructGyro(Document document) {
		//add plugin
		Node node = document.selectSingleNode("//plugin[@name='gyro']");
		if(node == null) {
			Element plugin = document.getRootElement().addElement("plugin");
			plugin.addAttribute("name", "gyro")
				.addAttribute("devices", "html5")
				.addAttribute("url", "plugins/gyro.js")
				.addAttribute("enabled", "false")
				.addAttribute("camroll", "true")
				.addAttribute("friction", "0.5")
				.addAttribute("velastic", "0.2")
				.addAttribute("keep", "true")
				.addAttribute("onavailable", "set(layer[gyrobutton].visible,true); set(layer[infotext].html,'Gyroscope available, press the gyro button...');")
				.addAttribute("onloaded", "delayedcall(1,ifnot(available, showgyrowarning() ));");
		}
		//add events
		node = document.selectSingleNode("//events[@onxmlcomplete='showgyrowarning();']");
		if(node == null) {
			Element events = document.getRootElement().addElement("events");
			events.addAttribute("onxmlcomplete", "showgyrowarning();")
				.addAttribute("devices", "flash");
		}
		//add actions
		node = document.selectSingleNode("//action[@name='showgyrowarning']");
		if(node == null) {
			Element action = document.getRootElement().addElement("action");
			action.addAttribute("name", "showgyrowarning")
				.addText("set(layer[infotext].html,'Gyroscope NOT available...'))");
		}
		//add layer1
		node = document.selectSingleNode("//layer[@name='bottombar' and @type='container']");
		if(node == null) {
			Element layer1 = document.getRootElement().addElement("layer");
			layer1.addAttribute("name", "bottombar")
				.addAttribute("type", "container")
				.addAttribute("bgcolor", "0x000000")
				.addAttribute("bgalpha", "0.5")
				.addAttribute("align", "leftbottom")
				.addAttribute("width", "100%")
				.addAttribute("height", "40")
				.addAttribute("keep", "true");
			Element layer11 = layer1.addElement("layer");
			layer11.addAttribute("name", "infotext")
				.addAttribute("url", "plugins/textfield.swf")
				.addAttribute("background", "false")
				.addAttribute("border", "false")
				.addAttribute("enabled", "false")
				.addAttribute("html", "")
				.addAttribute("css", "text-align:left; color:#FFFFFF; font-family:Arial; font-size:14px; font-weight:bold;")
				.addAttribute("align", "left")
				.addAttribute("x", "10")
				.addAttribute("height", "100%")
				.addAttribute("vcenter", "true");
			Element layer12 = layer1.addElement("layer");
			layer12.addAttribute("name", "gyrobutton")
				.addAttribute("url", "gyroicon.png")
				.addAttribute("scale", "0.5")
				.addAttribute("align", "right")
				.addAttribute("x", "10")
				.addAttribute("visible", "false")
				.addAttribute("onclick", "switch(plugin[gyro].enabled);");
			//add layer2
			node = document.selectSingleNode("//layer[@name='bottombar' and @devices='mobile']");
			Element layer2 = document.getRootElement().addElement("layer");
			layer2.addAttribute("name", "bottombar")
				.addAttribute("height", "80")
				.addAttribute("devices", "mobile");
			Element layer21 = layer2.addElement("layer");
			layer21.addAttribute("name", "infotext")
				.addAttribute("css", "text-align:left; color:#FFFFFF; font-family:Arial; font-size:22px; font-weight:bold;");
			Element layer22 = layer2.addElement("layer");
			layer22.addAttribute("name", "gyrobutton")
				.addAttribute("scale", "1.0");
		}
	}

	public static void unconstructGyro(Document document) {
		Node node = document.selectSingleNode("//plugin[@name='gyro']");
		if(node != null)
			document.getRootElement().remove(node);
		
		node = document.selectSingleNode("//events[@onxmlcomplete='showgyrowarning();']");
		if(node != null)
			document.getRootElement().remove(node);

		node = document.selectSingleNode("//action[@name='showgyrowarning']");
		if(node != null)
			document.getRootElement().remove(node);

		node = document.selectSingleNode("//layer[@name='bottombar' and @type='container']");
		if(node != null)
			document.getRootElement().remove(node);

		node = document.selectSingleNode("//layer[@name='bottombar' and @devices='mobile']");
		if(node != null)
			document.getRootElement().remove(node);
	}
	
	public static void constructMap(Document document,String mapUrl, List<MapspotDTO> mapspotDTOs) {
		//add the map image
		Node node = document.selectSingleNode("//layer[@name='map']");
		if(node == null) {
			Element layer = document.getRootElement().addElement("layer");
			layer.addAttribute("name", "map")
				.addAttribute("url", mapUrl)
				.addAttribute("keep", "true")
				.addAttribute("handcursor", "false")
				.addAttribute("capture", "false")
				.addAttribute("align", "leftbottom")
				.addAttribute("scale", "0.25")
				.addAttribute("scalechildren", "true")
				.addAttribute("onclick", "openmap();");
		}
		//map opening - scale the map up to 100% (or smaller if the screen is too small)
		node = document.selectSingleNode("//action[@name='openmap']");
		if(node == null) {
			Element action = document.getRootElement().addElement("action");
			action.addAttribute("name", "openmap")
				.addText("set(layer[map].onclick, closemap(); );layer[map].changeorigin(center,center);set(bigscale,1);if(layer[map].imagewidth GT stagewidth, div(bigscale, stagewidth, layer[map].imagewidth); );tween(layer[map].x, 0);tween(layer[map].y, 0);tween(layer[map].scale, get(bigscale));");
		}
		//map closing - scale the map back to 25%
		node = document.selectSingleNode("//action[@name='closemap']");
		if(node == null) {
			Element action = document.getRootElement().addElement("action");
			action.addAttribute("name", "closemap")
				.addText("set(layer[map].onclick, openmap(); );layer[map].changeorigin(leftbottom,leftbottom);tween(layer[map].x, 0);tween(layer[map].y, 0);tween(layer[map].scale, 0.25);");
		}
		//the map spot images
		node = document.selectSingleNode("//style[@name='mapspot']");
		if(node == null) {
			Element style = document.getRootElement().addElement("style");
			style.addAttribute("name", "mapspot")
				.addAttribute("keep", "true")
				.addAttribute("url", "camicon.png")
				.addAttribute("parent", "map")
				.addAttribute("align", "lefttop")
				.addAttribute("edge", "center")
				.addAttribute("scale.mobile", "2");
		}
		//for循环添加spots
		for(int i=0;i<mapspotDTOs.size();i++) {
			String sceneName = mapspotDTOs.get(i).getSceneName();
			Element layer = document.getRootElement().addElement("layer");
			layer.addAttribute("name", "spot"+i)
				.addAttribute("style", "mapspot")
				.addAttribute("x", mapspotDTOs.get(i).getX_percent())
				.addAttribute("y", mapspotDTOs.get(i).getY_percent())
				.addAttribute("zorder", "1")
				.addAttribute("onclick", "mapspot_loadscene(scene_"+sceneName.substring(0,sceneName.lastIndexOf("."))+");");
		}
		//action for loading the scene when clicking on the map spots
		node = document.selectSingleNode("//action[@name='mapspot_loadscene']");
		if(node == null) {
			Element action = document.getRootElement().addElement("action");
			action.addAttribute("name", "mapspot_loadscene")
				.addText("if(layer[map].scale GT 0.25,set(layer[map].enabled, false);tween(layer[map].alpha, 0.0, 0.25, default,loadscene(%1, null, MERGE, BLEND(1));set(layer[map].onclick, openmap(); );layer[map].changeorigin(leftbottom,leftbottom);set(layer[map].x, 0);set(layer[map].y, 0);set(layer[map].scale, 0.25);set(events[sceneload].onloadcomplete,delayedcall(1,tween(layer[map].alpha, 1.0, 0.5, default, set(layer[map].enabled, true); ););););,loadscene(%1, null, MERGE, BLEND(1)););");
		}
	}

	public static void unconstructMap(Document document, List<MapspotDTO> mapspotDTOs) {
		Node node = document.selectSingleNode("//layer[@name='map']");
		if(node != null)
			document.getRootElement().remove(node);
		node = document.selectSingleNode("//action[@name='openmap']");
		if(node != null)
			document.getRootElement().remove(node);
		node = document.selectSingleNode("//action[@name='closemap']");
		if(node != null)
			document.getRootElement().remove(node);
		node = document.selectSingleNode("//style[@name='mapspot']");
		if(node != null)
			document.getRootElement().remove(node);
		//for循环添加spots
		for(int i=0;i<mapspotDTOs.size();i++) {
			node = document.selectSingleNode("//layer[@name='spot"+i+"']");
			if(node != null)
				document.getRootElement().remove(node);
		}
		node = document.selectSingleNode("//action[@name='mapspot_loadscene']");
		if(node != null)
			document.getRootElement().remove(node);
	}
	
	public static void constructSkin(Document document) {
		Node node = document.selectSingleNode("//include[@url='skin/vtourskin.xml']");
		if(node == null) {
			Element include = document.getRootElement().addElement("include");
			include.addAttribute("url", "skin/vtourskin.xml");
		}
	}

	public static void unconstructSkin(Document document) {
		Node node = document.selectSingleNode("//include[@url='skin/vtourskin.xml']");
		if(node != null)
			document.getRootElement().remove(node);
	}
}
