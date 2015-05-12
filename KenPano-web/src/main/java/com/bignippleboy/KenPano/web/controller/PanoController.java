package com.bignippleboy.KenPano.web.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.dayatang.utils.Page;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.openkoala.koala.commons.InvokeResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bignippleboy.KenPano.facade.dto.VtourMapDTO;
import com.bignippleboy.KenPano.util.ImageUtil;
import com.bignippleboy.KenPano.util.PanoUtil;
import com.bignippleboy.pano.facade.PanoFacade;
import com.bignippleboy.pano.facade.VtourFacade;
import com.bignippleboy.pano.facade.XmlEditLogFacade;
import com.bignippleboy.pano.facade.dto.PanoDTO;
import com.bignippleboy.pano.facade.dto.VtourDTO;
import com.bignippleboy.pano.facade.dto.XmlEditLogDTO;

@Controller
@RequestMapping("/pano")
public class PanoController {

	@Inject
	private PanoFacade panoFacade;

	@Inject
	private VtourFacade vtourFacade;

	@Inject
	private XmlEditLogFacade xmlEditLogFacade;

	@ResponseBody
	@RequestMapping("/addPano")
	public InvokeResult addPano(PanoDTO panoDTO) {
		// return panoFacade.creatPano(panoDTO);
		return null;
	}

	@ResponseBody
	@RequestMapping("/addVtour")
	public InvokeResult addVtour(VtourDTO vtourDTO) {
		return vtourFacade.creatVtour(vtourDTO);
	}
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public InvokeResult get(@PathVariable Long id) {
		return vtourFacade.getVtour(id);
	}
	
	@RequestMapping("/getAllVtour")
	@ResponseBody
	public InvokeResult getAllVtour() {
		return InvokeResult.success(vtourFacade.getAllVtour());
	}

