package OOPs;

public class fraction {
    public static void main(String[] args) {
        problem p1 = new problem(21, 14);
        System.out.println(p1.num + "/" + p1.denom);
        problem p2 = new problem(7, 14);
        problem p3 = add(p1, p2);
        System.out.println(p3.num + "/" + p3.denom);
        problem p4 = mul(p1, p2);
        System.out.println(p4.num + "/" + p4.denom);


    }


    static problem add(problem p1, problem p2) {
        int n = p1.num * p2.denom + p2.num * p1.denom;
        int d = p1.denom * p2.denom;
        return new problem(n, d);

    }

    static problem mul(problem p1, problem p2) {
        int n = p1.num * p2.num;
        int d = p1.denom * p2.denom;
        problem p3 = new problem(n, d);
        return p3;

    }
}

class problem{
    int num ;
    int denom;
    problem()
    {
    }
    problem(int num,int denom)
     {
         this.num=num;
         this.denom=denom;
         int fact= gcd(num,denom);
         this.num/=fact;
         this.denom/=fact;

     }
     void simplify()
     {
         int fact= gcd(num,denom);
         num/=fact;
         denom/=fact;

     }
     static int gcd(int x , int y)
     {
         if(y==0) return x;
         return gcd( y,x%y);
     }


}
