package Linkedlist;

public class linkedlist1 {
    public static class node{
        int data;
        node next;
        node(int data){
            this.data=data;
        }
//        node(){
//
//        }
    }
    public static class linklist{
        node head =null;
        node tail=null;
        void add(int val){
            node temp=new node(val);
            if(head==null)
            {
                head=temp;
                tail=temp;

            }
            else{
                tail.next=temp;
                tail=temp;
            }

        }
         void display(){
            node temp=head;
            while(temp!=null)
            {
                System.out.print(temp.data+" ");
                temp=temp.next;
            }
             System.out.println();
        }
        void addAtBegin(int val){
            if(head==null)
            {
                node temp=new node(val);
                head=temp;
                tail=temp;
            }
            else{
                node temp= new node(val);
                temp.next=head;
                head=temp;
            }
        }
        node getNode(int idx){
            node temp=head;
            for(int i=0;i<idx;i++)
            {
                temp=temp.next;
            }
            return temp;
        }
        int size(){
            int count =0;
            node temp=head;
            while(temp!=null)
            {
                count++;
                temp=temp.next;
            }
            return count;
        }
        void deleteAt(int idx){
            if(idx==0) {
                head = head.next;
            }
            else if(idx<0||idx>=size()) {
                System.out.println("Invalid index");
                return;
            }
            else
            {   node temp=head;
                for(int i=0;i<idx-1;i++){
                   temp=temp.next;
                }
                temp.next=temp.next.next;
                if(idx==size())
                {
                    tail=temp;
                }
            }
        }
        void  insert(int position,int val ){
            if(position>size()){
                for(int i=size();i<position;i++){
                    add(0);
                }

            }
            node ans =new node(val);
            node temp=head;
            if(position==0) addAtBegin(val);
            else if(position==size()) add(val);
            else {
                for (int i = 0; i < position - 1; i++) {

                    temp = temp.next;
                }

                ans.next = temp.next;
                temp.next = ans;
            }

        }

    }
    public static void main(String[] args) {
        linklist l1 = new linklist();
        l1.addAtBegin(3);
        l1.add(5);//5-> null
        l1.add(6);//5-> 6
        l1.add(7);//5-> 7

        l1.addAtBegin(2);//2,3,5,6,7

        l1.insert(0,4);//4 2 3 5 6 7
        l1.display();
        l1.deleteAt(5);
        l1.display();
       // System.out.println(l1.tail.data);
    }
}
