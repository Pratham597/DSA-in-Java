package queue;

import java.util.Deque;
import java.util.LinkedList;

public class dequeCollections {
    public static void main(String[] args) {
        Deque<Integer> dq=new LinkedList<>();
        dq.add(2);
        dq.add(3);
        dq.addLast(4);
        dq.addFirst(5);
        dq.addLast(6);//5 2 3 4 6
        dq.remove();
        dq.removeLast();
        System.out.println(dq.peekLast());
        System.out.println(dq);
    }
}
