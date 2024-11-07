package Graphs;
import  java.util.*;
public class KruskalAlgo {
    public static void main(String[] args) {

    }
}

class Solution{
    static class DisjointSet{
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
    static class ListComparator<T extends Comparable<T>> implements Comparator<List<T>> {

        @Override
        public int compare(List<T> o1, List<T> o2) {
            for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
                int c = o1.get(i).compareTo(o2.get(i));
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        }

    }
    static int spanningTree(int V, int E, int edges[][]){
        DisjointSet ds =new DisjointSet(V);
        List<List<Integer>> adj=new ArrayList<>();
        for(int [] arr:edges){
            int node=arr[0];
            int adjnode=arr[1];
            int adjwt=arr[2];
            List<Integer> temp=new ArrayList<>();
            temp.add(adjwt);
            temp.add(node);
            temp.add(adjnode);
            adj.add(temp);
        }
        int size=0;
        Collections.sort(adj,new ListComparator<>());
        for(int i=0;i<E;i++){
            int wt=adj.get(i).get(0);
            int pnode=adj.get(i).get(1);
            int padj=adj.get(i).get(2);
            if(ds.findParent(pnode)!=ds.findParent(padj)){
                size+=wt;
                ds.unionBySize(pnode,padj);
            }
        }
        return size;
    }
}
