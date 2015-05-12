package com.bignippleboy.KenPano.web.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.dayatang.utils.Page;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bignippleboy.KenPano.facade.ReelspotFacade;
import com.bignippleboy.KenPano.facade.dto.ReelspotDTO;
import com.bignippleboy.KenPano.util.PanoUtil;

@Controller
@RequestMapping("/Reelspot")
public class ReelspotController {
		
	@Inject
	private ReelspotFacade reelspotFacade;
	
	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(@RequestBody ReelspotDTO reelspotDTO, HttpServletRequest req) throws DocumentException, IOException {
		//要保存xml: reelspot+reel
		File vtourPath = new File(req.getSession().getServletContext()
				.getRealPath("/uploads/")
				+ "/" + reelspotDTO.getVtour_uuid());
		File xmlFile = new File(vtourPath + "/vtour/tour.xml");
		//程序更新tour_editor.xml配置文件
		SAXReader reader = new SAXReader();
		Document doc;
		doc = reader.read(xmlFile);
		PanoUtil.addReel(doc, reelspotDTO);
		
		XMLWriter outputWriter = new XMLWriter(new FileWriter(xmlFile));
		outputWriter.write(doc);
		outputWriter.close();
		
		return reelspotFacade.creatReelspot(reelspotDTO);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public InvokeResult update(ReelspotDTO reelspotDTO) {
		return reelspotFacade.updateReelspot(reelspotDTO);
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(ReelspotDTO reelspotDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<ReelspotDTO> all = reelspotFacade.pageQueryReelspot(reelspotDTO, page, pagesize);
		return all;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public InvokeResult remove(@RequestParam String ids) {
		String[] value = ids.split(",");
        Long[] idArrs = new Long[value.length];
        for (int i = 0; i < value.length; i ++) {
        	        					idArrs[i] = Long.parseLong(value[i]);
						        }
        return reelspotFacade.removeReelspots(idArrs);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return reelspotFacade.getReelspot(id);
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
