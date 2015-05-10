package com.bignippleboy.KenPano.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

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

import com.bignippleboy.KenPano.facade.PanoInfoFacade;
import com.bignippleboy.KenPano.facade.dto.PanoInfoDTO;

@Controller
@RequestMapping("/PanoInfo")
public class PanoInfoController {

	LinkedList<FileMeta> files = new LinkedList<FileMeta>();

	@Inject
	private PanoInfoFacade panoInfoFacade;

	@ResponseBody
	@RequestMapping("/add")
	public InvokeResult add(PanoInfoDTO panoInfoDTO) {
		return panoInfoFacade.creatPanoInfo(panoInfoDTO);
	}

	@ResponseBody
	@RequestMapping("/update")
	public InvokeResult update(PanoInfoDTO panoInfoDTO) {
		return panoInfoFacade.updatePanoInfo(panoInfoDTO);
	}

	@ResponseBody
	@RequestMapping("/pageJson")
	public Page pageJson(PanoInfoDTO panoInfoDTO, @RequestParam int page,
			@RequestParam int pagesize) {
		Page<PanoInfoDTO> all = panoInfoFacade.pageQueryPanoInfo(panoInfoDTO,
				page, pagesize);
		return all;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public InvokeResult remove(@RequestParam String ids) {
		String[] value = ids.split(",");
		Long[] idArrs = new Long[value.length];
		for (int i = 0; i < value.length; i++) {
			idArrs[i] = Long.parseLong(value[i]);
		}
		return panoInfoFacade.removePanoInfos(idArrs);
	}

	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return panoInfoFacade.getPanoInfo(id);
	}

	/**
	 * LinkedList<FileMeta>
	 * @param attchs
	 * @param req
	 * @return
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	@ResponseBody
	public LinkedList<FileMeta> fileupload(
			@RequestParam(value = "attchss") MultipartFile attchs,
			HttpServletRequest req) throws IOException, InterruptedException {
		String realPath = req.getSession().getServletContext()
				.getRealPath("/uploads");
		System.out.println(realPath);
		File f = new File(realPath + "/" + attchs.getOriginalFilename());
		FileUtils.copyInputStreamToFile(attchs.getInputStream(), f);
		
		//调用java2sh
		//*****

		String shell = "/home/huangken/Documents/krpano_demo/shell/makeVtour_linux.sh";
//		String source_pano = "/home/huangken/Documents/krpano_demo/test/_sphere.tif";
		String source_pano = f.getAbsolutePath();
		System.out.println("source_pano="+source_pano);
		String[] cmdarray = { "/bin/sh", shell, source_pano };
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
		//*****
		
		FileMeta meta = new FileMeta();
		meta.setFileName(attchs.getOriginalFilename());
		meta.setFileSize(attchs.getSize()/1024+"kb");
		meta.setFileType(attchs.getContentType());
		// return "redirect:/user/fileupload";
		files.add(meta);
		return files;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		// CustomDateEditor 可以换成自己定义的编辑器。
		// 注册一个Date 类型的绑定器 。
		binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
	}

}
