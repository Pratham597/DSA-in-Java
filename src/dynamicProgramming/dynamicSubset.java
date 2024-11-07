package dynamicProgramming;
import java.util.Arrays;
import java.util.HashMap;

public class dynamicSubset {
    public static boolean f(int target,int idx,int [] arr,boolean [] dp){
        if(target==0) return true;
        if(idx<0) return false;
        if(!dp[target]) {
            System.out.println(target);
            return dp[target];
        }
        int ele=arr[idx];
        boolean left=(target-ele >= 0)? f(target-ele,idx-1,arr,dp):false;
        if(left) return left;
        return dp[target]=f(target,idx-1,arr,dp);
    }
    public static boolean subsetSumToK(int n, int target, int [] arr){
        boolean [] dp=new boolean[target+1];
        Arrays.fill(dp,true);
        if(target==0){
            for(int i=0;i<n;i++){
                if(arr[i]==0) return true;
            }
            return false;
        }
        boolean check= f(target,n-1,arr,dp);
        System.out.println(Arrays.toString(dp));
        return check;
    }
    public static boolean subsetSumToKTab(int n, int target, int arr[]){
        boolean [][] dp=new boolean[n][target+1];
        for(int i=0;i<n;i++) dp[i][0]=true;
        if(arr[0]<=target) dp[0][arr[0]]=true;
        for(int i=1;i<n;i++){
            for(int j=1;j<=target;j++){
                int ele=arr[i];
                boolean left=(j-ele >= 0)? dp[i-1][j-ele]:false;
                dp[i][j]=dp[i-1][j]||left;
            }
        }
        return dp[n-1][target];
    }
    public static int f(int n,int sum,int arrsum,int [] arr,int [][] dp){
        if(n==0) {
            int sum1=sum+arr[n];//present subset me hai!
            int sum2=sum;//Ya nahi hai!
            // return min abs diff.
            int abs1=Math.abs(arrsum-2*sum1);
            int abs2=Math.abs(arrsum-2*sum2);
            return Math.min(abs1,abs2);
        }
        if(dp[n][sum]!=-1) return dp[n][sum];
        int diff1=f(n-1,sum+arr[n],arrsum,arr,dp);
        int  diff2=f(n-1,sum,arrsum,arr,dp);
        return dp[n][sum]=Math.min(diff1,diff2);
    }
    public static int minSubsetSumDifference(int []arr, int n) {
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
        }
        int [][] dp=new int[n][sum+1];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        return f(n-1,0,sum,arr,dp);
    }
    public static int minSubsetSumDifferenceOptimised(int []arr, int n) {
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
        }
        //sum -> boundary of j(j ka n-1 ele h)
        int [][] dp=new int[n][sum+1];
        for(int j=0;j<=sum;j++){
            dp[0][j]=(j+arr[0]<=sum)?Math.abs(sum-2*(j+arr[0])):Math.abs(sum-2*j);
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<=sum;j++){
                int diff1=(j+arr[i]<=sum)?dp[i-1][j+arr[i]]:Integer.MAX_VALUE;
                int  diff2=dp[i-1][j];
                dp[i][j]=Math.min(diff1,diff2);
            }
        }
        return dp[n-1][0];
    }
    public static int minSubsetSumDifferenceOptimised2(int []arr, int n) {
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
        }
        //sum -> boundary of j(j ka n-1 ele h)
        int [] dp=new int[sum+1];
        for(int j=0;j<=sum;j++){
            dp[j]=(j+arr[0]<=sum)?Math.abs(sum-2*(j+arr[0])):Math.abs(sum-2*j);
        }
        for(int i=1;i<n;i++){
            int [] curr=new int[sum+1];
            for(int j=0;j<=sum;j++){
                int diff1=(j+arr[i]<=sum)?dp[j+arr[i]]:Integer.MAX_VALUE;
                int  diff2=dp[j];
                curr[j]=Math.min(diff1,diff2);
            }
            dp=curr;
        }
        return dp[0];
    }
    static int mod=(int)(1e9+7);
    public static int f( int n,int tar,int [] arr,int [][] dp){
        if(n==0){
            return (0==tar || tar-arr[n]==0 )?(arr[n]==0)?2:1:0;
        }
        if(dp[n][tar]!=0) return dp[n][tar];
        int  left=(tar-arr[n]>=0)?f(n-1,tar-arr[n],arr,dp)%mod:0;
        int right=f(n-1,tar,arr,dp)%mod;
        return dp[n][tar]=(left+right)%mod;
    }
    public static int findWays(int num[], int tar) {
        int n=num.length;
        int [][] dp=new int[n][tar+1];
        return f(num.length-1,tar,num,dp);
    }
    public static int findWaysOptmised(int num[], int tar) {
        int n=num.length;
        int mod=(int)(1e9+7);
        int [] dp=new int[tar+1];
        for(int j=0;j<=tar;j++){
            dp[j]=(0==j || j-num[0]==0 )?(num[0]==0)?2:1:0;
        }
        for(int i=1;i<n;i++){
            int [] curr=new int[tar+1];
            for(int j=0;j<=tar;j++){
                int  left=(j-num[i]>=0)?dp[j-num[i]]%mod:0;
                int right=dp[j]%mod;
                curr[j]=(left+right)%mod;
            }
            dp=curr;
        }
        return dp[tar];
    }
    static int f(int n,int tar,int [] w,int []v,int[][] dp){
        if(n==0){
            return (tar-w[0]>=0)?v[0]:0;
        }
        if(dp[n][tar]!=0) return dp[n][tar];
        int left=(tar-w[n]>=0)?f(n-1,tar-w[n],w,v,dp)+v[n]:0;
        int right=f(n-1,tar,w,v,dp);
        return dp[n][tar] =Math.max(left,right);
    }
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int [][] dp=new int[n][maxWeight+1];
        return f(n-1,maxWeight,weight,value,dp);
    }
    static int knapsackOptimised(int[] w, int[] v, int n, int tar) {
        int [] dp=new int[tar+1];
        for(int j=0;j<=tar;j++){
            dp[j]=(j-w[0]>=0)?v[0]:0;
        }
        for(int i=1;i<n;i++){
            int [] curr=new int[tar+1];
            for(int j=0;j<=tar;j++){
                int left=(j-w[i]>=0)?dp[j-w[i]]+v[i]:0;
                int right=dp[j];
                curr[j] =Math.max(left,right);
            }
            dp=curr;
        }
        return dp[tar];
    }
    public static int fmax(int n,int x,int [] arr,int [][] dp){
        if(n==0){
            return (x%arr[0]==0)?x/arr[0]:Integer.MAX_VALUE;
        }
        if(dp[n][x]!=0) return dp[n][x];
        int left=(x-arr[n]>=0)?fmax(n,x-arr[n],arr,dp):Integer.MAX_VALUE;
        if(left!=Integer.MAX_VALUE) left+=1;
        int right=fmax(n-1,x,arr,dp);
        return dp[n][x]=Math.min(left,right);
    }
    public static int minimumElements(int num[], int x) {
        int [][] dp=new int[num.length][x+1];
        int ans=fmax(num.length-1,x,num,dp);
        return (ans==Integer.MAX_VALUE)?-1:ans;
    }
    public static int minimumElementsOptimised(int arr[], int x) {
        int n=arr.length;
        int [] dp=new int[x+1];
        for(int j=0;j<=x;j++){
            dp[j]=(j%arr[0]==0)?j/arr[0]:Integer.MAX_VALUE;
        }
        for(int i=1;i<n;i++){
            int [] curr=new int[x+1];
            for(int j=0;j<=x;j++){
                int left=(j-arr[i]>=0)?curr[j-arr[i]]:Integer.MAX_VALUE;
                if(left!=Integer.MAX_VALUE) left+=1;
                int right=dp[j];
                curr[j]=Math.min(left,right);
            }
            dp=curr;
        }
        return (dp[x]==Integer.MAX_VALUE)?-1:dp[x];
    }
    public static int f(int n, int tar, int []arr, HashMap<Integer,Integer>[] dp){
        if(n==0){
            if(arr[n]==0 &&tar==0) return 2;
            if(tar+arr[n]==0||tar-arr[n]==0) return 1;
            return 0;
        }
        if(dp[n].containsKey(tar)){
            return dp[n].get(tar);
        }
        int left=f(n-1,tar+arr[n],arr,dp);
        int right=f(n-1,tar-arr[n],arr,dp);
        dp[n].put(tar,left+right);
        return left+right;
    }
    public static int targetSum(int n, int target, int[] arr) {
        HashMap<Integer,Integer> [] dp=new HashMap[n];
        for(int i=0;i<n;i++){
            dp[i]=new HashMap<>();
        }
        return f(n-1,target,arr,dp);
    }
    public static long countWaysToMakeChange(int arr[], int x){
        long [][] dp=new long[arr.length][x+1];
        return f(arr.length-1,x,arr,dp);
    }
    public static  long f(int n,int x,int []arr,long [][] dp){
        if(n==0){
            return (x%arr[n]==0)?1:0;
        }
        if(dp[n][x]!=0) return dp[n][x];
        long left=(x-arr[n]>=0)?f(n,x-arr[n],arr,dp):0;
        long right=f(n-1,x,arr,dp);
        return dp[n][x]= left+right;
    }
    public static long countWaysToMakeChangeOpyimised(int arr[], int x){
        int n=arr.length;
        long [] dp=new long[x+1];
        for(int i=0;i<=x;i++){
            dp[i]=(i%arr[0]==0)?1:0;
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<=x;j++){
                long left=(j-arr[i]>=0)?dp[j-arr[i]]:0;
                long right=dp[j];
                dp[j]= left+right;
            }
        }
        return dp[x];
    }
    public static int fcutRod(int n,int tar,int [] price,int [][] dp){
        if(n==0){
            return tar*price[0];
        }
        if(dp[n][tar]!=0) return dp[n][tar];
        int left=(tar-n-1>=0)?fcutRod(n,tar-n-1,price,dp)+price[n]:0;
        int right=fcutRod(n-1,tar,price,dp);
        return dp[n][tar]=Math.max(left,right);
    }
    public static int cutRodOptimised(int price[], int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i * price[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                int left = (j - i - 1 >= 0) ? dp[j - i - 1] + price[i] : 0;
                int right = dp[j];
                dp[j] = Math.max(left, right);
            }
        }
        return dp[n];
    }
    public  int f(int i,int jump,int [] arr,int[] dp){
        if(i==arr.length) return 0;
        if(dp[i]!=-1) return dp[i];
        int sum=0;
        int count=1;
        int max=arr[i];
        for(int k=i;k<i+jump&&k<arr.length;k++){
            max=Math.max(max,arr[k]);
            sum=Math.max(max*count+f(k+1,jump,arr,dp),sum);
            count++;
        }
        return dp[i]=sum;
    }
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n=arr.length;
        int [] dp=new int[n];
        Arrays.fill(dp,-1);
        return f(0,k,arr,dp);
    }
    public static void main(String[] args) {

    }
}
