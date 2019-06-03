package lee;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

//��Ʈ�ѷ� Ŭ����=>����ڷκ��� ��û�� �޾Ƽ� ó�����ִ� Ŭ����(=�׼�Ŭ����)
//   1) �������� ��Ʈ�ѷ�(Controller)->���� �Է�X   
//   2) ����ڷκ��� ���� �Է�ó�����ִ� ��Ʈ�ѷ�(�۾���,�ۼ���,�α���,->AbstractCommandController                  

public class UpdateActionController extends AbstractCommandController {

	BoardDAO dao;//BoardDAO dao=new BoardDAO();
	
	public void setDao(BoardDAO dao) { //<property name="dao"><ref bean="id��" /></properety>
		this.dao = dao;
		System.out.println("setDao() ȣ���!(dao)=>"+dao);
	}

	//1.request(��û��ü) 2.response(���䰴ü) 3.�Է¹������� ������ ��ü(Object(�� �Է�O))
	//4.BindException->����ڷκ��� ���� �Է½� �����߻�->������ ó�����ִ� ��ü
	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
			                                  Object command,BindException error) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("UpdateActionController �����!");
		request.setCharacterEncoding("utf-8");
		BoardCommand data=(BoardCommand)command;//�տ��� �Է¹�������->�ڵ�����
		//�Խù���ȣ ����(before)
		/*//                  Setter Method ȣ��
		String num=request.getParameter("num");
		//----------------------------------------------
		String author=data.getAuthor();
		String content=data.getContent();
		String title=data.getTitle();
		dao.update(num,author, title, content);//dao.update(data);
		*/
		//after
		dao.update(data);
		
		return new ModelAndView("redirect:/list.do");
	}
}




