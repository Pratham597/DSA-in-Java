package Segment_Trees;

import java.util.ArrayList;

public class basicImplement {
    public static class Segment{
        int [] segment;
        int [] arr;

        public Segment(int [] arr){
            this.arr=arr;
            int n=arr.length;
            segment=new int[4*n];
        }

        private int helperBuild(int st,int end,int idx,int [] segment,int [] arr){
            if(st==end){
                segment[idx]=arr[st];
                return segment[idx];
            }
            int  mid=st+(end-st)/2;
            int lans=helperBuild(st,mid,2*idx+1,segment,arr);
            int rans=helperBuild(mid+1,end,2*idx+2,segment,arr);
            return segment[idx]=Math.max(lans,rans);
        }
        public void build(){
            helperBuild(0,arr.length-1,0,segment,arr);
        }
        private int helperQuery(int idx,int st,int end,int l,int r,int [] segment){
            if(st>=l && end<=r) return segment[idx];
            else if(end<l || st>r) return Integer.MIN_VALUE;
            int mid=st+(end-st)/2;
            int lans=helperQuery(2*idx+1,st,mid,l,r,segment);
            int rans=helperQuery(2*idx+2,mid+1,end,l,r,segment);
            return Math.max(lans,rans);
        }
        public int query(int l,int r){
            return helperQuery(0,0,arr.length-1,l,r,segment);
        }
        // Point Updates.
        public int update(int st,int end,int pt,int val,int idx,int [] segment){
            if(st==end){
                return segment[idx]=val;
            }
            int mid=st+(end-st)/2;
            if(pt<=mid) {
                return segment[idx]=Math.max(segment[idx],update(st,mid,pt,val,2*idx+1,segment));
            }
            else{
                return segment[idx]=Math.max(segment[idx],update(mid+1,end,pt,val,2*idx+2,segment));
            }
        }
    }
    public static void main(String[] args) {
        int [] arr={2,3,1,4,5};
        Segment stree=new Segment(arr);
        stree.build();
        System.out.println(stree.query(0,2));
    }
}
