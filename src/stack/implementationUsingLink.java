package stack;

public class implementationUsingLink{
    public static class Node{
        int val;
        Node next;
        Node (int val){
            this.val=val;
        }

    }
    public static class stack{
        private Node head=null;//Pointing the top element of stack
        private int size=0;

        //Creating functions of that data  type
        void push(int val){
            Node temp=new Node(val);
            temp.next=head;
            head=temp;
            size+=1;
        }
        int pop(){
            if(head==null){
                System.out.println("Underflow");
                return -1;
            }
            int x=head.val;
            head=head.next;
            size--;
            return x;
        }
        int peek(){
            if(head==null){
                System.out.println("Underflow");
                return -1;
            }
            return head.val;
        }
        void display(){
            Node temp=head;
            printDisplay(temp);
            System.out.println();
        }
        private void printDisplay(Node temp){
            if(temp==null) return;
            printDisplay(temp.next);
            System.out.print(temp.val+" ");
        }
        int size(){//getter
            return size;
        }
    }
    public static void main(String[] args) {
        stack st=new stack();
        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);
        System.out.println(st.peek());
        st.pop();
        System.out.println(st.peek());
    }
}
