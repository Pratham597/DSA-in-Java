package Linkedlist;

import java.util.HashMap;
import java.util.HashSet;

//First we have compare first half and second reverse half in pallindrome question:
//second the we have perform additon on first half and second reverse half in twinSum:
//
public class linkproblems3 {
    public static void main(String[] args) {
        linkedlist1.linklist l1 = new linkedlist1.linklist();
        l1.add(1);
        l1.add(3);
        l1.add(2);
        l1.add(-3);
        l1.add(-2);
        l1.add(5);
        l1.add(5);
        l1.add(-5);
        l1.add(1);
        l1.head=check(l1.head);
        l1.display();

    }
    static linkedlist1.node check(linkedlist1.node head){
        HashMap<Integer,Integer> mp=new HashMap<>();
        HashMap<Integer,Integer> ans=new HashMap<>();
        mp.put(0,-1);
        linkedlist1.node temp=head;
        int sum=0,idx=0;
        while(temp!=null){
            sum+=temp.data;
            if(mp.containsKey(sum)){
                int x=mp.get(sum);
                ans.put(x+1,idx);
            }
            mp.putIfAbsent(sum,idx);
            temp=temp.next;
            idx++;
        }
        for(var y:ans.entrySet()){
            System.out.println(y.getKey()+" "+y.getValue());
        }
        //creating new list:
        linkedlist1.node subans=new linkedlist1.node(0);
        linkedlist1.node ret=subans;
        temp=head;
        idx=0;
        while(temp!=null){
            if(ans.containsKey(idx)){
                int n=ans.get(idx)-idx;
                for(int i=0;i<=n;i++){
                    if(temp==null) break;
                    temp=temp.next;
                    idx++;
                }
            }
            subans.next=temp;
            subans=temp;
            if(temp!=null) temp=temp.next;
            idx++;
        }
        return ret.next;
    }
    static linkedlist1.node  rotate(int n1,int n2,linkedlist1.node head){
        linkedlist1.node temp=head;
        for(int i=0;i<n1-1;i++){
            temp=temp.next;
        }
        linkedlist1.node next=temp.next;
        if(n1==0) next=head;
        linkedlist1.node curr=next;
        linkedlist1.node prev=null;
        for(int i=n1;i<=n2&&next!=null;i++){
            curr=next;
            next=next.next;
            curr.next=prev;
            prev=curr;
        }
        if(n1==0&&next==null){
            return curr;
        }
        temp.next.next=next;
        temp.next=curr;
        return head;
    }
    //wap to delete tht element if the only that particular node is given.
    static void remove(linkedlist1.node head){
        linkedlist1.node temp=head;
        while(temp.next!=null&&temp.next.next!=null){
            temp.data=temp.next.data;

            temp=temp.next;
        }
        temp.data=temp.next.data;
        temp.next=null;

    }
    //sum of ith and n-ith-1 index in liinkedlist question
    static int twinSum(linkedlist1.node head){
        linkedlist1.node slow=head;
        linkedlist1.node fast=head;
        while(fast!=null&&fast.next.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        linkedlist1.node prev=null;
        linkedlist1.node curr=slow.next;
        linkedlist1.node nextcurr=null;
        while(curr!=null){
            nextcurr=curr.next;
            curr.next=prev;
            prev=curr;
            if(nextcurr==null) break;
            curr=nextcurr;
        }
        slow.next=curr;
        fast=head;
        int sum=0,max=0;
        while(curr!=null){
            sum=fast.data+curr.data;
            if(max<sum) max=sum;
            fast=fast.next;
            curr=curr.next;
        }
        return max;
    }
    //Creating deep copy of list
    static linkedlist1.node deepCopy(linkedlist1.node head){
        linkedlist1.linklist l1=new linkedlist1.linklist();
        while(head!=null){
            l1.add(head.data);
            head=head.next;
        }
        return l1.head;
    }
    //1->2->3->4->5 --- 1->3->5->2->4
    static linkedlist1.node evenoddlist(linkedlist1.node head) {
        linkedlist1.node tempe=new linkedlist1.node(0);
        linkedlist1.node tempo=new linkedlist1.node(0);
        linkedlist1.node tempor=tempo;
        linkedlist1.node temper=tempe;
        linkedlist1.node temp=head;
        while(temp!=null){
            tempo.next=temp;
            tempo=tempo.next;
            temp=temp.next;

            tempe.next=temp;
            tempe=tempe.next;
            if(temp==null) break;
            temp=temp.next;
        }
        tempo.next=temper.next;
        return tempor.next;
    }
}

