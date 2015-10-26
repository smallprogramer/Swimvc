package testMethod;

import org.zz.swimvc.ModelView.ModelView;
import org.zz.swimvc.annotation.Controller;
import org.zz.swimvc.annotation.Mapping;



public class map {

	
	@Mapping("/AddStu")
	public ModelView Add(String name){
		
		ModelView mv = new ModelView("/WEB-INF/jsp/view.jsp");
		
		User user = new User();
		user.setName(name);
		user.setAge(20);
		mv.addModel("user", user);
		
		return mv;
	}
	
	
	
	@Mapping("/DeleteStu")
	public ModelView Delete(){
		
		System.out.println("delete·½·¨");
		ModelView mv = new ModelView("/WEB-INF/jsp/login.jsp");
		return mv;
		
	}
	

	
}
