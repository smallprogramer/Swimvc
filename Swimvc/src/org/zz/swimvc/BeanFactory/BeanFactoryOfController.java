package org.zz.swimvc.BeanFactory;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;
import org.zz.swimvc.ControllerMapping.ControllerMapping;
import org.zz.swimvc.ControllerMapping.SimpleControllerMapping;
import org.zz.swimvc.XmlAnalysis.XmlAnalysis;
import org.zz.swimvc.annotation.Mapping;



public class BeanFactoryOfController {

	public static <T extends ControllerMapping> Map<String, T> BeanOfControllerMapping() {
		// TODO Auto-generated method stub

		try {
			
			return UrlAndControllerMapping();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}
	
	public static <T extends ControllerMapping> Map<String,T> UrlAndControllerMapping() throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		Map<String, T> UrlAndControllerMap = new HashMap<String, T>();
		
		XmlAnalysis xa = new XmlAnalysis();
		String ClassName = xa.XmlRead();
		
		
		Class<?> clazz = Class.forName(ClassName);
		Method[] method = clazz.getMethods();
		
		Object obj = Class.forName(ClassName).newInstance();
		
		for( int i = 0 ; i < method.length - 9; i++){
			
			Mapping mapping = method[i].getAnnotation(Mapping.class);
			
			String url = method[i].getName();
			String value = mapping.value();			
			T cm =  (T) new SimpleControllerMapping(value,ClassName);
			
			UrlAndControllerMap.put(url, cm);
			
		}
		
		return UrlAndControllerMap;
	}

}
