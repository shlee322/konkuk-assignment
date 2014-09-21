import java.util.Scanner;
 
/**
 * 직원들의 분기별과 전체 판매실적 관리 프로그램
 * (컴퓨터공학프로그래밍1)
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2014. 04. 15.
 */
public class MemberManager {
    public static void main(String[] argv) {
        int [][]array = createTable();
        Sum(array);
        printTable(array);
    }
 
    private static int[][] createTable() {
        Scanner scan = new Scanner(System.in);
        System.out.print("직원 수를 입력 : ");
        int memCount = scan.nextInt();
 
        int [][]array = new int[memCount+1][5];
 
        for(int mem_i=0; mem_i<memCount; mem_i++) {
            for(int de_i=0; de_i<4; de_i++) {
                System.out.print((mem_i+1)+" 번째 직원 " + (de_i+1) + "분기 판매대수 : ");
                array[mem_i][de_i] = scan.nextInt();
            }
        }
        return array;
    }
 
    private static void Sum(int[][] array) {
        int memCount = array.length - 1;
 
        //각 분기별 판매대수 합계
        for(int de_i=0; de_i<4; de_i++) {
            int sum = 0;
            for(int mem_i=0; mem_i<memCount; mem_i++) {
                sum += array[mem_i][de_i];
            }
 
            array[memCount][de_i] = sum;
        }
 
        //각 직원별 총 판매대수 & 전체 합계
        for(int i=0; i<array.length; i++) {
            int sum = 0;
            for(int de_i=0; de_i<4; de_i++) {
                sum += array[i][de_i];
            }
            array[i][4] = sum;
        }
    }
 
    private static void printTable(int[][] array) {
        System.out.println("\n\n|-----------+-----------+-----------+-----------|-----------|");
        for(int i=0; i<array.length; i++) {
            if(i == (array.length - 1)) {
                System.out.println("|-----------+-----------+-----------+-----------|-----------|");
            }
            for(int de_i=0; de_i<5; de_i++) {
                System.out.printf("|%10d ", array[i][de_i]);
            }
            System.out.println("|");
        }
        System.out.println("|-----------+-----------+-----------+-----------|-----------|\n\n");
 
        for(int i=0; i<5; i++) {
            String de = String.valueOf(i+1) + "분기";
            if(i == 4) {
                de = "전체";
            }
 
            System.out.println(de+" 총 판매대수는 : " + array[array.length - 1][i]);
        }
    }
}
