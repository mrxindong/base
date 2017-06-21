package ThreadTest;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	public static void main(String[] args) {
		// 创建一个计数阈值为5的信号量对象  
		// 只能5个线程同时访问  
		Semaphore semp = new Semaphore(5);  
		  
		try {  
		    // 申请许可  
		    semp.acquire();  
		    try {  
		        // 业务逻辑  
		    } catch (Exception e) {  
		  
		    } finally {  
		        // 释放许可  
		        semp.release();  
		    }  
		} catch (InterruptedException e) {  
		  
		}  
	}

}
