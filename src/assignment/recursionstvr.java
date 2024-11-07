package assignment;

import java.util.*;

public class recursionstvr {
    static void printNames(int n){
        if(n==0) return;
        printNames(--n);
        System.out.println("Pratham"+n);
    }
    static void naturalNumbers(int n){
        if(n<=0) return;
        naturalNumbers(n-1);
        System.out.println(n);
    }
    static int  sumOfNNumbers(int n){
        if(n<=0) return 0;
        return sumOfNNumbers(n-1)+n;
    }
    static int factorial(int n){
        if(n==0) return 1;
        return n*factorial(n-1);
    }
    static void reverse(int [] arr,int idx){
        int n=arr.length;
        if(idx==n/2) return;
        int temp=arr[idx];
        arr[idx]=arr[n-idx-1];
        arr[n-idx-1]=temp;
        reverse(arr,idx+1);
    }
    static int fibonacci(int n){
        if(n==1||n==2) return 1;
        return fibonacci(n-2)+fibonacci(n-1);
    }
    static void subsequence(String s ,String ans){
        if(s.isEmpty()) {
            System.out.println(ans);
            return;
        }
        char c=s.charAt(0);
        subsequence(s.substring(1),ans+c);
        subsequence(s.substring(1),ans);
    }
    //wap to find susbsequence of given array.
    static void subsequence2(int idx,int [] arr, ArrayList<Integer> ans){
        if(idx==arr.length){
            for(Integer x:ans){
                System.out.print(x+" ");
            }
            if(ans.size()==0) System.out.print("{}");
            System.out.println();
            return;
        }
        //add and leave method
        int ele=arr[idx];
        ans.add(ele);
        subsequence2(idx+1,arr,ans);
        ans.remove(ans.size()-1);
        subsequence2(idx+1,arr,ans);
    }
    //wap to find the sum of subsequence equals k.
    static boolean subsequenceEqualK(int idx,int sum,int [] arr,ArrayList<Integer> store,ArrayList<ArrayList<Integer>> ans){
        if(sum<0) return false;
        else if(sum==0){
            ArrayList<Integer> temp=new ArrayList<>(store);
            ans.add(temp);
            return true;
        }
        else if(idx==arr.length) return false;
        store.add(arr[idx]);
        boolean check1=subsequenceEqualK(idx+1,sum-arr[idx],arr,store,ans);
        if(check1) return true;
        store.remove(store.size()-1);
        return subsequenceEqualK(idx+1,sum,arr,store,ans);
    }
    static void subsequenceEqualK2(int idx, int sum, int[] arr, List<Integer> store, List<List<Integer>> ans){
        if(sum<0) return;
        else if(sum==0){
            List<Integer> temp=new ArrayList<>(store);
             ans.add(temp);
            return;
        }
        else if(idx==arr.length) return ;
        store.add(arr[idx]);
        subsequenceEqualK2(idx+1,sum-arr[idx],arr,store,ans);
        store.remove(store.size()-1);
        while(idx<arr.length-1&&arr[idx+1]==arr[idx]){
            idx++;
        }
        subsequenceEqualK2(idx+1,sum,arr,store,ans);
    }

    //wap fo find the no. of sub sequence
    static void subsequencerepetitvely(int idx,int sum,int [] arr,ArrayList<Integer> store,ArrayList<ArrayList<Integer>> ans){
        if(sum<0) return;
        else if(sum==0){
            ArrayList<Integer> temp=new ArrayList<>(store);
            ans.add(temp);
            return;
        }
        else if(idx==arr.length) return ;
        store.add(arr[idx]);
        subsequencerepetitvely(idx,sum-arr[idx],arr,store,ans);
        store.remove(store.size()-1);
        subsequencerepetitvely(idx+1,sum,arr,store,ans);

    }
    static boolean checkPallindrome(String str){
        if(str.isEmpty()||str.length()==1) return true;
        int n=str.length();
        char c1=str.charAt(0);
        char c2=str.charAt(n-1);
        return c1==c2&&checkPallindrome(str.substring(1,n-1));
    }
    static void sumOfSubset(int idx,int sum,int []arr,ArrayList<Integer> ans){
        if(idx==arr.length){
            ans.add(sum);
            return;
        }
        sumOfSubset(idx+1,sum,arr,ans);
        sumOfSubset(idx+1,sum+arr[idx],arr,ans);
    }
    public static void main(String[] args) {
        ArrayList<Integer> store=new ArrayList<>();
        int [] brr={1,2,3};
        sumOfSubset(0,0,brr,store);
        Collections.sort(store);
        for(Integer x:store) System.out.print(x+" ");
//        List<List<Integer>> ans= new ArrayList<>();
//        for(var v:ans){
//            for(Integer x:v){
//                System.out.print(x+" ");
//            }
//            System.out.println();
//        }
    }
}
