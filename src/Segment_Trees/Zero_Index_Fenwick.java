package Segment_Trees;

public class Zero_Index_Fenwick {
    static class NumArray {
        int [] arr;
        int [] fenwick;
        public NumArray(int[] arr) {
            this.arr=arr;
            this.fenwick=new int[arr.length+1];
            for(int i=0;i<arr.length;i++){
                build(i,arr[i]);
            }
        }
        public void build(int idx,int val){
            int i=idx+1;
            while(i<fenwick.length){
                fenwick[i]+=val;
                i=i+(i&(-i));
            }
        }
        public void update(int idx, int v) {
            int i=idx+1;
            int val=v-arr[idx];
            arr[idx]=v;
            while(i<fenwick.length){
                fenwick[i]+=val;
                i=i+(i&(-i));
            }
        }
        public int query(int r){
            int s=0;
            while (r>0){
                s+=fenwick[r];
                r=r&(r-1);
            }
            return s;
        }
        public int sumRange(int l, int r) {
            return query(r+1)-query(l);
        }
    }
}
