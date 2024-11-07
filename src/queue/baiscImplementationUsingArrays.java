package queue;

public class baiscImplementationUsingArrays {
    public static class queue{
        private int front=0;
        private int rear=0;
        //operations:
        private int[] arr =new int [5];
        void add(int val){
            if(rear==arr.length){
                System.out.println("OutOfCapacity");
                return ;
            }
            arr[rear++]=val;
        }
        int poll(){
            if(front==rear){
                System.out.println("Error");
                return -1;
            }
            return arr[front++];
        }
        int peek(){
            return arr[front];
        }
        void display(){
            if(front==rear) System.out.print("[]");
            for(int i=front;i<rear;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
        int size(){
            return rear-front;
        }
    }

    public static void main(String[] args) {
        queue q=new queue();
        q.display();
    }
}
