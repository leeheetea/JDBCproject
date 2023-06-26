package system;

import java.util.List;
import java.util.Scanner;

public class MainConsole {

	private BikeDb bd;
	int pageNum = 1;

	public MainConsole() {
		bd = new BikeDb();
	}

	public void PrintFrontMenu() {
		System.out.println("+----------------------------------------------------------------+");
		System.out.println("\t 공공자전거 데이터 검색 시스템");
		System.out.println("+----------------------------------------------------------------+");
	}

	public int printMenu(Scanner sc) {
		// 끝 페이지 계산
		int EndPageNum = bd.EndPageCount();

		// 메뉴 출력
		System.out.println("+--------------------------------------------+");
		System.out.println("| 1. 이전페이지 | 2. 다음페이지 | 3. 검색 | 4. 종료 |");
		System.out.println("+--------------------------------------------+");
		System.out.println("|" + pageNum + " / " + EndPageNum + "|");
		int input = Integer.parseInt(sc.nextLine());
		return input;
	}

	public int adminMenu(Scanner sc) {
		int EndPageNum = bd.EndPageCount();

		System.out.println("---------------------------------------------------------------");
		System.out.println("| 1. 이전페이지 | 2. 다음페이지 | 3. 검색 | 4. 종료 | 5. 등록 | 6. 삭제 |");
		System.out.println("---------------------------------------------------------------");
		System.out.println("|" + pageNum + " / " + EndPageNum + "|");
		int input = Integer.parseInt(sc.nextLine());
		return input;
	}

	public void printRegistration(Scanner sc) {
		System.out.println("---------------------------------------------------------------");
		System.out.println("새로운 정거장을 등록합니다.");
		System.out.println("---------------------------------------------------------------");
		List<RentBikeStatus> list = bd.registration(sc);
		System.out.println("---------------------------------------------------------------");
		list.stream().forEach(System.out::println);
		System.out.println("---------------------------------------------------------------");
	}

	public void printDelete(Scanner sc) {
		System.out.println("---------------------------------------------------------------");
		System.out.println("정거장을 삭제합니다.");
		System.out.println("삭제할 정거장의 ID를 입력해주세요");
		System.out.println("---------------------------------------------------------------");
		String targetStation = sc.nextLine();
		System.out.println("삭제할 정거장의 ID가 " + targetStation + "이 맞습니까? (Y, N)");
		String answer = sc.nextLine();

		if (answer.equalsIgnoreCase("y")) {
			bd.deleteFromSql(targetStation);
		} else if (answer.equalsIgnoreCase("n")) {
			System.out.println("삭제를 취소합니다");
		}
	}

	public void printPrevious() {
		if (pageNum == 1) {
			System.out.println("첫 페이지 입니다.");
			return;
		}
		pageNum--;
	}

	public void printNext() {
		int EndPageNum = bd.EndPageCount();
		if (pageNum >= EndPageNum) {
			System.out.println("마지막 페이지 입니다.");
			EndPageNum--;
		}
		pageNum++;
	}

	public void printBikeList() {
		List<RentBikeStatus> list = bd.readToSql(pageNum);
		System.out.println("---------------------------------------------------------------");
		list.stream().forEach(System.out::println);
		System.out.println("---------------------------------------------------------------");
	}

	public void printSearchList(Scanner sc) {
		System.out.print("검색할 정거장의 이름을 입력해주세요 >");
		String input = sc.nextLine();
		List<RentBikeStatus> list = bd.SearchFromSql(input);
		System.out.println("---------------------------------------------------------------");
		list.stream().forEach(System.out::println);
		System.out.println("---------------------------------------------------------------");
	}

	public static void main(String[] args) {
		MainConsole mc = new MainConsole();
		int selectNum = 1;
		int adminSelectNum = 1;
		Scanner sc = new Scanner(System.in);

		mc.PrintFrontMenu();
		EXIT: while (true) {
			mc.printBikeList();
			selectNum = mc.printMenu(sc);
			switch (selectNum) {
			case 1:
				mc.printPrevious();
				break;
			case 2:
				mc.printNext();
				break;
			case 3:
				mc.printSearchList(sc);
				break;
			case 4:
				System.out.println("-------------------------------------------");
				System.out.println("프로그램을 종료합니다.");
				System.out.println("-------------------------------------------");
				break EXIT;
			case 99:
				while (true) {
					mc.PrintFrontMenu();
					mc.printBikeList();
					adminSelectNum = mc.adminMenu(sc);
					switch (adminSelectNum) {
					case 1:
						mc.printPrevious();
						break;
					case 2:
						mc.printNext();
						break;
					case 3:
						mc.printSearchList(sc);
						break;
					case 4:
						System.out.println("-------------------------------------------");
						System.out.println("프로그램을 종료합니다.");
						System.out.println("-------------------------------------------");
						break EXIT;
					case 5:
						mc.printRegistration(sc);
						break;
					case 6:
						mc.printDelete(sc);
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
