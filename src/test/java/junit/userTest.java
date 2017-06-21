package junit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sx_report.service.UserService;

public class userTest extends testBase {
	
	@Autowired
	UserService users;

	@Test
	public void test() throws Exception{
		String result=users.find();
		System.out.println(result);
	}
}
