package Graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class implement {
    //using adjacency matrix:
    public static void input(int [][] arr,int row,int col){
        arr[row][col]=1;
        arr[col][row]=1;
    }
    public static class Graphs{
        private ArrayList<ArrayList<Integer>> arr=new ArrayList<>();
        public Graphs(int nodes){
            for(int i=0;i<nodes+1;i++){
                arr.add(new ArrayList<Integer>());
            }
        }
        void insert(int row,int col){
            arr.get(row).add(col);
            arr.get(col).add(row);
        }
    }
    static void bfs(ArrayList<ArrayList<Integer>> arr,int root){
        Queue<Integer> q=new LinkedList<>();
        HashSet<Integer> hs=new HashSet<>();
        //we can use array of boolean also that mark whichever node is visited.
        //but its simple to use hashset for checking elements.
        q.add(root);
        hs.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                int pos=q.poll();
                System.out.print(pos+" ");
                int child=arr.get(pos).size();
                for(int j=0;j<child;j++){
                    int ele=arr.get(pos).get(j);
                    if(!hs.contains(ele)){
                        hs.add(ele);
                        q.add(ele);
                    }
                }
            }
            System.out.println();
        }
    }
    static void dfs(ArrayList<ArrayList<Integer>> arr,int st,boolean [] isVisited,ArrayList<Integer> ans){
        if(isVisited[st]) return;
        isVisited[st]=true;
        ans.add(st);
        for(Integer x:arr.get(st)){
            dfs(arr,x,isVisited,ans);
        }
    }
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> arr=new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            arr.add(new ArrayList<>());
        }
        arr.get(1).add(2);
        arr.get(1).add(6);
        arr.get(2).add(1);
        arr.get(2).add(3);
        arr.get(2).add(4);
        arr.get(6).add(1);
        arr.get(6).add(7);
        arr.get(6).add(8);
        arr.get(4).add(5);
        arr.get(7).add(5);
        ArrayList<Integer> ans=new ArrayList<>();
        boolean[] isVisited=new boolean[9];
        dfs(arr,1,isVisited,ans);
        for(Integer x:ans){
            System.out.print(x+" ");
        }
    }
}
