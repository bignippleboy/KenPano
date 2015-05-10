package com.bignippleboy.KenPano.core.domain;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.openkoala.koala.commons.domain.KoalaAbstractEntity;

import com.bignippleboy.constant.Constants;

@Entity
@Table(name="VTOUR")
public class Vtour extends KoalaAbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 706469688786449208L;

	private boolean h5OrFlash;
	
	private String[] plugins;
	
	private String[] layers;
	
	private String[] actions;
	
	private String[] scenes;
	
	private Hotspot[] hotspots ;
	
	private String preview;
	
	private boolean isGyroscope;
	
	private boolean isMapOpen;
	
	private boolean isSkinOpen = true;
	
	@Column(name="UUID")
	private String uuid;
	
	@OneToOne(mappedBy="vtour")
	private VtourMap vtourMap;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="vtourid")
	private Set<Pano> panos = new HashSet<Pano>();
	
	public boolean isH5OrFlash() {
		return h5OrFlash;
	}

	public void setH5OrFlash(boolean h5OrFlash) {
		this.h5OrFlash = h5OrFlash;
	}

	public boolean isSkinOpen() {
		return isSkinOpen;
	}

	public void setSkinOpen(boolean isSkinOpen) {
		this.isSkinOpen = isSkinOpen;
	}

	public boolean isMapOpen() {
		return isMapOpen;
	}

	public void setMapOpen(boolean isMapOpen) {
		this.isMapOpen = isMapOpen;
	}

	public VtourMap getVtourMap() {
		return vtourMap;
	}

	public void setVtourMap(VtourMap vtourMap) {
		this.vtourMap = vtourMap;
	}

	public String[] getPlugins() {
		return plugins;
	}

	public void setPlugins(String[] plugins) {
		this.plugins = plugins;
	}

	public String[] getLayers() {
		return layers;
	}

	public void setLayers(String[] layers) {
		this.layers = layers;
	}

	public String[] getActions() {
		return actions;
	}

	public void setActions(String[] actions) {
		this.actions = actions;
	}

	public String[] getScenes() {
		return scenes;
	}

	public void setScenes(String[] scenes) {
		this.scenes = scenes;
	}

	public Hotspot[] getHotspots() {
		return hotspots;
	}

	public void setHotspots(Hotspot[] hotspots) {
		this.hotspots = hotspots;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public boolean isGyroscope() {
		return isGyroscope;
	}

	public void setGyroscope(boolean isGyroscope) {
		this.isGyroscope = isGyroscope;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Set<Pano> getPanos() {
		return panos;
	}

	public void setPanos(Set<Pano> panos) {
		this.panos = panos;
	}

	public void generate(String panoImgSrc) throws IOException, InterruptedException {

		String[] cmdarray = { "/bin/sh", Constants.MAKE_VTOUR_SHELL, panoImgSrc };
		Process pcs = Runtime.getRuntime().exec(cmdarray);
		// 若不加这下面的代码也调不成功shell脚本

		InputStreamReader ir = new InputStreamReader(pcs.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		String line = null;
		while ((line = input.readLine()) != null) {
			System.out.println(line);
		}
		if (null != input) {
			input.close();
		}

		if (null != ir) {
			ir.close();
		}
		int extValue = pcs.waitFor(); // 返回码 0 表示正常退出 1表示异常退出
		System.out.println("extValue="+extValue);

		System.out.println("end to execute linux shell");
		
	}
	
	public void edit() {
		
	}
	
	public void addPlugin() {
		
	}
	
	public void addLayer() {
		
	}
	
	public void addHotspot() {
		
	}

	@Override
	public String[] businessKeys() {
		// TODO Auto-generated method stub
		return null;
	}

}
