package Segment_Trees;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class SerejaAndBrackets {
    static class Node{
        int left;
        int right;
        int full;
        public Node(int left,int right,int full){
            this.left=left;
            this.right=right;
            this.full=full;
        }
    }
    static class Segment{
        Node [] segment;
        String s;

        public Segment(String s){
            this.s=s;
            int n=s.length();
            segment=new Node[4*n];
        }
        private void helperBuild(int st,int end,int idx){
            if(st==end){
                char c=s.charAt(st);
                if(c=='(') segment[idx]=new Node(1,0,0);
                else segment[idx]=new Node(0,1,0);
                return ;
            }
            int mid=(st+end)/2;
            helperBuild(st,mid,2*idx+1);
            helperBuild(mid+1,end,2*idx+2);

            Node left=segment[2*idx+1];
            Node right=segment[2*idx+2];

            int sfull=Math.min(left.left,right.right);
            int full=left.full+right.full+sfull;
            int fright=left.right+right.right-sfull;
            int fleft=right.left+left.left-sfull;
            segment[idx]=new Node(fleft,fright,full);
        }
        public void build(){
            helperBuild(0,s.length()-1,0);
        }

        private Node  helperQuery(int idx,int st,int end,int l,int r){
            if(st>=l && end<=r) return segment[idx];
            else if(end<l || st>r) return new Node(0,0,0);
            int mid=(st+end)/2;
            Node left=helperQuery(2*idx+1,st,mid,l,r);
            Node right=helperQuery(2*idx+2,mid+1,end,l,r);

            int sfull=Math.min(left.left,right.right);
            int full=left.full+right.full+sfull;
            int fright=left.right+right.right-sfull;
            int fleft=right.left+left.left-sfull;
            return new Node(fleft,fright,full);

        }
        public int query(int l,int r){
            return 2*helperQuery(0,0,s.length()-1,l,r).full;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int testCase=1;
        while (testCase-- >0){
            String s=sc.next();

            int m=sc.nextInt();

            Segment tree=new Segment(s);
            tree.build();
            for(int i=0;i<m;i++){
                int l=sc.nextInt();
                int r=sc.nextInt();
                System.out.println(tree.query(l-1,r-1));
            }
        }
    }
}
