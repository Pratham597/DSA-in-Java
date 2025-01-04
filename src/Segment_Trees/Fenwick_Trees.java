package Segment_Trees;

import java.util.Arrays;
public class Fenwick_Trees {
    static class Fenwick{
        int [] arr;

        public Fenwick(int [] arr){
            this.arr=arr;
        }
        void update(int point,int val){
            int i=point;
            int n=arr.length;
            while (i<n){
                arr[i]+=val;
                i=i+(i&(-i));
            }
        }
        int query(int r){
            int s=0;
            while (r>0){
                s+=arr[r];
                r=r&(r-1);
            }
            return s;
        }
        int rangeSum(int l,int r){
            return query(r)-query(l-1);
        }
        // Helps to find the lower bound that sum upto given sum(k).
    }
    public static void main(String[] args) {
        int [] arr={1,3,5};
        int []f=new int[arr.length+1];
        Fenwick f1=new Fenwick(f);
        for(int i=0;i<arr.length;i++){
            f1.update(i+1,arr[i]);
        }
        System.out.println(Arrays.toString(f1.arr));
        f1.update(2,2);
        System.out.println(Arrays.toString(f1.arr));
    }
}
