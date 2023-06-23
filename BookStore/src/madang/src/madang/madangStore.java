package madang;

public class madangStore {
	
	public static void main(String[] args) {
		Book book = new Book();
//		10, "나는 전설이다", "윌 스미스", 20000
		
		book.check();
		
		book.register();
		
		System.out.println(Book.bookList.toString());
		
		book.register();
		
		System.out.println(Book.bookList.toString());
		
		book.delete();
		
		System.out.println(Book.bookList.toString());
	}

}
