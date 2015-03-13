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
    private static Coffee[] coffees = {
        new Coffee("아메리카노", "에스프레소 + 물"),
        new Coffee("카페라떼", "에스프레소 + 우유"),
        new Coffee("카푸치노", "에스프레소 + 우유거품")
    };

    private static class Coffee {
        private String name;
        private String guide;

        public Coffee(String name, String guide) {
            this.name = name;
            this.guide = guide;
        }

        public String getName() {
            return this.name;
        }

        public String getGuide() {
            return this.guide;
        }
    }

    private static void showMenu() {
        for(int i=0; i<coffees.length; i++) {
            System.out.printf("%d. %s\n", i+1, coffees[i].getName());
        }
        System.out.printf("%d. 종료\n", coffees.length + 1);
    }

    public static void main(String[] args) {
        boolean isRun = true;
        int choiceMenu = -1;

        while (isRun) {
            // Show Menu
            showMenu();

            // Choice Menu
            System.out.print("메뉴 선택 : ");
            choiceMenu = scanner.nextInt();

            if(choiceMenu > 0 && choiceMenu <= coffees.length) {
                Coffee coffee = coffees[choiceMenu - 1];
                System.out.printf("%s : %s\n", coffee.getName(), coffee.getGuide());
            } else if(choiceMenu == (coffees.length + 1)) {
                isRun = false;
            } else {
                System.out.println("[Error] 잘못된 메뉴를 선택하였습니다. 다시 선택해주세요");
            }

            System.out.println();
        }
    }
}

