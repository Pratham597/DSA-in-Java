package stack;

import java.util.Stack;


public class revision {
    public static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val=val;
        }
    }
    public static class stack{
        private Node head;
        public void add(int val){
            Node temp=new Node(val);
            temp.next=head;
            head=temp;
        }
        public int pop(){
            int val=head.val;
            head=head.next;
            return val;//value of top
        }
        public int peek(){
            return head.val;
        }
        public void display(){
            helpdisplay(head);
            System.out.println();
        }
        private void helpdisplay(Node temp){
            if(temp==null) return;
            helpdisplay(temp.next);
            System.out.print(temp.val+" ");
        }
    }
    static void reverseStack(Stack<Integer> st){
        if(st.isEmpty()) return;
        int x=st.pop();
        reverseStack(st);
        addAtIdx(st,0,x);
    }
    static void displayRevRec(Stack<Integer> st){
        if(st.isEmpty()) return;
        int x=st.pop();
        System.out.print(x+" ");
        displayRevRec(st);
        st.push(x);
    }
    static void displayRec(Stack<Integer> st){
        if(st.isEmpty()) return ;
        int x=st.pop();
        displayRec(st);
        System.out.print(x+" ");
        st.push(x);
    }
    static boolean validParenthesis(String ans){
        Stack<Character> st=new Stack<>();
        for(int i=0;i<ans.length();i++){
            char c=ans.charAt(i);
            if(c=='('){
                st.push(c);
            }
            else{
                if(st.isEmpty()) return false;
                else st.pop();
            }
        }
        return st.isEmpty();
    }
    static void removesubseq(int [] arr){
        Stack<Integer> st=new Stack<>();
        st.push(arr[0]);
        for(int i=1;i<arr.length;i++){
            if(arr[i-1]!=arr[i]) st.push(arr[i]);
            else if(!st.isEmpty()&&st.peek()==arr[i-1]) st.pop();
        }
        displayRec(st);
    }//From starting index:
    static void nxtGreaterElement(int [] arr){
        int n=arr.length;
        int [] ans=new int [n];
        Stack<Integer> st=new Stack<>();
        st.push(0);
        for(int i=1;i<n;i++){
            while(!st.isEmpty()&&arr[i]>arr[st.peek()])  ans[st.pop()]=arr[i];
            st.push(i);
        }
        while(!st.isEmpty()) ans[st.pop()]=-1;
        for(int x:ans) System.out.print(x+" ");
        System.out.println();
    }
    //From last Index:
    static void nxtGreaterElement2(int [] arr){
        int n=arr.length;
        int [] ans=new int [n];
        Stack<Integer> st=new Stack<>();
        ans[n-1]=-1;
        st.push(arr[n-1]);
        for(int i=n-2;i>=0;i--){
            while(!st.isEmpty()&&st.peek()<arr[i]) st.pop();
            if(st.isEmpty()) ans[i]=-1;
            else ans[i]=st.peek();
            st.push(arr[i]);
        }
        for(int x:ans) System.out.print(x+" ");
        System.out.println();
    }
    static void addAtIdx(Stack<Integer> st,int idx,int val){
        Stack<Integer> temp=new Stack<>();
        while(st.size()>idx){
            temp.push(st.pop());
        }
    }
    static int histogram(int [] arr){
        //Initialisation:
        int n=arr.length;
        int [] next=new int[n];//store nextsmaller ele.
        Stack<Integer> st=new Stack<>();
        int [] prev=new int[n];//store previous smaller ele.

        //Working Area:
        st.push(n-1);
        next[n-1]=n;
        for(int i=n-2;i>=0;i--){
            while(!st.isEmpty()&&arr[i]<=arr[st.peek()]){
                st.pop();
            }
            if(st.isEmpty()) next[i]=n;
            else next[i]=st.peek();
            st.push(i);
        }
        while(st.size()>0) st.pop();
        prev[0]=-1;
        st.push(0);
        for(int i=1;i<n;i++){
            while(!st.isEmpty()&&arr[i]<=arr[st.peek()]){
                st.pop();
            }
            if(st.isEmpty()) prev[i]=-1;
            else prev[i]=st.peek();
            st.push(i);
        }
        int ans=0;
        for(int i=0;i<n;i++){
            int num=next[i]-prev[i]-1;
            ans=Math.max(ans,num*arr[i]);
        }
        return ans;
    }
    public static void main(String[] args) {
        int [] arr={1,3,4,3,5,2};
        System.out.println(histogram(arr));
    }
}
