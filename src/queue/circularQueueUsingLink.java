package queue;

public class circularQueueUsingLink {
    public static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val=val;
        }
    }
    public static class circularqueue{
        Node head=null;
        Node tail=null;
        int size=0;
        //Operations;
        void add(int val){
            Node temp=new Node(val);
            if(head==null) head=temp;
            else tail.next=temp;
            tail=temp;
            tail.next=head;
            size++;
        }
        int poll()throws Exception{
            if(size==0) throw new Exception("Underflow");
            int x=head.val;
            head=head.next;
            tail.next=head;
            size--;
            return x;
        }
        int peek()throws Exception{
            if(size==0) throw new Exception("Underflow");
            return head.val;
        }
        void display(){
            if(size==0) System.out.println("[]");
            else {
                Node temp=head;
                do{
                    System.out.print(temp.val+" ");
                    temp=temp.next;
                }while(temp!=head);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws Exception {
        circularqueue q=new circularqueue();

    }
}
