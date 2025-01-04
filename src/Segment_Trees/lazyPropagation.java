package Segment_Trees;

import java.util.Arrays;
import java.util.Scanner;

public class lazyPropagation {

    static class Segment{
        int [] arr;
        int [] lazy;
        int [] segment;
        public  Segment(int []arr){
            this.arr=arr;
            int n=arr.length;
            lazy=new int[4*n];
            segment=new int[4*n];
        }
        void build(int idx,int st,int end){
            if(st==end){
                segment[idx]=arr[st];
                return;
            }
            int mid=st+(end-st)/2;
            build(2*idx+1,st,mid);
            build(2*idx+2,mid+1,end);
            segment[idx]=segment[2*idx+1] +segment[2*idx+2];
        }
        void update(int l,int r,int val){
            update(0,0,arr.length-1,l,r,val);
        }
        private void update(int idx,int st,int end,int l,int r,int val){
            // Previous updates.
            if(lazy[idx]!=0){
                segment[idx]=segment[idx]+(end-st+1)*lazy[idx];

                // Propagate the value to child.

                if(st!=end){
                    lazy[2*idx+1]+=lazy[idx];
                    lazy[2*idx+2]+=lazy[idx];
                }
                lazy[idx]=0;
            }
            if(st>r ||end<l) return ;
            if(st>=l && end<=r){
                int ele=end-st+1;
                segment[idx]=segment[idx]+ele*val;
                if(st!=end){
                    lazy[2*idx+1]+=val;
                    lazy[2*idx+2]+=val;
                }
                return ;
            }
            int mid=st+(end-st)/2;
            update(2*idx+1,st,mid,l,r,val);
            update(2*idx+2,mid+1,end,l,r,val);
            segment[idx]=segment[2*idx+1]+segment[2*idx+2];
        }
        int helperQuery(int idx,int st,int end,int l,int r){
            // Previous updates.
            if(lazy[idx]!=0){
                segment[idx]=segment[idx]+(end-st+1)*lazy[idx];

                if(st!=end){
                    lazy[2*idx+1]+=lazy[idx];
                    lazy[2*idx+2]+=lazy[idx];
                }
                lazy[idx]=0;
            }

            if(st>r || end<l) return 0;
            else if( st>=l && end<=r) return segment[idx];
            int mid=st+(end-st)/2;
            int lans=helperQuery(2*idx+1,st,mid,l,r);
            int rans=helperQuery(2*idx+2,mid+1,end,l,r);
            return lans+rans;
        }
        int query(int l,int r){
            return helperQuery(0,0,arr.length-1,l,r);
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int testCase=1;
        while (testCase-- >0){
            int n=sc.nextInt();
            int [] arr=new int[n];
            for(int i=0;i<n;i++) arr[i]=sc.nextInt();
            Segment tree=new Segment(arr);
            tree.build(0,0,arr.length-1);

            int m=sc.nextInt();
            while (m-- > 0){
                int type=sc.nextInt();
                int l=sc.nextInt();
                int r=sc.nextInt();
                if(type==1){
                    System.out.println(tree.query(l,r));
                }
                else{
                    int val=sc.nextInt();
                    tree.update(l,r,val);
                }
            }
        }
    }
}
