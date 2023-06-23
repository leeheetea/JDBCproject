package madang;

import java.util.ArrayList;
import java.util.List;

public class Customer implements AbleMadang {

	int custid;
	String name;
	String address;
	String phone;
	public static List<Customer> customerList = new ArrayList<>();
	
	public Customer(int custid, String name, String address, String phone) {
		this.custid = custid;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
