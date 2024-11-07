package dynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class dynamicStocks {
    //Dp-II
    public static long getMaximumProfit(int n, long[] arr) {
        if (n == 0)
            return 0;
        long[] dp = new long[2];
        for (int sell = 0; sell < 2; sell++) {
            if (sell == 1)
                dp[sell] = 0;
            else if (sell == 0)
                dp[sell] = -1 * arr[0];
        }
        for (int i = 1; i < n; i++) {
            for (int sell = 1; sell >=0; sell--) {

                if (sell == 0) {
                    long left = dp[1] - arr[i];
                    long right =dp[0];
                    dp[sell] = Math.max(left, right);
                }

                else{
                    long left =dp[0]+ arr[i];
                    long right = dp[1];
                    dp[sell] = Math.max(left, right);
                }
            }
        }
        return dp[1];
    }
    //Dp -||most optimised.
    public int maxProfit(int[] arr) {
        int n=arr.length;
        int  first = 0;
        int zero = -arr[0];
        for (int i = 1; i < n; i++) {
            first = Math.max(first, zero+arr[i]);
            zero = Math.max(first-arr[i], zero);
        }
        return first;
    }
    //dp-3
    public static int DP3(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[2][3];
        int[][] curr = new int[2][3];
        for (int j = 0; j < 2; j++) {
            for (int k = 1; k < 3; k++) {
                dp[j][k] = (j == 1) ? 0 : -1 * arr[0];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 1; k < 3; k++) {
                    if (j == 1) {
                        int left = dp[0][k] + arr[i];
                        int right = dp[1][k];
                        curr[j][k] = Math.max(left, right);
                    } else {
                        int left = dp[1][k - 1] - arr[i];
                        int right = dp[0][k];
                        curr[j][k] = Math.max(left, right);
                    }
                }
            }
            int[][] temp = curr;
            curr = dp;
            dp = temp;
        }
        return dp[1][2];
    }

    //Dp-5
    public static int stockProfit(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -arr[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 1) {
                    int left = dp[i - 1][0] + arr[i];
                    int right = dp[i - 1][1];
                    dp[i][j] = Math.max(left, right);
                } else {
                    int left = dp[i - 1][0];
                    int right = (i - 2 >= 0) ? dp[i - 2][1] - arr[i] : -arr[i];
                    dp[i][j] = Math.max(left, right);
                }
            }
        }
        return dp[n - 1][1];
    }
    //Dp-6
    public static int maximumProfit(int[] arr, int k, int fee) {
        int n=arr.length;
        int  first = 0;
        int zero = -arr[0]-fee;
        for (int i = 1; i < n; i++) {
            first = Math.max(first, zero+arr[i]);
            zero = Math.max(first-arr[i]-fee, zero);
        }
        return first;
    }
    public static void main(String[] args) {
    ArrayList<Integer> arr=new ArrayList<>();

    }
}
