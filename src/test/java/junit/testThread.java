package junit;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sx_report.service.task.MultiThread;

public class testThread  extends testBase{
	
	@Autowired
	MultiThread multiThread;
	
	@Test
	public void test1(){
		Map<Integer,Future> resultMap= multiThread.createThread();
	
		for (Integer key : resultMap.keySet()) {
		 
		    Future<?> f =  (Future<?>) resultMap.get(key);  
		  
		    List lst = null;
		    try {  
		        lst = (List) f.get();  
		    } catch (InterruptedException e) {  
		        e.printStackTrace();  
		    } catch (ExecutionException e) {  
		        e.printStackTrace();  
		    }  
		    //检查从Hibernate返回的List  
		    System.out.println(key+" :"+lst);  
		      
		   
		}		
	}
}
