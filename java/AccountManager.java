import java.util.Scanner;
 
/**
 * 컴퓨터공학프로그래밍1 수업
 * 
 * 용돈 관리 프로그램
 * 
 * @author Lee Sanghyuck (shlee322@gmail.com)
 * @since 2014.03.11
 * 
 */
 
public class AccountManager {
	private static Scanner scanner;
	
	public static void main(String[] args) {
		AccountManager.scanner = new Scanner(System.in);
		
		int moneyBalance = 0;
		moneyBalance = takeMoney("받은 용돈을 입력하세요 : ");
		moneyBalance -= takeMoney("오늘 사용한 금액을 입력하세요 : ");
		
		System.out.printf("잔고 : %d", moneyBalance);
	}
	
	/**
	 * 인자로 받는 메시지를 출력하고
	 * 표준입력으로부터 Int형의 데이터를 받아 반환하는 함수
	 * 
	 * @param msg 출력할 메시지
	 * @return 표준입력으로 부터 받아온 데이터
	 */
	private static int takeMoney(String msg) {
		System.out.print(msg);	
		return AccountManager.scanner.nextInt();
	}
}
