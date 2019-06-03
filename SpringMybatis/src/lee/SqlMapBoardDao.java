package lee;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

//SqlSessionDaoSupport을 상속
//SqlSession클래스 객체가 필요=>getSqlSession()필요->DB연결해서 데이터를 가져오기때문에
public class SqlMapBoardDao extends SqlSessionDaoSupport implements BoardDAO {

	@Override
	public List list() throws DataAccessException {
		// TODO Auto-generated method stub
		//select->레코드한개이상 ->selectList("실행시킬 sql구문의 id")
		//select->레코드 한개->selectOne("실행시킬 sql구문의 id")
		return getSqlSession().selectList("list");//~("board.list") id값이 인식X 네임스페이스명
	}
	
	@Override
	public int getNewNum() throws DataAccessException {
		// TODO Auto-generated method stub
		//레코드 한개(SelectOne),특정필드의 자료형(Wrapper)
		//형식)selectOne("실행시킬 sql의 id",매개변수전달할값)
		return (Integer)getSqlSession().selectOne("getNewNum");//Object=>Integer->int
	}

	@Override
	public void write(BoardCommand data) throws DataAccessException {
		// TODO Auto-generated method stub
		//형식) sqlSession객체명.insert("실행시킬 sql구문의 id",전달할 매개변수명)
		getSqlSession().insert("write",data);
	}
	
	//조회수 증가=>RetrieveActionController,retrieve.jsp 소스변경
	@Override
	public void updateReadcnt(String num) throws DataAccessException {
		// TODO Auto-generated method stub
		//형식)update태그->sqlSession객체명.update("실행시킬 sql구문의 id",매개변수명)
		getSqlSession().update("updateReadcnt",num);
	}
	//글상세보기
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
		//selectOne()->레코드 한개,필드 한개의 자료형을 기술할때
		return getSqlSession().selectList("search",data);
	}
	
	
}