	@RequestMapping("/pageJson")
	@ResponseBody
	public Page<VtourDTO> pageJson(VtourDTO vtourDTO, @RequestParam int page,
			@RequestParam int pagesize) {
		Page<VtourDTO> all = vtourFacade.pageQueryVtour(vtourDTO, page,
				pagesize);
		return all;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public InvokeResult UpdateVtour(VtourDTO dto, HttpServletRequest request) throws DocumentException, IOException {
		String uploadPath = request.getSession().getServletContext()
				.getRealPath("/uploads");
		//如果isGyroscope设置有改动，则要程序更新tour.xml配置文件
		SAXReader reader = new SAXReader();
		String vtourXml = uploadPath+"/"+dto.getUuid()+"/vtour/tour.xml";
		File file = new File(vtourXml);
		Document doc;
		doc = reader.read(file);
		if("true".equals(dto.getIsGyroscope())) {
			PanoUtil.constructGyro(doc);
		} else {
			PanoUtil.unconstructGyro(doc);
		}
		//如果打开地图设置，则要程序更新xml
		VtourMapDTO vtourMapDTO = vtourFacade.findMapspotsByVtour(dto);
		if("true".equals(dto.getIsMapOpen())) {
			PanoUtil.constructMap(doc, request.getSession().getServletContext().getContextPath()+"/uploads/maps/"+vtourMapDTO.getMapUUID(), vtourMapDTO.getMapspotDTOs());
		} else {
			PanoUtil.unconstructMap(doc, vtourMapDTO.getMapspotDTOs());
		}
		//如果打开默认皮肤设置，则要程序更新xml
		if("true".equals(dto.getIsSkinOpen())) {
			PanoUtil.constructSkin(doc);
		} else {
			PanoUtil.unconstructSkin(doc);
		}
		
		XMLWriter outputWriter = new XMLWriter(new FileWriter(vtourXml));
		outputWriter.write(doc);
		outputWriter.close();
		
		return vtourFacade.updateVtour(dto);
	}

	// @RequestMapping(value = "/upload", method = RequestMethod.POST)
	// @ResponseBody
	// public void save(@RequestParam(value="files") MultipartFile[] files,
	// HttpServletRequest request) {
	// for(MultipartFile file: files) {
	// System.out.println(file.getOriginalFilename());
	// }
	// }

	/**
	 * pano保存路径：$constantPath/uploads/pano/uuid/uuid.后缀
	 * 
	 * @param pano
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	// public LinkedList<FileMeta> upload(MultipartHttpServletRequest request,
	// HttpServletResponse response) throws IOException {
	public LinkedList<FileMeta> upload(
			@RequestParam(value = "files") MultipartFile[] panosFiles,
			HttpServletRequest request) throws IOException {

		LinkedList<FileMeta> files = new LinkedList<FileMeta>();

		String uploadPath = request.getSession().getServletContext()
				.getRealPath("/uploads");

		// 生出vtour文件夹id
		String vtour_uuid = UUID.randomUUID().toString();
		VtourDTO vDto = new VtourDTO();
		vDto.setUuid(vtour_uuid);

		for (MultipartFile pano : panosFiles) {

			// 上传pano
			String fullName = pano.getOriginalFilename();
			String suffix = fullName.substring(fullName.lastIndexOf("."));
			String uuid = UUID.randomUUID().toString();
			String saveName = uuid + suffix;
			File savePanoFile = new File(uploadPath + "/" + vtour_uuid + "/"
					+ "/" + saveName);
			FileUtils
					.copyInputStreamToFile(pano.getInputStream(), savePanoFile);

			// 保存pano信息
			PanoDTO panoDTO = new PanoDTO();
			panoDTO.setSrcPanoImgName(fullName);
			panoDTO.setDestPanoImgPath(saveName);
			vDto.getPanoDTOs().add(panoDTO);

			FileMeta meta = new FileMeta();
			meta.setFileName(pano.getOriginalFilename());
			meta.setFileSize(pano.getSize() / 1024 + "kb");
			meta.setFileType(pano.getContentType());
			files.add(meta);
		}

		addVtour(vDto);

		return files;
	}

	@RequestMapping(value = "/upload_xml", method = RequestMethod.POST)
	@ResponseBody
	public void uploadXML(
			@RequestParam(value = "xml_file") MultipartFile vtourXML,
			@RequestParam(value = "vtour_uuid") String vtour_uuid,
			HttpServletRequest req) throws IOException {
		String uploadPath = req.getSession().getServletContext()
				.getRealPath("/uploads");
		File overrideXML = new File(uploadPath + "/" + vtour_uuid
				+ "/vtour/tour.xml");

		// 保存xml变更日志
		XmlEditLogDTO dto = new XmlEditLogDTO();
		dto.setVtour_uuid(vtour_uuid);
		dto.setOrginal_xml_str(FileUtils.readFileToString(overrideXML));
		// 覆盖更新vtour xml
		FileUtils.copyInputStreamToFile(vtourXML.getInputStream(), overrideXML);
		
		dto.setChanged_xml_str(FileUtils.readFileToString(overrideXML));
		dto.setChange_time(new Date());
		xmlEditLogFacade.saveLog(dto);

	}

	/**
	 * 调用java2sh，生成vTour
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws DocumentException 
	 */
	@RequestMapping(value = "/generateVtour", method = RequestMethod.POST)
	@ResponseBody
	public void generateVtour(String vtourUUID, String isMac,
			HttpServletRequest req) throws IOException, InterruptedException, DocumentException {
		// 调用java2sh
		// *****

		System.out.println("start to execute shell");
		String linux_shell = "/home/huangken/Documents/krpano_demo/shell/makeVtour_linux.sh";
		String mac_shell = "/Users/huangken/Documents/temp/makeVtour_mac.sh";

		// String source_pano =
		// "/home/huangken/Documents/krpano_demo/test/_sphere.tif";
		File vtourPath = new File(req.getSession().getServletContext()
				.getRealPath("/uploads/")
				+ "/" + vtourUUID);

		String panoPathStr = "";

		if (vtourPath.isDirectory()) {
			for (File file : vtourPath.listFiles()) {
				panoPathStr += file.getAbsolutePath() + " ";
			}
		}
		System.out.println("panoPathStr=" + panoPathStr);
		String[] cmdarray = { "/bin/sh", linux_shell, panoPathStr };
		if ("Y".equals(isMac)) {
			// cmdarray[0] = "open -a ";
			cmdarray[1] = mac_shell;
		}
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
		System.out.println("extValue=" + extValue);

		System.out.println("end to execute shell");
		// *****

		//生出二维码
		String uploadPath = req.getSession().getServletContext().getRealPath("/uploads");
		String targetPath = uploadPath+"/qrimgs/"+vtourUUID;
		File targetFile = new File(targetPath);
		if(!targetFile.exists()) {
			targetFile.mkdirs();
		}
		String fileSuffix = ".jpg";// 后缀名
		String storageName = "qr" + fileSuffix;// 存储名字
		String content = req.getSession().getServletContext().getContextPath()+"/uploads/"+vtourUUID+"/vtour/tour.html";
		ImageUtil.writeImage(targetPath, storageName, content);
		
		/*
		 * 由于krpano原生不支持生出多个xmlpath，所以只能自己copy tour.xml成tour_editor.xml，
		 * 供tour_editor.html读取，已满足查看和编辑单独开来
		 */
		File xmlFile = new File(vtourPath + "/vtour/tour.xml");
		File editorXml = new File(vtourPath + "/vtour/tour_editor.xml");
		FileUtils.copyFile(xmlFile, editorXml);
		//程序更新tour_editor.xml配置文件
		SAXReader reader = new SAXReader();
		Document doc;
		doc = reader.read(editorXml);
		PanoUtil.addReelProperties(doc);
		
		XMLWriter outputWriter = new XMLWriter(new FileWriter(editorXml));
		outputWriter.write(doc);
		outputWriter.close();
	}

}
