import java.util.Scanner;

/**
 * 자료구조 - 3주차 과제
 *
 * 하노이의 탑 구현
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2015.04.05
 * @link https://github.com/shlee322/konkuk-assignment/tree/master/datastructure/
 *
 */
public class Hanoi {
    private static Stack[] stacks = new Stack[3];
    private static Scanner scanner = new Scanner(System.in);
    private static int moveCount = 0;

    public static void main(String[] args) {
        for(int i=0; i<stacks.length; i++) {
            stacks[i] = new Stack();
        }

        System.out.print("Number of disk : ");
        int diskNum = scanner.nextInt();

        for(int i=0; i<diskNum; i++) {
            stacks[0].push(diskNum-i);
        }

        hanoi(diskNum, 0, 1, 2);
    }

    private static void hanoi(int n, int s, int m, int e) {
        if(n == 1) {
            move(s, e);
            return;
        }

        hanoi(n-1, s, e, m);
        move(s, e);
        hanoi(n-1, m, s, e);
    }

    private static void move(int from, int to) {
        int value = stacks[from].pop();
        stacks[to].push(value);

        moveCount++;
        System.out.printf("[%d] %d -> %d : %d\n[%d] ",
                moveCount, from, to, value, moveCount);

        for(int i=0; i<stacks.length; i++) {
            try {
                System.out.printf("%d ", stacks[i].top());
            } catch (IllegalStateException e) {
                System.out.print("0 ");
            }
        }
        System.out.println();
    }
}
