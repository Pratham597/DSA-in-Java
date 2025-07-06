package Segment_Trees;
public class rangeSum {
    public static class Segment{
        int [] segment;
        int [] arr;

        public Segment(int [] arr){
            this.arr=arr;
            int n=arr.length;
            segment=new int[4*n];
        }

        private void helperBuild(int st,int end,int idx){
            if(st==end){
                segment[idx]=arr[st];
                return;
            }
            int  mid=st+(end-st)/2;
            helperBuild(st,mid,2*idx+1);
            helperBuild(mid+1,end,2*idx+2);
            segment[idx]=segment[2*idx+1]+segment[2*idx+2];
        }
        public void build(){
            helperBuild(0,arr.length-1,0);
        }
        private int helperQuery(int idx,int st,int end,int l,int r,int [] segment){
            if(st>=l && end<=r) return segment[idx];
            else if(end<l || st>r) return Integer.MIN_VALUE;
            int mid=st+(end-st)/2;
            int lans=helperQuery(2*idx+1,st,mid,l,r,segment);
            int rans=helperQuery(2*idx+2,mid+1,end,l,r,segment);
            return lans+rans;
        }
        public int query(int l,int r){
            return helperQuery(0,0,arr.length-1,l,r,segment);
        }

        // Point Updates.
        public void update(int st,int end,int pt,int val,int idx,int [] segment){
            if(st==end){
                segment[idx]=val;
                return;
            }
            int mid=st+(end-st)/2;
            if(pt<=mid) {
                update(st,mid,pt,val,idx,segment);
            }
            else{
                update(mid+1,end,pt,val,idx,segment);
            }
            segment[idx]=segment[2*idx+1]+segment[2*idx+2];
        }
    }
    public static void main(String[] args) {

    }
}
