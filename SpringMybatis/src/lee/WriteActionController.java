package lee;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

//컨트롤러 클래스=>사용자로부터 요청을 받아서 처리해주는 클래스(=액션클래스)
//   1) 보편적인 컨트롤러(Controller)   
//   2) 사용자로부터 값을 입력처리해주는 컨트롤러(글쓰기,글수정,로그인,->AbstractCommandController                  
public class WriteActionController extends AbstractCommandController {

	BoardDAO dao;//BoardDAO dao=new BoardDAO();
	//commandClass  ->setCommandClass(BoardCommand command) 상속받은 멤버변수와 메서드
	//<property name="commandClass"  value="lee.BoardCommand" />
	
	public void setDao(BoardDAO dao) { //<property name="dao"><ref bean="id명" /></properety>
		this.dao = dao;
		System.out.println("setDao() 호출됨!(dao)=>"+dao);
	}

	//1.request(요청객체) 2.response(응답객체) 3.입력받은값을 저장한 객체(Object(다 입력O))
	//4.BindException->사용자로부터 값을 입력시 에러발생->에러를 처리해주는 객체
	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
			                                  Object command,BindException error) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("WriteActionController 실행됨!");
		request.setCharacterEncoding("utf-8");
		BoardCommand data=(BoardCommand)command;//num이 빠져있는 상태
		/*
		String author=data.getAuthor();
		String content=data.getContent();
		String title=data.getTitle();
		*/
		int newNum=dao.getNewNum()+1;
		data.setNum(newNum);//새로 저장할 게시물번호까지 계산이 된 상태에서 저장
		dao.write(data);//dao.write(data);
		
		return new ModelAndView("redirect:/list.do");
	}
}




