package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class dynamicLIS {
    //Recursive way to code lis
    public static int fLIS(int n,int next,int [] arr,int [][] dp){
        if(n==0){
            int prev=(next==arr.length)?Integer.MAX_VALUE:arr[next];
            return (arr[0]<prev)?1:0;
        }
        if(dp[n][next]!=0) return dp[n][next];
        int prev=(next==arr.length)?Integer.MAX_VALUE:arr[next];
        int left=(arr[n]<prev)?fLIS(n-1,n,arr,dp)+1:0;
        int right=fLIS(n-1,next,arr,dp);
        return dp[n][next]=Math.max(left,right);
    }
    public static int longestIncreasingSubsequence(int arr[]) {
        int n=arr.length;
        int []dp=new int[n+1];
        int [] curr=new int[n+1];
        for(int next=0;next<=n;next++){
            int prev=(next==n)?Integer.MAX_VALUE:arr[next];
            dp[next]=(arr[0]<prev)?1:0;
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<=n;j++){
                int prev=(j==n)?Integer.MAX_VALUE:arr[j];
                int left=(arr[i]<prev)?dp[i]+1:0;
                int right=dp[j];
                curr[j]=Math.max(left,right);
            }
            int [] temp=curr;
            curr=dp;
            dp=temp;
        }
        return dp[n];
    }
    public static int longestIncreasingSubsequenceTabulated(int arr[]) {
        int n=arr.length;
        int [] dp=new int[n];
        int ans=Integer.MIN_VALUE;
        Arrays.fill(dp,1);
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                if(arr[j]<arr[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            ans=Math.max(ans, dp[i]);
        }
        return ans;
    }
    //Longest Increasing Subset.
    public List<Integer> largestDivisibleSubset(int[] arr) {
        Arrays.sort(arr);
        int n=arr.length;
        int [] ans=new int[n];
        int [] hash=new int[n];
        int max=0;
        int idx=0;

        for(int i=0;i<n;i++){
            ans[i]=1;
            hash[i]=i;
            for(int j=0;j<i;j++){
                if(arr[i]%arr[j]==0 &&ans[j]+1>ans[i]){
                    ans[i]=ans[j]+1;
                    hash[i]=j;
                }
            }
            if(ans[i]>max){
                max=ans[i];
                idx=i;
            }
        }
        List<Integer> list=new ArrayList<>();
        list.add(arr[idx]);
        while(hash[idx]!=idx){
            idx=hash[idx];
            list.add(arr[idx]);
        }
        // Collections.reverse(list);
        return list;
    }
    public static int[] f(int [] arr){
        int n=arr.length;
        int [] ans=new int[n];
        for(int i=0;i<n;i++){
            ans[i]=1;
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j] && ans[j]+1>ans[i]){
                    ans[i]=ans[j]+1;
                }
            }
        }
        return ans;
    }
    public static void reverse(int [] arr){
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            // Swapping elements at start and end indices
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            // Moving towards the center of the array
            start++;
            end--;
        }
    }
    public static int LongestBitonicSequence(int n, int[] arr) {
        int [] inc=f(arr);
        reverse(arr);
        int [] dec=f(arr);
        int max=0;
        for(int i=0;i<n;i++){
            if(inc[i]>1 &&dec[n-i-1]>1){
                max=Math.max(max,inc[i]+dec[n-i-1]-1);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        
    }
}
