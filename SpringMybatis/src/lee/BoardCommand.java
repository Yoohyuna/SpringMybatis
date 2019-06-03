package lee;

//실질적으로 사용자로부터 입력받는값만 처리해주는 클래스
public class BoardCommand {

	int num;
	String author,title,content;//num,date,readcnt=>입력X(디폴트처리)
    //스프링->입력받는 갯수에 상관없이=>자동으로 Setter Method 호출
	//추가
	String writeday;//작성날짜
	int readcnt;//조회수
	//--------------검색을 위한 새로운 클래스를 만들지 않고 추가---------------------
	String searchName;//검색분야
	String searchValue;//검색어
	
	public int getNum() {
		System.out.println("getNum()호출됨!");
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriteday() {
		return writeday;
	}

	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	//---------------------------------
	public String getAuthor() {  //#{author}
		System.out.println("getAuthor()호출됨!");
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
		System.out.println("setAuthor() 호출됨!");
	}

	public String getTitle() {
		System.out.println("getTitle()호출됨!");
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		System.out.println("setTitle() 호출됨!");
	}

	public String getContent() {
		System.out.println("getContent()호출됨!");
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		System.out.println("setContent() 호출됨!");
	}
}



