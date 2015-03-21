import java.util.Scanner;

/**
 * 자료구조 - 2주차 과제
 *
 * 팩토리얼
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2015.03.21
 * @link https://github.com/shlee322/konkuk-assignment/tree/master/datastructure/
 *
 */
public class FactorialTest {
    private long start_time;
    private long end_time;

    private void start() {
        this.start_time = System.nanoTime();
    }

    private void end() {
        this.end_time = System.nanoTime();
    }

    private long getExecutionTime() {
        return this.end_time - this.start_time;
    }

    private int recursiveFactorial(int n) {
        if(n == 0) return 1;
        return n * recursiveFactorial(n - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FactorialTest factorial = new FactorialTest();
        int input = 0;
        int result = 0;

        System.out.print("Input : ");
        input = scanner.nextInt();
        if(input < 0) {
            System.out.println("0 이상의 수만 입력 가능합니다.");
            return;
        }

        factorial.start();
        result = factorial.recursiveFactorial(input);
        factorial.end();

        System.out.printf("Execution Time : %fms\n", factorial.getExecutionTime() / 1000000.0);
        System.out.printf("Factorial results : %d\n", result);
    }
}
