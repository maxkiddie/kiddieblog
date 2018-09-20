package com.winter.utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FileUtil {
	private static Logger log = LogManager.getLogger(FileUtil.class);

	public static boolean createDirIfNotFound(String dirpath) {
		File file = new File(dirpath);
		if (!file.exists()) {
			log.info("文件路径不存在!");
			if (file.mkdirs()) {
				log.info("文件路径创建成功!创建文件路径:" + dirpath);
			} else {
				log.error("文件路径创建失败!");
				return false;
			}
		}
		return true;
	}

	public static boolean createFileIfNotFound(String filepath) {
		File file = new File(filepath);
		if (!file.exists()) {
			String dirpath = filepath.substring(0, filepath.lastIndexOf(File.separator));
			if (createDirIfNotFound(dirpath)) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					log.error("文件创建失败！" + filepath);
					e.printStackTrace();
					return false;
				}
			} else {
				log.error("创建路径失败!" + dirpath);
				return false;
			}
		} else {
			log.info("文件存在!直接修改文件,绝对路径为:" + filepath);
		}
		return true;
	}

	/**
	 * 判断是否符合文件后缀 xuzhaojie -2016-9-14 上午11:54:27
	 */
	public static boolean isFitFormat(String filename, String[] fotmat) {
		// 如果文件名为空或者格式，则直接错误
		if (StringUtils.isNull(filename) || fotmat == null)
			return false;
		String name = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		for (String str : fotmat) {
			if (name.equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String filename = "E:" + File.separator + "mykiddie" + File.separator + "dd.xls";
		File file = new File(filename);
		System.out.println(file.getAbsolutePath());
	}
}
