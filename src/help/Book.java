package help;

import java.util.Objects;

//	CREATE TABLE `book` (
//		  `bookid` int NOT NULL,
//		  `bookname` varchar(40) DEFAULT NULL,
//		  `publisher` varchar(40) DEFAULT NULL,
//		  `price` int DEFAULT NULL,
//		  PRIMARY KEY (`bookid`)


public class Book{
	private int bookid = 0;
	private String bookname = "";
	private String publisher = "";	
	private int price = 10000;

	
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
}
