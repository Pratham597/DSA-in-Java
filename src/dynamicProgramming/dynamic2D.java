package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class dynamic2D {
    public static int ninjaHelper(int preWork,int idx,int n,int [][] points,int [][] dp){
        if(idx==n) return 0;
        if(preWork!=-1 && dp[idx][preWork]!=-1) {
            System.out.println(idx+" "+preWork);
            return dp[idx][preWork];
        }
        int ans=0;
        for(int i=0;i<3;i++){
            if(i==preWork) continue;
            ans=Math.max(ans,points[idx][i]+ninjaHelper(i,idx+1,n,points,dp));
        }
        if(preWork!=-1) dp[idx][preWork]=ans;
        return ans;
    }
    public static int ninjaTrainingTab(int n, int points[][]) {
        int [] dp=new int[3];
        for(int i=0;i<3;i++){
            int last=i;
            int ans=0;
            for(int j=0;j<3;j++){
                if(j==last) continue;
                ans=Math.max(ans,points[0][j]);
            }
            dp[last]=ans;
        }
        for(int idx=1;idx<n;idx++){
            int [] dp2=new int[3];
            for(int i=0;i<3;i++){

                int last=i;
                int ans=0;
                for(int  j=0;j<3;j++){
                    if(j==last) continue;
                    ans=Math.max(ans,points[idx][j]+dp[j]);
                }
                dp2[last]=ans;
            }
            dp=dp2;
        }
        int ans1=dp[0];
        int ans2=dp[1];
        int ans3=dp[2];
        return Math.max(ans1,Math.max(ans2,ans3));
    }
    public static int uniquePaths(int n,int m){
        if(n==0 && m==0) return 1;
        int left= (n - 1>= 0) ? uniquePaths(n - 1, m) : 0;
        int right=(m-1 >=0) ? uniquePaths(n,m-1):0;
        return left+right;
    }

    public static int uniquePathsTab(int n,int m){
        int [][] dp=new int[n][m];
        dp[0][0]=1;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0&&j==0) continue;
                int up=(i-1>=0)?dp[i-1][j]:0;
                int left=(j-1>=0)?dp[i][j-1]:0;
                dp[i][j]=up+left;
            }
        }
        return dp[n-1][m-1];
    }
    public static int uniquePathsSpace(int n,int m){
        int [] dp=new int[m];
        Arrays.fill(dp,1);
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                dp[j]+=dp[j-1];
            }
        }
        return dp[m-1];
    }
    static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        int div=(int) 1e9+7;
        int [][] dp=new int[n][m];
        dp[0][0]=1;
        int ans= uniqueBlock(n-1,m-1,mat,dp);
        return ans%div;
    }
    public static int uniqueBlock(int n,int m,ArrayList<ArrayList<Integer>> grids,int [][] dp){
        if(dp[n][m]!=0) return dp[n][m];
        if(grids.get(n).get(m)==-1) return 0;
        int left=(n-1>=0)? uniqueBlock(n-1,m,grids,dp):0;
        int right=(m-1>=0)? uniqueBlock(n,m-1,grids,dp):0;
        return dp[n][m]= left+right;
    }
    static int mazeObstaclesOptimised(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        int div=(int)1e9+7;
        int [] dp=new int[m];
        dp[0]=1;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0 &&j==0) continue;
                else if(mat.get(i).get(j)==-1) dp[j]=0;
                else{
                    int up=(i-1>=0)?dp[j]:0;
                    int left=(j-1>=0)?dp[j-1]:0;
                    dp[j]=(left+up)%div;
                }
            }
        }
        return dp[m-1];
    }
    public static int minSumPathMemo(int n,int m,int [][] grid,int [][]dp){
        if(dp[n][m]!=0) return dp[n][m];
        int left=(m-1>=0)?minSumPathMemo(n,m-1,grid,dp):Integer.MAX_VALUE;
        int up=(n-1>=0)?minSumPathMemo(n-1,m,grid,dp):Integer.MAX_VALUE;
        return dp[n][m]= grid[n][m]+Math.min(left,up);
    }
    public static int minSumPath(int[][] grid) {
        int n= grid.length;
        int m=grid[0].length;
        int [][] dp=new int[n][m];
        dp[0][0]=grid[0][0];// marking the base case of grid for zero zero.
        return minSumPathMemo(n-1,m-1,grid,dp);
    }
    public static int minSumPathTab(int[][] grid) {
        int n= grid.length;
        int m=grid[0].length;
        int [][] dp=new int[n][m];
        dp[0][0]=grid[0][0];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0 && j==0) continue;
                int left=(j-1>=0)?dp[i][j-1]:Integer.MAX_VALUE;
                int up=(i-1>=0)?dp[i-1][j]:Integer.MAX_VALUE;

                dp[i][j]=grid[i][j]+Math.min(left,up);
            }
        }
        return dp[n-1][m-1];
    }
    public static int minSumPathOptimised(int[][] grid) {
        int n= grid.length;
        int m=grid[0].length;
        int [] dp=new int[m];
        dp[0]=grid[0][0];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0 && j==0) continue;
                int left=(j-1>=0)?dp[j-1]:Integer.MAX_VALUE;
                int up=(i-1>=0)?dp[j]:Integer.MAX_VALUE;

                dp[j]=grid[i][j]+Math.min(left,up);
            }
        }
        return dp[m-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePathsSpace(3,2));
    }
}
