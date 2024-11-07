package dynamicProgramming;

public class dynamic1D {
    static int fibonacciUsingDp(int n,int []dp){
     if(n==0||n==1) return n;
     else if(dp[n]!=-1) return dp[n];
     dp[n]=fibonacciUsingDp(n-1,dp)+fibonacciUsingDp(n-2,dp);
     return dp[n];
    }
    static int climbStairsUsingDp(int n,int x,int [] dp){
        if(n==x){
            return 1;
        }
        if(dp[n]!=-1) return dp[n];
        int ans1=climbStairsUsingDp(n+1,x,dp);
        if(n==x-1) return dp[n]=ans1;
        int ans2=climbStairsUsingDp(n+2,x,dp);
        return dp[n]=ans1+ans2;
    }
    static int climbStairs(int n,int x,String ans){
        if(n==x){
            System.out.println(ans);
            return 1;
        }
        int ans1=climbStairs(n+1,x,ans+"One");
        if(n==x-1) return ans1;
        int ans2=climbStairs(n+2,x,ans+"two");
        return ans1+ans2;
    }
    static int fibonacci(int n){
        if(n==0) return 0;
        if(n==1) return 1;
        return fibonacci(n-1)+fibonacci(n-2);
    }

    static int frogJump(int idx,int [] arr){
        if(idx==arr.length-1){
            return 0;
        }
        int ans=Math.abs(arr[idx]-arr[idx+1])+frogJump(idx+1,arr);
        if(idx==arr.length-2) return ans;
        int ans1=Math.abs(arr[idx]-arr[idx+2])+frogJump(idx+2,arr);
        return Math.min(ans,ans1);
    }
    //wap to solve a frog jump using memotization:
    //time->O(n) and space->O(2n);
    static int frogjumpUsingDp(int idx,int [] arr,int[] dp){
        if(idx==arr.length-1){
            return 0;
        }
        if(dp[idx]!=-1) return dp[idx];
        int ans=Math.abs(arr[idx]-arr[idx+1])+frogjumpUsingDp(idx+1,arr,dp);
        if(idx==arr.length-2) return dp[idx]=ans;
        int ans1=Math.abs(arr[idx]-arr[idx+2])+frogjumpUsingDp(idx+2,arr,dp);
        return dp[idx]=Math.min(ans,ans1);
    }
    //wap to solve a problem using tabulation means from(bottom to top)
    //time->O(n) and space->O(n);
    static int frogJumpTabulation(int[] arr){
        int n=arr.length;
        int[] dp=new int[n];
        dp[n-1]=0;
        int right=Integer.MAX_VALUE;
        for(int i=n-2;i>=0;i--){
            int left=Math.abs(arr[i]-arr[i+1])+ dp[i+1];
            if(i<n-2)  right=Math.abs(arr[i]-arr[i+2])+dp[i+2];
            dp[i]=Math.min(left,right);
        }
        return dp[0];
    }
    // wap to solve same problem without using dp array:
    //time->O(n) and space->O(1);
    static int frogJumpOptimised(int[] arr){
        int n=arr.length;
        int right=Integer.MAX_VALUE;
        int ans=0;//represent -> dp[i+2] means store previous value before updating nextans:
        int nextans=0;//represent -> dp[i+1];
        for(int i=n-2;i>=0;i--){
            int left=Math.abs(arr[i]-arr[i+1])+nextans;
            if(i<n-2) right=Math.abs(arr[i]-arr[i+2])+ans;
            ans=nextans;
            nextans=Math.min(left,right);
        }
        return nextans;
    }
    static int frogKJumpUsingDp(int[] arr,int k,int idx,int[] dp){
        if(idx==arr.length-1) {
            return 0;
        }
        if(dp[idx]!=0) return dp[idx];
        int ans=Integer.MAX_VALUE;
        for(int i=1;i<=k;i++){
            if((idx+i)<arr.length){
                int ans1=Math.abs(arr[idx]-arr[idx+i])+frogKJumpUsingDp(arr,k,idx+i,dp);
                ans=Math.min(ans1,ans);
            }
        }
        return dp[idx]=ans;
    }
    static int frogKJump(int[] arr,int k,int idx,String str){
        if(idx==arr.length-1) {
            System.out.println(str);
            return 0;
        }
        int ans=Integer.MAX_VALUE;
        for(int i=1;i<=k;i++){
            if((idx+i)<arr.length){
                char c= (char) ('0'+i);
                int ans1=Math.abs(arr[idx]-arr[idx+i])+frogKJump(arr,k,idx+i,str+c);
                ans=Math.min(ans1,ans);
            }
        }
        return ans;
    }
    static int frogKJumpUsingTabulation(int[]arr,int k){
        int n=arr.length;
        int []dp=new int[n];
        dp[n-1]=0;
        for(int i=n-2;i>=0;i--){
            int ans=Integer.MAX_VALUE;
            for(int j=1;j<=k;j++){
                if(i+j<n){
                    int ans1=Math.abs(arr[i]-arr[j-1])+dp[i+j];
                       ans=Math.min(ans,ans1);
                }
            }
            dp[i]=ans;
        }
        return dp[0];
    }
    static int maxSumAdj(int[] arr,int idx,String str){
        if(idx>=arr.length){
            System.out.println(str);
            return 0;
        }
        int ans=arr[idx]+maxSumAdj(arr,idx+2,str+arr[idx]);
        if(idx== arr.length-1) return ans;
        int ans1=arr[idx+1]+maxSumAdj(arr,idx+3,str+arr[idx+1]);
        return Math.max(ans,ans1);
    }
    static int maxSumAdjUsingDp(int[] arr,int idx,int[] dp){
        if(idx>=arr.length){
            return 0;
        }
        else if(dp[idx]!=-1) return dp[idx];
        int ans=arr[idx]+maxSumAdjUsingDp(arr,idx+2,dp);
        int ans1=maxSumAdjUsingDp(arr,idx+1,dp);
        return dp[idx]=Math.max(ans,ans1);
    }
    static int maxSumOptimisation(int[] arr){
        int n=arr.length;
        int ans=arr[n-1];
        int ans1=0;
        for(int i=n-2;i>=0;i--){
            ans1=ans1+arr[i];
            int x=Math.max(ans,ans1);
            ans1=ans;
            ans=x;
        }
        return ans;
    }
    static int maxSumTabulation(int[] arr){
        int n=arr.length;
        int [] dp=new int[n];
        dp[n-1]=arr[n-1];
        int ans=arr[n-2];
        for(int i=n-2;i>=0;i--){
            if(i+2<n){
                ans=arr[i]+dp[i+2];
            }
            int ans1=dp[i+1];
            dp[i]=Math.max(ans,ans1);
        }
//        for(int x:dp) System.out.println(x);
        return dp[0];
    }
    static int ninjasWorkOut(int [][] arr,int row,int k,String str){
        if(row==arr.length){
            System.out.println(str);
            return 0;
        }
        int ans=Integer.MIN_VALUE;
        for(int j=0;j<3;j++){
            if(k!=j){
                int ans1=arr[row][j]+ninjasWorkOut(arr,row+1,j,str+arr[row][j]+" ");
                ans=Math.max(ans,ans1);
            }
        }
        return ans;
    }
    static int ninjasUsingTabulation(int [][] arr){
        int n=arr.length;
        int [][]dp=new int [n][3];
        dp[n-1][0]=arr[n-1][0];
        dp[n-1][1]=arr[n-1][1];
        dp[n-1][2]=arr[n-1][2];
        for(int i=n-2;i>=0;i--){
            for(int j=0;j<3;j++){
                int ans=Integer.MIN_VALUE;
                for(int k=0;k<3;k++){
                    if(j==k) continue;
                    ans=Math.max(ans,dp[i+1][k]);
                }
                dp[i][j]=arr[i][j]+ans;
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return Math.max(dp[0][0],Math.max(dp[0][1],dp[0][2]));
    }

    static int ninjasWorkUsingDp(int [][] arr,int row , int k,int [][] dp){
        if(row==arr.length){
            return 0;
        }
        else if(dp[row][k]!=0) return dp[row][k];
        int ans=Integer.MIN_VALUE;
        for(int j=0;j<3;j++){
            if(k!=j){
                int ans1=arr[row][j]+ninjasWorkUsingDp(arr,row+1,j,dp);
                ans=Math.max(ans,ans1);
            }
        }
        return dp[row][k]=ans;
        //row->signifies the answer for that day and k is the  task of previous day which you have done.
    }
    static int ninjasWorkOptimisation(int [][] arr){
        int n=arr.length;
        //int [] dp=new int [3];
        for(int i=n-2;i>=0;i--){
            for(int j=0;j<3;j++){
                //int temp[]=new int [3];
                int ans=Integer.MIN_VALUE;
                for(int k=0;k<3;k++){
                    if(j==k) continue;
                    ans=Math.max(ans,arr[i+1][k]);
                    //ans=Math.max(ans,dp[k]);
                }
                arr[i][j]=arr[i][j]+ans;
                //temp[j]=arr[i][j]+ans;
                System.out.print(arr[i][j]+" ");
            }
            //dp=
            System.out.println();
        }
        return Math.max(arr[0][0],Math.max(arr[0][1],arr[0][2]));
    }
    public static void main(String[] args){
        int[][] arr={{10,50,1},{5,100,1},{40,20,100},{1,2,3}};
        int [][]dp=new int[arr.length][4];
       // System.out.println(ninjasWorkOut(arr,0,-1,""));
        //System.out.println(ninjasWorkUsingDp(arr,0,3,dp));
        System.out.println(ninjasUsingTabulation(arr));
        System.out.println(ninjasWorkOptimisation(arr));
    }
}
