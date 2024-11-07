package dynamicProgramming;
import java.util.*;
public class dynamic1DPractice {
//          long startTime=System.nanoTime();
//        System.out.println(fiboTab(40));
//        long endTIme=System.nanoTime();

//          long startTime=System.nanoTime();
//        System.out.println(fibonacci(n,dp));
//        long endTIme=System.nanoTime();
//        System.out.println((endTIme-startTime)/(1e6));
    static int fibonacci(int n,int [] ans){
        if(n==0||n==1) return n;
        if(ans[n]!=1) return ans[n];
        return ans[n]=fibonacci(n-1,ans)+fibonacci(n-2,ans);
    }
    static int fiboTab(int n){
        int zero=0;
        int first=1;
        for(int i=2;i<=n;i++){
            int ans=first+zero;
            zero=first;
            first=ans;
        }
        return first;
    }
    static int climbStairs(int n){
        if(n==0||n==1) return 1;
        return climbStairs(n-1)+climbStairs(n-2);
    }
    static int climbStairsDp(int n,int [] dp){
        if(n==0||n==1) return 1;
        else if(dp[n]!=-1) return dp[n];
        return dp[n]=climbStairsDp(n-1,dp)+climbStairsDp(n-2,dp);
    }
    static long climbStairsTab(int n){
        long zero=1;
        long first=1;
        for(int i=2;i<=n;i++){
            long ans=zero+first;
            zero=first;
            first=ans;
        }
        return first;
    }
    static int frogJump(int idx,int n,int [] heights){
        if(idx==n-2||idx==n-1) {
            return Math.abs(heights[n-1]-heights[idx]);
        }
        int left=Math.abs(heights[idx]-heights[idx+1])+frogJump(idx+1,n,heights);
        int right=Math.abs(heights[idx]-heights[idx+2])+frogJump(idx+2,n,heights);

        return Math.min(left,right);
    }
    static int frogJumpMemo(int idx,int n,int [] heights,int [] dp){
        if(idx==n-2||idx==n-1) {
            return Math.abs(heights[n-1]-heights[idx]);
        }
        if(dp[idx]!=-1) return dp[idx];
        int left=Math.abs(heights[idx]-heights[idx+1])+frogJumpMemo(idx+1,n,heights,dp);
        int right=Math.abs(heights[idx]-heights[idx+2])+frogJumpMemo(idx+2,n,heights,dp);
        return dp[idx]=Math.min(left,right);
    }
    static int frogJumpTab(int n,int [] heights){
        int zero=0;
        if(n==1) return zero;
        int first=Math.abs(heights[n-1]-heights[n-2]);
        for(int i=n-2;i>0;i--){
            int left=Math.abs(heights[i-1]-heights[i])+first;
            int right=Math.abs(heights[i-1]-heights[i+1])+zero;

            int ans=Math.min(left,right);

            zero=first;
            first=ans;
        }
        return first;
    }
    static int frogKJump(int idx,int k,int n,int [] heights,int [] dp){
        int ans=Integer.MAX_VALUE;
        if(idx==n-1) return 0;//dp[n-1]=0; initially ans marked zero for index n-1;
        if(dp[idx]!=-1) return dp[idx];
        for(int i=1;i<=k;i++){
            if(idx+i<n){
                int left=Math.abs(heights[idx]-heights[idx+i])+frogKJump(idx+i,k,n,heights,dp);
                ans=Math.min(ans,left);
            }
        }
        return dp[idx]=ans;
    }
    public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
        return maximumSpaceOptimised(nums);
    }
    static int maximum(int idx,ArrayList<Integer> nums, int [] dp){

        if(dp[idx]!=-1) return dp[idx];
        int left=nums.get(idx);
        int right=nums.get(idx+1);

        if(idx+2<nums.size()) left+=maximum(idx+2,nums,dp);
        if(idx+3<nums.size()) right+=maximum(idx+3,nums,dp);

        return dp[idx]=Math.max(left,right);
    }
    public static int maximumTab(ArrayList<Integer> nums){
        int n=nums.size();
        int [] dp=new int[n];
        dp[0]=nums.get(0);
        for(int i=1;i<n;i++){
            int pick=nums.get(i);
            if(i-2>=0) pick+=dp[i-2];
            int right=dp[i-1];

            dp[i]=Math.max(pick,right);
        }
        System.out.println(Arrays.toString(dp));
        return dp[n-1];
    }
    public static int maximumSpaceOptimised(ArrayList<Integer> nums){
        int n=nums.size();

        int first=0;
        int zero=nums.get(0);
        for(int i=1;i<n;i++){
            first+=nums.get(i);
            int ans=Math.max(first,zero);
            first=zero;
            zero=ans;
        }
        return zero;
    }
    public static void main(String[] args) {
        int [] heights={9,9,8,11,5};
        ArrayList<Integer> arr=new ArrayList<>();
        for(int x:heights){
            arr.add(x);
        }
        System.out.println(maximumNonAdjacentSum( arr));
    }
}
