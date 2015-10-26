package org.zz.swimvc.javassistGetParamName;

import org.zz.swimvc.tools.FindPath;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class GetParamName {

	
	public String GetName(String MethodName , String ClassAllName){
		
//		FindPath fp = new FindPath();
//		ClassAllName = fp.FindPath(ClassAllName);
		
	
		try {
			
			 
			   try  {
				   
				   ClassLoader classLoader =  this.getClass().getClassLoader();
		        	
		           Class clazz = classLoader.loadClass(ClassAllName);
		                    	
		           ClassPool pool  =  ClassPool.getDefault();
		           pool.appendClassPath(new ClassClassPath(clazz));
		           CtClass ctClass = pool.get(clazz.getName());
		           CtMethod cm  =  ctClass.getDeclaredMethod(MethodName);
		           
		             
		            
		          
		          // 使用javaassist的反射方法获取方法的参数名 
		          MethodInfo methodInfo  =  cm.getMethodInfo();  
		          CodeAttribute codeAttribute  =  methodInfo.getCodeAttribute();  
		          LocalVariableAttribute attr  =  (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);  
		           if  (attr  ==   null )  {
		               // exception 
		          }
		         String[] paramNames  =   new  String[cm.getParameterTypes().length];  
		           int  pos  =  Modifier.isStatic(cm.getModifiers())  ?   0  :  1 ;  
		           for  ( int  i  =   0 ; i  <  paramNames.length; i ++ )  
		              paramNames[i]  =  attr.variableName(i  +  pos);      
		           // paramNames即参数名 
		           for  ( int  i  =   0 ; i  <  paramNames.length; i ++ ) {
		              return paramNames[i];
		          }
		          
		      }  catch  (NotFoundException e) {
		          e.printStackTrace();
		      } 
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   
     

		return null;
	}
}
