package dynamicProgramming;
import java.util.Arrays;
public class dynamicJagg2D {
    public static void dfs(int dist,int row,int col,int [][] dp,int [][] triangle){
        int [] delrow={-1,-1};
        int [] delcol={0,-1};
        for(int i=0;i<2;i++){
            int nrow=delrow[i]+row;
            int ncol=delcol[i]+col;
            if(nrow>=0 &&ncol>=0 && ncol<nrow+1){
                if(dist+triangle[nrow][ncol]<dp[nrow][ncol]){
                    dp[nrow][ncol]=dist+triangle[nrow][ncol];
                    dfs(dist+triangle[nrow][ncol],nrow,ncol,dp,triangle);
                }
            }
        }
    }
    public static int triangleMinimumPathSum(int[][] triangle, int n) {
        int [][] dp=new int[n][];
        for(int i=0;i<n;i++){
            dp[i]=new int[i+1];
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }

        for(int i=0;i<n;i++){
            dp[n-1][i]=triangle[n-1][i];
            dfs(triangle[n-1][i],n-1,i,dp,triangle);
        }
        return dp[0][0];
    }
    public static int triangleMinimumPathSumOptimised(int[][] triangle, int n) {
        int [] dp=new int[1];
        //mark the base case
        dp[0]=triangle[0][0];
        if(n==1) return dp[0];
        int ans=Integer.MAX_VALUE;
        for(int i=1;i<n;i++){
            int [] temp=new int[i+1];
            for(int j=0;j<i+1;j++){
                int up=(j!=i)?dp[j]:Integer.MAX_VALUE;
                int dg=(j-1>=0)?dp[j-1]:Integer.MAX_VALUE;
                temp[j]=Math.min(up,dg)+triangle[i][j];
                if(i==n-1) ans=Math.min(temp[j],ans);
            }
            dp=temp;
        }
        return ans;
    }
    // funtion for computing the max path sum from any starting point and ending point.
    public static int helperGet(int n,int m,int [][] grid,int [][] dp){
        if(n==0) return grid[n][m];
        if(dp[n][m]!=Integer.MAX_VALUE) return dp[n][m];
        int left=(m-1>=0)?helperGet(n-1,m-1,grid,dp):Integer.MIN_VALUE;
        int up=helperGet(n-1,m,grid,dp);
        int right=(m+1<grid[0].length)?helperGet(n-1,m+1,grid,dp):Integer.MIN_VALUE;
        int ans=Math.max(left,Math.max(up,right));
        return dp[n][m]=grid[n][m]+ans;
    }
    public static int getMaxPathSum(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int [][] dp=new int[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        int ans=Integer.MIN_VALUE;
        for(int i=0;i<m;i++){
            ans=Math.max(ans,helperGet(n-1,i,matrix,dp));
        }
        return ans;
    }
    public static int getMaxPathSumTab(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int [][] dp=new int[n][m];
        int ans=Integer.MIN_VALUE;
        for(int j=0;j<m;j++) {
            dp[0][j]=grid[0][j];
            ans=Math.max(ans,dp[0][j]);
        }
        if(n==1) return ans;
        ans=Integer.MIN_VALUE;
        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                int left=(j-1>=0)?dp[i-1][j-1]:Integer.MIN_VALUE;
                int up=dp[i-1][j];
                int right=(j+1<m)?dp[i-1][j+1]:Integer.MIN_VALUE;
                dp[i][j]=Math.max(left,Math.max(up,right))+grid[i][j];
                if(i==n-1) ans=Math.max(ans,dp[i][j]);
            }
        }
        return ans;
    }
    public static int getMaxPathSumOptimiesed(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int [] dp=new int[m];
        int ans=Integer.MIN_VALUE;

        for(int j=0;j<m;j++) {
            dp[j]=grid[0][j];
            ans=Math.max(ans,dp[j]);
        }
        if(n==1) return ans;
        ans=Integer.MIN_VALUE;
        for(int i=1;i<n;i++){
            int [] curr=new int[m];
            for(int j=0;j<m;j++){
                int left=(j-1>=0)?dp[j-1]:Integer.MIN_VALUE;
                int up=dp[j];
                int right=(j+1<m)?dp[j+1]:Integer.MIN_VALUE;
                curr[j]=Math.max(left,Math.max(up,right))+grid[i][j];
                if(i==n-1) ans=Math.max(ans,curr[j]);
            }
            dp=curr;
        }
        return ans;
    }
    public static int maximumChocolates(int r, int c, int[][] grid) {
        int [][][] dp=new int[r][c][c];// memoisation
        return chocloateshelper(0,0,c-1,grid,dp);
    }
    public static int chocloateshelper(int rowA,int colA,int colB,int [][] grid,int [][][] dp){
        if(rowA==grid.length-1){
            if(colA==colB) return grid[rowA][colA];
            else return grid[rowA][colA]+grid[rowA][colB];
        }
        if(dp[rowA][colA][colB]!=0) return dp[rowA][colA][colB];
        int [] delrow={1,1,1};
        int [] delcol={-1,0,1};
        int ans=Integer.MIN_VALUE;

        for(int i=0;i<3;i++){
            int nrowA=rowA+delrow[i];
            int ncolA=colA+delcol[i];
            for(int j=0;j<3;j++){
                int m=grid[0].length;
                int ncolB=colB+delcol[j];
                int fans=(ncolA>=0 &&ncolB>=0 &&ncolA<m &&ncolB<m)?chocloateshelper(nrowA,ncolA,ncolB,grid,dp)
                        :Integer.MIN_VALUE;
                ans=Math.max(fans,ans);
            }
        }
        return dp[rowA][colA][colB]=(colA==colB)?ans+grid[rowA][colA]:ans+grid[rowA][colA]+grid[rowA][colB];
    }
    public static int maximumChocolatesTab(int r, int c, int[][] grid) {
        int [][][] dp=new int[r][c][c];
        //marking the base-cases in dp array .
        for(int j=0;j<c;j++){
            for(int k=0;k<c;k++){
                if(j==k) dp[r-1][j][k]=grid[r-1][k];
                else dp[r-1][j][k]=grid[r-1][j]+grid[r-1][k];
            }
        }
        int [] delrow={1,1,1};
        int [] delcol={-1,0,1};
        for(int i=r-2;i>=0;i--){
            for(int j=0;j<c;j++){
                for(int k=0;k<c;k++){
                    int ans=Integer.MIN_VALUE;
                    for(int a=0;a<3;a++){
                        int nrowA=i+delrow[a];
                        int ncolA=j+delcol[a];
                        for(int b=0;b<3;b++){
                            int m=grid[0].length;
                            int ncolB=k+delcol[b];
                            int fans=(ncolA>=0 &&ncolB>=0 &&ncolA<m &&ncolB<m)?dp[nrowA][ncolA][ncolB]
                                    :Integer.MIN_VALUE;
                            ans=Math.max(fans,ans);
                        }
                    }
                    dp[i][j][k]=(j==k)?ans+grid[i][j]:ans+grid[i][j]+grid[i][k];;
                }
            }
        }
        return dp[0][0][c-1];
    }
    public static int maximumChocolatesOpimised(int r, int c, int[][] grid) {
        int [][] dp=new int[c][c];
        //marking the base-cases in dp array .
        for(int j=0;j<c;j++){
            for(int k=0;k<c;k++){
                if(j==k) dp[j][k]=grid[r-1][k];
                else dp[j][k]=grid[r-1][j]+grid[r-1][k];
            }
        }
        int [] delrow={1,1,1};
        int [] delcol={-1,0,1};
        for(int i=r-2;i>=0;i--){
            int [][] curr=new int[c][c];
            for(int j=0;j<c;j++){
                for(int k=0;k<c;k++){
                    int ans=Integer.MIN_VALUE;
                    for(int a=0;a<3;a++){
                        int ncolA=j+delcol[a];
                        for(int b=0;b<3;b++){
                            int m=grid[0].length;
                            int ncolB=k+delcol[b];
                            int fans=(ncolA>=0 &&ncolB>=0 &&ncolA<m &&ncolB<m)?dp[ncolA][ncolB]
                                    :Integer.MIN_VALUE;
                            ans=Math.max(fans,ans);
                        }
                    }
                    curr[j][k]=(j==k)?ans+grid[i][j]:ans+grid[i][j]+grid[i][k];;
                }
            }
            dp=curr;
        }
        return dp[0][c-1];
    }
    public static void main(String[] args) {

    }
}
