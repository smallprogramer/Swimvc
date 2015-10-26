package org.zz.swimvc.ControllerMapping;

public class SimpleControllerMapping implements ControllerMapping{


	String Controller;
	String className;
	
	
	public SimpleControllerMapping(String Controller, String className){	
		
		this.Controller = Controller;
		this.className = className;
	}
	
	public SimpleControllerMapping() {
		// TODO Auto-generated constructor stub
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	
	
	public String getController() {
		return Controller;
	}
	public void setController(String controller) {
		Controller = controller;
	}
	
	
	@Override
	public ControllerMapping getControl() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
