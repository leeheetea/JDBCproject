package help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookList {

	Connection con;
	public BookList() {
		String driver="com.mysql.cj.jdbc.Driver";
		String url ="jdbc:mysql://localhost:3306/madang";
		String userid="root";
		String pwd="root";
			
		try {
			Class.forName(driver);
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}
		try {
			System.out.println("데이터베이스 연결 준비");
			con=DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패");
		}
	}
	
	private void sqlRun() {
		String query = "SELECT * FROM Book Where price > 20000";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			System.out.println("BOOK NO \tBOOK NAME \t\tPUBLISHER \tPRICE");
			while(rs.next()){
				System.out.print("\t" + rs.getInt(1));
				System.out.print("\t" + rs.getString(2));
				System.out.print("\t\t" + rs.getString(3));
				System.out.println("\t\t" + rs.getInt(4));
			}
		} catch (SQLException e) {
			System.out.println("데이터를 불러오는 과정에서 오류가 발생");
		}
	}
	
	private void sqlInsert() {
		try {
			//sql문 스페이스바 간격 주의
			String sql = "insert into book (bookid, bookname, publisher, price)" 
					+ " values (30, 'StrarCraft', '한빛 소프트', 20000)";

					
			Statement stmt = con.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result == 1) {
				System.out.println("레코드 추가 성공, 30");
			}else {
				System.out.println("레코드 추가 실패");
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터 삽입 과정에서 오류가 발생");
		}
		
	}
	
	private void insertBook(Book book) {
		int bookid = book.getBookid();
		String bookname = book.getBookname();
		String publisher = book.getPublisher();
		int price = book.getPrice();
		
		String sql = "insert into book (bookid, bookname, publisher, price)"
				+ " values (?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookid);
			pstmt.setString(2, bookname);
			pstmt.setString(3, publisher);
			pstmt.setInt(4, price);
			
			int result = pstmt.executeUpdate();
			System.out.println("result :" + result);
			if(result > 0) {
				System.out.println("데이터 삽입 성공");
			}else {
				System.out.println("데이터 삽입 실패");
			}
		} catch (SQLException e) {
			System.out.println("데이터 삽입 실패");
			e.printStackTrace();
		}
		
	}	
	
	private void deleteBook(int bookid) {
		int bookNumber = bookid;
		
		String sql = "DELETE FROM book WHERE bookid = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookNumber);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateBook(int bookid, String bookname, String publisher, int price) {
		int newbookid = bookid;
		String newbookname = bookname;
		String newpublisher = publisher;
		int newprice =  price;
		
		String sql = "UPDATE book SET bookid = ?, bookname = ?, publisher = ?, price = ? WHERE bookid = ?";
		PreparedStatement pstmt;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, newbookid);
			pstmt.setString(2, newbookname);
			pstmt.setString(3, newpublisher);
			pstmt.setInt(4, newprice);
			pstmt.setInt(5, newbookid);
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("수정 성공");
			}else {
				System.out.println("수정 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public static void main(String[] args) {
		BookList so = new BookList();
		so.sqlRun();
//		so.sqlInster();
		so.deleteBook(31);
		
		Book book = new Book(31, "Starcraft2", "한빛 소프트", 25000);
		so.insertBook(book);
		so.updateBook(31, "Warcraft3", "블리자드", 30000);
		so.sqlRun();
	}
}
