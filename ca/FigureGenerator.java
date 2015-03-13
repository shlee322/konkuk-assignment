import java.util.Scanner;

/**
 * 컴퓨터 응용 및 실습 1 과제 - 2 주차
 *
 * 도형 만들기
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2015.03.12
 * @link https://github.com/shlee322/konkuk-assignment/tree/master/ca/
 *
 */

public class FigureGenerator {
    private static Scanner scanner = new Scanner(System.in);

    private static void generateTriangle(int width, int height) {
        for(int h_i=1; h_i<=height; h_i++) {
            double currentWidth = Math.ceil((double)width/height*h_i);

            for(int w_i=1; w_i<=currentWidth; w_i++) System.out.print("*");

            System.out.println();
        }
    }

    private static void generateRectangle(int width, int height) {
        for(int h_i=0; h_i<height; h_i++) {
            for (int w_i = 0; w_i < width; w_i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    private static void generateRhombus(int width, int height) {
        for(int h_i=0; h_i<=height; h_i++) {
            double currentWidth = Math.ceil(height/2 - Math.abs((double)width/height*h_i - (height / 2)));

            for(int w_i=0; w_i<(width/2-currentWidth); w_i++) System.out.print(" ");

            for(int w_i=0; w_i<=currentWidth*2; w_i++) System.out.print("*");

            System.out.println();
        }
    }

    public static void main(String[] args) {
        int choiceMenu = -1;
        int width = 0;
        int height = 0;

        while (true) {
            System.out.println("1. 삼각형");
            System.out.println("2. 사각형");
            System.out.println("3. 마름모");
            System.out.println("4. 종료");

            System.out.print("메뉴 선택 : ");
            choiceMenu = scanner.nextInt();

            if(choiceMenu == 4) {
                break;
            }

            System.out.print("너비 : ");
            width = scanner.nextInt();
            System.out.print("높이 : ");
            height = scanner.nextInt();

            switch (choiceMenu) {
                case 1:
                    generateTriangle(width, height);
                    break;
                case 2:
                    generateRectangle(width, height);
                    break;
                case 3:
                    generateRhombus(width, height);
                    break;
                default:
                    System.out.println("[Error] 잘못된 메뉴를 선택하였습니다. 다시 선택해주세요");
            }

            System.out.println();
        }
    }
}
