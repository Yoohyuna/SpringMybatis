package lee;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//��Ʈ�ѷ� Ŭ����=>����ڷκ��� ��û�� �޾Ƽ� ó�����ִ� Ŭ����(=�׼�Ŭ����)
//                          Controller�� ��ӹ޾ƾ� �ȴ�.=>handleRequest �޼��� �̿��ҷ���
public class RetrieveActionController implements Controller {

	BoardDAO dao;//has a ����
	
	public void setDao(BoardDAO dao) { //<property name="dao"><ref bean="id��" /></properety>
		this.dao = dao;
		System.out.println("setDao() ȣ���!(dao)=>"+dao);
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                              HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("RetrieveActionController �����!");
		///retrieve.do?num=3
	    String num=request.getParameter("num");
	    System.out.println("���޹��� num=>"+num);
	    //���ڵ� ���� ��ü �ʿ�
	    //Board data=dao.retrieve(num);->before
	    //after
	    dao.updateReadcnt(num);//readcnt=readcnt+1
	    BoardCommand data=dao.retrieve(num);
	    /*
		ModelAndView mav=new ModelAndView();//�������� ��Ƽ�->jsp�� �����ϴ� ����
		mav.setViewName("retrieve"); */
	    ModelAndView mav=new ModelAndView("retrieve");//������(�̵��� ��������)
		mav.addObject("data",data);//${data(Ű��)} ->request.getAttribute("data");
		return mav;
	}
}




