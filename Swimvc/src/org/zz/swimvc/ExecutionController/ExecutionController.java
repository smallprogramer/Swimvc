package org.zz.swimvc.ExecutionController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zz.swimvc.ControllerMapping.SimpleControllerMapping;
import org.zz.swimvc.Exception.NotFoundControllerException;
import org.zz.swimvc.ModelView.ModelView;
import org.zz.swimvc.javassistGetParamName.GetParamName;

public class ExecutionController {

	public ModelView Execution(HttpServletRequest request,
			HttpServletResponse response,Map<String,SimpleControllerMapping> map){
	
		SimpleControllerMapping scm = new SimpleControllerMapping();
		
		//取出Uri
		String RequestUrl = request.getRequestURI();
		RequestUrl = RequestUrl.substring(RequestUrl.lastIndexOf("/"), RequestUrl.length());
		
		//返回指定的Controller
		Map.Entry<String, SimpleControllerMapping> ControlEntry = CollectController(RequestUrl, map);
		
		if(ControlEntry == null){		
			try {				
				throw new NotFoundControllerException("未找到对应处理器");					
			} catch (NotFoundControllerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String MethodName = ControlEntry.getKey();
		scm = ControlEntry.getValue();
		
		ModelView mv = InvokeMethod(MethodName , scm , request);
		
		return mv;
		

	}
	
	
	
	
	
	public static ModelView InvokeMethod(String MethodName , SimpleControllerMapping sm , HttpServletRequest request){
		
		try {
			
			String url = sm.getController();
			
			String ClassAllName = sm.getClassName();
			
			Class<?> clazz = Class.forName(ClassAllName);
			Object obj;
			
			try {
				
				GetParamName gp = new GetParamName();
				
				obj = Class.forName(ClassAllName).newInstance();
				String ParamName = gp.GetName(MethodName , ClassAllName);
				Method[] methods = clazz.getMethods();
				
				for(Method me : methods){
					
					if(me.getName().equals(MethodName)){
						
						if(ParamName != null){
							
							String value = request.getParameter(ParamName);
							
							if(value != null){
								
								ModelView ob = (ModelView) me.invoke(obj, value);
					
								return (ModelView) ob;
							}else{
								ModelView ob = (ModelView) me.invoke(obj,"null");
								return (ModelView) ob;
							}

							
						}else{
						
							ModelView ob = (ModelView) me.invoke(obj);
							return (ModelView) ob;
						}
						
						
					}
					
				}
				
				
				
				
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
			} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
			}
			
			}  catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public static Map.Entry<String, SimpleControllerMapping> CollectController(String RequestUrl , Map<String,SimpleControllerMapping> map){
		
		for(Map.Entry<String, SimpleControllerMapping> cmap : map.entrySet()){
			
			if(cmap.getValue().getController().equals(RequestUrl))
				
				return cmap;
						
		}
		return null;
	}
	
	
}
