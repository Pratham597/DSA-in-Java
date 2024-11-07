package backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class problems2 {
    static void subset(int [] arr, int idx,ArrayList<Integer> temp,ArrayList<ArrayList<Integer>> ans){
        int n=arr.length;
        if(idx==n){
            ans.add(new ArrayList<>(temp));
            return;
        }
        int val=arr[idx];
        temp.add(val);
        subset(arr,idx+1,temp,ans);
        temp.remove(temp.size()-1);
        subset(arr,idx+1,temp,ans);
    }
    static void subans(int k, int n,int idx, List<Integer> arr,List<List<Integer>> ans){
        if(k==arr.size()&&n==0){
            for(int x:arr) System.out.print(x+" ");
            System.out.println();
            ans.add(new ArrayList<>(arr));
            return;
        }
        else if(n<=0) return;
        for(int i=idx;i<=9;i++){
            arr.add(i);
            subans(k,n-i,i+1,arr,ans);
            arr.remove(arr.size()-1);
        }
    }
    public static void main(String[] args) {
        List<Integer> arr=new ArrayList<>();
        List<List<Integer>> ans=new ArrayList<>();
        subans(4,1,1,arr,ans);
    }
}
