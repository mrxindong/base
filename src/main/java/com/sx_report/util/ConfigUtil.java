/**
 * ConfigUtil 读取信息配置类
 * Copyright(c) 2015-2016, All Rights Reserved.
 * Project: hadoopMarker 
 * Author: wh
 * Createdate: 2015-8-19
 * Version: 1.0
 */
package com.sx_report.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	public static String NWServiceURL = null;
	public static String ELECTRURL = null;
	public static String STATIONURL = null;
	static{
		try {
			Properties p = new Properties();
			InputStream is = ConfigUtil.class.getResourceAsStream("/config.properties");
			p.load(is);
			NWServiceURL = p.getProperty("NWServiceURL");
			ELECTRURL = p.getProperty("ELECTRURL");
			STATIONURL=p.getProperty("STATIONURL");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
