/**
 * 자료구조 - 1주차 과제
 *
 * 참조 : pt의 '<- Assignment'를 빌어 모든 대입 연산자를 계산
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2015.03.15
 * @link https://github.com/shlee322/konkuk-assignment/tree/master/datastructure/
 *
 */
public class Exercise1 extends Exercise {
    @Override
    public long run(int[] A) {
        long s = assignInt(A[0]);
        for(int i=assignInt(1); i<A.length; assignInt(i++)) {
            s = assignLong(s + A[i]);
        }
        return s;
    }
}
