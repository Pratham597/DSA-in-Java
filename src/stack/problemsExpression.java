package stack;

import java.util.Scanner;
import java.util.Stack;

public class problemsExpression {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
        int n=sc.nextInt();
        int [] arr=new int [n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int k=sc.nextInt();
        for(int x:maxSlidingWindow(arr,k)){
            System.out.print(x+" ");
        }
    }
    static int[] maxSlidingWindow(int[] arr, int k) {
        int n=arr.length,j=0;
        Stack<Integer> st=new Stack<>();
        int []ans=new int [n-k+1];
        for(int i=0;i<n;i++){
            if(i>=k){//checking answer
                ans[j]=arr[st.peek()];
                j++;
                if(!(st.peek()>i-k)) {
                    st.pop();
                    st.push(i-1);
                }
            }
            //checking conditions:
            if(st.isEmpty()) st.push(i);
            while(arr[st.peek()]>arr[i]){
                st.pop();
                st.push(i);
            }
        }
        ans[j]=arr[st.peek()];
        return ans;
    }
    static String postfixInfix(String str){
        Stack<String> st=new Stack<>();
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(c>=48&&c<=57){
                st.push(c+"");
            }
            else{
                String v1=st.pop();
                String v2=st.pop();
                st.push('('+v2+c+v1+')');
            }
        }
        return st.pop();

    }
    //wap to conver prefix into infix:
    static String prefixInfix(String str){
        Stack<String> st=new Stack<>();
        for(int i=str.length()-1;i>=0;i--){
            char c=str.charAt(i);
            if(c>=48&&c<=57){
                st.push(c+"");
            }
            else{
                String v1=st.pop();
                String v2=st.pop();
                st.push('('+v1+c+v2+')');
            }
        }
        return st.pop();
    }
    //wap to convert from postfix into prefix:
    static String postfixprefix(String str){
        Stack<String> st=new Stack<>();
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(c>=48&&c<=57){
                st.push(c+"");
            }
            else{
                String v1=st.pop();
                String v2=st.pop();
                st.push(c+v2+v1);
            }
        }
        return st.pop();

    }
    //wap to convert from prefix into postfix:
    static String prefixPostfix(String str){
        Stack<String> st=new Stack<>();
        for(int i=str.length()-1;i>=0;i--){
             char c=str.charAt(i);
             if(c>=48&&c<=57){
                 st.push(c+"");
            }
             else{
                 String v1=st.pop();
                 String v2=st.pop();
                 st.push(v1+v2+c);
             }
        }
        return st.pop();
    }
    static int valueOfPrefix(String str){
        Stack<Integer> st=new Stack<>();
        for(int i=str.length()-1;i>=0;i--){
            char c=str.charAt(i);
            if(c>=48&&c<=57) st.push(c-'0');
            else{
                int v1=st.pop();
                int v2=st.pop();
                switch(c) {
                    case '+':
                        st.push(v1 + v2);
                        break;
                    case '-':
                        st.push(v1 - v2);
                        break;
                    case '*':
                        st.push(v1*v2);
                        break;
                    case '/':
                        st.push(v1/v2);
                        break;
                }
            }
        }
        return st.pop();
    }
    static int valueOfPostfix(String str){
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(c>=48&&c<=57){
                st.push(c-'0');
            }
            else {
                int v1=st.pop();
                int v2=st.pop();
                switch(c) {
                    case '+':
                        st.push(v1 + v2);
                        break;
                    case '-':
                        st.push(v2 - v1);
                        break;
                    case '*':
                        st.push(v1*v2);
                        break;
                    case '/':
                        st.push(v2/v1);
                        break;
                }
            }
        }
        return st.pop();
    }
}
