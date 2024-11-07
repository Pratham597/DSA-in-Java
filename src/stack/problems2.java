package stack;

import java.util.Scanner;
import java.util.Stack;

public class problems2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int [] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
//        for(int x:nextSmaller(arr)){
//            System.out.print(x+" ");
//        }
//        System.out.println();
        System.out.println(histogram(arr));
    }
    //wap to find the largest area of given histogram -O(n)
    static int histogram(int [] arr){
        int n=arr.length;
        int pS[]=previousSmaller(arr);
        int nS[]=nextSmaller(arr);
        int max=arr[0];
        for(int i=0;i<n;i++){
            int ans=(nS[i]-pS[i]-1)*arr[i];
            if(max<ans) max=ans;
        }
        return max;
    }
    static int[] previousSmaller(int []arr){
        int n=arr.length;
        int [] ans=new int [n];
        Stack<Integer> st= new Stack<>();
        st.push(0);
        ans[0]=-1;
        for(int i=1;i<n;i++){
            while(st.size()>0&&arr[i]<=arr[st.peek()]){
                st.pop();
            }
            if(st.isEmpty()) ans[i]=-1;
            else ans[i]=st.peek();
            st.push(i);
        }
        return ans;
    }
    static int[]nextSmaller(int [] arr){
        int n=arr.length;
        int ans[]=new int[n];
        Stack<Integer> st=new Stack<>();
        st.push(n-1);
        ans[n-1]=n;
        for(int i=n-2;i>=0;i--){
            while(st.size()>0&&arr[i]<=arr[st.peek()]){
                st.pop();
            }
            if(st.isEmpty()) ans[i]=n;
            else ans[i]=st.peek();
            st.push(i);
        }
        return ans;
    }
    static int trap(int[] arr) {
        Stack<Integer> st=new Stack<>();
        int n=arr.length;
        st.push(0);
        int [] prev=new int[n];
        prev[0]=0;
        int [] next=new int[n];
        for(int i=1;i<n;i++){
            while(!st.isEmpty()&&arr[i]>arr[st.peek()]){
                st.pop();
            }
            if(st.isEmpty()) prev[i]=-1;
            else prev[i]=st.peek();
            st.push(i);
        }
        st=new Stack<Integer>();
        st.push(n-1);
        next[n-1]=n-1;
        for(int i=n-2;i>=0;i--){
            while(!st.isEmpty()&&arr[i]>=arr[st.peek()]){
                st.pop();
            }
            if(st.isEmpty()) next[i]=i;
            else next[i]=st.peek();
            st.push(i);
        }
        int sum=0;
        for(int x:prev) System.out.print(x+" ");
        System.out.println();
        for(int x:next) System.out.print(x+" ");
        System.out.println();
        for(int i=0;i<n;i++){
            if(prev[i]==-1) {
                System.out.print(sum+" ");
                continue;
            }
            int pidx=prev[i];
            int nidx=next[i];
            int pval=arr[pidx];
            int nval=arr[nidx];
            int ans=Math.min(pval,nval);
            sum=sum+(nidx-pidx-1)*(ans-arr[i]);
            System.out.print(sum+" ");
        }
        return sum;
    }
}
