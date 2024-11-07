package Practice;

import java.lang.reflect.Array;
import java.util.*;

public class dp {
//    public static int f(int n, int[] dp) {
//        if (n == 1 || n == 0) return n;
//        if (dp[n] != 0) return dp[n];
//        int left = f(n - 1, dp);
//        int right = f(n - 2, dp);
//        return dp[n] = left + right;
//    }

    public static int ftab(int n) {
        int zero = 0;
        if (n == 0) return zero;
        int first = 1;
        for (int i = 2; i <= n; i++) {
            int left = first;
            int right = zero;
            int ans = left + right;
            zero = first;
            first = ans;
        }
        return first;
    }

    public static int frogJump(int n, int[] heights, int[] dp) {
        if (n <= 0) return 0;
        if (dp[n] != 0) return dp[n];
        int left = frogJump(n - 1, heights, dp) + Math.abs(heights[n] - heights[n - 1]);
        int right = (n - 2 >= 0) ? frogJump(n - 2, heights, dp) + Math.abs(heights[n] - heights[n - 2]) : Integer.MAX_VALUE;
        return dp[n] = Math.min(left, right);
    }

    public static int frogTab(int n, int[] heights) {
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            int left = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            int right = (i - 2 >= 0) ? dp[i - 2] + Math.abs(heights[i] - heights[i - 2]) : Integer.MAX_VALUE;
            dp[i] = Math.min(left, right);
        }
        return dp[n - 1];
    }

    public static int fhigh(int idx, int k, int n, String s) {
        if (k == 0 || idx < 0) return 0;
        int num = s.charAt(idx) - '0';
        int left = 0;
        if (n == k) {
            if (num % 2 != 0) left = fhigh(idx - 1, k - 1, n, s) * 10 + num;
        } else left = fhigh(idx - 1, k - 1, n, s) * 10 + num;
        int right = fhigh(idx - 1, k, n, s);
        return Math.max(left, right);
    }

    public static int flow(int idx, int k, int n, String s) {
        if (k == 0) return 0;
        if (idx < 0) return (int) 1e9;

        int num = s.charAt(idx) - '0';
        int left = (int) 1e9;
        if (k == 1) {
            if (num != 0) left = fevenlow(idx - 1, k - 1, n, s) * 10 + num;
        } else if (n == k) {
            if (num % 2 != 0) left = flow(idx - 1, k - 1, n, s) * 10 + num;
        } else left = flow(idx - 1, k - 1, n, s) * 10 + num;
        int right = flow(idx - 1, k, n, s);
        return Math.min(left, right);
    }

    public static int fevenhigh(int idx, int k, int n, String s) {
        if (k == 0 || idx < 0) return 0;
        int num = s.charAt(idx) - '0';
        int left = 0;
        if (n == k) {
            if (num % 2 == 0) left = fevenhigh(idx - 1, k - 1, n, s) * 10 + num;
        } else left = fevenhigh(idx - 1, k - 1, n, s) * 10 + num;
        int right = fevenhigh(idx - 1, k, n, s);
        return Math.max(left, right);
    }

    public static int fevenlow(int idx, int k, int n, String s) {
        if (k == 0) return 0;
        if (idx < 0) return (int) 1e9;

        int num = s.charAt(idx) - '0';
        int left = (int) 1e9;
        if (k == 1) {
            if (num != 0) left = fevenlow(idx - 1, k - 1, n, s) * 10 + num;
        } else if (n == k) {
            if (num % 2 == 0) left = fevenlow(idx - 1, k - 1, n, s) * 10 + num;
        } else left = fevenlow(idx - 1, k - 1, n, s) * 10 + num;
        int right = fevenlow(idx - 1, k, n, s);
        return Math.min(left, right);
    }

    public static long f(int sidx, int tidx, int k, int n, String s, String t) {
        if (k == 0) return 0;
        if (sidx < 0 || tidx < 0) return (long) 1e9;

        long top = (long) 1e9;
        long snum = 0;
        if (k != 1) {
            snum = Math.abs(s.charAt(sidx) - t.charAt(tidx));
        } else {
            if (s.charAt(sidx) != '0' && t.charAt(tidx) != '0') {
                snum = Math.abs(s.charAt(sidx) - t.charAt(tidx));
            } else {
                if (s.charAt(sidx) == '0') f(sidx - 1, tidx, k, n, s, t);
                else return f(sidx, tidx - 1, k, n, s, t);
            }
        }
        if (k == n) {
            if (snum % 2 != 0) top = f(sidx - 1, tidx - 1, k - 1, n, s, t) * 10 + snum;
        } else top = f(sidx - 1, tidx - 1, k - 1, n, s, t) * 10 + snum;
        long left = f(sidx, tidx - 1, k, n, s, t);
        long right = f(sidx - 1, tidx, k, n, s, t);
        return Math.min(top, Math.min(left, right));
    }

    public static int greed(int idx, int[] arr, int[] brr, boolean[] visit) {
        if (idx == arr.length) return 1;
        int n = arr.length;
        int min = Integer.MAX_VALUE;
        int mod = (int) (1e9 + 7);
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                int sum = (brr[idx] + arr[i]);
                int left = (greed(idx + 1, arr, brr, visit) * sum) % mod;
                min = Math.min(min, left);
                visit[i] = false;
            }
        }
        System.out.println(min);
        return min;
    }



