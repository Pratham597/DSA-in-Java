package stack;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class problems {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int [] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        System.out.println(histogram(arr));
    }
    //wap to find the largest area of given histogram -O(n2)
    static int histogram(int[] arr){
        int n=arr.length;
        Stack<Integer> st=new Stack<>();
        int ans=arr[0];
        for(int i=0;i<n;i++){
            st.push(arr[i]);
            for(int j=i+1;j<n;j++){
                if(arr[j]<st.peek()){
                    st.pop();
                    st.push(arr[j]);
                }
                int x=st.peek()*(j-i+1);
                if(ans<x) ans=x;
            }
            st.pop();
        }
        return ans;
    }
    //Wap to find previous greater element array:
    static int [] previousGreater(int [] arr){
        Stack<Integer> st =new Stack<>();
        int ans[] =new int[arr.length];
        ans[0]=-1;
        st.push(arr[0]);
        for(int i=1;i<arr.length;i++){
            while(st.size()>0&&arr[i]>st.peek()){
                st.pop();
            }
            if(st.isEmpty())
                ans[i]=-1;
            else ans[i]=st.peek();
            st.push(arr[i]);
        }
        return ans;
    }
//    static ArrayList<Integer> nextRec(int[] arr, int idx){
//        ArrayList<Integer> ans=new ArrayList<>();
//        if(idx==arr.length) return ans;
//        if(idx==arr.length-1){
//            ans.addFirst(-1);
//            return ans;
//        }
//        ArrayList<Integer> temp=nextRec(arr,idx+1);
//        for(int i=idx+1;i<arr.length;i++){
//            if(arr[idx]<arr[i])
//                ans.addFirst(arr[i]);
//        }
//        ans.addAll(temp);
//        return ans;
//    }
    //wap to find array of stockarray:
    static int[] stockArray(int [] arr){
        // 100 80 60 70 60 75 85 -> Que
        // 1 1 1 2 1 4 6 -> ans
        // code
        Stack<Integer> st=new Stack<>();
        int n=arr.length;
        int [] ans=new int[n];
        ans[0]=1;
        st.push(0);
        for(int i=1;i<n;i++){
            while(st.size()>0&&arr[i]>arr[st.peek()]){
                st.pop();
            }
            ans[i]=i-st.peek();
            st.push(i);
//          System.out.println(st);
        }
        return ans;
    }
    //wap to find greater element array using stacks:-O(n) time complexity
    //Algorithm - pop mark and push
    static int[] greaterelement(int[] arr){
        Stack<Integer> st=new Stack<>();
        int n=arr.length;
        st.push(arr[n-1]);
        int ans[]=new int[n];
        ans[n-1]=-1;
        for(int i=n-2;i>=0;i--){
            while(st.size()>0&&arr[i]>st.peek()){
                    st.pop();
            }
            if(st.isEmpty()){
                    ans[i]=-1;
            }
            else ans[i]=st.peek();
            st.push(arr[i]);
            }
            return ans;
    }
    //second approach
    static int[] greaterelement2(int [] arr){
        Stack<Integer> st=new Stack<>();
        int n=arr.length;
        st.push(0);
        int [] ans = new int[arr.length];
        for(int i=1;i<n;i++){
            ans[i]=-1;
            while(st.size()>0&&arr[i]>arr[st.peek()]){
                ans[st.peek()]=arr[i];
                st.pop();
            }
            st.push(i);
        }
        return ans;
    }
    //Wap to find greater element array using arrays;
    static int[] greaterElement(int[] arr){
        //1-3-2-1-8-6-3-4
        //ans 3-8-8-8 -1 -1 4 -1
        int [] ans=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            ans[i]=-1;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]>arr[i]){
                    ans[i]=arr[j];
                    break;
                }
            }
        }
        return ans;
    }
    //Wap to remove consecutive subsequences:
    static int[] subsequences(int[] arr){
        // 1-2-2-2-3-3-3-2-4-4-5
        // ans 1-2-5
        if(arr.length==0) return arr;
        Stack<Integer> st=new Stack<>();
        st.push(arr[0]);
        for(int i=1;i<arr.length;i++){
            if(!st.isEmpty()&&arr[i]==st.peek()) st.pop();//pop the element from stack when it is same.
            else if(arr[i-1]!=arr[i]) st.push(arr[i]);//check before pushing elements into stack.
        }
        int [] ans=new int [st.size()];
        int i=ans.length-1;
        while(st.size()>0){
            ans[i]=st.pop();
            i--;
        }
        return ans;
    }
    //Wap to find out given string is balanced or not using stack
    static boolean balancedBrackets(String str){
        //(-> true && )->false
        int x=str.length();
        Stack<Boolean> st=new Stack<Boolean>();
        for(int i=0;i<x;i++){
            if(str.charAt(i)=='(') st.push(true);
            else{
                if(st.isEmpty()) return false;
                else if(st.peek()) st.pop();

            }
        }
        if(st.isEmpty()) return true;
        return false;
    }
    static int removeBrackets(String str){
        int x=str.length();
        Stack<Boolean> st=new Stack<Boolean>();
        for(int i=0;i<x;i++){
            if(str.charAt(i)=='(') st.push(true);
            else{
                if(st.size()!=0&&st.peek()) st.pop();
                else st.push(false);
            }
        }
        return st.size();
    }
}
