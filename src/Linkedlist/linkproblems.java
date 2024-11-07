package Linkedlist;

public class linkproblems {
    public static void main(String[] args) {
        linkedlist1.linklist l1=new linkedlist1.linklist();
        l1.add(5);
        l1.add(6);
        l1.add(10);
        linkedlist1.node temp=l1.tail;
        l1.add(8);
        l1.add(7);
        l1.tail.next=temp;
        //l1.display();5-6-10-8-7
        System.out.println(problem8(l1.head).data);
    }
    static linkedlist1.node problem8(linkedlist1.node head){
        linkedlist1.node slow=head;
        linkedlist1.node fast=head;
        int i=0;
        while(fast!=null&&fast.next!=null){
            fast=fast.next;
            i++;
            for(int j=0;j<i;j++){
                if(fast.next==slow) return slow;
                slow=slow.next;
            }
            slow=head;
        }
        return null;
    }

    //two pointers efficient approach for determining cycle in linklist :
    static boolean problem7_1(linkedlist1.node head){

        linkedlist1.node slow=head;
        linkedlist1.node fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(fast==slow) return true;
        }
        return false;
    }
    //Bruteforce approach for determining cycle in linklist :
    static boolean problem7(linkedlist1.node head ){
     linkedlist1.node slow=head;
     linkedlist1.node fast=head;
     int i=0;
     while(fast!=null&&fast.next!=null){
         fast=fast.next;
         i++;
         for(int j=0;j<i;j++){
             if(fast.next==slow) return true;
             slow=slow.next;
         }
         slow=head;
     }
     return false;
    }
    //program for deleting middle element in linked list
    static void problem6(linkedlist1.node head,linkedlist1.linklist l1){
        linkedlist1.node fast = head.next;
        linkedlist1.node slow = head;
        if(fast==null) {
            l1.head =null;
        }
        else {
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            slow.next = slow.next.next;
        }
    }
    // program for finding middle element in linked list :
    //if there is even length of linked list so it will be for first middle element
    static linkedlist1.node problem5(linkedlist1.node head){
        linkedlist1.node fast=head;
        linkedlist1.node slow =head;
        while(fast.next!=null&&fast.next.next!=null)
        {
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;

    }
    // program for finding middle element in linked list :
    //if there is even length of linked list so it will be for second  middle element
    static linkedlist1.node problem5_1(linkedlist1.node head) {
        linkedlist1.node fast = head;
        linkedlist1.node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    static linkedlist1.node problem4(linkedlist1.node head, linkedlist1.node head1){
        int len1=0;
        int len2=0;
        linkedlist1.node temp=head;
        linkedlist1.node temp1=head1;
        while(temp!=null){
            len1++;
            temp=temp.next;
        }
        temp=head1;
        while(temp!=null){
            len2++;
            temp=temp.next;
        }
        temp=head;
        for(int i=0;i<Math.abs(len1-len2);i++){
            if(len1>len2) temp=temp.next;
            else temp1= temp1.next;
        }
        while(temp!=temp1)
        {
            temp=temp.next;
            temp1=temp1.next;
        }
        return temp;
    }
    static void problem3(linkedlist1.node head,int idx,linkedlist1.linklist l1){
        linkedlist1.node fast=head;
        linkedlist1.node slow=head;
        for(int i=0;i<idx;i++){
            fast=fast.next;

        }
        if(fast==null){
            l1.head=head.next;
            return;
        }
        while(fast.next!=null){
            slow=slow.next;
            fast=fast.next;
        }
    }
    //nth element from last;O(n);
    static linkedlist1.node problem2_1(linkedlist1.node head,int idx){
        linkedlist1.node fast=head;
        linkedlist1.node slow=head;
        for(int i=0;i<idx;i++){
            fast=fast.next;
        }
        while(fast!=null){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;

    }
    //nth element from last;O(2n)
    static linkedlist1.node problem2(linkedlist1.node head, int idx){
        int len=0;
        linkedlist1.node temp=head;
        while(temp!=null){
            temp=temp.next;
            len++;
        }
        temp=head;
        for(int i=0;i<len-idx;i++){
            temp=temp.next;
        }
        return temp;
    }
    //helps in deleting element except last element
    static void problem1(linkedlist1.node que){
        que.data=que.next.data;
        que.next=que.next.next;

    }
    // node which we are passing can chnge their value like arrays;
//    static void checkN(linkedlist1.node head){
//        head.next=null;
//    }
}
