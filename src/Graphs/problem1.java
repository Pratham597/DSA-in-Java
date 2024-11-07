package Graphs;

import java.util.*;

public class problem1 {
    static void helper(ArrayList<ArrayList<Integer>> arr,boolean [] isVisited,int st){
        if(isVisited[st]) return;
        isVisited[st]=true;
        for(int i=0;i<arr.get(st).size();i++){
            int ele=arr.get(st).get(i);
            if(ele==0) continue;
            helper(arr,isVisited,i);
        }
    }
    static int numProvinces(ArrayList<ArrayList<Integer>> arr, int V) {
        int sum=0;
        boolean [] isVisited=new boolean[V];
        for(int i=0;i<arr.size();i++){
            if(!isVisited[i]){
                helper(arr,isVisited,i);
                sum++;
            }
        }
        return sum;
    }
    //It is program to mark the grid zero.
    static void markZero(ArrayList<ArrayList<Integer>> arr, int row,int col){
        int n=arr.size();
        int m=arr.get(0).size();
        if(row<0||col<0||row>=n||col>=m) return;
        if(arr.get(row).get(col)==0) return;
        arr.get(row).set(col,0);
        markZero(arr,row-1,col);
        markZero(arr,row+1,col);
        markZero(arr,row,col-1);
        markZero(arr,row,col+1);
        markZero(arr,row-1,col-1);
        markZero(arr,row+1,col+1);
        markZero(arr,row-1,col+1);
        markZero(arr,row+1,col-1);
    }
    //wap to find the no. of islands in the given graph.
    static int noOfIslands(ArrayList<ArrayList<Integer>> arr){
        int n=arr.size();
        int count=0;
        for (int i = 0; i < n; i++) {
            for(int j=0;j<arr.get(i).size();j++){
                if(arr.get(i).get(j)==1) {
                    markZero(arr,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    
    //wap to implement flood fill algorithm:
    static void colorFlood(ArrayList<ArrayList<Integer>> arr,int row,int col,int fill,boolean[][] isVisited){
        int n=arr.size();
        int m=arr.get(0).size();
        if(row<0||col<0||row>n||col>m) return;
        if(isVisited[row][col]) return;
        isVisited[row][col]=true;
        int ele=arr.get(row).get(col);
        arr.get(row).set(col,fill);
        if(row-1>=0&&arr.get(row-1).get(col)==ele) colorFlood(arr,row-1,col,fill,isVisited);
        if(col+1<m&&arr.get(row).get(col+1)==ele) colorFlood(arr,row,col+1,fill,isVisited);
        if(row+1<n&&arr.get(row+1).get(col)==ele) colorFlood(arr,row+1,col,fill,isVisited);
        if(col-1>=0&& arr.get(row).get(col-1)==ele) colorFlood(arr,row,col-1,fill,isVisited);
    }
    public int helper(int [][] arr,int rst,int cst,int row,int col,boolean[][]visit){
        int n=arr.length;
        int m=arr[0].length;
        int temp=Math.abs(rst-row)+Math.abs(cst-col);
        if(visit[row][col]) return 0;
        if(temp>1){
            if(row-1>=0&&arr[row-1][col]==2) return 0;
            if(row+1<n&&arr[row+1][col]==2) return 0;
            if(col-1>=0&&arr[row][col-1]==2) return 0;
            if(col+1<m&&arr[row][col+1]==2) return 0;
        }
        visit[row][col]=true;
        int up=(row-1>=0&&arr[row-1][col]==1)?helper(arr,rst,cst,row-1,col,visit)+1:0;
        int down=(row+1<n&&arr[row+1][col]==1)?helper(arr,rst,cst,row+1,col,visit)+1:0;
        int left=(col-1>=0&&arr[row][col-1]==1)?helper(arr,rst,cst,row,col-1,visit)+1:0;
        int right=(col+1<m&&arr[row][col+1]==1)?helper(arr,rst,cst,row,col+1,visit)+1:0;
        int temp1=Math.max(up,down);
        int temp2=Math.max(left,right);
        return Math.max(temp1,temp2);
    }
    public static class pair{
        int row;
        int col;
        public pair(int row,int col){
            this.row=row;
            this.col=col;
        }
        void display(){
            System.out.println(row+" "+col);
        }
    }
    public static void helperRotten(int [][] arr){
        Queue<pair> q=new LinkedList<>();
        int n=arr.length,m=arr[0].length;
        int [][] matrix=new int[n][m];
        for (int i = 0; i < arr.length; i++) {
            for(int j=0;j<arr[0].length;j++){
                matrix[i][j]=arr[i][j];
            }
        }
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(arr[i][j]==2) {
                    //up
                    if (i - 1 >= 0 && arr[i - 1][j] == 1 && matrix[i - 1][j] == 1) {
                        q.add(new pair(i - 1, j));
                        matrix[i - 1][j] = 2;
                    }
                    //down
                    if (i + 1 < n && arr[i + 1][j] == 1 && matrix[i + 1][j] == 1) {
                        q.add(new pair(i + 1, j));
                        matrix[i + 1][j] = 2;
                    }
                    //left
                    if (j - 1 >= 0 && arr[i][j - 1] == 1 && matrix[i][j - 1] == 1) {
                        q.add(new pair(i, j - 1));
                        matrix[i][j - 1] = 2;
                    }
                    //right
                    if (j + 1 < m && arr[i][j + 1] == 1 && matrix[i][j + 1] == 1) {
                        q.add(new pair(i, j + 1));
                        matrix[i][j + 1] = 2;
                    }
                }
            }
        }
        //edge case q is empty.
        int count=0;
        while(!q.isEmpty()){
            int size=q.size();
            for(pair p:q){
                p.display();
            }
            System.out.println();
            count++;
            for(int k=0;k<size;k++){
                pair p=q.poll();
                int i=p.row;
                int j=p.col;
                if (i - 1 >= 0&& matrix[i - 1][j] == 1) {
                    q.add(new pair(i - 1, j));
                    matrix[i - 1][j] = 2;
                }
                //down
                if (i + 1 < n &&matrix[i + 1][j] == 1) {
                    q.add(new pair(i + 1, j));
                    matrix[i + 1][j] = 2;
                }
                //left
                if (j - 1 >= 0 &&matrix[i][j - 1] == 1) {
                    q.add(new pair(i, j - 1));
                    matrix[i][j - 1] = 2;
                }
                //right
                if (j + 1 < m && matrix[i][j + 1] == 1) {
                    q.add(new pair(i, j + 1));
                    matrix[i][j + 1] = 2;
                }
            }
        }
        System.out.println(count);
    }
    //wap to detect a cycle using dfs approach.
    public static boolean helperCycle(ArrayList<ArrayList<Integer>> arr, HashMap<Integer,Integer> mp, int st, int level){
        mp.put(st,level);
        for(Integer x:arr.get(st)){
            if(mp.containsKey(x)){
                int l=mp.get(x);
                if(level-l>1) return true;
            }
            else if(helperCycle(arr,mp,x,level+1)) return true;

        }
        return false;
    }
    public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> arr){
        HashMap<Integer,Integer> mp=new HashMap<>();
        for(int i=0;i<V;i++){
            if(!mp.containsKey(i)){
                if(helperCycle(arr,mp,i,0)) {
                    System.out.println(mp);
                    return true;
                }
            }
        }
        return false;
    }
    public static void nearest(int [][] arr){
        int n=arr.length;
        int m=arr[0].length;
        int [][] matrix=new int[n][m];
        boolean[][] visit=new boolean[n][m];
        Queue<pair> q=new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <m ; j++) {
                if(arr[i][j]==1) {
                    visit[i][j]=true;
                    q.add(new pair(i,j));
                    matrix[i][j]=0;
                }
            }
        }
        int count=1;
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                pair p=q.poll();
                int row=p.row;
                int col=p.col;
                if(row-1>=0&&!visit[row-1][col]){
                    visit[row-1][col]=true;
                    matrix[row-1][col]=count;
                    q.add(new pair(row-1,col));
                }
                if(row+1<n&&!visit[row+1][col]){
                    visit[row+1][col]=true;
                    matrix[row+1][col]=count;
                    q.add(new pair(row+1,col));
                }
                if(col+1<m&&!visit[row][col+1]){
                    visit[row][col+1]=true;
                    matrix[row][col+1]=count;
                    q.add(new pair(row,col+1));
                }
                if(col-1>=0&&!visit[row][col-1]){
                    visit[row][col-1]=true;
                    matrix[row][col-1]=count;
                    q.add(new pair(row,col-1));
                }
            }
            count++;
        }
    }
    public static  void helperfill(char [][] arr,int row,int col,boolean[][] visit){
        int n=arr.length;
        int m=arr[0].length;
        if(row<0||col<0||row>=n||col>=m) return;
        if(visit[row][col]) return;
        visit[row][col]=true;
        int [] delrow={-1,1,0,0};
        int [] delcol={0,0,-1,1};
        for(int i=0;i<4;i++){
            int nrow=delrow[i]+row;
            int ncol=delcol[i]+col;
            helperfill(arr,nrow,ncol,visit);
        }
    }
    public static void fillX(char [][] arr){
        int n=arr.length;
        int m=arr[0].length;
        int [][] matrix=new int[n][m];
        boolean [][] visit=new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j]=='O'&&(i==0||j==0||i==n-1||j==n-1)){
                    helperfill(arr,i,j,visit);
                }
            }
        }
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(visit[i][j]) matrix[i][j]='O';
                else matrix[i][j]='X';
            }
        }
    }
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> arr=new ArrayList<>();
        int n=4;
        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
        }
        arr.get(0).add(1);
        arr.get(0).add(2);
        arr.get(0).add(3);
        arr.get(1).add(0);
        arr.get(1).add(2);
        arr.get(2).add(1);
        arr.get(2).add(0);
        arr.get(3).add(1);
        char [][] arr1={ {'X','X','O'},{'X','O','X'},{'O','O','X'},{'X','X','O'}};
        for(char [] x:arr1){
            for(char y:x){
                System.out.print(y+" ");
            }
            System.out.println();
        }
        System.out.println();
        fillX(arr1);
    }
}
