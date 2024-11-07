package stack;

import java.util.Scanner;
import java.util.Stack;
public class basicImplementation {
    public static void main(String[] args) {
        //Input for stack:-
        Stack<Integer> st=new Stack<>();
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int i=1;i<=n;i++){
            st.push(sc.nextInt());
        }
        System.out.println(st);
//        int k=st.size(),i=1;//k-size of stack
//        //Display a stack using arr and stack:
//        int [] arr=new int[k];// arr
//        Stack<Integer> temp=new Stack<>();
//        while(st.size()>0){
//            int x=st.pop();
//            temp.push(x);
//            arr[k-i]=x;
//            i++;
//        }
//        i=0;
//        while(temp.size()>0){
//            int x=temp.pop();
//            System.out.print(x+" ");
//            st.push(x);
//        }
//        System.out.println(st);
//        for(int x:arr) {
//            System.out.print(x+" ");
//        }
        removeAtIdx(st,5);
        System.out.println(st);

    }
    //Wap to remove any element from stack
    static void removeAtIdx(Stack<Integer> st,int idx){
        Stack<Integer> temp=new Stack<>();
        while(st.size()>idx+1){
            temp.push(st.pop());
        }
        st.pop();
        while(temp.size()>0){
            st.push(temp.pop());
        }
    }
    //Wap to make stack reverse of initial stack:
    static void reverseStack(Stack<Integer> st){
        if(st.size()==0) return;
        int x=st.pop();
        reverseStack(st);
        addAtIdx(st,0,x);
    }
    //Wap to add at any given index using recursion
    static void addAtIdxRec(Stack<Integer> st,int idx,int val){
        if(st.size()==idx) {
            st.push(val);
            return;
        }
        int x=st.pop();
        addAtIdxRec(st,idx,val);
        st.push(x);
    }
    //Wap to copy one stack;
    static Stack<Integer> copyStack(Stack<Integer> st){
        Stack<Integer> ans= new  Stack<>();
        if(st.isEmpty()) return ans;
        int x=st.pop();
        ans=copyStack(st);
        ans.push(x);
        return ans;
    }
    static void displayr(Stack<Integer> st){
        if(st.size()==0) {
            System.out.println();
            return;
        }
        int x=st.pop();
        System.out.print(x+" ");
        displayr(st);
        st.push(x);
    }
    static void display(Stack<Integer> st){
        if(st.size()==0) {
            System.out.println();
            return ;
        }
        int x=st.pop();
        display(st);
        System.out.print(x+" ");
        st.push(x);
    }
    static void add(Stack<Integer> st,int val){
        Stack<Integer> temp=new Stack<>();
        while(st.size()>0){
            temp.push(st.pop());
        }
        st.push(val);
        while(temp.size()>0){
            st.push(temp.pop());
        }
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
    static Stack<Integer> copyStackR(Stack<Integer> st){
        Stack<Integer> ans= new  Stack<>();
        if(st.isEmpty()) return ans;
        ans.push(st.pop());
        Stack<Integer> ans2=copyStackR(st);
        ans.addAll(ans2);
        return ans;
    }

}
