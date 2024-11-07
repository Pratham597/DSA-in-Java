package Linkedlist;
public class linkproblems2 {
    public static void main(String[] args) {
        linkedlist1.linklist l1=new linkedlist1.linklist();
        l1.add(1);
        l1.add(2);
        l1.add(4);
        l1.add(5);
        l1.add(5);
        linkedlist1.linklist l2=new linkedlist1.linklist();
        l2.add(1);
        l2.add(3);
        l2.add(4);
        l2.add(6);
        l2.add(7);
        l2.add(8);
        l2.add(9);
        l1.head=problemtemp(l1.head,l2.head);
        l1.display();;

//        linkedlist1.node ans=listReverseRec(l1.head);
//        while(ans!=null){
//            System.out.print(ans.data+" ");
//            ans=ans.next;
//        }
    }
    //Wap to find whether given linklist is pallindrome.
    //left middle
    //Right half reverse
    //two pointers on first half and second half for comparison
    static boolean isPallindrom(linkedlist1.node head){
        // 1->2->3->2->1
        linkedlist1.node slow=head;
        linkedlist1.node fast=head;
        while(fast!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }

        linkedlist1.node prev=null;
        linkedlist1.node curr=slow.next;
        linkedlist1.node nextcurr=null;
        if(curr!=null) nextcurr=curr.next;
        while(curr!=null){
            nextcurr=curr.next;
            curr.next=prev;
            prev=curr;
            if(nextcurr==null) break;
            curr=nextcurr;
        }
        slow.next=curr;
        fast=head;
        while(curr!=null){
            if(fast.data!= curr.data) return false;
            fast=fast.next;
            curr=curr.next;
        }
        return true;
    }
    //Wap to reverse list using recursion
    static linkedlist1.node listReverseRec(linkedlist1.node head){
          if(head.next==null) return head;
          linkedlist1.node newHead = listReverseRec(head.next);
          head.next.next=head;//Exchanging the connections.
          head.next=null;
          return newHead;
          //## That's what I think
//        linkedlist1.node ans= listReverseRec(head.next);
//        linkedlist1.node ansr=ans;
//        while(ans.next!=null){
//            ans=ans.next;
//        }
//        ans.next=head;
//        head.next=null;
//        return ansr;
    }
    //Wap to reverse the linkedlist:
    static void listReverse(linkedlist1.linklist l1){
        linkedlist1.node head=l1.head;
        linkedlist1.node next=head.next;
        if(next==null) return;
        else if(next.next==null){
            next.next=head;
            head.next=null;
            l1.head=next;
            return;
        }
        linkedlist1.node next2=next.next;
        head.next=null;
        while(next2!=null){
            next.next=head;
            head=next;
            next=next2;
            next2=next2.next;
        }
        next.next=head;
        l1.head=next;
    }
    //WAP TO remove duplicates from sorted linked list :-
    static void listRemoveDup(linkedlist1.linklist l1){
        linkedlist1.node st=l1.head;
        linkedlist1.node end=st.next;
        while(end!=null){
            if(st.data!=end.data) {
                st.next=end;
                st=end;
            }
            end=end.next;
        }
        st.next=null;
    }
    //Wap to divide list in two parts such thst 1->3->5>2>4(1,2,3,4,5) without using extra space:
    static void listEvenOdd(linkedlist1.linklist l1){
        linkedlist1.node tail=null ;
        linkedlist1.node st=l1.head;
        linkedlist1.node temp=null;
        linkedlist1.node tempC=null;
        while(st.data%2==0){
            st=st.next;
        }
        temp=st;
        st=l1.head;
        l1.head=temp;
        temp=null;
        while(st!=null){
            if((st.data%2)==0) {
                if(tail==null){
                    tail=st;
                    tempC=tail;
                }
                else {
                    tail.next=st;
                    tail=st;
                }
                st=st.next;
            }
            else if(tail!=null&&temp!=null){
                    temp.next=st;
                    temp=st;
                    st=st.next;
                    temp.next=tempC;
            }
            else{
                temp=st;
                st=st.next;
                }
            }
        tail.next=null;
    }
    //Wap to split list into two list basis of odd and even
    static void listSplitEvenOdd(linkedlist1.node head){
        linkedlist1.node temp=head;
        linkedlist1.linklist le= new linkedlist1.linklist();
        linkedlist1.linklist lo=new linkedlist1.linklist();
        while(temp!=null){
            if(temp.data%2==0) le.add(temp.data);
            else lo.add(temp.data);
            temp=temp.next;

        }
        le.display();
        lo.display();
    }
    static linkedlist1.node problemtemp(linkedlist1.node list1 ,linkedlist1.node list2){
        if(list1==null) return list2;
        if(list2==null) return list1;
        linkedlist1.node t1=list1;
        linkedlist1.node t2=list2;
        linkedlist1.node temp=null;
        linkedlist1.node temp2=null;
        linkedlist1.node h;
        if(list1.data<=list2.data) h=list1;
        else h=list2;
        while(t1!=null&&t2!=null){
            while(t1!=null&&t2!=null&&t1.data<=t2.data){
                temp=t1;
                t1=t1.next;
            }
            if(t1!=null&&t2!=null&&temp!=null)temp.next=t2;
            while(t2!=null&&t1!=null&&t2.data<=t1.data){
                temp2=t2;
                t2=t2.next;
            }
            if(t1!=null&&t2!=null&&temp2!=null)temp2.next=t1;
        }
        linkedlist1.linklist l1=new linkedlist1.linklist();
        l1.head=t2;
        l1.display();
        if(t1==null) temp.next=t2;
        else if(t2==null) temp2.next=t1;
        return h;
    }

