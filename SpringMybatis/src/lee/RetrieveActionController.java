package lee;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//컨트롤러 클래스=>사용자로부터 요청을 받아서 처리해주는 클래스(=액션클래스)
//                          Controller를 상속받아야 된다.=>handleRequest 메서드 이용할려고
public class RetrieveActionController implements Controller {

	BoardDAO dao;//has a 관계
	
	public void setDao(BoardDAO dao) { //<property name="dao"><ref bean="id명" /></properety>
		this.dao = dao;
		System.out.println("setDao() 호출됨!(dao)=>"+dao);
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                              HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("RetrieveActionController 실행됨!");
		///retrieve.do?num=3
	    String num=request.getParameter("num");
	    System.out.println("전달받은 num=>"+num);
	    //레코드 담을 객체 필요
	    //Board data=dao.retrieve(num);->before
	    //after
	    dao.updateReadcnt(num);//readcnt=readcnt+1
	    BoardCommand data=dao.retrieve(num);
	    /*
		ModelAndView mav=new ModelAndView();//실행결과를 담아서->jsp로 전달하는 역할
		mav.setViewName("retrieve"); */
	    ModelAndView mav=new ModelAndView("retrieve");//생성자(이동할 페이지명)
		mav.addObject("data",data);//${data(키명)} ->request.getAttribute("data");
		return mav;
	}
}




