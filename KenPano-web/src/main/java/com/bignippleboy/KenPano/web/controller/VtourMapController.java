package com.bignippleboy.KenPano.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.dayatang.utils.Page;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bignippleboy.KenPano.facade.VtourMapFacade;
import com.bignippleboy.KenPano.facade.dto.MapspotDTO;
import com.bignippleboy.KenPano.facade.dto.VtourMapDTO;

@Controller
@RequestMapping("/VtourMap")
public class VtourMapController {
		
	@Inject
	private VtourMapFacade vtourMapFacade;
	
	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(VtourMapDTO vtourMapDTO) {
		return vtourMapFacade.creatVtourMap(vtourMapDTO);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public InvokeResult update(VtourMapDTO vtourMapDTO) {
		return vtourMapFacade.updateVtourMap(vtourMapDTO);
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(VtourMapDTO vtourMapDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<VtourMapDTO> all = vtourMapFacade.pageQueryVtourMap(vtourMapDTO, page, pagesize);
		return all;
	}
	
	@RequestMapping(value="/editMapspot",method=RequestMethod.POST)
	@ResponseBody
	public InvokeResult editMapspot(@RequestBody VtourMapDTO vDto) {
		return vtourMapFacade.updateVtourMap(vDto);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public InvokeResult remove(@RequestParam String ids) {
		String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i ++) {
        	        					idArrs[i] = Long.parseLong(value[i]);
						        }
        return vtourMapFacade.removeVtourMaps(idArrs);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		InvokeResult result = vtourMapFacade.getVtourMap(id);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/getAllMap")
	public InvokeResult getAllMap() {
		InvokeResult result = InvokeResult.success(vtourMapFacade.findAllVtourMap());
		return result;
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ResponseBody
	public FileMeta upload(@RequestParam(value="mapImg") MultipartFile mapFile, HttpServletRequest request) throws IOException {

		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		
		String uploadPath = request.getSession().getServletContext()
				.getRealPath("/uploads");
		String mapFileStr = mapFile.getOriginalFilename();
		String genFileName = UUID.randomUUID().toString()+mapFileStr.substring(mapFileStr.lastIndexOf("."));
		String mapFilePath = uploadPath+"/maps/"+genFileName;
		
		File f = new File(mapFilePath);
		FileUtils.copyInputStreamToFile(mapFile.getInputStream(), f);
		
		//获取图片长宽比
		BufferedImage bufferedImage = ImageIO.read(f);
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		
		FileMeta meta = new FileMeta();
		meta.setFileName(mapFile.getOriginalFilename());
		meta.setFileSize(mapFile.getSize()/1024+"kb");
		meta.setFileType(mapFile.getContentType());
		meta.setFileUUID(genFileName);
		meta.setAspectRadio(getPercent(height,width));
		meta.setWidth(String.valueOf(width));
		meta.setHeight(String.valueOf(height));
		
		
//		files.add(meta);
		
		return meta;
	}
	
	public String getPercent(int diliverNum, int queryMailNum) {

		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		String result = numberFormat.format((float) diliverNum
				/ (float) queryMailNum * 100);
		
		return result+"%";
	}
	
	@ResponseBody
	@RequestMapping("/findMapspotsByVtourMap/{id}")
	public List<MapspotDTO> findMapspotsByVtourMap(@PathVariable Long id) {
		return vtourMapFacade.findMapspotsByVtourMap(id);
	}		
	
    @InitBinder    
    public void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
        dateFormat.setLenient(false);    
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
        //CustomDateEditor 可以换成自己定义的编辑器。  
        //注册一个Date 类型的绑定器 。
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
    }
	
}
