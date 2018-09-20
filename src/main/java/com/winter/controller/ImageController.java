/**
 * 
 */
package com.winter.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.base.Result;
import com.winter.base.ResultEnum;
import com.winter.utils.DateUtil;
import com.winter.utils.FileUtil;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 下午3:52:10
 */
@Controller
@RequestMapping(value = "/image")
public class ImageController {
	private final static Logger log = LoggerFactory.getLogger(ImageController.class);

	@Autowired
	private ResourceLoader resourceLoader;
	// 图片http前缀
	@Value("${img_url_pre}")
	private String IMG_URL_PRE;
	// 图片路径
	@Value("${img_path}")
	private String IMG_PATH;
	// 图片后缀
	@Value("${img_suffix}")
	private String IMG_SUFFIX;

	// 处理文件上传
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public Result upload(@RequestParam MultipartFile file[]) throws IOException {
		Result result = new Result();
		String path = IMG_PATH + "/image/" + DateUtil.getTodatDate().replace("-", "/");
		String suffixArr[] = IMG_SUFFIX.split(",");
		if (FileUtil.createDirIfNotFound(path)) {
			boolean flag = true;// 判断标志
			for (MultipartFile tempfile : file) {
				if (!FileUtil.isFitFormat(tempfile.getOriginalFilename(), suffixArr)) {
					flag = false;
				}
			}
			if (flag) {
				List<String> list = new LinkedList<String>();
				for (MultipartFile f : file) {
					if (!f.isEmpty()) {
						String filename = System.currentTimeMillis() + f.getOriginalFilename()
								.substring(f.getOriginalFilename().lastIndexOf("."), f.getOriginalFilename().length());
						String realPath = path + "/" + filename;
						File uploadfile = new File(realPath);
						if (!uploadfile.exists()) {
							uploadfile.createNewFile();
						}
						try {
							f.transferTo(uploadfile);
							log.info("绝对路径为：" + realPath);
							String urlpic = IMG_URL_PRE + "/image/show" + path.replace(IMG_PATH, "") + "/" + filename;
							log.info("上传的文件访问的http地址为：" + urlpic);
							list.add(urlpic);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				if (list.size() <= 0) {
					result.setResultEnum(ResultEnum.FILE_UPLOAD_ERROR);
				} else {
					result.setResultEnum(ResultEnum.SUSS);
					result.setData(list);
				}
			} else {
				log.error("上传的文件格式不对!");
				result.setResultEnum(ResultEnum.FILE_FORMAT_ERROR);
			}
		} else {
			log.error("创建目录失败!系统出现错误");
			result.setResultEnum(ResultEnum.FAIL);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/show/**")
	public ResponseEntity<?> getFile(String filename, HttpServletRequest request) {
		try {
			String path = request.getRequestURI().split("/show")[1];
			String abPath = IMG_PATH + path;
			System.out.println(abPath);
			return ResponseEntity.ok(resourceLoader.getResource("file:" + abPath));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
