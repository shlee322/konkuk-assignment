import java.util.Scanner;
 
/**
 * 사각형과 삼각형의 넓이를 계산하고 최대 넓이를
 * 가지는 도형과 최소 넓이를 가지는 도형을 출력하는 프로그램입니다.
 * 
 * 201411294 이상혁
 * @author Lee Sanghyuck <shlee322@elab.kr>
 * @since 2014.04.01
 */
public class SizeManager {
	public static Scanner scan = new Scanner(System.in);
	
	public static int maxId;
	public static double maxSize = Double.MIN_VALUE;
	
	public static int minId;
	public static double minSize = Double.MAX_VALUE;
	
	public static void main(String[] args) {
		int select = 0;
		
		while(select != 4) {
			select = showMenu();
			switch (select) {
			case 1: //삼각형
				tri();
				break;
			case 2: //사각형
				square();
				break;
			case 3: //출력
				show();
				break;
			case 4: //종료
				System.out.println("종료");
				break;
			default: //존재하지 않는 메뉴
				System.out.println("[Error] 존재하지 않는 메뉴입니다.");
				break;
			}
		}
	}
	
	/**
	 * 메뉴를 출력하는 메소드
	 * @return 선택한 메뉴
	 */
	public static int showMenu() {
		System.out.println("==메뉴==");
		System.out.println("1. 삼각형");
		System.out.println("2. 사각형");
		System.out.println("3. 가장 크고 작은 도형");
		System.out.println("4. 종료");
		
		return scan.nextInt();
	}
	
	/**
	 * 삼각형의 넓이를 입력받는 메소드
	 */
	public static void tri() {
		int id = inputInt("도형 id : ");
		double w = inputDouble("밑변 : ");
		double h = inputDouble("높이 : ");
		
		saveSize(id, w * h / 2.0);
		
		System.out.println("삼각형의 넓이는 " + (w * h / 2.0));
	}
	
	/**
	 * 사각형의 넓이를 입력받는 메소드
	 */
	public static void square() {
		int id = inputInt("도형 id : ");
		double w = inputDouble("밑변 : ");
		double h = inputDouble("높이 : ");
		
		saveSize(id, w * h);
		
		System.out.println("사각형의 넓이는 " + (w * h));
	}
	
	/**
	 * 도형의 넓이를 저장하는 메소드
	 * 만약 저장을 요청한 도형이 최대, 최소 넓이를 가지면 해당 데이터를 저장한다.
	 * 
	 * @param id 도형의 ID
	 * @param size 도형의 넓이
	 */
	public static void saveSize(int id, double size) {
		if(size <= 0) {
			System.out.println("[등록실패] 넓이가 0 이하입니다.");
			return;
		}
		
		if(size > maxSize) {
			maxId = id;
			maxSize = size;
		}
		
		if(size < minSize) {
			minId = id;
			minSize = size;
		}
	}
	
	/**
	 * 가장 작은 도형과 가장 큰 도형을 출력하는 메소드
	 */
	public static void show() {
		System.out.println("가장 넓이가 큰 도형 : " + maxId + ", 넓이는 " + maxSize);
		System.out.println("가장 넓이가 작은 도형 : " + minId + ", 넓이는 " + minSize);
	}
	
	/**
	 * int형 데이터를 입력 받는 메소드
	 * 
	 * @param msg 화면에 표시할 메시지
	 * @return 입력된 int형 데이터
	 */
	public static int inputInt(String msg) {
		System.out.print(msg);
		return scan.nextInt();
	}
	
	/**
	 * double형 데이터를 입력 받는 메소드
	 * 입력 데이터가 0 이하이면 0을 출력 (도형 밑변 높이가 모두 -가 되어 +되는 것을 방지)
	 * @param msg 화면에 표시할 메시지
	 * @return 입력된 double형 데이터
	 */
	public static double inputDouble(String msg) {
		System.out.print(msg);
		double inputData = scan.nextDouble();
		if(inputData < 0)
		{
			System.out.println("[경고] 입력 받은 데이터가 0 보다 작습니다.");
			return 0;
		}
		
		return inputData;
	}
}
