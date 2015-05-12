package com.bignippleboy.KenPano.util;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.bignippleboy.KenPano.facade.dto.MapspotDTO;
import com.bignippleboy.KenPano.facade.dto.ReelspotDTO;

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
	
	public static void addReelProperties(Document document) {
		//define a button style
		Node node = document.selectSingleNode("//style[@name='button']");
		if(node == null) {
			Element style = document.getRootElement().addElement("style");
			style.addAttribute("name", "button")
				.addAttribute("url", "%SWFPATH%/plugins/textfield.swf")
				.addAttribute("children", "false")
				.addAttribute("css", "text-align:center; color:#FFFFFF; font-family:Arial; font-weight:bold; font-size:10px;")
				.addAttribute("vcenter", "true")
				.addAttribute("backgroundcolor", "0x000000")
				.addAttribute("backgroundalpha", "0.667")
				.addAttribute("roundedge", "1")
				.addAttribute("border", "true")
				.addAttribute("bordercolor", "0xDDDDDD")
				.addAttribute("width", "150")
				.addAttribute("height", "24")
				.addAttribute("shadow", "0");
		}

		node = document.selectSingleNode("//layer[@name='addButton']");
		if(node == null) {
			Element layer = document.getRootElement().addElement("layer");
			layer.addAttribute("name", "addButton")
				.addAttribute("keep", "true")
				.addAttribute("html", "添加reelspot")
				.addAttribute("align", "rightbottom")
				.addAttribute("x", "32")
				.addAttribute("y", "150")
				.addAttribute("width", "70")
				.addAttribute("height", "50")
				.addAttribute("zorder", "99999")
				.addAttribute("style", "button")
				.addAttribute("alpha", "1.0")
				.addAttribute("onloaded", "delayedcall(1.0,tween(alpha,1.0));")
				.addAttribute("onclick", "addReelspot();");
		}

		node = document.selectSingleNode("//style[@name='hotspot_ani_white']");
		if(node == null) {
			Element style = document.getRootElement().addElement("style");
			style.addAttribute("name", "hotspot_ani_white")
				.addAttribute("editorplugin_ishotspotstyle", "true")
				.addAttribute("url", "hotspot_ani_white_64x64x20.png")
				.addAttribute("crop", "0|0|64|64")
				.addAttribute("frames", "20")
				.addAttribute("framewidth", "64")
				.addAttribute("frameheight", "64")
				.addAttribute("frame", "0")
				.addAttribute("onloaded", "if(device.mobile, set(scale,1.5)); hotspot_animate();");
		}

		node = document.selectSingleNode("//action[@name='hotspot_animate']");
		if(node == null) {
			Element action = document.getRootElement().addElement("action");
			action.addAttribute("name", "hotspot_animate")
				.addText("if(loaded,inc(frame,1,get(frames),0);mul(ypos,frame,frameheight);txtadd(crop,'0|',get(ypos),'|',get(framewidth),'|',get(frameheight));delayedcall(0.03, hotspot_animate() ););");
		}

		node = document.selectSingleNode("//action[@name='addReelspot']");
		if(node == null) {
			Element action = document.getRootElement().addElement("action");
			action.addAttribute("name", "addReelspot")
				.addText("addhotspot(reelspot);hotspot[reelspot].loadstyle('hotspot_ani_white');set(hotspot[reelspot].style,null);set(hotspot[reelspot].ath,0,heading);set(hotspot[reelspot].atv,0);set(hotspot[reelspot].onclick,'openmap();');set(hotspot[reelspot].ondown,'draghotspot();');");
		}

		node = document.selectSingleNode("//action[@name='draghotspot']");
		if(node == null) {
			Element action = document.getRootElement().addElement("action");
			action.addAttribute("name", "draghotspot")
				.addText("if(%1 != dragging,spheretoscreen(ath, atv, hotspotcenterx, hotspotcentery);sub(drag_adjustx, mouse.stagex, hotspotcenterx); sub(drag_adjusty, mouse.stagey, hotspotcentery); draghotspot(dragging);,if(pressed,sub(dx, mouse.stagex, drag_adjustx);sub(dy, mouse.stagey, drag_adjusty);screentosphere(dx, dy, ath, atv);copy(print_ath, ath);copy(print_atv, atv);roundval(print_ath, 3);roundval(print_atv, 3);delayedcall(0, draghotspot(dragging) );););");
		}
	}
	
	public static void addReel(Document document, ReelspotDTO reelspotDTO) {

		Node node = document.selectSingleNode("//style[@name='hotspot_ani_white']");
		if(node == null) {
			Element style = document.getRootElement().addElement("style");
			style.addAttribute("name", "hotspot_ani_white")
				.addAttribute("editorplugin_ishotspotstyle", "true")
				.addAttribute("url", "hotspot_ani_white_64x64x20.png")
				.addAttribute("crop", "0|0|64|64")
				.addAttribute("frames", "20")
				.addAttribute("framewidth", "64")
				.addAttribute("frameheight", "64")
				.addAttribute("frame", "0")
				.addAttribute("onloaded", "if(device.mobile, set(scale,1.5)); hotspot_animate();");
		}

		node = document.selectSingleNode("//action[@name='hotspot_animate']");
		if(node == null) {
			Element action = document.getRootElement().addElement("action");
			action.addAttribute("name", "hotspot_animate")
				.addText("if(loaded,inc(frame,1,get(frames),0);mul(ypos,frame,frameheight);txtadd(crop,'0|',get(ypos),'|',get(framewidth),'|',get(frameheight));delayedcall(0.03, hotspot_animate() ););");
		}

		node = document.selectSingleNode("//hotspot[@name='reelspot']");
		if(node == null) {
			Element hotspot = document.getRootElement().addElement("hotspot");
			hotspot.addAttribute("name", "hotspot")
				.addAttribute("style", "hotspot_ani_white")
				.addAttribute("ath", reelspotDTO.getAth())
				.addAttribute("atv", reelspotDTO.getAtv())
				.addAttribute("keep", "true")
				.addAttribute("onclick", "");
		}

	}
}