//    public static int find(int idx,int ele,int [] arr){
//        int n=arr.length;
//        for(int i=idx+1;i<n;i++){
//            if(ele==arr[i]) return i;
//        }
//        return -1;
//    }
//    public static int[] concatenate(int[] a, int[] b) {
//        int[] result = new int[a.length + b.length];
//        System.arraycopy(a, 0, result, 0, a.length);
//        System.arraycopy(b, 0, result, a.length, b.length);
//        return result;
//    }
    public static int f(int l,int []arr,int [] dp){
        if(l==arr.length) return 0;
        if(dp[l]!=-1) return dp[l];
        int res =  f(l + 1, arr,dp);

        for (int i = l + 1; i < arr.length; ++i)
        {
            if (arr[l] ==arr[i])
            {
                res = Math.max(res,
                        i-l+1+ f(i+1 ,arr,dp));
            }
        }
        return dp[l]= res;
    }
    public static void duplicate(int [] arr){
        int n=arr.length;
        for(int i=0;i<n;i++){

        }
    }
    public static int[] find(int [][] arr,int [] insert){
        int n=arr.length;
        int st=insert[0];
        int end=insert[1];
        int stIdx=-1;
        int endIdx=n;
        for(int i=0;i<n;i++){
            if(st>arr[i][1]) stIdx=i;
            if(end<arr[i][0]) endIdx=i;
            if(stIdx!=n && endIdx!=n) break;
        }
        return new int[]{stIdx,endIdx};
    }
    public  static int[] find2(int [][] arr,int [] insert) {
        int n = arr.length;
        int st = insert[0];
        int end = insert[1];
        int stIdx = n;
        int endIdx = n;
        for (int i = 0; i < n; i++) {
            if (stIdx == n && st <= arr[i][1]) stIdx = i;
            if (end >= arr[i][0]) endIdx = i;
        }
        return new int[]{stIdx, endIdx};
    }
        public static int[][] findArr(){
            int [][] arr={
                    {0,0,0,0},
                    {0,0,0,1},
                    {0,0,1,0},
                    {0,0,1,1},
                    {0,1,0,0},
                    {0,1,0,1},
                    {0,1,1,0},
                    {0,1,1,1},
                    {1,0,0,0},
                    {1,0,0,1},
                    {1,0,1,0},
                    {1,0,1,1},
                    {1,1,0,0},
                    {1,1,0,1},
                    {1,1,1,0},
                    {1,1,1,1}
            };
            return arr;
        }
        public static int f(int row,int col,int [][] arr,int [][] dir,boolean [][] visit) {
            int[] delrow = {-1, 0, 1, 0};
            int[] delcol = {0, 1, 0, -1};
            int n = arr.length;
            int m = arr[0].length;
            int ele = arr[row][col];
            System.out.print(ele+" ");
            visit[row][col] = true;
            int count = 1;
            for (int i = 0; i < 4; i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];
                int idx = (i + 2) % 4;
                if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < m && !visit[nrow][ncol] && dir[ele][i] == 0 && dir[arr[nrow][ncol]][idx] == 0) {
                    count += f(nrow, ncol, arr, dir, visit);
                }
            }
            return count;
        }

        static int[] row4D = {-1, 0, 1, 0};
        static int[] col4D = {0, 1, 0, -1};

        static int n, m, currArea;
        static boolean[][] visited;
        static int[][][] grid;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            n = sc.nextInt();
            m = sc.nextInt();

            visited = new boolean[n][m];
            grid = new int[n][m][4]; // 0: North, 1: East, 2: South, 3: West

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int x = sc.nextInt();

                    // North
                    if ((x & (1 << 3)) > 0) {
                        grid[i][j][0] = 1;
                        if (i - 1 >= 0)
                            grid[i - 1][j][2] = 1;
                    }

                    // East
                    if ((x & (1 << 2)) > 0) {
                        grid[i][j][1] = 1;
                        if (j + 1 < m)
                            grid[i][j + 1][3] = 1;
                    }

                    // South
                    if ((x & (1 << 1)) > 0) {
                        grid[i][j][2] = 1;
                        if (i + 1 < n)
                            grid[i + 1][j][0] = 1;
                    }

                    // West
                    if ((x & (1 << 0)) > 0) {
                        grid[i][j][3] = 1;
                        if (j - 1 >= 0)
                            grid[i][j - 1][1] = 1;
                    }
                }
            }

            List<Integer> area = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j]) {
                        currArea = 0;
                        floodFill(i, j);
                        area.add(currArea);
                    }
                }
            }

            area.sort(Collections.reverseOrder());

            for (int a : area) {
                System.out.print(a + " ");
            }
        }

        static void floodFill(int r, int c) {
            visited[r][c] = true;
            currArea++;

            for (int k = 0; k < 4; k++) {
                int adjR = r + row4D[k];
                int adjC = c + col4D[k];

                if (adjR >= 0 && adjR < n && adjC >= 0 && adjC < m && !visited[adjR][adjC]) {
                    // North
                    if (k == 0) {
                        if (grid[r][c][k] == 0 && grid[adjR][adjC][2] == 0)
                            floodFill(adjR, adjC);
                    }
                    // East
                    else if (k == 1) {
                        if (grid[r][c][k] == 0 && grid[adjR][adjC][3] == 0)
                            floodFill(adjR, adjC);
                    }
                    // South
                    else if (k == 2) {
                        if (grid[r][c][k] == 0 && grid[adjR][adjC][0] == 0)
                            floodFill(adjR, adjC);
                    }
                    // West
                    else if (k == 3) {
                        if (grid[r][c][k] == 0 && grid[adjR][adjC][1] == 0)
                            floodFill(adjR, adjC);
                    }
                }
            }
        }
}
        