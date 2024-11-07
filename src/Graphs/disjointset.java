package Graphs;

import java.util.*;

public class disjointset {
    public static class DisjointSet{
        List<Integer> parent;
        List<Integer> rank;
        List<Integer> size;
        public DisjointSet(int n){
            parent=new ArrayList<>();
            rank=new ArrayList<>();
            size=new ArrayList<>();
            for(int i=0;i<n;i++){
                rank.add(0);
                parent.add(i);
                size.add(1);
            }
        }
        public int findParent(int node){
            if(parent.get(node)==node) return node;
            parent.set(node,findParent(parent.get(node)));
            return parent.get(node);
        }
        public void unionByRank(int u,int v){
            int su=findParent(u);
            int sv=findParent(v);
            if(su==sv) return;
            if(rank.get(su)<rank.get(sv)){
                parent.set(su,sv);
            }
            else if(rank.get(sv)<rank.get(su)){
                parent.set(sv,su);
            }
            else{
                parent.set(sv,su);
                rank.set(su,rank.get(su)+1);
            }
        }
        public void unionBySize(int u,int v){
            int su=findParent(u);
            int sv=findParent(v);
            if(su==sv) return;
            if(size.get(su)<size.get(sv)){
                // su-> Parent is sv.
                parent.set(su,sv);
                size.set(sv,size.get(sv)+size.get(su));
            }
            else{
                //sv-Parent is su.
                parent.set(sv,su);
                size.set(su,size.get(sv)+size.get(su));
            }
        }
    }
    static int maxRemove(int[][] stones, int stone_n) {

        int maxrow=Integer.MIN_VALUE;
        int maxcol=Integer.MIN_VALUE;
        HashSet<Integer> rows=new HashSet<>();
        HashSet<Integer> cols=new HashSet<>();
        for(int i=0;i<stone_n;i++){
            int row=stones[i][0];
            int col=stones[i][1];
            rows.add(row);
            cols.add(col);
            maxrow=Math.max(row,maxrow);
            maxcol=Math.max(col,maxcol);
        }
        int n=maxrow+2;
        int m=maxcol+2;
        DisjointSet ds=new DisjointSet(n*m);
        for(int i=0;i<stone_n;i++){
            int row=stones[i][0]+1;
            int col=stones[i][1]+1;
            int ele=row*m+col;
            int u=row*m;
            int v=col;
            ds.unionBySize(ele,u);
            ds.unionBySize(ele,v);
        }
        int size=0;
        for(int i=0;i<n*m;i++){
            if(ds.findParent(i)==i){
                size+=ds.size.get(i)-1;

            }
        }
        return size-cols.size()-rows.size();

    }
    public static void main(String[] args) {
       int [][] stones={{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        System.out.println(maxRemove(stones,6));
    }
}
