package madang;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders implements AbleMadang {

	int orderid;
	Book book;
	Customer customer;
	int salePrice;
	Date date;
	public static List<Orders> orderList = new ArrayList<>();
	
	public Orders(int orderid, Book book, Customer customer, int salePrice, Date date) {
		this.orderid = orderid;
		this.book = book;
		this.customer = customer;
		this.salePrice = salePrice;
		this.date = date;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void check() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
