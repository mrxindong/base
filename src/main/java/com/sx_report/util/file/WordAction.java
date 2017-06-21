package com.sx_report.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;


/**
 * @Desc：生成word
 * @Author：张轮
 * @Date：2014-1-22下午04:52:03
 */
@Component
@SuppressWarnings("serial")
public class WordAction {

 private String filePath; //文件路径
 private String fileName; //文件名称
 private String fileOnlyName; //文件唯一名称

 /**
  * @Desc：生成word文档
  * @Author：张轮
  * @Date：2014-1-22下午07:29:58
  * @return
  */
    public String createWord(Map<String, Object> dataMap) {

        /** 组装数据 */
       
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
        dataMap.put("currDate",sdf.format(new Date()));
        
        Map secMap=new HashMap<String,Object>();
        secMap.put("ACCOUNTNAME", "tom jerry");
        secMap.put("PASSWORD", "password123");
        
        dataMap.put("SEC_USER", secMap);
        
        List<Map<String, Object>> newsList=new ArrayList<Map<String,Object>>();
        for(int i=1;i<=10;i++){
         Map<String, Object> map=new HashMap<String, Object>();
         map.put("NAME", "name"+i);
         map.put("OUTCODE", "OUTCODE"+(i*2));
         map.put("DISPLAYNAME", "DISPLAYNAME"+(i*3));
         newsList.add(map);
        }
        dataMap.put("BI_REPORT_CONFIG",newsList);
        
        /** 文件名称，唯一字符串 */
        Random r=new Random();
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        StringBuffer sb=new StringBuffer();
        sb.append(sdf1.format(new Date()));
        sb.append("_");
        sb.append(r.nextInt(100));
        
        //文件路径
       	
		/*try {
			File cfgFile = ResourceUtils.getFile("classpath:wordT.xml");
			filePath=cfgFile.getPath();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
        
        //文件唯一名称
        fileOnlyName = "用freemarker导出的Word文档_"+sb+".doc";
        
        //文件名称
        fileName="用freemarker导出的Word文档.doc";
        
        /** 生成word */
        WordUtil.createWord(dataMap, "wordT.ftl", "F:\\Test", fileOnlyName);
        
        return "createWordSuccess";
    }
    
}