package OOPs;

public   class player {


         String name;
         int runs;
        private static double runrate;
        public player()
        {

        }
        public player(String name,int runs)
        {
            this.name=name;
            this.runs=runs;
        }
        static double getRunrate()
        {
            return runrate;
        }
         void  setRunrate(double runrate)
        {
            this.runrate=runrate;
        }


    public static void main(String[] args) {
        System.out.println(args[0]);
        player obj = new player();
        System.out.println(obj.runrate);

    }

    }


