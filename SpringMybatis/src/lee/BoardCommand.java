package lee;

//���������� ����ڷκ��� �Է¹޴°��� ó�����ִ� Ŭ����
public class BoardCommand {

	int num;
	String author,title,content;//num,date,readcnt=>�Է�X(����Ʈó��)
    //������->�Է¹޴� ������ �������=>�ڵ����� Setter Method ȣ��
	//�߰�
	String writeday;//�ۼ���¥
	int readcnt;//��ȸ��
	//--------------�˻��� ���� ���ο� Ŭ������ ������ �ʰ� �߰�---------------------
	String searchName;//�˻��о�
	String searchValue;//�˻���
	
	public int getNum() {
		System.out.println("getNum()ȣ���!");
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
		System.out.println("getAuthor()ȣ���!");
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
		System.out.println("setAuthor() ȣ���!");
	}

	public String getTitle() {
		System.out.println("getTitle()ȣ���!");
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		System.out.println("setTitle() ȣ���!");
	}

	public String getContent() {
		System.out.println("getContent()ȣ���!");
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		System.out.println("setContent() ȣ���!");
	}
}



