import java.util.Scanner;
 
/**
 * 공약수, 2진수 변환기
 * (컴퓨터공학프로그래밍1)
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2014. 05. 12.
 */
public class Calculator {
    private static Scanner scan = new Scanner(System.in);
 
    public static void main(String[] args) {
        while(true) {
            int index = showMenu();
            if(index == 1) {
                compCommonDivisor();
            } else if(index == 2) {
                convBinary();
            } else {
                System.out.println("잘못된 메뉴입니다.");
            }
 
            if(isExit()) {
                break;
            }
        }
    }
 
    private static int showMenu() {
        System.out.println("[Menu]");
        System.out.println("1. 공약수 구하기");
        System.out.println("2. 2진수 변환");
        return scan.nextInt();
    }
 
    private static void compCommonDivisor() {
        int numA;
        int numB;
 
        System.out.print("첫번째 숫자 입력 :");
        numA = scan.nextInt();
        System.out.print("두번째 숫자 입력 :");
        numB = scan.nextInt();
 
        if(numA < 1 || numB < 1) {
            System.out.println("입력된 숫자중 1보다 작은값이 있습니다.");
            return;
        }
 
        int[] arr = new int[numA];
        int arr_i=0;
 
        for(int num=1; num<=numA; num++) {
            if(numA%num == 0) {
                arr[arr_i++] = num;
            }
        }
 
        int[] arr2 = new int[arr_i];
        arr_i=0;
 
        for(int num=1; num<=numB; num++) {
            if(numB%num == 0) {
                for(int i=0; i<arr2.length; i++) {
                    if(arr[i] == num) {
                        arr2[arr_i++] = num;
                    }
                }
            }
        }
 
        for(int i=0; i<arr_i; i++) {
            System.out.println(arr2[i]);
        }
    }
 
    private static void convBinary() {
        System.out.print("숫자 입력 : ");
        int num = scan.nextInt();
 
        int []b = new int[getBinarySize(num)];
        setBinaryArray(b, num);
 
        for(int i=0; i<b.length; i++) {
            System.out.print(b[i]);
        }
 
        System.out.println();
    }
 
    private static int getBinarySize(int num) {
        if(num == 0) {
            return 1;
        }
 
        int i=0;
        while(num != 0) {
            i++;
            num = num/2;
        }
 
        return i;
    }
 
    private static void setBinaryArray(int[] arr, int num) {
        if(num == 0) {
            arr[0] = 0;
            return;
        }
 
        int i = arr.length;
        while(num != 0) {
            arr[--i] = num%2;
            num = num/2;
        }
    }
 
    private static boolean isExit() {
        System.out.println("계속하시겠습니까? 1: 계속, 그외 : 종료");
        return scan.nextInt() != 1;
    }
}
