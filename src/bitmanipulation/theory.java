package bitmanipulation;

import java.util.*;

public class theory {
    public static StringBuilder bs(int n){
        if(n==1||n==0) return new StringBuilder().append(n);
        return bs(n/2).append(n%2);
    }
    public static void swap(){
        int a=5;
        int b=6;
        a=a^b;
        b=a^b;//b->a
        a=a^b;//a->b
        System.out.println(a+" "+b);
    }
    public static boolean setBit(int n,int pos){
        int no=n>>pos;
        return (no&1)!=0;
    }
    public static int setKthBit(int N,int K){
        int n=1<<K;
        return N|n;
    }
    public static int clearKthBit(int N,int K){
        int n=~(1<<K);
        return n&N;
    }
    public static int toggleKthBit(int N,int K){
        int n=1<<K;
        return N^n;
    }
    public static int removeLastSetBit(int n){
        return n&n-1;
    }
    public static boolean powerof2(int n){
        return (n&n-1)==0;
    }

    public static int next(int [] arr,int idx){
        int st=idx+1;
        int end=arr.length-1;
        int ele=arr[idx];
        while(st<=end){
            int mid=st+(end-st)/2;
            if(arr[mid]>ele) end=mid-1;
            else st=mid+1;
        }
        return st;
    }
    public  static boolean f(int [] arr,int group){
        Arrays.sort(arr);
        int n=arr.length;
        int [] count=new int[n];
        for(int i=0;i<n;i++){
            if(count[i]==group-1) continue;
            int idx=i+1;
            while(idx<n && (count[idx]!=0|| arr[idx]==arr[i])){
                if(arr[idx]-arr[i]<=1) idx++;
                else return false;
            }
            if(idx<n && arr[idx]-arr[i]==1){
                count[idx]=count[i]+1;
            }
            else return false;
        }
        return true;
    }
    public static String f(String s){
        StringBuilder sb=new StringBuilder();
        int n=s.length();
        boolean [] arr=new boolean[n];
        for(int i=0;i<n;i++){
            if(arr[i]) continue;
            char c=s.charAt(i);
            sb.append(s.charAt(i));
            int idx=i+1;
            if(idx==n) {
                int check=0;
                while(check<n && sb.charAt(check)==c) {
                    check++;
                }
                sb.deleteCharAt(check);
            }
            while(idx<n && (s.charAt(idx)==c||arr[idx])) idx++;
            if(idx<n) arr[idx]=true;
            else break;
        }
        return sb.toString();
    }
    public static int f(int m,int m1,int n,int n1,int[] h, int[] v){
        if(m==m1){
            int sum=0;
            for(int i=n;i<n1-1;i++){
                sum+=v[i];
            }
            return sum;
        }
        if(n==n1){
            int sum=0;
            System.out.println(m+" "+m1);
            for(int i=m;i<m1-1;i++){
                sum+=h[i];
            }
            return sum;
        }
        int max=0;
        for(int i=n;i<n1-1;i++){
            int left=f(m,m1,n,i,h,v)+f(m,m1,i+1,n1,h,v)+v[i];
            max=Math.max(left,max);
        }
        for(int i=m;i<m1-1;i++){
            int right=f(m,i,n,n1,h,v)+f(i+1,m1,n,n1,h,v)+h[i];
            max=Math.max(max,right);
        }
        return max;
    }
    public static int minimumCost(int m, int n, int[] h, int[] v) {
        return f(0,m,0,n,h,v);
    }
    public static void main(String args[] ) throws Exception {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int [][] arr=new int[n][2];
        for(int i=0;i<n;i++){
            arr[i][0]=sc.nextInt();
            arr[i][1]=sc.nextInt();
        }
        boolean flag=false;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                long pi=arr[i][0];
                long di=arr[i][1];
                long spiti= pi+pi*di+pi*di+di;

                long pj=arr[j][0];
                long dj=arr[j][1];

                long spitj= pj+pj*dj+pj*dj+dj;

                System.out.println(spiti +" "+spitj);

                if(spiti+pi==pj && spitj+pj==pi){
                    flag=true;
                    break;
                }
            }
            if(flag) break;
        }
        if(flag) System.out.println("YES");
        else System.out.println("NO");
    }
}
