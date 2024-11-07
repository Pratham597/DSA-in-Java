package dynamicProgramming;

import java.util.Arrays;

public class dyamicPartition {
    //Matrix chain multiplication
    public static int f(int i,int j,int [] arr,int [][] dp){
        if(i==j) return 0;
        if(dp[i][j]!=0) return dp[i][j];
        int ans=(int)1e9;
        for(int k=i;k<=j-1;k++){
            int left= arr[i-1]*arr[k]*arr[j] +f(i,k,arr,dp)+f(k+1,j,arr,dp);
            ans=Math.min(ans,left);
        }
        return dp[i][j]=ans;
    }
    //Tabulation for matrix chain multiplication.
    public static int matrixMultiplication(int[] arr, int N) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int ans = (int) 1e9;
                for (int k = i; k <= j - 1; k++) {
                    int left = arr[i - 1] * arr[k] * arr[j] + dp[i][k] + dp[k + 1][j];
                    ans = Math.min(ans, left);
                }
                dp[i][j] = ans;
            }
        }
        return dp[1][n - 1];
    }
    public int fmincost(int i,int j,int [] cuts,int[][] dp){
        if(i>j) return 0;
        int min=(int) 1e9;
        if(dp[i][j]!=0) return dp[i][j];
        for(int k=i;k<=j;k++){
            min=Math.min(min,cuts[j+1]-cuts[i-1]+fmincost(i,k-1,cuts,dp)+fmincost(k+1,j,cuts,dp));
        }
        return dp[i][j]=min;
    }
    public int minCost(int n, int[] cuts) {
        int c=cuts.length;
        int [] arr=new int[c+2];
        int [][] dp=new int[c+2][c+2];
        arr[0]=0;
        arr[c+1]=n;
        for(int i=1;i<=c;i++){
            arr[i]=cuts[i-1];
        }
        Arrays.sort(arr);
        return f(1,c,arr,dp);
    }
    public static int cost(int n, int d, int cuts[]) {
        int c = cuts.length;
        int[] arr = new int[c + 2];
        int[][] dp = new int[c + 2][c + 2];
        arr[0] = 0;
        arr[c + 1] = n;
        for (int i = 1; i <= c; i++) {
            arr[i] = cuts[i - 1];
        }
        Arrays.sort(arr);

        for (int i = c; i > 0; i--) {
            for (int j = i; j <= c; j++) {
                int min=Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, arr[j + 1] - arr[i - 1] +dp[i][k-1]+ dp[k+1][j]);
                }
                dp[i][j] = min;
            }
        }
        return dp[1][c];
    }
    public static int f(int i,int j,int [] arr){
        if(i>j) return 0;
        int ans=(int)(-1e9);
        for(int k=i;k<=j;k++){
            int left=(k-1>=0)?arr[k-1]:1;
            int right=(k+1<arr.length)?arr[k+1]:1;
            int ele=arr[k];
            arr[k]=right;
            int leftans=f(i,k-1,arr);
            arr[k]=left;
            int rightans=f(k+1,j,arr);
            arr[k]=ele;
            ans=Math.max(ans,ele*left*right+leftans+rightans);
        }
        return ans;
    }
    public static int maxCoins(int a[]) {
        int n=a.length;
        return f(0,n-1,a);
    }
    public static int singleNumber(int[] nums) {
        long and=1;
        long or=0;
        long xor=0;
        for(int i=0;i<nums.length;i++){
            and=and &nums[i];
            or=or|nums[i];
            xor=xor^nums[i];
        }
        // x+y :-
        long sxy=and+or;
        //2xy
        long pxy=(sxy-xor);
        // x-y:-
        long subxy= (long) Math.sqrt((sxy*sxy)-(2*pxy));
        // x
        long x=(sxy+subxy)/2;
        long y=(sxy-x);
        System.out.println(x+" "+y);
        return((xor ^ x)==0 )?(int)y: (int) x;
    }
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 3, 2, 4, 4, 4, 5, 5, 5, 7, 7, 7}));
    }
}
