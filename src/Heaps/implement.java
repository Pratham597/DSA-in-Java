package Heaps;

import java.util.ArrayList;

public class implement {
    public static  class Heap{
        private ArrayList<Integer> arr;
        boolean priority=false;
        public Heap(boolean priority){
            arr=new ArrayList<>();
            this.priority=priority;
        }
        public Heap(){
            arr=new ArrayList<>();
        }
        private void downheapify(ArrayList<Integer> arr,int idx,boolean check){
            if(idx>=arr.size()) return ;
            int left=2*idx+1<arr.size()?2*idx+1:idx;
            int right=2*idx+2<arr.size()?2*idx+2:idx;
            int min=idx;
            if(!check&&left<arr.size()&&arr.get(min)>arr.get(left)) min=left;
            if(!check&&right<arr.size()&&arr.get(min)>arr.get(right)) min=right;
            if(check&&left<arr.size()&&arr.get(min)<arr.get(left)) min=left;
            if(check&&right<arr.size()&&arr.get(min)<arr.get(right)) min=right;
            if(min!=idx){
                int temp=arr.get(min);
                arr.set(min,arr.get(idx));
                arr.set(idx,temp);
                downheapify(arr,min,check);
            }
        }
        private void upheapify(ArrayList<Integer> arr){
            int idx=arr.size()-1;
            int parent=(idx-1)/2;
            while(parent>=0&&arr.get(idx)<arr.get(parent)){
                int temp=arr.get(parent);
                arr.set(parent,arr.get(idx));
                arr.set(idx,temp);
                idx=parent;
                parent  =(idx-1)/2;
            }
        }
        private void upheapify2(ArrayList<Integer> arr,int idx){
            if(idx<=0) return ;
            int parent=(idx-1)/2;
            if(arr.get(idx)>arr.get(parent)){
                int temp=arr.get(idx);
                arr.set(idx,arr.get(parent));
                arr.set(parent,temp);
                upheapify2(arr,parent);
            }
        }
        void insert(int val){
            arr.add(val);
            if(!priority)upheapify(arr);
            else upheapify2(arr,arr.size()-1);
        }
        int remove(){
            int temp=arr.get(0);
            int last=arr.size()-1;
            arr.set(0,arr.get(last));
            arr.remove(last);
            downheapify(arr,0,priority);
            return temp;
        }
        private void helperDisplay(ArrayList<Integer> arr,int idx){
            if(idx>=arr.size()) return ;
            System.out.print(arr.get(idx)+" ");
            helperDisplay(arr,2*idx+1);
            helperDisplay(arr,2*idx+2);
        }
        void display(){
            helperDisplay(arr,0);
            System.out.println();
        }
    }
    static void createHeapFromArray(ArrayList<Integer> arr,int idx){
        if(idx>=arr.size()) return ;
        createHeapFromArray(arr,2*idx+1);
        createHeapFromArray(arr,2*idx+2);
        downheapifyhelper(arr,idx,true);

    }
    static void downheapifyhelper(ArrayList<Integer> arr,int idx,boolean check){
        if(idx>=arr.size()) return ;
        int left=2*idx+1<arr.size()?2*idx+1:idx;
        int right=2*idx+2<arr.size()?2*idx+2:idx;
        int min=idx;
        if(!check&&left<arr.size()&&arr.get(min)>arr.get(left)) min=left;
        if(!check&&right<arr.size()&&arr.get(min)>arr.get(right)) min=right;
        if(check&&left<arr.size()&&arr.get(min)<arr.get(left)) min=left;
        if(check&&right<arr.size()&&arr.get(min)<arr.get(right)) min=right;
        if(min!=idx){
            int temp=arr.get(min);
            arr.set(min,arr.get(idx));
            arr.set(idx,temp);
            downheapifyhelper(arr,min,check);
        }
    }
    public static void main(String[] args) {
        Heap hp=new Heap();
        int [] arr={2,7,6,9,10,5,4,3};
        ArrayList list=new ArrayList();
        for(int x:arr) list.add(x);
        createHeapFromArray(list,0);
        System.out.println(list);
    }
}
