package lee;

import java.util.*;//List->�۸�Ϻ���
import org.springframework.dao.DataAccessException;//�������� ���뿹�� ó��Ŭ����

public interface BoardDAO{
	//1.�۸�Ϻ���
	public List list() throws DataAccessException;
	
	//2.�ִ밪�� ���ϱ�
	public int getNewNum() throws DataAccessException;
	
	//3.�۾���
	public void write(BoardCommand data) throws DataAccessException;
	
	//4-1)��ȸ�� �����ϱ�
	public void updateReadcnt(String num) throws DataAccessException;
	
	//4-2)�ۻ󼼺���->select * from springboard where num=?
	public BoardCommand retrieve(String num) throws DataAccessException;
	
	//5.�ۼ����ϱ�
	public void update(BoardCommand data) throws DataAccessException;
	
	//6.�ۻ����ϱ�
	public void delete(String num) throws DataAccessException;
	
	//7.�۰˻��ϱ�->(String searchName,String searchValue)=>HashMapó�� ����
	public List search(BoardCommand data) throws DataAccessException;
}







/*
 * package lee;
 * 
 * import java.sql.*;//Connection,PrepardStatement,,, import
 * java.util.*;//ArrayList,List~ //---------------------------------------
 * //�߰�->xml�� ���ؼ� DB�������ִ� JNDI����� �̿� import
 * javax.sql.*;//DataSource��ü->getConnection()�� �̿� import
 * javax.naming.*;//Context(�������̽�),InitialContext��ü //lookup('ã�����ϴ� JNDI��')
 * //-------------------------------------------------------------------- public
 * class BoardDAO{
 * 
 * DataSource ds;//DBConnectionMgr pool=null; has a ����
 * 
 * public BoardDAO(){ //������ : DataSource ��� : InitialContext �� JNDI �� try {
 * //InitialContext ctx=new InitialContext(); Context ctx=new InitialContext();
 * //����) lookup("java:comp/env/ã�����ϴ� JNDI��")\
 * ds=(DataSource)ctx.lookup("java:comp/env/jdbc/orcl");
 * System.out.println("ds=>"+ds); }catch(Exception e) { e.printStackTrace(); } }
 * 
 * public ArrayList list(){ // ��Ϻ���
 * 
 * ArrayList list = new ArrayList();//�������� ���ڵ带 ���� ��ü�� �̸� ���� try{ String sql =
 * "SELECT * FROM springboard ORDER BY num desc"; Connection con =
 * ds.getConnection();//pool.getConnection(); PreparedStatement stmt =
 * con.prepareStatement(sql); ResultSet rs = stmt.executeQuery();
 * while(rs.next()){ Board data = new Board();//DTO����->�˻��� �ʵ庰�� ���->���� ���
 * data.setNum( rs.getInt( "num" ) );//�Խù���ȣ data.setAuthor(rs.getString(
 * "author" ));//�ۼ��� data.setTitle(rs.getString( "title"));//������
 * data.setContent(rs.getString( "content" ));//�۳��� data.setDate(rs.getString(
 * "writeday" ));//�ۼ���¥->���� data.setReadcnt(rs.getInt( "readcnt" ));//��ȸ��
 * list.add( data ); }//end while rs.close(); stmt.close(); con.close();
 * }catch(Exception e){ e.printStackTrace(); }
 * 
 * return list; }//end list
 * 
 * public int getNewNum(){ //�۾��� ��ȣ ��� int newNum=1;//������ �Խù���ȣ ����Ʈ ������ 1 try {
 * String sql="select max(num) from springboard"; Connection
 * con=ds.getConnection(); PreparedStatement stmt=con.prepareStatement(sql);
 * ResultSet rs=stmt.executeQuery(sql); if(rs.next()) { //���ڵ尡 �Ѱ��̻� �����Ѵٸ�
 * =>�ִ밪+1 newNum=rs.getInt(1)+1; } }catch(Exception e) {e.printStackTrace();}
 * return newNum; }//end getNewNum();
 * 
 * public void write(String author, String title , String content){ //~(Board
 * board){ try{ int newNum = getNewNum();//�Խù���ȣ�� ���ؿ;� �Ѵ�.
 * System.out.println("newNum=>"+newNum); String sql
 * ="insert into springboard(num,author,title,content) values("; sql += newNum +
 * ",'" + author + "','" + title + "','" + content + "')";
 * System.out.println(sql);//�ѱ۱������� Ȯ���ϱ����ؼ� sql���� �����
 * 
 * Connection con = ds.getConnection(); PreparedStatement stmt =
 * con.prepareStatement(sql); stmt.execute(sql);//stmt.executeUpdate(sql);
 * stmt.close(); con.close();//���ڵ� }catch(Exception e ) {e.printStackTrace();}
 * }//end write
 * 
 * //select * from springboard where num=3; //public List<Board>~ public Board
 * retrieve(String num){ // �� �ڼ��� ���� Board data=new Board(); try { //1.��ȸ���� ����
 * String sql="update springboard set readcnt=readcnt+1 where num="+num;
 * Connection con=ds.getConnection(); PreparedStatement
 * stmt=con.prepareStatement(sql); int update=stmt.executeUpdate(sql);
 * System.out.println("��ȸ�� ��������(update)=>"+update); stmt=null;//���� ����� ��������
 * 
 * sql="select * from springboard where num="+num;
 * stmt=con.prepareStatement(sql); ResultSet rs=stmt.executeQuery(sql);
 * if(rs.next()) { data.setNum(rs.getInt("num"));
 * data.setAuthor(rs.getString("author")); data.setTitle(rs.getString("title"));
 * data.setContent(rs.getString("content")); } rs.close(); stmt.close();
 * con.close();//�޸� ������ ������ ������ ���� }catch(Exception e) {e.printStackTrace();}
 * return data; }//end retrieve
 * 
 * //public void update(Board board){ public void update( String num , String
 * author, String title , String content){ // �� �����ϱ� try{ //?�� �Է��� �޾Ƽ� ó�����ִ� �ڵ带
 * �ݵ�� ����=>Secure Coding(���ȶ�����) String sql ="update springboard set title='" +
 * title + "',"; sql += " content='" + content+"',"; sql += " author ='" +
 * author+"'"; sql += " where num=" + num; System.out.println(sql);//����� ������
 * �ƴ����� üũ
 * 
 * Connection con = ds.getConnection(); PreparedStatement stmt =
 * con.prepareStatement(sql); int update=stmt.executeUpdate(sql);
 * System.out.println("�����ͼ��� ����(update)=>"+update); stmt.close(); con.close();
 * //rs.close()=>select������ �ƴϱ⿡ }catch(Exception e){e.printStackTrace();} }//end
 * update
 * 
 * //delete from springboard where num=3; public void delete( String num){ //��
 * �����ϱ� try { String sql="delete from springboard where num="+num; Connection
 * con=ds.getConnection(); PreparedStatement pstmt=con.prepareStatement(sql);
 * int delete=pstmt.executeUpdate(sql);
 * System.out.println("delete ��������="+delete);//1(����),0(����) pstmt.close();
 * con.close(); }catch(Exception e) {System.out.println("delete()��������=>"+e);}
 * }//end delete //select * from springboard where name(�ʵ��) like '%value(�˻���)%'
 * public ArrayList search( String name , String value ){ //�˻��ϱ�
 * 
 * ArrayList list = new ArrayList();//�������� ���ڵ带 ���� ��ü���� try{ String sql =
 * "SELECT * FROM springboard"; sql += " WHERE  " + name + " LIKE  '%" + value +
 * "%' "; System.out.println( sql );//�˻��� �ȵǴ� ���->�ѱ��� �������� Ȯ��
 * 
 * Connection con = ds.getConnection(); PreparedStatement stmt =
 * con.prepareStatement(sql); ResultSet rs = stmt.executeQuery( sql );
 * while(rs.next()){ Board data = new Board();//�˻��� ������ŭ ���ڵ带 ���� ��ü �ʿ�
 * data.setNum(rs.getInt( "num" )); data.setAuthor(rs.getString( "author" ));
 * data.setTitle(rs.getString( "title")); data.setContent(rs.getString(
 * "content" )); data.setDate(rs.getString( "writeday" ));
 * data.setReadcnt(rs.getInt( "readcnt" )); // list.add( data );//ȭ�鿡 null�� �����
 * �ȴ�.(��� ������ ������) } rs.close(); stmt.close(); con.close(); }catch( Exception
 * e){ e.printStackTrace();} return list; } }
 */