package system;

import java.util.List;
import java.util.Scanner;

public class MainConsole {

	private BikeDb bd;
	private MainConsole ms;
	int pageNum = 1;

	public MainConsole() {
		bd = new BikeDb();
		ms = new MainConsole();
	}
	
	public void FrontMenu() {

	}
	
	public int EndPageCount() {
		int count = bd.getCount();
		int EndPageNum = count/10;
		if (EndPageNum%10 != 0) {
			EndPageNum = EndPageNum++;
		}
		return EndPageNum;
	}

	public int printMenu() {
		// 끝 페이지 계산
		int EndPageNum = ms.EndPageCount();
		
		//메뉴 출력
		System.out.println("+---------------------------------------------------------------------------------------");
		System.out.println("| 1. 이전페이지 | 2. 다음페이지 | 3. 검색 | 4. 종료 |");
		System.out.println("+---------------------------------------------------------------------------------------");
		System.out.println("|"+pageNum +" / " + EndPageNum+"|");
		Scanner sc = new Scanner(System.in);
		int input = Integer.parseInt(sc.nextLine());
		return input;
	}

	public int adminMenu() {
		int EndPageNum = ms.EndPageCount();

		System.out.println("+---------------------------------------------------------------------------------------");
		System.out.println("| 1. 이전페이지 | 2. 다음페이지 | 3. 검색 | 4. 종료 | 5. 등록 | 6. 삭제");
		System.out.println("+---------------------------------------------------------------------------------------");
		System.out.println("|"+pageNum +" / " + EndPageNum+"|");
		Scanner sc = new Scanner(System.in);
		int input = Integer.parseInt(sc.nextLine());
		return input;
	}
	
	public void printPrevious() {
		if(pageNum == 1) {
			System.out.println("첫 페이지 입니다.");
			return;
		}
		pageNum --;
	}
	
	public void printNext() {
		int EndPageNum = ms.EndPageCount();
		if(pageNum > EndPageNum) {
			System.out.println("마지막 페이지 입니다.");
			EndPageNum --;
		}
		pageNum ++;
	}
	
	public void printBikeList() {
		List<RentBikeStatus> list = bd.readToSql(pageNum);
		System.out.println("----------------------------------------------------------------------------------------");
		list.stream().forEach(System.out::println);
		System.out.println("----------------------------------------------------------------------------------------");
	}

	public static void main(String[] args) {
		MainConsole mc = new MainConsole();
		int selectNum = 1;
		int adminSelectNum = 1;
		EXIT:
		while(true)	{
			mc.printBikeList();
			selectNum = mc.printMenu();
			switch(selectNum) {
			case 1:
				mc.printPrevious();
				break;
			case 2:
				mc.printNext();
				break;
			case 3:
				break;
			case 4:
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("프로그램을 종료합니다.");
				System.out.println("----------------------------------------------------------------------------------------");
				break EXIT;
			case 99:
				while(true) {
					mc.adminMenu();
					adminSelectNum = mc.adminMenu();
					switch(adminSelectNum) {
					case 1:
						mc.printPrevious();
						break;
					case 2:
						mc.printNext();
						break;
					case 3:
						break;
					case 4:
						System.out.println("----------------------------------------------------------------------------------------");
						System.out.println("프로그램을 종료합니다.");
						System.out.println("----------------------------------------------------------------------------------------");
						break EXIT;
					case 5:
						break;
					case 6:
						break;
					default:
						System.out.println("잘못된 입력입니다.");
						break;
					}
				}
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}
		}
	}

}
