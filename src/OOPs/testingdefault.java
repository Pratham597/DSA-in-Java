package OOPs;

public class testingdefault {

    static int x=0;
    int y=0;
    static void testing()
    {
        player obj2= new player("Pratham",96);
        // void functions can access static variable .
          System.out.println(player.getRunrate());
          //static functions cant access any other variable except static .
//        String [] ans=new String[10];
//
//        player.main(ans);
//        System.out.println(obj2.name);
//        System.out.println(obj2.runs);
//        player obj3=new player();
//        obj3.name="Raghav";
//        System.out.println(obj3.name);
//        obj2.setRunrate(92);
//        System.out.println(obj2.getRunrate());

    }
    void printHello(){
        System.out.println("Hell World");
    }
    public static void main(String[] args) {
       testingdefault obj=new testingdefault();
       obj.testing();
    }
}
// Static functions can access only static variables.
//Class can call directly static functions without creating an object but non-static functions can't be accessed
// directly.
