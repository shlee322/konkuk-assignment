/**
 * 자료구조 - 3주차 과제
 *
 * 하노이의 탑 구현 - 스택(linkedlist로 구현함)
 *
 * @author Lee Sanghyuck (shlee322@elab.kr)
 * @since 2015.04.05
 * @link https://github.com/shlee322/konkuk-assignment/tree/master/datastructure/
 *
 */
public class Stack {
    private static class StackNode {
        int value;
        StackNode prevNode;

        public StackNode(int value, StackNode prevNode) {
            this.value = value;
            this.prevNode = prevNode;
        }
    }

    private StackNode topNode=null;
    private int count=0;

    public void push(int value) {
        this.topNode = new StackNode(value, this.topNode);
        this.count++;
    }

    public int pop() {
        if(this.topNode == null) {
            throw new IllegalStateException("스택이 비어있습니다.");
        }

        int result = this.topNode.value;
        this.topNode = this.topNode.prevNode;
        this.count--;
        return result;
    }

    public int top() {
        if(this.topNode == null) {
            throw new IllegalStateException("스택이 비어있습니다.");
        }

        return this.topNode.value;
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.topNode == null;
    }
}
