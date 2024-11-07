package queue;

import java.util.LinkedList;
import java.util.Queue;

public class queueCollectionFrame {
    public static void main(String[] args) {
        Queue<Integer> q=new LinkedList<>();
        q.add(5);
        q.add(6);
        q.add(8);
        q.add(5);
        q.add(3);
        q.add(1);
        System.out.println(q);

    }
    static void display(Queue<Integer> q){
        Queue<Integer> temp=new LinkedList<>();
        while(q.size()>0){
            System.out.print(q.peek()+" ");
            temp.add(q.remove());
        }
        System.out.println();
        while(temp.size()>0){
            q.add(temp.remove());
        }
    }
    static void displayrec(Queue<Integer> q){
        if(q.isEmpty()) {
            System.out.println();
            return;
        }
        int x=q.poll();
        System.out.print(x+" ");
        displayrec(q);
        q.add(x);
    }
    static void addAtIdx(Queue<Integer> q,int idx,int x){
        Queue<Integer> temp=new LinkedList<>();
        for(int i=0;i<idx;i++){
            temp.add(q.poll());
        }
        temp.add(x);
        while(q.size()>0){
            temp.add(q.poll());
        }
        while(temp.size()>0){
            q.add(temp.poll());
        }
    }
    static void displayrRec(Queue<Integer> q){
        if(q.isEmpty()) return ;
        int x=q.poll();
        displayrRec(q);
        System.out.print(x+" ");
        addAtTop(q,x);
    }
    static void addAtTop(Queue<Integer> q,int x){
        Queue<Integer> temp=new LinkedList<>();
        temp.add(x);
        while(q.size()>0){
            temp.add(q.poll());
        }
        while(temp.size()>0){
            q.add(temp.poll());
        }
    }
}
