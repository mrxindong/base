package com.sx_report.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sun.corba.se.impl.io.OptionalDataException;

@Component
public class Utils {
	
	public boolean filterStr(String input){
		if (input.contains("{") || input.contains("}") || input.contains("-") || input.contains("#")) {
			return false;
		}else{
			return true;
		}
	}
	
	public static String stringToUtf(String value){
		try {
			return new String(value.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T > boolean compareList(List<T>  a ,List<T> b){
		if(a.size() != b.size()){
		    return false;
		}
		return true;
		 
	}
	
	/**
	 * 深拷贝
	 * @return
	 * @throws IOException
	 * @throws OptionalDataException
	 * @throws ClassNotFoundException
	 */
	private static Object deepClone(Object target) throws IOException, OptionalDataException, ClassNotFoundException {
		// 将对象写到流里
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(target);
		// 从流里读出来
		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (oi.readObject());
	}
	
	public static Object Clone(Object target){
		Object oo=null;
		try {
			oo =  Utils.deepClone(target);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oo;
	}
}
