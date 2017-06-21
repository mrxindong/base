package com.sx_report.service.task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

@Component
public class MultiThread {
	private Map sqlMap;  
    private SqlSessionFactory sessionFacotry;  
    private CountDownLatch endSingle;    
    private int count=3;
  
    public SqlSessionFactory getSessionFacotry() {  
        return sessionFacotry;  
    }  
      
    /* 
    * Spring容器注入SessionFactory 
    */  
    @Resource(name="sqlSessionFactory")  
    public void setSessionFacotry(SqlSessionFactory sessionFacotry) {  
        this.sessionFacotry = sessionFacotry;  
    }  
  
    public Map getSqlMap() {  
        return sqlMap;  
    }  
  
    public void setSqlMap(Map sqlMap) {  
        this.sqlMap = sqlMap;  
        this.endSingle = endSingle;  
    }  
  
    public MultiThread(Map sqlMap) {  
        super();  
        this.sqlMap = sqlMap;  
    }  
    
    public MultiThread(int count) {  
        super();  
        this.count = count;  
    }  
  
    private MultiThread() {  
        super();  
    }  
  
    /* 
    * @param endSingle线程计数器 
    * @param pool线程池 
    */  
      
    public Map createThread() {  
  
        CountDownLatch endSingle = new CountDownLatch(count);  
          
        Map resultMap = new HashMap<String,Future>();  
        ExecutorService pool = Executors.newCachedThreadPool();  
  
//        Iterator iterator = this.sqlMap.keySet().iterator();  
        int i=0;
        Future<?> future = null;  
        while (i<count) {  
            // tableName is the key of resultMap  
//            String tableName = (String) iterator.next();  
              
//            String sql = (String) this.sqlMap.get(tableName);  
  
//            if (tableName != null && sql != null) {  
                // new 子线程  
                Callable task = new DaoThread(sessionFacotry,"",endSingle);  
                  
                //提交到线程池  
                future = pool.submit(task);  
                  
                // 执行结果放在resultMap  
                resultMap.put(i, future);  
                i++;
//            }  
        }  
          
        //线程池关闭，不接受新任务，只执行已提交的任务  
        pool.shutdown();  
          
        //等待所有线程执行完毕  
        try {  
            endSingle.await();  
        } catch (InterruptedException e1) {  
            e1.printStackTrace();  
        }  
          
        return resultMap;  
  
    }  
}
