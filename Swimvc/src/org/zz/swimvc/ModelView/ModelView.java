package org.zz.swimvc.ModelView;

import java.util.HashMap;



import java.util.Map;

import org.zz.swimvc.View.View;

public class ModelView {

	private View view = new View();
	
	private Map<String, Object> map = new HashMap<String, Object>();
	private void sysout() {
		// TODO Auto-generated method stub

	}
	
	
	
	public ModelView(String ViewPath){
		
		this.view.setViewPath(ViewPath);
		
	}

	public <T> void addModel(String key , Object value){
		
		this.map.put(key,value);
		
	}
	
	
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Map<String, Object> getMap() {
		return  map;
	}

	public void setMap(Map map) {
		this.map =  map;
	}
	
	
	
}
