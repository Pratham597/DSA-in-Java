package queue;

public class circularQueue {
    public static class circularqueue{
        private int front=0;
        private int rear=0;
        private int size=0;
        private int [] arr=new int[5];
        // Operations:
        void add(int val) throws Exception{
            if(size==arr.length){
                throw new Exception("QueueOverFlowError");
            }
            else if(rear<arr.length) arr[rear++]=val;
            else{
                rear=0;
                arr[rear++]=val;
            }
            size++;
        }
        int poll() throws Exception{
            if(size==0) {
                throw new Exception("QueueUnderFlow");
            }
            else{
                int x=arr[front];
                if(front==arr.length-1) front=0;
                else front++;
                size--;
                return x;
            }
        }
        int peek() throws Exception{
            if(size==0) {
                throw new Exception("QueueUnderFlow");
            }
            else return arr[front];
        }
        void display(){
            if(size==0) System.out.println("[]");
            else if(front<rear){
                for(int i=front;i<rear;i++){
                    System.out.print(arr[i]+" ");
                }
                System.out.println();
            }
            else{
                for(int i=front;i<arr.length;i++){
                    System.out.print(arr[i]+" ");
                }
                for(int i=0;i<rear;i++){
                    System.out.print(arr[i]+" ");
                }
                System.out.println();
            }
        }
    }
    public static void main(String[] args)throws Exception{
        circularqueue q=new circularqueue();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.poll();
        q.poll();
        q.add(8);
        q.add(9);
        q.display();
        for(int x:q.arr){
            System.out.print(x+" ");
        }
    }
}
