package system;

import java.util.List;

public class MainConsole {
	
	private BikeDb bd;
	
	public MainConsole() {
		bd = new BikeDb();
	}
	
	public void printBikeList() {
		List<RentBikeStatus> list = bd.readToSql();
		System.out.println("----------------------------------------------------------------------------------------");
		list.stream().forEach(System.out::println);
		System.out.println("----------------------------------------------------------------------------------------");
	}

	public static void main(String[] args) {
		MainConsole mc = new MainConsole();
		mc.printBikeList();
	}
	
}
