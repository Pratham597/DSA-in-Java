package stack;
import java.util.Stack;
import java.util.Scanner;
public class expressionStack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(postfixExpression(str));
    }
    //wap for postfix expression:
    static String postfixExpression(String str){
        Stack<String> st=new Stack<>();
        Stack<String> op=new Stack<>();
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(c>=48&&c<=57){
                st.push(c+"");
            }
            else if(op.isEmpty()||c=='('||op.peek().equals("(")){
                op.push(c+"");
            }
            else if(c==')'){
                while(!op.peek().equals("(")){
                    String v1=st.pop();
                    String v2=st.pop();
                    st.push(v2+v1+op.peek());
                    op.pop();
                }
                op.pop();
            }
            else{
                if(c=='-'||c=='+'){
                    String v1=st.pop();
                    String v2=st.pop();
                    st.push(v2+v1+op.peek());
                    op.pop();
                    op.push(c+"");
                }
                else if((c=='*'&&!op.peek().equals("/"))||(c=='/'&&!op.peek().equals("*"))){
                    op.push(c+"");
                }
                else {
                    String v1=st.pop();
                    String v2=st.pop();
                    st.push(v2+v1+op.peek());
                    op.pop();
                    op.push(c+"");
                }
            }
        }
        while(st.size()>1){
            String v1=st.pop();
            String v2=st.pop();
            st.push(v2+v1+op.peek());
            op.pop();
        }
        return st.peek();
    }
    //wap for prefix expression:
    static String prefixExpression(String str){
        Stack<String> st=new Stack<>();
        Stack<String> op=new Stack<>();
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(c>=48&&c<=57){
                st.push(c+"");
            }
            else if(op.isEmpty()||c=='('||op.peek().equals("(")){
                op.push(c+"");
            }
            else if(c==')'){
                while(!op.peek().equals("(")){
                    String v1=st.pop();
                    String v2=st.pop();
                    st.push(op.peek()+v2+v1);
                    op.pop();
                }
                op.pop();
            }
            else{
                if(c=='-'||c=='+'){
                    String v1=st.pop();
                    String v2=st.pop();
                    st.push(op.peek()+v2+v1);
                    op.pop();
                    op.push(c+"");
                }
                else if((c=='*'&&!op.peek().equals("/"))||(c=='/'&&!op.peek().equals("*"))){
                    op.push(c+"");
                }
                else {
                    String v1=st.pop();
                    String v2=st.pop();
                    if(op.peek().equals("/") )st.push("/"+v2+v1);
                    else if(op.peek().equals("*")) st.push("*"+v2+v1);
                    op.pop();
                    op.push(c+"");
                }
            }
        }
        while(st.size()>1){
            String v1=st.pop();
            String v2=st.pop();
            st.push(op.peek()+v2+v1);
            op.pop();
        }
        return st.peek();
    }
    //program for single digits:
    static int infixExpression(String str){
        Stack<Integer> st=new Stack<>();
        Stack<Character> op=new Stack<>();
        //Traversing a expression
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            int ascii=(int)ch;
            if(ascii>=48 && ascii<=57){
                int x=ch-'0';
                st.push(x);
            }
            else if(op.isEmpty()||ch=='('||op.peek()=='('){
                op.push(ch);
            }
            else if(ch==')'){
                while(op.peek()!='('){
                    int v1=st.pop();
                    int v2=st.pop();
                    switch(op.peek()) {
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
                    op.pop();
                }
                op.pop();//removal of '(' from op stack:
            }
            else{
                if(ch=='-'||ch=='+'){
                    int v1=st.pop();
                    int v2=st.pop();
                    if(op.peek()=='+')      st.push(v1+v2);
                    else if(op.peek()=='-') st.push(v2-v1);
                    else if(op.peek()=='*') st.push(v1*v2);
                    else if(op.peek()=='/') st.push(v2/v1);
                    op.pop();
                    op.push(ch);
                }
                else if((ch=='*'&&op.peek()!='/')||(ch=='/'&&op.peek()!='*')){
                    op.push(ch);
                }
                else{
                    int v1=st.pop();
                    int v2=st.pop();
                    if(op.peek()=='*') st.push(v1*v2);
                    else if(op.peek()=='/') st.push(v2/v1);
                    op.pop();
                    op.push(ch);
                }
            }
        }
        //val stack:
        while(st.size()>1&&op.size()>0){
            int v1=st.pop();
            int v2=st.pop();
            switch(op.peek()) {
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
            op.pop();
        }
        return st.peek();
    }

}
