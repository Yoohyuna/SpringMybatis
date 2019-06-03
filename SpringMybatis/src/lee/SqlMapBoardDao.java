package lee;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

//SqlSessionDaoSupport�� ���
//SqlSessionŬ���� ��ü�� �ʿ�=>getSqlSession()�ʿ�->DB�����ؼ� �����͸� �������⶧����
public class SqlMapBoardDao extends SqlSessionDaoSupport implements BoardDAO {

	@Override
	public List list() throws DataAccessException {
		// TODO Auto-generated method stub
		//select->���ڵ��Ѱ��̻� ->selectList("�����ų sql������ id")
		//select->���ڵ� �Ѱ�->selectOne("�����ų sql������ id")
		return getSqlSession().selectList("list");//~("board.list") id���� �ν�X ���ӽ����̽���
	}
	
	@Override
	public int getNewNum() throws DataAccessException {
		// TODO Auto-generated method stub
		//���ڵ� �Ѱ�(SelectOne),Ư���ʵ��� �ڷ���(Wrapper)
		//����)selectOne("�����ų sql�� id",�Ű����������Ұ�)
		return (Integer)getSqlSession().selectOne("getNewNum");//Object=>Integer->int
	}

	@Override
	public void write(BoardCommand data) throws DataAccessException {
		// TODO Auto-generated method stub
		//����) sqlSession��ü��.insert("�����ų sql������ id",������ �Ű�������)
		getSqlSession().insert("write",data);
	}
	
	//��ȸ�� ����=>RetrieveActionController,retrieve.jsp �ҽ�����
	@Override
	public void updateReadcnt(String num) throws DataAccessException {
		// TODO Auto-generated method stub
		//����)update�±�->sqlSession��ü��.update("�����ų sql������ id",�Ű�������)
		getSqlSession().update("updateReadcnt",num);
	}
	//�ۻ󼼺���
	@Override
	public BoardCommand retrieve(String num) throws DataAccessException {
		// TODO Auto-generated method stub
		return (BoardCommand)getSqlSession().selectOne("retrieve",num);
	}
	
	@Override
	public void update(BoardCommand data) throws DataAccessException {
		// TODO Auto-generated method stub
		getSqlSession().update("update",data);
	}
	
	@Override
	public void delete(String num) throws DataAccessException {
		// TODO Auto-generated method stub
		getSqlSession().delete("delete",num);
	}
	
	
	@Override
	public List search(BoardCommand data) throws DataAccessException {
		// TODO Auto-generated method stub
		//selectOne()->���ڵ� �Ѱ�,�ʵ� �Ѱ��� �ڷ����� ����Ҷ�
		return getSqlSession().selectList("search",data);
	}
	
	
}



