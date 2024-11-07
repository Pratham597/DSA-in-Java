package Linkedlist;
public class circularLinkedList {
    //1->2->3->4
    //'<-6<-5<-'
    public static class Node{
        int val;
        Node next;
        Node(int val){
            this.val=val;

        }
    }
    public static void main(String[] args) {
        Node a = new Node(2);
        Node b= new Node(3);
        Node c=new Node(4);
        Node d = new Node(7);
        a.next=b;
        b.next=c;
        c.next=d;
        d.next=a;
        Node temp=delete(a);
        do{
            System.out.print(temp.val+" ");
            temp=temp.next;
        }while(temp!=b);
    }
    static Node delete(Node head){
        Node temp=head;
        while(temp.next!=head){
            temp=temp.next;
        }
        temp.next=head.next;
        return head.next;
    }
}
