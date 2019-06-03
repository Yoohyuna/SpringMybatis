package lee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//컨트롤러 클래스=>사용자로부터 요청을 받아서 처리해주는 클래스(=액션클래스)
//                          Controller를 상속받아야 된다.=>handleRequest 메서드 이용할려고
public class SearchActionController implements Controller {

	BoardDAO dao;//BoardDAO dao=new BoardDAO();
	
	public void setDao(BoardDAO dao) { //<property name="dao"><ref bean="id명" /></properety>
		this.dao = dao;
		System.out.println("setDao() 호출됨!(dao)=>"+dao);
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                              HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SearchActionController 실행됨!");
		//검색분야,검색어에 해당하는 레코드만 보여줄 수 있도록
		String searchName=request.getParameter("searchName");//검색분야
		String searchValue=request.getParameter("searchValue");//검색어
		//----------------------------------------------------------------
		//ArrayList list=dao.search(searchName,searchValue);//dao.list(); before
		//-------after------------------------------------------------
		BoardCommand data=new BoardCommand();
		data.setSearchName(searchName);
		data.setSearchValue(searchValue);
		List list=dao.search(data);
		//------------------------------------------------------------
		ModelAndView mav=new ModelAndView();//실행결과를 담아서->jsp로 전달하는 역할
		mav.setViewName("list");//이동할 파일명만 사용-> /list.jsp
		mav.addObject("list",list);//${list(키명)} =>검색된 데이터가 출력이 된다.
		return mav;
	}
}




