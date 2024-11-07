package queue;

import java.util.ArrayList;

public class revision {
//    public static class queue<K>{
//        private int front=0;
//        private int rear=0;
//        private int capacity=5;
//        ArrayList<K> arr=new ArrayList<>(5);
//        void insert(K val){
//            if(rear==capacity){
//                System.out.println("Error");
//                return ;
//            }
//            arr.add(rear,val);
//            rear++;
//        }
//        K poll(){
//            if(front==rear){
//                System.out.print("Error");
//                return null;
//            }
//            return arr.get(front++);
//        }
//        void display(){
//            if(front==rear) return;
//            for(int i=front;i<rear;i++){
//                System.out.print(arr.get(i)+" ");
//            }
//            System.out.println();
//        }
//        int size(){
//            return rear-front;
//        }
//    }

    public static class queue<K>{
        private int front=0;
        private int rear=0;
        private int size=0;
        private int capacity=5;
        ArrayList<K> arr=new ArrayList<>();
        void insert(K val)throws Exception{
            if(size==capacity) throw new Exception("Overflow");
            if(rear<capacity) arr.add(rear++,val);
            else{
                rear=0;
                arr.add(rear++,val);
            }
            size++;
        }
        K poll() throws Exception {
            if (size == 0) throw new Exception("Underflow");
            else if (front == capacity) front = 0;
            size--;
            return arr.get(front++);
        }
        int size(){return size;}
        void display(){
            if(size==0) return ;
            if(front<rear){
                for(int i=front;i<rear;i++){
                    System.out.print(arr.get(i));
                }
            }
            else{
                for(int i=front;i<capacity;i++){
                    System.out.print(arr.get(i));
                }
                for(int i=0;i<rear;i++){
                    System.out.print(arr.get(i));
                }
            }
            System.out.println();
        }
}
    public static void main(String[] args)throws Exception {
        queue<String> q=new queue<>();
        q.insert("pratham");
        q.insert("mah");
        q.insert("dhwani");
        q.display();
        q.poll();
        System.out.println(q.size());
        q.display();
    }
}
