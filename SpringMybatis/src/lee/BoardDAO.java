package lee;

import java.util.*;//List->글목록보기
import org.springframework.dao.DataAccessException;//스프링의 전용예외 처리클래스

public interface BoardDAO{
	//1.글목록보기
	public List list() throws DataAccessException;
	
	//2.최대값을 구하기
	public int getNewNum() throws DataAccessException;
	
	//3.글쓰기
	public void write(BoardCommand data) throws DataAccessException;
	
	//4-1)조회수 증가하기
	public void updateReadcnt(String num) throws DataAccessException;
	
	//4-2)글상세보기->select * from springboard where num=?
	public BoardCommand retrieve(String num) throws DataAccessException;
	
	//5.글수정하기
	public void update(BoardCommand data) throws DataAccessException;
	
	//6.글삭제하기
	public void delete(String num) throws DataAccessException;
	
	//7.글검색하기->(String searchName,String searchValue)=>HashMap처리 관례
	public List search(BoardCommand data) throws DataAccessException;
}







/*
 * package lee;
 * 
 * import java.sql.*;//Connection,PrepardStatement,,, import
 * java.util.*;//ArrayList,List~ //---------------------------------------
 * //추가->xml를 통해서 DB접속해주는 JNDI방법을 이용 import
 * javax.sql.*;//DataSource객체->getConnection()을 이용 import
 * javax.naming.*;//Context(인터페이스),InitialContext객체 //lookup('찾고자하는 JNDI명')
 * //-------------------------------------------------------------------- public
 * class BoardDAO{
 * 
 * DataSource ds;//DBConnectionMgr pool=null; has a 관계
 * 
 * public BoardDAO(){ //생성자 : DataSource 얻기 : InitialContext 와 JNDI 명 try {
 * //InitialContext ctx=new InitialContext(); Context ctx=new InitialContext();
 * //형식) lookup("java:comp/env/찾고자하는 JNDI명")\
 * ds=(DataSource)ctx.lookup("java:comp/env/jdbc/orcl");
 * System.out.println("ds=>"+ds); }catch(Exception e) { e.printStackTrace(); } }
 * 
 * public ArrayList list(){ // 목록보기
 * 
 * ArrayList list = new ArrayList();//여러개의 레코드를 담을 객체를 미리 생성 try{ String sql =
 * "SELECT * FROM springboard ORDER BY num desc"; Connection con =
 * ds.getConnection();//pool.getConnection(); PreparedStatement stmt =
 * con.prepareStatement(sql); ResultSet rs = stmt.executeQuery();
 * while(rs.next()){ Board data = new Board();//DTO생성->검색된 필드별로 담아->웹에 출력
 * data.setNum( rs.getInt( "num" ) );//게시물번호 data.setAuthor(rs.getString(
 * "author" ));//작성자 data.setTitle(rs.getString( "title"));//글제목
 * data.setContent(rs.getString( "content" ));//글내용 data.setDate(rs.getString(
 * "writeday" ));//작성날짜->문자 data.setReadcnt(rs.getInt( "readcnt" ));//조회수
 * list.add( data ); }//end while rs.close(); stmt.close(); con.close();
 * }catch(Exception e){ e.printStackTrace(); }
 * 
 * return list; }//end list
 * 
 * public int getNewNum(){ //글쓰기 번호 얻기 int newNum=1;//저장할 게시물번호 디폴트 설정값 1 try {
 * String sql="select max(num) from springboard"; Connection
 * con=ds.getConnection(); PreparedStatement stmt=con.prepareStatement(sql);
 * ResultSet rs=stmt.executeQuery(sql); if(rs.next()) { //레코드가 한개이상 존재한다면
 * =>최대값+1 newNum=rs.getInt(1)+1; } }catch(Exception e) {e.printStackTrace();}
 * return newNum; }//end getNewNum();
 * 
 * public void write(String author, String title , String content){ //~(Board
 * board){ try{ int newNum = getNewNum();//게시물번호를 구해와야 한다.
 * System.out.println("newNum=>"+newNum); String sql
 * ="insert into springboard(num,author,title,content) values("; sql += newNum +
 * ",'" + author + "','" + title + "','" + content + "')";
 * System.out.println(sql);//한글깨지는지 확인하기위해서 sql구문 출력함
 * 
 * Connection con = ds.getConnection(); PreparedStatement stmt =
 * con.prepareStatement(sql); stmt.execute(sql);//stmt.executeUpdate(sql);
 * stmt.close(); con.close();//약코딩 }catch(Exception e ) {e.printStackTrace();}
 * }//end write
 * 
 * //select * from springboard where num=3; //public List<Board>~ public Board
 * retrieve(String num){ // 글 자세히 보기 Board data=new Board(); try { //1.조회수를 증가
 * String sql="update springboard set readcnt=readcnt+1 where num="+num;
 * Connection con=ds.getConnection(); PreparedStatement
 * stmt=con.prepareStatement(sql); int update=stmt.executeUpdate(sql);
 * System.out.println("조회수 증가유무(update)=>"+update); stmt=null;//전에 저장된 정보제거
 * 
 * sql="select * from springboard where num="+num;
 * stmt=con.prepareStatement(sql); ResultSet rs=stmt.executeQuery(sql);
 * if(rs.next()) { data.setNum(rs.getInt("num"));
 * data.setAuthor(rs.getString("author")); data.setTitle(rs.getString("title"));
 * data.setContent(rs.getString("content")); } rs.close(); stmt.close();
 * con.close();//메모리 해제는 생성된 순서의 역순 }catch(Exception e) {e.printStackTrace();}
 * return data; }//end retrieve
 * 
 * //public void update(Board board){ public void update( String num , String
 * author, String title , String content){ // 글 수정하기 try{ //?로 입력을 받아서 처리해주는 코드를
 * 반드시 구현=>Secure Coding(보안때문에) String sql ="update springboard set title='" +
 * title + "',"; sql += " content='" + content+"',"; sql += " author ='" +
 * author+"'"; sql += " where num=" + num; System.out.println(sql);//제대로 수정이
 * 됐는지를 체크
 * 
 * Connection con = ds.getConnection(); PreparedStatement stmt =
 * con.prepareStatement(sql); int update=stmt.executeUpdate(sql);
 * System.out.println("데이터수정 유무(update)=>"+update); stmt.close(); con.close();
 * //rs.close()=>select구문이 아니기에 }catch(Exception e){e.printStackTrace();} }//end
 * update
 * 
 * //delete from springboard where num=3; public void delete( String num){ //글
 * 삭제하기 try { String sql="delete from springboard where num="+num; Connection
 * con=ds.getConnection(); PreparedStatement pstmt=con.prepareStatement(sql);
 * int delete=pstmt.executeUpdate(sql);
 * System.out.println("delete 삭제유무="+delete);//1(성공),0(실패) pstmt.close();
 * con.close(); }catch(Exception e) {System.out.println("delete()에러유발=>"+e);}
 * }//end delete //select * from springboard where name(필드명) like '%value(검색어)%'
 * public ArrayList search( String name , String value ){ //검색하기
 * 
 * ArrayList list = new ArrayList();//여러개의 레코드를 담을 객체생성 try{ String sql =
 * "SELECT * FROM springboard"; sql += " WHERE  " + name + " LIKE  '%" + value +
 * "%' "; System.out.println( sql );//검색이 안되는 경우->한글이 깨지는지 확인
 * 
 * Connection con = ds.getConnection(); PreparedStatement stmt =
 * con.prepareStatement(sql); ResultSet rs = stmt.executeQuery( sql );
 * while(rs.next()){ Board data = new Board();//검색된 갯수만큼 레코드를 담을 객체 필요
 * data.setNum(rs.getInt( "num" )); data.setAuthor(rs.getString( "author" ));
 * data.setTitle(rs.getString( "title")); data.setContent(rs.getString(
 * "content" )); data.setDate(rs.getString( "writeday" ));
 * data.setReadcnt(rs.getInt( "readcnt" )); // list.add( data );//화면에 null이 출력이
 * 된다.(담는 구문이 없으면) } rs.close(); stmt.close(); con.close(); }catch( Exception
 * e){ e.printStackTrace();} return list; } }
 */