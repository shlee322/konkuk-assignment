import java.util.Scanner;
 
public class CalCircleArea {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.printf("원의 반지름을 입력하세요 "); 
		double r = scan.nextDouble();
		System.out.printf("원의 면적 : %f\n", Math.PI * Math.pow(r, 2));
	}
}
