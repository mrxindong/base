package junit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sx_report.dao.mapper.SEC_USERMapper;
import com.sx_report.util.reportConfig;
import com.sx_report.util.file.WordAction;
import com.sx_report.util.file.WordUtil;

public class wordTest extends testBase {

	@Autowired
	SEC_USERMapper sec_user;

	@Autowired
	reportConfig config;

	static WordAction action = new WordAction();

	@Test
	public void test1() {
		Map<String, Object> dataMap = getData();
		WordUtil.createWord(dataMap, "wordT.ftl", "F:\\Test", "目标.doc");
	}

	public Map<String, Object> getData() {

		Map<String, Object> resultMap = new HashMap<>();
		String configField = config.getWordField();
		String[] fields = configField.split(";");
		for (String field : fields) {
			if (null != field && "" != field) {
				String table = field.split(":")[0];
				String fieldsName = field.split(":")[1];
				List<Map> result = sec_user.getDefineInfo(fieldsName, table, "1=1");
				resultMap.put(table.toUpperCase(), result);
			}
		}
		System.out.println(resultMap);
		return resultMap;
	}
}
