package lee;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//��Ʈ�ѷ� Ŭ����=>����ڷκ��� ��û�� �޾Ƽ� ó�����ִ� Ŭ����(=�׼�Ŭ����)
//                          Controller�� ��ӹ޾ƾ� �ȴ�.=>handleRequest �޼��� �̿��ҷ���
public class SearchActionController implements Controller {

	BoardDAO dao;//BoardDAO dao=new BoardDAO();
	
	public void setDao(BoardDAO dao) { //<property name="dao"><ref bean="id��" /></properety>
		this.dao = dao;
		System.out.println("setDao() ȣ���!(dao)=>"+dao);
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                              HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SearchActionController �����!");
		//�˻��о�,�˻�� �ش��ϴ� ���ڵ常 ������ �� �ֵ���
		String searchName=request.getParameter("searchName");//�˻��о�
		String searchValue=request.getParameter("searchValue");//�˻���
		//----------------------------------------------------------------
		//ArrayList list=dao.search(searchName,searchValue);//dao.list(); before
		//-------after------------------------------------------------
		BoardCommand data=new BoardCommand();
		data.setSearchName(searchName);
		data.setSearchValue(searchValue);
		List list=dao.search(data);
		//------------------------------------------------------------
		ModelAndView mav=new ModelAndView();//�������� ��Ƽ�->jsp�� �����ϴ� ����
		mav.setViewName("list");//�̵��� ���ϸ� ���-> /list.jsp
		mav.addObject("list",list);//${list(Ű��)} =>�˻��� �����Ͱ� ����� �ȴ�.
		return mav;
	}
}




