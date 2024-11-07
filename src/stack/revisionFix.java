package stack;

import java.util.Scanner;
import java.util.Stack;

public class revisionFix {
    static String prefix(String s){
        Stack<String> op=new Stack<>();
        Stack<String> num=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c>48&&c<58){
                num.push(c+"");
            }
            else if(op.isEmpty()){
                op.push(c+"");
            }
            else{
                if(c=='+'||c=='-'){
                    String s1=num.pop();
                    String s2=num.pop();
                    String ans=op.pop()+s2+s1;
                    num.push(ans);
                }
                else if(c=='*'&&op.peek().equals("/")||c=='/'&&op.peek().equals("*")){
                    String s1=num.pop();
                    String s2=num.pop();
                    String ans=op.pop()+s2+s1;
                    num.push(ans);
                }
                op.push(c+"");
            }
        }
        while(!op.isEmpty()){
            String s1=num.pop();
            String s2=num.pop();
            String ans=op.pop()+s2+s1;
            num.push(ans);
        }
        return num.pop();
    }
    static String postfix(String s){
        Stack<String> op=new Stack<>();
        Stack<String> num=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c>48&&c<58){
                num.push(c+"");
            }
            else if(op.isEmpty()){
                op.push(c+"");
            }
            else{
                if(c=='+'||c=='-'){
                    String s1=num.pop();
                    String s2=num.pop();
                    String ans=s2+s1+op.pop();
                    num.push(ans);
                }
                else if(c=='*'&&op.peek().equals("/")||c=='/'&&op.peek().equals("*")){
                    String s1=num.pop();
                    String s2=num.pop();
                    String ans=s2+s1+op.pop();
                    num.push(ans);
                }
                op.push(c+"");
            }
        }
        while(!op.isEmpty()){
            String s1=num.pop();
            String s2=num.pop();
            String ans=s2+s1+op.pop();
            num.push(ans);
        }
        return num.pop();
    }
    static String postfixInfix(String s){
        Stack<String> st=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c>=48&&c<58){
                st.push(c+"");
            }
            else{
                String s1=st.pop();
                String s2=st.pop();
                String ans='('+s2+c+s1+')';
                st.push(ans);
            }
        }
        return st.peek();
    }
    static String prefixInfix(String s){
        Stack<String> st=new Stack<>();
        int n=s.length();
        for(int i=n-1;i>=0;i--){
            char c=s.charAt(i);
            if(c>=48&&c<58){
                st.push(c+"");
            }
            else{
                String s1=st.pop();
                String s2=st.pop();
                String ans='('+s1+c+s2+')';
                st.push(ans);
            }
        }
        return st.peek();
    }
    static String postfixPrefix(String s){
        Stack<String> st=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c>=48&&c<58){
                st.push(c+"");
            }
            else{
                String s1=st.pop();
                String s2=st.pop();
                String ans=c+s2+s1;
                st.push(ans);
            }
        }
        return st.peek();
    }
    static String prefixPostfix(String s){
        int n=s.length();
        Stack<String> st=new Stack<>();
        for(int i=n-1;i>=0;i--){
            char c=s.charAt(i);
            if(c>=48&&c<58){
                st.push(c+"");
            }
            else{
                String s1=st.pop();
                String s2=st.pop();
                String ans=s1+s2+c;
                st.push(ans);
            }
        }
        return st.peek();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String ans=sc.next();
        System.out.println(postfix(ans));
    }
}
