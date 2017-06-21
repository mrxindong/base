package junit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sx_report.dao.redisDao.CacheUtilImpl;

public class testRedis extends testBase {
	@Autowired
	CacheUtilImpl cacheUtil;


	@Test
	public void test() {
		 //简单字符串处理  
	    cacheUtil.put("name", "1232");  
	    System.out.println("String---name--"+cacheUtil.get("name"));  
	}
}
