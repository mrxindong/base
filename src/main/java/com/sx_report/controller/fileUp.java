package com.sx_report.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sx_report.model.FileInfo;

@RequestMapping("/fileup/wopi")
@Controller
public class fileUp {
	
	static String filePath="E:\\document\\"; //规划BS需要卦装的服务接口.doc

	@RequestMapping("/files/{name}")
	@ResponseBody
	public static Object checkFileInfo(@PathVariable( "name")  String name, ServletRequest request, ServletResponse response) {
	    HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;
	    String uri = httpRequest.getRequestURI();

	    FileInfo info = new FileInfo();
	    try {
	        // 获取文件名
	        String fileName = URLDecoder.decode(uri.substring(uri.indexOf("wopi/files/") + 11, uri.length()), "UTF-8");
	        if (fileName != null && fileName.length() > 0) {
	            String path = filePath + fileName.substring(0,fileName.lastIndexOf("."));
	            File file = new File(path);
	            if (file.exists()) {
	            	// 取得文件名
	                info.setBaseFileName(file.getName());
	                info.setSize(file.length());
	                info.setOwnerId("admin");
	                info.setVersion(file.lastModified());
	                info.setSha256(getHash256(file));
	                info.setAllowExternalMarketplace(true);
	                info.setUserCanWrite(true);
	                info.setSupportsUpdate(true);
	            }
	        }
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	    return info;
	}
	
	@RequestMapping(value="/files/{name}/contents", method= RequestMethod.GET)
	public void getFile(@PathVariable( "name") String name, HttpServletResponse response) {
	    try {
	        // 文件的路径
	        String path = filePath + name;
	        File file = new File(path);
	        // 取得文件名
	        String filename = file.getName();
	        String contentType = "application/octet-stream";
	        // 以流的形式下载文件
	        InputStream fis = new BufferedInputStream(new FileInputStream(path));
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        // 清空response
	        response.reset();

	        // 设置response的Header
	        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("utf-8"), "ISO-8859-1"));
	        response.addHeader("Content-Length", "" + file.length());
	        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	        response.setContentType(contentType);
	        toClient.write(buffer);
	        toClient.flush();
	        toClient.close();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}    
	
	@RequestMapping(value="/files/{name}/contents", method= RequestMethod.POST)
	public void postFile(@PathVariable( "name") String name, @RequestBody byte[] content) {
	    // 文件的路径
	    String path = filePath + name;
	    File file = new File(path);

	    try {
	        if (!file.exists()) {
	            file.createNewFile();//构建文件
	        }
	        FileOutputStream fop = new FileOutputStream(file);
	        fop.write(content);
	        fop.flush();
	        fop.close();
	        System.out.println("------------ save file ------------ ");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	

    /**
     * 获取文件的SHA-256值
     * @param file
     * @return
     */
    public static String getHash256(File file) {
        String value = "";
        // 获取hash值
        try {
            byte[] buffer = new byte[1024];
            int numRead;
            InputStream fis = new FileInputStream(file);
            //如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
            MessageDigest complete = MessageDigest.getInstance("SHA-256");
            do {
                //从文件读到buffer
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    //用读到的字节进行MD5的计算，第二个参数是偏移量
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);

            fis.close();
            value = new String(Base64.encodeBase64(complete.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
    /***
     *  利用Apache的工具类实现SHA-256加密
     * @param str 加密后的报文
     * @return
     */
    public static String getSHA256Str(String str){
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }
}
