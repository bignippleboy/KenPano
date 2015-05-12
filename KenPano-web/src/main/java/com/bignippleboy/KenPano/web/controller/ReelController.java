package com.bignippleboy.KenPano.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bignippleboy.KenPano.facade.ReelFacade;
import com.bignippleboy.KenPano.facade.dto.ReelDTO;
import com.bignippleboy.pano.facade.dto.PanoDTO;
import com.bignippleboy.pano.facade.dto.VtourDTO;

@Controller
@RequestMapping("/Reel")
public class ReelController {
		
	@Inject
	private ReelFacade reelFacade;
	
	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(ReelDTO reelDTO) {
		return reelFacade.creatReel(reelDTO);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public InvokeResult update(ReelDTO reelDTO) {
		return reelFacade.updateReel(reelDTO);
	}
	
	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(ReelDTO reelDTO, @RequestParam int page, @RequestParam int pagesize) {
		Page<ReelDTO> all = reelFacade.pageQueryReel(reelDTO, page, pagesize);
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
        return reelFacade.removeReels(idArrs);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return reelFacade.getReel(id);
	}

	/**
	 * pano保存路径：$constantPath/uploads/reel/uuid/###.后缀
	 * 
	 * @param pano
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	// public LinkedList<FileMeta> upload(MultipartHttpServletRequest request,
	// HttpServletResponse response) throws IOException {
	public void upload(
			@RequestParam(value = "files") MultipartFile[] reelsFiles,
			HttpServletRequest request) throws IOException {

		LinkedList<FileMeta> files = new LinkedList<FileMeta>();

		String uploadPath = request.getSession().getServletContext()
				.getRealPath("/uploads");
		
		String contentPath = request.getSession().getServletContext().getContextPath();

		// 生出reel文件夹id
		String reel_uuid = UUID.randomUUID().toString();
		ReelDTO vDto = new ReelDTO();
		vDto.setUuid(reel_uuid);
		vDto.setImages(contentPath+"/reel/"+reel_uuid+"/###.png");
		vDto.setFrames(String.valueOf(reelsFiles.length));

		for (int i=0; i<reelsFiles.length; i++) {

			// 上传reel
			String fullName = reelsFiles[i].getOriginalFilename();
			String suffix = fullName.substring(fullName.lastIndexOf("."));
			String saveName = getSaveName(i,3) + suffix;
			File savePanoFile = new File(uploadPath + "/reel/" + reel_uuid + "/"
					+ "/" + saveName);
			FileUtils
					.copyInputStreamToFile(reelsFiles[i].getInputStream(), savePanoFile);
		}

		add(vDto);

	}
	
	private static String getSaveName(int i, int maxLength) {
		int length = Integer.valueOf(i).toString().length();
		String prefix="";
		for(int index=0;index<maxLength-length;index++) {
			prefix+="0";
		}
		return prefix+i;
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
