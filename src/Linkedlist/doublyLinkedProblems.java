package Linkedlist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class doublyLinkedProblems {
    public static void main(String[] args) {

//        doublyLinkedList.doublyLnkList l1=new doublyLinkedList.doublyLnkList();
//        l1.add(1);
//        l1.add(2);
//        l1.add(5);
//        l1.add(3);
//        l1.add(2);
//        l1.display();
//        l1.head=reverse(l1.head);
//        l1.display();
//        int x='a';
//        int y='e';
//        int z='i';
//        int a='o';
//        int b='u';
//        System.out.println(x+" "+y+" "+z+" "+a+" "+b);
//        StringBuilder sb=new StringBuilder();
        System.out.println(maxVowels("novowels",1));
        Queue<Character> q=new ArrayDeque<>();
    }
    static boolean isVowel(char c){
        if(c==97||c==101||c==105||c==111||c==117) return true;
        return false;
    }
    static int maxVowels(String s, int k) {
        StringBuilder sb=new StringBuilder();
        int n=s.length();
        int count=0;
        int max=0;
        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            if(sb.length()>=k){
                String temp=sb.deleteCharAt(0)+"";
                System.out.println(temp);
                if(isVowel(temp.charAt(0))) count--;
            }
            if(isVowel(c)) count++;
            max=Math.max(count,max);
            sb.append(c);
            System.out.println(i);
        }
        return max;
    }
    static doublyLinkedList.Node reverse(doublyLinkedList.Node head){
        doublyLinkedList.Node fast=head;
        doublyLinkedList.Node curr=null;
        doublyLinkedList.Node prev=null;
        while(fast!=null){
            curr=fast;
            fast=curr.next;
            curr.next=prev;
            if(prev!=null) prev.prev=curr;
            prev=curr;
        }
        prev.prev=null;
        fast=head;
        while(fast!=null){
            System.out.print(fast.val+" ");
            fast=fast.prev;
        }
        System.out.println();
        return prev;
    }
    static int[] criticalPoint(doublyLinkedList.Node head){
        doublyLinkedList.Node temp=head;
        int cP1=0,cP2=0,max=Integer.MIN_VALUE,min=Integer.MAX_VALUE,i=1;
        int [] ans=new int[2];
        if(temp.next.val>temp.val) temp=temp.next;
        while(temp.next!=null){
            if(temp.val>temp.prev.val&&temp.val>temp.next.val){
                cP1=i;
            }
            else if(temp.prev.val>temp.val&&temp.next.val> temp.val){
                cP2=i;
            }
            i++;
            temp=temp.next;
            if(max<Math.abs(cP1-cP2)) max=Math.abs(cP1-cP2);
            if(cP1-cP2!=0) {
                if (min > Math.abs(cP1 - cP2)) min=Math.abs(cP1 - cP2);
            }
        }
        ans[0]=max;
        ans[1]=min;
        return ans;
    }
    static void twoSum(doublyLinkedList.Node head,int x){
        doublyLinkedList.Node temped=head;
        doublyLinkedList.Node tempst=head;
        while(temped.next!=null){
            temped=temped.next;
        }
        while(tempst.val<temped.val){
            if(tempst.val+temped.val==x) {
                System.out.println(tempst.val+" "+temped.val);
                break;
            }
            else if(tempst.val+temped.val>x) temped=temped.prev;
            else tempst=tempst.next;
        }
    }
    static boolean isPallindrome(doublyLinkedList.Node head){
        doublyLinkedList.Node temped=head;
        doublyLinkedList.Node tempst=head;
        while(temped.next!=null){
            temped=temped.next;
        }
        //temped:- Marking tail
        while(tempst!=temped){
            if(tempst.val!=temped.val) return false;
            tempst=tempst.next;
            temped=temped.prev;
        }
        return true;
    }

}
