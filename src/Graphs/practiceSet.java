package Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.HashSet;

public class practiceSet {
    public static  class DisjointSet{
        int [] parent;
        int [] rank;
        int [] size;

        public  DisjointSet(int n){
            parent =new int[n];
            rank=new int[n];
            size=new int[n];
            for(int i=0;i<n;i++){
                parent[i]=i;
                size[i]=1;
            }
        }
        int findParent(int node){
            if(node==parent[node]) return node;
            parent[node]=findParent(parent[node]);
            return parent[node];
        }
        void unionBySize(int u,int v){
            int pu=findParent(u);
            int pv=findParent(v);
            if(pu==pv) return;
            if(size[pu]<size[pv]){
                parent[pu]=pv;
                size[pv]=size[pv]+size[pu];
            }
            else {
                parent[pv]=pu;
                size[pu]=size[pu]+size[pv];
            }
        }
        void unionByRank(int u,int v){
            int pu=findParent(u);
            int pv=findParent(v);
            if(pu==pv) return;
            if(rank[pu]<rank[pv]){
                parent[pu]=pv;
            }
            else if(rank[pv]<rank[pu]){
                parent[pv]=pu;
            }
            else{
                parent[pv]=pu;
                rank[pu]+=1;
            }
        }
    }
    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        DisjointSet hs=new DisjointSet(V);
        for(int i=0;i<adj.size();i++){
            for(int j=0;j<adj.get(i).size();j++){
                if(adj.get(i).get(j)==1 &&i!=j){
                    hs.unionBySize(i,j);
                }
            }
        }
        for(int i=0;i<V;i++){
            hs.findParent(i);
        }
        HashSet<Integer> set=new HashSet<>();
        int []arr=hs.parent;
        for (int i=0;i<arr.length;i++){
            set.add(arr[i]);
        }
        return set.size();
    }

    static List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String,List<SortedSet<String>>> mp=new HashMap<>();
        int n=accounts.size();
        for(int i=0;i<n;i++){
            int m=accounts.get(i).size();
            String name=accounts.get(i).get(0);
            if(!mp.containsKey(name)){
                List<SortedSet<String>> temp=new ArrayList<>();
                SortedSet<String> hs=new TreeSet<>();
                for(int j=1;j<m;j++){
                    String s=accounts.get(i).get(j);
                    hs.add(s);
                }
                temp.add(hs);
                mp.put(name,temp);
            }
            else{
                boolean check=true;
                List<SortedSet<String>> pset=mp.get(name);
                int size=pset.size();
                SortedSet<String> hs=new TreeSet<>();
                int idx=0;
                for(int j=1;j<m;j++){
                    String s=accounts.get(i).get(j);
                    hs.add(s);

                    for(int k=0;k<size;k++){
                        if(pset.get(k).contains(s)){
                            idx=k;
                            check=false;
                            break;
                        }
                    }
                    if(!check) break;
                }
                if(check){
                    pset.add(hs);
                    mp.put(name,pset);
                }
                else{
                    for(int j=1;j<m;j++){
                        String s1= accounts.get(i).get(j);
                        pset.get(idx).add(s1);
                    }
                    mp.put(name,pset);
                }
            }
        }
        List<List<String>> ans=new ArrayList<>();
        System.out.println(mp);
        for(String key:mp.keySet()){
            int size=mp.get(key).size();
            for(int i=0;i<size;i++){
                List<String> temp=new ArrayList<>();
                temp.add(key);
                for(String s:mp.get(key).get(i)){
                    temp.add(s);
                }
                ans.add(temp);
            }
        }
        return ans;
    }
    public static List<List<String>> arraysToList(String[][] arrays) {
        List<List<String>> list = new ArrayList<>();
        for (String[] array : arrays) {
            List<String> innerList = new ArrayList<>();
            for (String element : array) {
                innerList.add(element);
            }
            list.add(innerList);
        }
        return list;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        List<List<String>> accounts=new ArrayList<>();

    }
}
