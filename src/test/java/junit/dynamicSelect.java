package junit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sx_report.dao.mapper.SEC_USERMapper;
import com.sx_report.util.reportConfig;

public class dynamicSelect extends testBase {
	@Autowired
	SEC_USERMapper sec_user;

	@Autowired
	reportConfig config;

	// @Test
	public void defineInfo() {
		List<Map> result = sec_user.getDefineInfo("*", "sec_user", "1=1");
		System.out.println(result);
	}

	@Test
	public  Map<String, Object>  defineConfig(){
	
		Map<String, Object> resultMap=new HashMap<>();
		String condation=" sid=100882 ";
		String configField=config.getWordField();
		String[] fields=configField.split(";");
		for (String field : fields) {
			if (null!=field && "" !=field) {
				String table=field.split(":")[0];
				String fieldsName=field.split(":")[1];
				List<Map> result = sec_user.getDefineInfo(fieldsName, table, "1=1");
				resultMap.put(table.toUpperCase(), result);		
			}			
		}
		System.out.println(resultMap);
		return resultMap;
	}
	
	private String replaceMark(String input,Map<String,List<Map>> resultMap){
		
		return input;
	}
}
