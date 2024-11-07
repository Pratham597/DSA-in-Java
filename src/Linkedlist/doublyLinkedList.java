package Linkedlist;

public class doublyLinkedList {
    public static class Node{
        int val;
        Node next;
        Node prev;
        Node(int val){
            this.val=val;

        }
    }
    public static class doublyLnkList{
        Node head;
        Node tail;
        int size=0;

        //For adding new elements in linklist:
        void add(int val){
            Node temp=new Node(val);
            size=size+1;
            if(head==null){
                head=temp;
            }
            else{
                temp.prev=tail;
                tail.next=temp;
            }
            tail=temp;
        }
        void delete(){
            size-=1;
            tail=tail.prev;
            tail.next=null;
        }
        void deleteAt(int position){
            Node temp=head;
            size-=1;
            if(position==1){
                head=head.next;
                head.prev=null;
                return;
            }
            int x=position-1;
            while(x>1){
                temp=temp.next;
                x--;
            }
            temp.next=temp.next.next;
            temp.next.prev=temp;
        }
        void display(){
            Node temp=head;
            while(temp!=null){
                System.out.print(temp.val+" ");
                temp=temp.next;

            }
            System.out.println();
        }
        void displayr(){
            Node temp=tail;
            while(temp!=null){
                System.out.print(temp.val+" ");
                temp=temp.prev;
            }
        }
        void insert(int position , int val){
            Node temp=new Node(val);
            size=size+1;
            if(position==1){
                temp.next=head;
                head.prev=temp;
                head=temp;
                return;
            }
            else if(position==size){
                tail.next=temp;
                temp.prev=tail;
                tail=temp;
                return;
            }
            Node cur=head;
            int x=position-1;
            while(x>1){
                cur=cur.next;
                x--;
            }
            //cur->node before that given position
            temp.next=cur.next;
            cur.next.prev=temp;
            cur.next=temp;
            temp.prev=cur;
        }
    }

    public static void main(String[] args) {
        //Basic implementation :
        //4-10-2-99-13
//        Node a = new Node(4);
//        Node b = new Node(10);
//        Node c = new Node(2);
//        Node d = new Node(99);
//        Node e = new Node(13);
////        Node f = new Node(5);
//        a.prev=null;
//        a.next=b;
//        b.prev=a;
//        b.next=c;
//        c.next=d;
//        c.prev=b;
//        d.next=e;
//        d.prev=c;
//        e.prev=d;
//        Node temp=c;//Given the any nnode of list
//        while(temp.prev!=null){
//            temp=temp.prev;
//        }
//        //Printing in given order
//        while(temp!=null){
//            System.out.print(temp.val+" ");
//            temp=temp.next;
//        }
//        //Printing the given list in reverse order:
        doublyLnkList l1=new doublyLnkList();
        l1.add(2);
        l1.add(3);
        l1.add(4);
        l1.add(5);
        l1.add(6);
        l1.deleteAt(2);
        l1.display();
    }
}
