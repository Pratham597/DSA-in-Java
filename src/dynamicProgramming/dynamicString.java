package dynamicProgramming;

import java.util.HashMap;
import  java.util.HashSet;

public class dynamicString {
    static void printSubsequence(int idx,String ans,String s){
        if(idx==s.length()){
            System.out.println(ans);
            return;
        }
        printSubsequence(idx+1,ans+s.charAt(idx),s);
        printSubsequence(idx+1,ans,s);
    }
    public static int f(int ls,int ts,String s, String t,int [][]dp){
        if(ls<0||ts<0) return 0;
        if(dp[ls][ts]!=0) return dp[ls][ts];
        if(s.charAt(ls)==t.charAt(ts)){
         	return dp[ls][ts]=1+f(ls-1,ts-1,s,t,dp);
        }
        int left=f(ls-1,ts,s,t,dp);
        int right=f(ls,ts-1,s,t,dp);
        return dp[ls][ts]= Math.max(left,right);
    }
    public static int lcs(String s, String t) {
        int [][] dp=new int[s.length()][t.length()];
        return f(s.length()-1,t.length()-1,s,t,dp);
    }
    public static int lcsOptimised (String s, String t) {
        int n=s.length();
        int m=t.length();
        int [] dp=new int[m];
        for(int i=0;i<n;i++){
            int [] curr=new int[m];
            for(int j=0;j<m;j++){
                if(i==0 &&j==0) {
                    curr[j]=(s.charAt(i)==t.charAt(j))?1:0;
                    continue;
                }
                if(s.charAt(i)==t.charAt(j)){
                    int ans=(j-1>=0 &&i-1>=0)?dp[j-1]:0;
                    curr[j]=1+ans;
                    continue;
                }
                int left=(i-1>=0)?dp[j]:0;
                int right=(j-1>=0)?curr[j-1]:0;
                curr[j]= Math.max(left,right);
            }
            dp=curr;
        }
        return dp[m-1];
    }

    public static String lcsPrint(int ls,int lt,String s,String t,String[][] dp){
        if(ls<0 || lt<0) return "";
        if(dp[ls][lt]!=null) return dp[ls][lt];
        if(s.charAt(ls)==t.charAt(lt)){
            return dp[ls][lt]=lcsPrint(ls-1,lt-1,s,t,dp)+s.charAt(ls);
        }
        String left=lcsPrint(ls-1,lt,s,t,dp);
        String right=lcsPrint(ls,lt-1,s,t,dp);
        return dp[ls][lt]= (left.length()>right.length())?left:right;
    }
    public static String lcsPrintf(String s,String t){
        String[][] dp=new String[s.length()][t.length()];
        System.out.println(lcsPrint(s.length()-1,t.length()-1,s,t,dp));
        for(String [] st:dp){
            for(String tt:st){
                System.out.print(tt+"-");
            }
            System.out.println();
        }
        return "";
    }
    public static String lcsPrintTab(String s,String t){
        int n=s.length();
        int m=t.length();
        String [][] dp=new String[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0 &&j==0) {
                    dp[0][0]=(s.charAt(0)==t.charAt(0))?s.charAt(0)+"":"";
                }
                else if(s.charAt(i)==t.charAt(j)){
                    String ans=(j-1>=0 &&i-1>=0)?dp[i-1][j-1]:"";
                    dp[i][j]=ans+s.charAt(i);

                }
                else{
                    String left=(i-1>=0)?dp[i-1][j]:"";
                    String right=(j-1>=0)?dp[i][j-1]:"";
                    dp[i][j]= (left.length()>right.length())?left:right;
                }
            }
        }
        for(String [] st:dp){
            for(String tt:st){
                System.out.print(tt+"-");
            }
            System.out.println();
        }
        return dp[n-1][m-1];
    }
    public static int longestCommonSubstringTabulation(String s, String t){
        int n=s.length();
        int m=t.length();
        int [][] dp=new int [n+1][m+1];
        int ans=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                    ans=Math.max(ans,dp[i][j]);
                }
            }
        }
        return ans;
    }
    public static int lcsMostOptimised(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int prev = 0; // Stores the value of dp[i-1][j-1] before it gets overwritten
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // Store the value of dp[i-1][j] before it gets overwritten
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = temp;
            }
        }
        return dp[n];
    }
    public static String shortestSupersequence(String s, String t) {
        int n=s.length();
        int m=t.length();
        int [][] dp=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        int i=n;
        int j=m;
        while(i>0 &&j>0){
            if(s.charAt(i-1)==t.charAt(j-1)){
                sb.append(s.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i-1][j]>dp[i][j-1]){
                sb.append(s.charAt(i-1));
                i--;
            }
            else{
                sb.append(t.charAt(j-1));
                j--;
            }
        }
        while(i>0){
            sb.append(s.charAt(i-1));
            i--;
        }
        while(j>0){
            sb.append(t.charAt(j-1));
            j--;
        }
        return sb.reverse().toString();
    }
    public static int feditDistance(int ls,int lt,String s,String t,int [][] dp){
        if(ls<0 ||lt<0) return Math.abs(ls-lt);
        if(dp[ls][lt]!=0) return dp[ls][lt];
        if(s.charAt(ls)==t.charAt(lt)){
            return feditDistance(ls-1,lt-1,s,t,dp);// having the same characters.
        }
        int left=feditDistance(ls,lt-1,s,t,dp)+1;// deleted operations.
        int right=feditDistance(ls-1,lt,s,t,dp)+1; //insertion operations.
        int middle=1+feditDistance(ls-1,lt-1,s,t,dp); // replacing the characters
        return dp[ls][lt]=Math.min(left,Math.min(right,middle));// returning the minimum answers.
    }
    public static boolean fwildCardMatching(int ls,int lt,String s,String t,int [][] dp){
        if(ls<0 &&lt<0) return true;
        if(lt<0){
            return (s.charAt(ls)=='*')?fwildCardMatching(ls-1,lt,s,t,dp):false;
        }
        if(ls<0) return false;
        if(dp[ls][lt]!=0) return (dp[ls][lt]==1)?true:false;
        char c1=s.charAt(ls);
        char c2=t.charAt(lt);
        if(c1==c2|| c1=='?') {
            boolean ans= fwildCardMatching(ls-1,lt-1,s,t,dp);
            dp[ls][lt]=(ans==true)?1:2;
            return ans;
        }
        else if(c1=='*'){
            boolean ans= fwildCardMatching(ls,lt-1,s,t,dp)||fwildCardMatching(ls-1,lt,s,t,dp);
            dp[ls][lt]=(ans==true)?1:2;
            return ans;
        }
        boolean ans=false;
        dp[ls][lt]=(ans==true)?1:2;
        return ans;
    }
    public static boolean wildcardMatchingOptimised(String s, String t) {
        int n=s.length();
        int m=t.length();
        boolean [] dp=new boolean[m+1];
        boolean [] curr=new boolean[m+1];
        dp[0]=true;
        for(int i=1;i<=n;i++){
            curr[0]=(s.charAt(i-1)=='*')?dp[0]:false;
            for(int j=1;j<=m;j++){
                char c1=s.charAt(i-1);
                char c2=t.charAt(j-1);

                if(c1==c2|| c1=='?') {
                    curr[j]=dp[j-1];
                }
                else if(c1=='*'){
                    curr[j]=dp[j]||curr[j-1];
                }
                else curr[j]=false;
            }
            boolean [] temp=curr;
            curr=dp;
            dp=temp;
        }
        return dp[m];
    }
    public static void main(String[] args) {
        
    }
}
