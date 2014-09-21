import java.util.Scanner;
 
/**
 * 컴퓨터공학프로그래밍1 수업
 * 
 * 연비 계산 프로그램
 * 
 * @author Lee Sanghyuck (shlee322@gmail.com)
 * @since 2014.03.11
 * 
 */
 
public class CalFuelEfficiency {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		double driveDistance;	// 주행거리
		double fuels;			// 사용한 휘발유 양
		double fuelEfficiency;	// 연비
 
		driveDistance = inputDouble("주행거리를 입력하세요 : ");
		fuels = inputDouble("사용한 휘발유 양을 입력하세요 : ");
		
		fuelEfficiency = driveDistance / fuels;
		
		System.out.printf("연비 : %f\n", fuelEfficiency);
	}
	
	private static double inputDouble(String msg) {
		System.out.println(msg);
		
		return CalFuelEfficiency.scanner.nextDouble();
	}
}
