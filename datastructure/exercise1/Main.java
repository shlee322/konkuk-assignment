import java.util.Random;

/**
 * 자료구조 - 1주차 과제
 *
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2015.03.15
 * @link https://github.com/shlee322/konkuk-assignment/tree/master/datastructure/
 *
 */
public class Main {
    private static Random random = new Random();

    private static int[] generateRandomArray(int length) {
        int[] array = new int[length];
        for(int i=0; i<length; i++) {
            array[i] = random.nextInt(9) + 1;
        }
        return array;
    }

    private static void run(Class<?> exerciseClass) {
        Exercise exercise;
        int array_size = 1;
        int[] array;

        System.out.println("\nNumber of array\t\t\t\tExecution time\t\t\t\tNumber of <-");
        while (array_size <= 1000000) {
            try {
                array = generateRandomArray(array_size);

                ////////////////////////////////////////////////////////////////////////////////
                exercise = (Exercise) exerciseClass.newInstance();
                exercise.start();
                exercise.run(array);
                exercise.end();
                ////////////////////////////////////////////////////////////////////////////////

                // 밀리세컨드로 변환을 위해 1000000 나눔
                System.out.printf("%20d\t\t%20.6f ms\t\t%20d\n", array_size, exercise.getExecutionTime() / 1000000.0, exercise.getAssignCount());
            } catch (Exception e) {
                e.printStackTrace();
            }
            array_size *= 10;
        }
    }

    public static void main(String[] args) {
        run(Exercise1.class);
        run(Exercise2.class);
    }
}
