import java.util.Scanner;

/**
 * 컴퓨터 응용 및 실습 1 과제 - 2 주차
 *
 * 커피머신
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2015.03.12
 * @link https://github.com/shlee322/konkuk-assignment/tree/master/ca/
 *
 */

public class CoffeeMachine {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;

        while (isRun) {
            // Show Menu
            System.out.println("1. 아메리카노");
            System.out.println("2. 카페라떼");
            System.out.println("3. 카푸치노");
            System.out.println("4. 종료");

            // Choice Menu
            System.out.print("메뉴 선택 : ");

            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("아메리카노 : 에스프레소 + 물");
                    break;
                case 2:
                    System.out.println("카페라떼 : 에스프레소 + 우유");
                    break;
                case 3:
                    System.out.println("카푸치노 : 에스프레소 + 우유거품");
                    break;
                case 4:
                    isRun = false;
                    break;
                default:
                    System.out.println("[Error] 잘못된 메뉴를 선택하였습니다. 다시 선택해주세요");
                    break;
            }

            System.out.println();
        }
    }
}
