package lee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//��Ʈ�ѷ� Ŭ����=>����ڷκ��� ��û�� �޾Ƽ� ó�����ִ� Ŭ����(=�׼�Ŭ����)
//                          Controller�� ��ӹ޾ƾ� �ȴ�.=>handleRequest �޼��� �̿��ҷ���
public class ListActionController implements Controller {

	BoardDAO dao;//BoardDAO dao=new BoardDAO();
	
	public void setDao(BoardDAO dao) { //<property name="dao"><ref bean="id��" /></properety>
		this.dao = dao;
		System.out.println("setDao() ȣ���!(dao)=>"+dao);
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                              HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("ListActionController �����!");
		//ArrayList list=dao.list();
		List list=dao.list();
		//--------------------------
		ModelAndView mav=new ModelAndView();//�������� ��Ƽ�->jsp�� �����ϴ� ����
		mav.setViewName("list");//�̵��� ���ϸ� ���-> /list.jsp
		//request.setAttribute("list",list);
		mav.addObject("list",list);//${list(Ű��)}
		return mav;
	}
}




