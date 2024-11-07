package queue;

public class basicImplementationUsingLink {
    public static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val=val;
        }
    }
    public static class queue{
        private Node head=null;
        private Node tail=null;
        private int size=0;
        //Operations:
        void add(int val){
            Node temp=new Node(val);
            if(head==null) head=temp;
            else tail.next=temp;
            tail=temp;
            size++;
        }
        int pop(){
            int x=head.val;
            head=head.next;
            size--;
            return x;
        }
        int peek(){
            return head.val;
        }
        void display(){
            Node temp=head;
            if(temp==null) System.out.print("[]");
            while(temp!=null){
                System.out.print(temp.val+" ");
                temp=temp.next;
            }
            System.out.println();
        }

    }
    public static void main(String[] args){
        queue q=new queue();
        q.display();
        q.add(2);
        q.add(3);
        q.add(7);
        q.pop();
        q.add(9);
        q.display();
        System.out.println(q.size );

    }
}
