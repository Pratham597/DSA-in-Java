package Segment_Trees;

import java.util.Arrays;
import java.util.Scanner;

public class XeniaAndBit {
    static class Segment{
        int [] arr;
        int [] segment;

        public Segment(int [] arr){
            this.arr=arr;
            int n=arr.length;
            segment=new int[4*n];
        }

        int helperBuild(int idx,int st,int end){
            if(st==end){
                segment[idx]=arr[st];
                return 0;
            }
            int mid=st+(end-st)/2;
            int lans=helperBuild(2*idx+1,st,mid);
            helperBuild(2*idx+2,mid+1,end);

            if(lans%2==0){
                segment[idx]=segment[2*idx+1]|segment[2*idx+2];
            }
            else{
                segment[idx]=segment[2*idx+1]^segment[2*idx+2];
            }
            return lans+1;
        }
        void build(){
            helperBuild(0,0,arr.length-1);
        }
        int helperUpdate(int idx,int st,int end,int pt,int val){
            if(st==end){
                segment[idx]=val;
                return 0;
            }
            int mid=st+(end-st)/2;
            int lans;
            if(pt<=mid) {
                lans = helperUpdate(2 * idx + 1, st, mid, pt, val);
            }
            else{
                lans=helperUpdate(2*idx+2,mid+1,end,pt,val);
            }
            if(lans%2==0){
                segment[idx]=segment[2*idx+1]|segment[2*idx+2];
            }
            else{
                segment[idx]=segment[2*idx+1]^segment[2*idx+2];
            }
            return lans+1;

        }
        int update(int idx,int val){
            helperUpdate(0,0,arr.length-1,idx-1,val);
            return segment[0];
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int testCase=1;
        while (testCase-- >0){
            int n=sc.nextInt();
            int m=sc.nextInt();

            int nTotal=(int)Math.pow(2,n);
            int [] arr=new int[nTotal];
            for(int i=0;i<nTotal;i++){
                arr[i]=sc.nextInt();
            }
            Segment tree=new Segment(arr);
            tree.build();
            for(int i=0;i<m;i++){
                int pt=sc.nextInt();
                int val=sc.nextInt();
                System.out.println(tree.update(pt,val));
            }
        }
    }
}
