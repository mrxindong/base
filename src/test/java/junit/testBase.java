package junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ResourceUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:springmvc.xml" })
public class testBase extends AbstractTransactionalJUnit4SpringContextTests {

	 
	public void clearConsole()
	{
	    try
	    {
	        String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (Exception e)
	    {
	       
	    }
	}
	
//	@Test
	public void printPath() throws FileNotFoundException{
		File cfgFile = ResourceUtils.getFile("classpath:wordT.xml");
		String aa=cfgFile.getPath();
		String dd=cfgFile.getParent();
		String bb=testBase.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String cc=testBase.class.getResource("/").getPath();
		
		System.out.println(aa);
		System.out.println(bb);
		System.out.println(cc);
		System.out.println(dd);
	}
	
	
	
}
