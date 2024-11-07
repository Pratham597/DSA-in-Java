package queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class problem {
    public static void main(String[] args) {
        Queue<Integer> q=new LinkedList<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.add(6);
        q.add(7);
        //q.add(8);
//        System.out.println(q);
//        reorder2(q);
        System.out.println(q);
        reorder(q);
        System.out.println(q);
        reorder(q);
        System.out.println(q);
        reorder(q);
        System.out.println(q);
        reorder(q);

//        System.out.println(q);
//        reorderQueue(q);
//        System.out.println(q);

    }
    //wap to reorder ueue through st:
    static void reorder2(Queue<Integer> q){
        int n=q.size();
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<n/2;i++){
            st.push(q.poll());
        }
        reverseStack(st);
        while(st.size()>0){
            q.add(st.pop());
            q.add(q.poll());
        }
    }
    static void reverseStack(Stack<Integer> st){
        if(st.size()==0) return;
        int x=st.pop();
        reverseStack(st);
        addAtIdx(st,0,x);
    }
    static void addAtIdx(Stack<Integer> st,int idx,int val){
        Stack<Integer> temp=new Stack<>();
        while(st.size()>idx){
            temp.push(st.pop());
        }
        st.push(val);
        while(temp.size()>0){
            st.push(temp.pop());
        }
    }
    static void reorder(Queue<Integer> q){
        int n=q.size();
        int []ans=new int[n];
        for(int i=0;i<n;i+=2){
            ans[i]=q.poll();
        }
        for(int i=1;i<n;i+=2){
            ans[i]=q.poll();
        }
        for(int i=0;i<n;i++){
            q.add(ans[i]);
        }
    }
    //wap to reorder using queue
    static void reorderQueue(Queue<Integer> q){
        int n=q.size();
        Queue<Integer> temp=new LinkedList<>();
        Queue<Integer> temp1=new LinkedList<>();
        for(int i=0;i<n/2;i++){
            temp.add(q.poll());
        }
        for(int i=n/2;i<n;i++){
            temp1.add(q.poll());
        }
        while(!temp.isEmpty()&&!temp1.isEmpty()){
            q.add(temp.poll());
            q.add(temp1.poll());
        }
        if(!temp.isEmpty()) q.add(temp.poll());
        else if(!temp1.isEmpty()) q.add(temp1.poll());
    }
    static long[] firstnegativewindow(long [] arr,int k){
        Queue<Integer> q=new LinkedList<>();
        int n=arr.length;
        for(int i=0;i<n;i++){
            if(arr[i]<0) q.add(i);
        }//pushing elements which are negative:
        long [] ans=new long[n-k+1];
        for(int i=0;i<n-k+1;i++){
            if(q.size()>0&&q.peek()<i) q.poll();
            if(q.size()>0&&q.peek()<=i+k-1){
                ans[i]=arr[q.peek()];
            }
        }
        return ans;
    }
    static int[] firstNegativeWindow(int[] arr,int k){
        int [] neg=new int[arr.length];
        Queue<Integer> q=new LinkedList<>();
        int n=arr.length;
        q.add(n-1);
        neg[n-1]=n;
        for(int i=n-1;i>=0;i--){
            if(arr[i]<0){
                neg[i]=i;
                if(!q.isEmpty()) q.poll();
                q.add(i);
            }
            else{
                neg[i]=q.peek();
            }
        }
        for(int x:neg){
            System.out.println(x);
        }
        int [] ans=new int[n-k+1];
        int c=0,j=0;
        for(int i=0;i<n-k+1;i++){
            j=i;
            while(j<i+k){
                c=arr[j];
                if(j==neg[j]) break;
                j=neg[j];
            }
            //-1,8,-3,4,5,-1
            if(c<0) ans[i]=c;
            else ans[i]=0;
        }
        return ans;
    }
    static void reverseFirst(Queue<Integer> q,int k){
        Queue<Integer> temp=new LinkedList<>();
        for(int i=0;i<k;i++) {
            temp.add(q.poll());
        }
        reverse(q);
        while(temp.size()>0){
            q.add(temp.poll());
        }
        reverse(q);
    }
    static void reverse(Queue<Integer> q){//O(n)-space and time
        Stack<Integer> st=new Stack<>();
        while(q.size()>0){
            st.push(q.poll());
        }
        while(st.size()>0){
            q.add(st.pop());
        }
    }
}