    //Wap to merge list without using extra space
    static linkedlist1.node problem10_1(linkedlist1.node head1 ,linkedlist1.node head2){
       linkedlist1.node temp1=head1;
       linkedlist1.node temp2=head2;
       linkedlist1.node temp3=null;

       if(head1.data<head2.data){
           temp3=temp1;
           temp1=temp1.next;
       }
       else{
           temp3=temp2;
           temp2=temp2.next;
       }
       linkedlist1.node h=temp3;
      while(temp1!=null&&temp2!=null){
           if(temp1.data<temp2.data){
               temp3.next =temp1;
               temp3=temp3.next;
               temp1=temp1.next;
           }
           else{
               temp3.next=temp2;
               temp3=temp3.next;
               temp2=temp2.next;
           }
       }
       if(temp1==null) temp3.next=temp2;
       else temp3.next=temp1;
       return h ;
    }
    //Wap to implement merge fucntion (merge sort) in list:
    static linkedlist1.linklist problem10(linkedlist1.node head1 ,linkedlist1.node head2){
        linkedlist1.linklist l3=new linkedlist1.linklist();
        linkedlist1.node temp1=head1;
        linkedlist1.node temp2=head2;
        while(temp1!=null&&temp2!=null){
            if(temp1.data<temp2.data) {
                l3.add(temp1.data);
                temp1=temp1.next;
            }
            else {
                l3.add(temp2.data);
                temp2=temp2.next;
            }
        }
        while(temp1!=null){
            l3.add(temp1.data);
            temp1=temp1.next;
        }
        while(temp2!=null){
            l3.add(temp2.data);
            temp2=temp2.next;
        }
        return l3;
    }
    static linkedlist1.node  problem9(linkedlist1.node head) {
        linkedlist1.node slow = head;
        linkedlist1.node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) break;
        }
        linkedlist1.node temp = head;
        while (temp != slow) {
            slow = slow.next;
            temp = temp.next;
        }
        return slow;
    }
    //Wap to find that node from which tails connect.
    //for finding cycle in linkedlist.
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
}
