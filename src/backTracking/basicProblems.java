package backTracking;

import java.util.*;

public class basicProblems {
    static void printPath(int r,int c,int endr,int endc,String str){
        if(r==endr&&endc==c) {
            System.out.println(str);
            return;
        }
        else if(r>endr||c>endc) return ;
        printPath(r+1,c,endr,endc ,str+"R");
        printPath(r,c+1,endr,endc,str+"D");
    }
    static int countmazepath(int r,int c,int endr,int endc){
        if(r==endr&&endc==c) return 1;
        else if(r>endr||c>endc) return 0;
        int ans=countmazepath(r+1,c,endr,endc);
        int ans1=countmazepath(r,c+1,endr,endc);
        return ans+ans1;
    }
    static void printPathBack(int r,int c,int ar,int ac,String str,int [][] arr){
        if(r>ar||c>ac) return ;
        else if(r<0||c<0) return;
        else if(arr[r][c]==0) return;
        else if(r==ar&&c==ac){
            System.out.println(str);
            return;
        }
        arr[r][c]=0;
        printPathBack(r,c+1,ar,ac,str+"R",arr);
        printPathBack(r+1,c,ar,ac,str+"D",arr);
        printPathBack(r,c-1,ar,ac,str+"L",arr);
        printPathBack(r-1,c,ar,ac,str+"U",arr);
        arr[r][c]=1;
    }
    static void printPathBackTrack(int r,int c,int endr,int endc,String str,boolean [][]back){
        if(r<0||c<0) return;
        else if(r>endr||c>endc) return ;
        else if(back[r][c]==true) return;
        else if(r==endr&&endc==c) {
            System.out.println(str);
            return;
        }
        back[r][c]=true;
        printPathBackTrack(r,c+1,endr,endc ,str+"R",back);
        printPathBackTrack(r+1,c,endr,endc,str+"D",back);
        printPathBackTrack(r,c-1,endr,endc,str+"L",back);
        printPathBackTrack(r-1,c,endr,endc,str+"U",back);
        back[r][c]=false;

    }
    //path containing stones.
    static void normalpath2(int r,int c,int ar,int ac,String str,int[][] arr){
        if(r>ar||c>ac) return;
       // else if(r<0||c<0) return;
        else if(arr[r][c]==0) return;
        else if(r==ar&&c==ac){
            System.out.println(str);
            return;
        }
        normalpath2(r+1,c,ar,ac,str+"D",arr);
        normalpath2(r,c+1,ar,ac,str+"R",arr);
    }
    static void swap(int [] arr,int idx1,int idx){
        int temp=arr[idx];
        arr[idx]=arr[idx1];
        arr[idx1]=temp;
    }
    static  void swaps(StringBuilder s1,int st,int exc){
        char c=s1.charAt(st);
        s1.setCharAt(st,s1.charAt(exc));
        s1.setCharAt(exc,c);
    }
    //wap to find permutations of string without arraylist:
    static void permString1(String s,String ans){
        if(s.isEmpty()){
//            ans=ans+"";
            System.out.println(ans);
        }
        for(int i=0;i<s.length();i++){
            StringBuilder temp=new StringBuilder(s);
            swaps(temp,0,i);
            char c= temp.charAt(0);
            s=temp+"";
            permString1(s.substring(1),ans+c);
        }
    }
    //wap to find the permutations of given string:
    static ArrayList<String> permString(String s){
        ArrayList<String> ans=new ArrayList<>();
        if(s.isEmpty()) {
            ans.add("");
            return ans;
        }
        for(int i=0;i<s.length();i++){
            StringBuilder temp=new StringBuilder(s);
            swaps(temp,0,i);
            char c= temp.charAt(0);
            s=temp+"";
            ArrayList<String> ans1=permString(s.substring(1));
            for(String s1:ans1){
                ans.add(c+s1);
            }
        }
        return ans;
    }
    //wap to find permutations of given list:
    static ArrayList<ArrayList<Integer>> permutations(int []arr,int idx){
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        if(arr.length==idx) {
            ans.add(new ArrayList<Integer>());
            return ans;
        }
        for(int i=idx;i<arr.length;i++){
            swap(arr,idx,i);//check condition : swap first and second fist:
            ArrayList<ArrayList<Integer>> help=permutations(arr,idx+1);
            for(ArrayList<Integer> x:help){
                x.addFirst(arr[idx]);
            }
            ans.addAll(help);
            swap(arr,idx,i);//uncheck : swap first and second first:
        }
        return ans;
    }
    static void nQueen(char [][] board,int row){
        int n= board.length;
        if(row==n){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        for(int j=0;j<n;j++){// representing a column in chess board.
            if(isSafe(board,row,j)){
                board[row][j]='Q';
                nQueen(board,row+1);
                board[row][j]='.';
            }
        }
    }
    static boolean isSafe(char[][] board, int row, int col) {
        //check the row;
        int n=board.length;
        for(int j=0;j<n;j++){
            if(board[row][j]=='Q') return false;
            if(board[j][col]=='Q') return false;
        }
        //check north east:
        int i=row;
        int j=col;
        while(i>=0&&j<n){
            if(board[i][j]=='Q') return false;
            i--;
            j++;
        }
        //check south east;
        i=row;
        j=col;
        while(i<n&&j<n){
            if(board[i][j]=='Q') return false;
            i++;
            j++;
        }
        //check south west:
        i=row;
        j=col;
        while(i<n&&j>=0){
            if(board[i][j]=='Q') return false;
            i++;
            j--;
        }
        //check north west:
        i=row;
        j=col;
        while(i>=0 &&j>=0){
            if(board[i][j]=='Q') return false;
            i--;
            j--;
        }
        return true;
    }

    static void  boysandGirls(char[] ans,int idx){
        if(ans.length==idx){
            for(char c:ans){
                System.out.print(c);
            }
            System.out.println();
            return;
        }
        for(int i=idx;i<ans.length;i++){
            swapc(ans,i,idx);
            boysandGirls(ans,idx+1);
            swapc(ans,i,idx);
        }
    }

    static void swapc(char[] ans, int i, int idx) {
        if(!isRight(ans)){
            char c=ans[i];
            ans[i]=ans[idx];
            ans[idx]=c;
        }
    }

    static boolean isRight(char[] ans) {
        int n=ans.length;
        return ans[n/2]=='g';
    }
    static void sumSubsets(int [] ans,boolean [] back,int sum,int idx,int target){
        if(sum>target) return;
        else if(sum==target){
            for(boolean x: back){
                if(x) System.out.print(1);
                else System.out.print(0);
            }
            System.out.println();
            return;
        }
        for(int i=idx;i<ans.length;i++){
            back[i]=true;
            sumSubsets(ans,back,sum+ans[i],idx+1,target);
            back[i]=false;
        }
    }
    static void graphColoring(int idx,char [] ans,char []colors,int[][]edge){
        if(idx==ans.length){
            for(char c:ans){
                System.out.print(c);
            }
            System.out.println();
            return;
        }
        for(int j=0;j<colors.length;j++){
            if(possible(ans,colors[j],idx,edge)){
                ans[idx]=colors[j];
                graphColoring(idx+1,ans,colors,edge);
                ans[idx]='.';
            }
        }
    }
    // wap to find valid for graph coloring:
    static boolean possible(char [] ans,char c,int idx,int[][] edge){
        for(int i=0;i<edge.length;i++){
            int[]temp=edge[i];
            if(contains(temp,idx+1)){
                for(int x:temp){
                    if(x!=idx+1){
                        if(ans[x-1]==c) return false;
                    }
                }
            }
        }
        return true;
    }
    static boolean contains(int [] temp,int n){
        for(int i=0;i<temp.length;i++){
            if(n==temp[i]){
                return true;
            }
        }
        return false;
    }
    //wap to check the configuration of knight tour given as in grid:
    static boolean basic(int[][] grid,int row,int col,int count){
        int r= grid.length;
        int c=grid[0].length;
        if(count==r*c-1) return true;
        if(row+1<r&&col+2<c&&grid[row+1][col+2]==count+1){//
            return basic(grid,row+1,col+2,count+1);//
        }
        else if(row-1>=0&&col+2<c&&grid[row-1][col+2]==count+1){
            return basic(grid,row-1,col+2,count+1);//
        }
        else if(row-2>=0&&col+1<c&&grid[row-2][col+1]==count+1){
            return basic(grid,row-2,col+1,count+1);//
        }
        else if(row-2>=0&&col-1>=0&&grid[row-2][col-1]==count+1){//
            return basic(grid,row-2,col-1,count+1);
        }
        else if(row-1>=0&&col-2>=0&&grid[row-1][col-2]==count+1){///
            return basic(grid,row-1,col-2,count+1);
        }
        else if(row+1<r&&col-2>=0&&grid[row+1][col-2]==count+1){
            return basic(grid,row+1,col-2,count+1);
        }
        else if(row+2<r&&col-1>=0&&grid[row+2][col-1]==count+1){
            return basic(grid,row+2,col-1,count+1);
        }
        else if(row+2<r&&col+1<c&&grid[row+2][col+1]==count+1){
            return basic(grid,row+2,col+1,count+1);
        }
        return false;
    }
    //binary search on search area and that will return boolean for our mid:
    static boolean checkNKnights(char[][] board,int row,int col,int count,int target){
        if(count==target){
            return true;
        }
        if(row==board.length){
            return false;
        }
        for(int i=col;i<board.length;i++){
            if(isKnightSafe(board,row,i)){
                board[row][i]='K';
                if(checkNKnights(board, row, i + 1, count + 1, target)) return true;
                board[row][i]='.';
            }
        }
        return checkNKnights(board,row+1,0,count,target);

    }
    //wap to find max N knights can be placed on chess board:
    static int findMaxNKnights(char[][] board,int row,int col,int count){
        int st=0 ,ans=0;
        int end= (int) 1e9;
        while(st<=end){
            int mid=st+(end-st)/2;
            if(checkNKnights( board, row, col,count,mid)){
                ans=mid;
                st=mid+1;
            }
            else end=mid-1;
        }
        return ans;
    }
    //program for placing a given n kknights on board and its all configurations:
    static void placeNKnights(char[][] board,int row,int col,int count){
        if(count==12){
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board[i].length;j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        if(row==board.length){
            return;
        }
        for(int i=col;i<board.length;i++){
            if(isKnightSafe(board,row,i)){
                board[row][i]='K';
                placeNKnights(board,row,i+1,count+1);
                board[row][i]='.';
            }
        }
        placeNKnights(board,row+1,0,count);
    }
    //wap to find the all configurations of difft no. knights: on board
    static void placeKnights(char[][] board,int row,int col){
        if(row==board.length){
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board[i].length;j++){
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        for(int i=col;i<board.length;i++){
            if(isKnightSafe(board,row,i)){
                board[row][i]='K';
                placeKnights(board,row,i+1);
                board[row][i]='.';
            }
        }
        placeKnights(board,row+1,0);
    }
    //wap to check whether we are palcing a right or wrong:
    static boolean isKnightSafe(char [][] grid,int row,int col){
        int r=grid.length;
        int c=grid[0].length;
        if(row+1<r&&col+2<c&&grid[row+1][col+2]=='K') return false;
        else if(row-1>=0&&col+2<c&&grid[row-1][col+2]=='K') return false;
        else if(row-2>=0&&col+1<c&&grid[row-2][col+1]=='K') return false;
        else if(row-2>=0&&col-1>=0&&grid[row-2][col-1]=='K') return false;
        else if(row-1>=0&&col-2>=0&&grid[row-1][col-2]=='K') return false;
        else if(row+1<r&&col-2>=0&&grid[row+1][col-2]=='K') return false;
        else if(row+2<r&&col-1>=0&&grid[row+2][col-1]=='K') return false;
        else if(row+2<r&&col+1<c&&grid[row+2][col+1]=='K') return false;
        return true;
    }
    static boolean isValid(int[][]arr,int row,int col){
        for(int i=0;i<arr.length;i++){
            if(arr[row][i]==arr[row][col]&&i!=col) return false;
            if(arr[i][col]==arr[row][col]&&i!=row) return false;
        }
        int k=row-row%3;
        int m=col-col%3;
        for(int i=k;i<k+3;i++){
            for(int j=m;j<m+3;j++){
                if(arr[i][j]==arr[row][col]&&i!=row&&j!=col) return false;
            }
        }
        return true;
    }
    static void sudokuSolver(int[][] arr,int row,int col){
        if(row==arr.length){
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr.length;j++){
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        for(int j=col;j<arr.length;j++){
            if(arr[row][j]!=0) continue;
            for(int i=1;i<=9;i++){
                if(isValidSolver(arr,row,j,i)){
                    arr[row][j]=i;
                    sudokuSolver(arr,row,j+1);
                    arr[row][j]=0;
                }
            }
            return;
        }
        sudokuSolver(arr,row+1,0);
    }
    static boolean isValidSolver(int[][] arr,int row,int col, int x){
        for(int i=0;i<arr.length;i++){
            if(arr[row][i]==x) return false;
            if(arr[i][col]==x) return false;
        }
        int k=row-row%3;
        int m=col-col%3;
        for(int i=k;i<k+3;i++){
            for(int j=m;j<m+3;j++){
                if(arr[i][j]==x) return false;
            }
        }
        return true;
    }
    static boolean validSudoku(int[][] arr,int row,int col){
        if(row==arr.length) return true;
        else if(arr[row][col]==0||isValid(arr,row,col)){
            if(col!=arr.length-1) return validSudoku(arr,row,col+1);
            else return validSudoku(arr,row+1,0);
        }
        return false;
    }
    static void helper(int[] arr,int x,int count,List<List<Integer>> ans,List<Integer> temp,int idx){
        if(count>x) return;
        else if(count==x){
            List<Integer> temp1=new ArrayList<>();
            for(Integer y:temp){
                temp1.add(y);
            }
            ans.add(temp1);
            return;
        }
        for(int i=idx;i<arr.length;i++){
            temp.add(arr[i]);
            helper(arr,x,count+arr[i],ans,temp,i);
            temp.remove(temp.lastIndexOf(arr[i]));
        }
    }
    static void printSub(int [] arr,int idx,String ans,List<List<Integer>> temp ){
        if(idx==arr.length){
            List<Integer> temp2=new ArrayList<>();
            for(int i=0;i<ans.length();i++){
                int x=ans.charAt(i)-'0';
                temp2.add(x);
            }
            temp.add(temp2);
            return;
        }
        printSub(arr,idx+1,ans+arr[idx],temp);
        printSub(arr,idx+1,ans,temp);
    }
    static int flipsum(boolean[][]check){
        int count=0;
        for(int i=0;i<check.length;i++){
            for(int j=0;j<check[0].length;j++){
                if(check[i][j]){
                    helpercheck(check,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    static void helpercheck(boolean[][]check,int row,int col){
        int n=check.length;
        int m=check[0].length;
        if(row<0||col<0) return;
        if(row>=n||col>=m) return;
        if(!check[row][col])return;
        System.out.println(row+" "+col);
        check[row][col]=false;
        helpercheck(check,row-1,col);
        helpercheck(check,row,col+1);
        helpercheck(check,row+1,col);
        helpercheck(check,row,col-1);
    }
    public static void main(String[] args) {
        int [] arr={1,2,3};
        List<List<Integer>> temp =new ArrayList<>();
        printSub(arr,0,"",temp);
        for(var x:temp){
            for(int y:x){
                System.out.print(y);
            }
            System.out.println();
        }
    }
}

