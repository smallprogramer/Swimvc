package org.zz.swimvc.doDistributeServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zz.swimvc.BeanFactory.BeanFactoryOfController;
import org.zz.swimvc.ControllerMapping.SimpleControllerMapping;
import org.zz.swimvc.ExecutionController.ExecutionController;
import org.zz.swimvc.ModelView.ModelView;
import org.zz.swimvc.View.View;

import testMethod.map;



public class DistributeServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	private Map<String,SimpleControllerMapping> UrlMapping = new HashMap<String, SimpleControllerMapping>();
	
	
	@Override
	public void init() throws ServletException {		
		initSwimvc();
	}


	private void initSwimvc() {
		
		initControllerMapping();

	}


	private void initControllerMapping() {
		
		UrlMapping = BeanFactoryOfController.BeanOfControllerMapping();

	}

	
	
	
	private void doDistributeServlet(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelView mv = null;
		ExecutionController ec = new ExecutionController();
		
		
		mv = ec.Execution(request, response, UrlMapping);		
		render(mv,request,response);
		
	       
		
		
		
		
		
	}

	


	private static void render(ModelView mv, HttpServletRequest request,
			HttpServletResponse response) {
		
		View view;
		view = mv.getView();		
		packModel(mv,request);		
		DispatchForward(view,request,response);
		
		
	}


	private static void DispatchForward(View view, HttpServletRequest request,
			HttpServletResponse response) {
		
		String forwardPath = view.getViewPath();
		try {
			
			request.getRequestDispatcher(forwardPath).forward(request, response);
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private static <T> void packModel(ModelView mv, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String,T> MMap = (Map<String, T>) mv.getMap();
		
		if(MMap == null){
			
			return;
			
		}
		
		for(Map.Entry<String,T> entry : MMap.entrySet()){
			
			String key = entry.getKey();
			T value =  entry.getValue();
			
			request.setAttribute(key,value);
			
		}
		
		
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doDistributeServlet(request,response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doDistributeServlet(request,response);
		
	}
	
	


}
