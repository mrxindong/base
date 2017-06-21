package com.sx_report.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sx_report.service.UserService;

@RequestMapping("/convertController")
@Controller
public class ConvertController {

	@Autowired
	UserService users;

	@RequestMapping("/word2Pdf")
	public ModelAndView word2Pdf(HttpServletRequest request, HttpServletResponse response, String param)
			throws Exception {
		if (param.equals("aaa")) {
			throw new Exception();
		}

		String result = users.find();
		java.util.Map<String, Object> pdfMap = new HashMap<String, Object>();
		pdfMap.put("result", result);
		return new ModelAndView("/pdf", pdfMap);

	}

	@RequestMapping("/download")
	public void download(HttpServletRequest request, HttpServletResponse response, String path) {
		String fileName = path.substring(path.lastIndexOf("\\") + 1);
		// response.setCharacterEncoding("utf-8");
		try {
			// fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
			if (fileName.contains("..") == false) { // 限制目录返回
				InputStream inputStream = new FileInputStream(new File(path));
				OutputStream os = response.getOutputStream();
				byte[] b = new byte[2048];
				int length;
				while ((length = inputStream.read(b)) > 0) {
					os.write(b, 0, length);
				}
				// 这里主要关闭。
				os.close();
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
