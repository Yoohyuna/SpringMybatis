package lee;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//컨트롤러 클래스=>사용자로부터 요청을 받아서 처리해주는 클래스(=액션클래스)
//                          Controller를 상속받아야 된다.=>handleRequest 메서드 이용할려고
public class DeleteActionController implements Controller {

	BoardDAO dao;//has a 관계
	
	public void setDao(BoardDAO dao) { //<property name="dao"><ref bean="id명" /></properety>
		this.dao = dao;
		System.out.println("setDao() 호출됨!(dao)=>"+dao);
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                              HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("DeleteActionController 실행됨!");
		///delete.do?num=3
	    String num=request.getParameter("num");
	    System.out.println("전달받은 num=>"+num);
	 
	    dao.delete(num);//그대로 사용
	    
		ModelAndView mav=new ModelAndView();//실행결과를 담아서->jsp로 전달하는 역할
		mav.setViewName("redirect:/list.do"); //ListActionController->/list.jsp
	   
		return mav;
	}
}




