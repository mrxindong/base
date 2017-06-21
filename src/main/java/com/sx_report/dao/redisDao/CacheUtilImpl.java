package com.sx_report.dao.redisDao;

import java.io.IOException;
import java.util.List;  
import java.util.concurrent.TimeUnit;  
  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.data.redis.core.StringRedisTemplate;  
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
  
@Component("cacheUtil")  
public class CacheUtilImpl  {  
      
    @Autowired  
    private StringRedisTemplate redisTemplate;//redis操作模板  
    
    ObjectMapper mapper = new ObjectMapper(); 
      
          
    public void put(String key, String value) {  
        if (key==null || "".equals(key)) {  
            return;  
        }  
        redisTemplate.opsForHash().put(key, key, value);  
          
    }  
  
      
    public void put(String key, Object value) {  
        if (key==null || "".equals(key)) {  
            return;  
        }  
        try {
			redisTemplate.opsForHash().put(key, key, mapper.writeValueAsString(value));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}  
          
    }  
  
      
    public <T> T get(String key, Class<T> className) {  
        Object obj = redisTemplate.opsForHash().get(key, key);  
        if(obj == null){  
            return null;  
        }  
        try {
			return (T) mapper.readValue((String)obj, className.getClass());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return null;
    }  
  
      
    public String get(String key) {  
        Object obj = redisTemplate.opsForHash().get(key, key);  
        if(obj == null){  
            return null;  
        }else{  
            return String.valueOf(obj);  
        }  
    }  
      
}  