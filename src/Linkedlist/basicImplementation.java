package Linkedlist;

public  class basicImplementation {
    public static class node {
        int data;
        node next;

        node(int data) {
            this.data = data;
        }

        node() {

        }
    }

    public static void main(String[] args) {
        node a = new node(5);
        node b = new node(6);
        node c = new node(2);
        node d= new node(4);
        a.next = b;
        b.next = c;
        c.next=d;
        node ans = a;

        //Displaying Linked List
        while (ans!=null) {
            System.out.println(ans.data);
            ans=ans.next;
        }
        //displaying length;
        int count=0;
        ans=a;
        while (ans!=null) {
            count++;
            ans = ans.next;
        }
        System.out.println(count);
        System.out.println("Length: " + lengthr(a));



    }
//    static void  check(node ans){
//        ans=ans.next;
//    }
//    query , but check again .
    static void recursion(node a)
    {
        if(a==null) return;
        recursion(a.next);
        System.out.println(a.data);
    }
    static  int lengthr(node a )
    {
        if(a==null) return 0;
        return 1+lengthr(a.next);
    }

}