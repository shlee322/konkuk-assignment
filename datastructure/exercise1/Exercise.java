/**
 * 자료구조 - 1주차 과제
 *
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2015.03.15
 * @link https://github.com/shlee322/konkuk-assignment/tree/master/datastructure/
 *
 */
public abstract class Exercise {
    private long assign_count = 0;
    private long start_time;
    private long end_time;

    public long getAssignCount() {
        return this.assign_count;
    }

    protected int assignInt(int assign) {
        this.assign_count++;
        return assign;
    }

    protected long assignLong(long assign) {
        this.assign_count++;
        return assign;
    }

    public void start() {
        this.start_time = System.nanoTime();
    }

    public void end() {
        this.end_time = System.nanoTime();
    }

    public long getExecutionTime() {
        return this.end_time - this.start_time;
    }

    public abstract long run(int[] A);

}
