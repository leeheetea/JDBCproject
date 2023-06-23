package madang;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Book implements AbleMadang {
	private int bookid = 0;
	private String bookname = "";
	private String publisher = "";	
	private int price = 10000;
	
	public static List<Book> bookList = new ArrayList<>(
			List.of(new Book(1, "축구의 역사", "굿스포츠", 7000),
			        new Book(2, "축구아는 여자", "나무수", 13000))
	);
	
	
	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public Book(int bookid, String bookname, String publisher, int price) {
		this.bookid = bookid;
		this.bookname = bookname;
		this.publisher = publisher;
		this.price = price;
	}
	
	public Book() {
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return bookid == other.bookid;
	}
	
	@Override
	public String toString() {
		return "(" + bookid + ", " + bookname + ", " + publisher + ", " + price +")";
	}

	@Override
	public void register() {
		
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("+====================+");
			System.out.println("|새 도서 등록 시스템입니다.|");
			System.out.println("+====================+");
			int intInput;
			try {
				System.out.print("도서번호 : ");
				intInput = Integer.parseInt(sc.nextLine());
				bookid = intInput;
				Book newBook = new Book(bookid, bookname, publisher, price);
				if(bookList.contains(newBook)) {
					System.out.println("같은 도서번호를 가진 도서가 존재합니다.");
					System.out.println("도서번호는 중복을 허용하지 않습니다. 등록이 취소됩니다.");
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.println("올바른 숫자를 입력해주세요. 처음으로 돌아갑니다.");
				continue;
			}

			System.out.println("====================");
			System.out.print("도서제목 : ");
			String stringInput = sc.nextLine();
			bookname = stringInput;
			System.out.println("====================");
			
			System.out.print("출판사 : ");
			stringInput = sc.nextLine();
			publisher = stringInput;
			System.out.println("====================");
			
			System.out.print("가격 : ");
			try {
				intInput = Integer.parseInt(sc.nextLine());
				price = intInput;
			} catch (NumberFormatException e) {
				System.out.println("올바른 숫자를 입력해주세요. 처음으로 돌아갑니다.");
				continue;
			}
			System.out.println();
			
			Book newBook = new Book(bookid, bookname, publisher, price);
			bookList.add(newBook);
			System.out.println(newBook.getBookid() + "번 도서가 성공적으로 등록되었습니다.");
			break;
			
		}
	}

	@Override
	public void search() {
		System.out.println("두 가지 검색 기능을 지원합니다.");
		System.out.println("도서명 검색(1), 출판사 검색(2), 종료(0) ");
		Scanner sc = new Scanner(System.in);
		int searchNum = Integer.parseInt(sc.nextLine());
		String searchName = "";
		String searchPublisher = "";
		
		while(true) {
			if(searchNum == 1) {
				System.out.println("찾으시는 도서명을 입력해주세요.");
				searchName = sc.nextLine();
				for(Book b : bookList) {
					if(b.bookname.equals(searchName)) {
						System.out.println(b);						
					}
				}
			}
			if(searchNum == 2){
				System.out.println("찾으시는 도서의 출판사를 입력해주세요.");
				searchPublisher = sc.nextLine();
				for(Book b : bookList) {
					if(b.publisher.equals(searchPublisher)) {
						System.out.println(b);
					}
				}
				
			}if(searchNum == 0) {
				System.out.println("종료합니다.");
				break;
			}

			
		}
	}

	@Override
	public void delete() {
		Scanner sc = new Scanner(System.in);;
		Book newBook;
		try {
			System.out.print("삭제할 도서번호를 입력하세요");
			int intInput = Integer.parseInt(sc.nextLine());
			bookid = intInput;
		} catch (NumberFormatException e) {
			System.out.println("올바른 번호가 아닙니다.");
		}
		newBook = new Book(bookid, bookname, publisher, price);
		if(bookList.contains(newBook)) {
			System.out.println(newBook.bookid + "번 도서를 데이베이스에서 삭제합니다.");
			System.out.print("삭제하겠습니까? (Yes : y, No : anyKey)");
				String answerInput = sc.nextLine();
				switch(answerInput) {
				case "y" :
					bookList.remove(newBook);
					System.out.println("삭제되었습니다.");
					break;
				case "n" :	
					System.out.println("삭제를 취소합니다.");
					break;
				default :
					sc.close();
				}
		}
	}

	@Override
	public void check() {
		System.out.println("도서 목록");
		System.out.println("+====================+");
		System.out.print("도서번호, 도서이름, 출판사, 가격");
		System.out.println();
		for(Book b : bookList) {
			System.out.println(b);
		}
	}

	@Override
	public void modify() {
		
	}
	
	

}
