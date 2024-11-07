package stack;

import java.util.ArrayList;

public class implementationUsingArrays {
    public static class stacks{
        private int [] arr=new int[5];
        private int idx=0;

        boolean isFull(){
            if(idx==arr.length){
                System.out.println("ERROR OVERFLOW");
                return true;
            }
            return false;
        }
        boolean isEmpty(){
            if(idx==0){
                System.out.println("ERROR UNDERFLOW");
                return true;
            }
            return false;
        }
        void add(int val){
            if(isFull()) return;
            arr[idx]=val;
            idx+=1;
        }
        int size(){
            return idx;
        }
        int peek(){
            if(isEmpty()) return -1;
            return arr[idx-1];
        }
        int pop(){
            if(isEmpty()) return -1;
            int x=arr[idx-1];
            arr[idx-1]=0;
            idx-=1;
            return x;
        }
        void display(){
            for(int i=0;i<idx;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();

        }
    }
    public static class stack {
        private ArrayList<Integer> arr = new ArrayList<>();

        void push(int val) {
            arr.add(val);
        }

        int peek() {
            return arr.getLast();
        }

        int pop() {
            if (arr.isEmpty()) {
                System.out.println("ERROR StackUnderflow");
                return 0;
            }
            return arr.removeLast();
        }

        int size() {
            return arr.size();
        }

        void display() {

            for (int x : arr) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        stacks st=new stacks();
        st.add(7);
        st.add(8);
        st.add(9);
        st.add(10);
        st.add(11);
    }
}
//class stack {
//    private ArrayList<Integer> arr = new ArrayList<>();
//
//    void push(int val) {
//        arr.add(val);
//    }
//    int peek() {
//        return arr.getLast();
//    }
//
//    int pop() {
//        if (arr.isEmpty()) {
//            System.out.println("ERROR StackUnderflow");
//            return 0;
//        }
//        return arr.removeLast();
//    }
//
//    int size() {
//        return arr.size();
//    }
//
//    void display() {
//        for (int x : arr) {
//            System.out.print(x + " ");
//        }
//        System.out.println();
//    }
//}
