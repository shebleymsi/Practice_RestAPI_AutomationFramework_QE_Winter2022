package Utils;

import java.util.Random;

public class LearnRandomNumber {


    public static void main(String[] args) {
        double[] priceList = new double[10];

        int[] stId = new int[18];
        stId[0]=200;
        stId[1]=204;
        stId[2]=205;

        Random random = new Random(1);

        for (int i = 0; i < stId.length; i++) {
            stId[i] = random.nextInt(100);
            System.out.println(stId[i]);
        }


        System.out.println("*********************** Using Math.random() ");


        System.out.println(Math.random());
        System.out.println("SID101"+Math.random());

        System.out.println("************** Generate Random Email Address");

        System.out.println("demo"+  randomNumberGenerate()  +"@gmail.com");
        System.out.println("646-789-1"+  randomNumberGenerate());

    }


    public static int randomNumberGenerate(){
      //  Random ran=new Random();
       // Random random=new Random( ran.nextInt(10));
        Random random=new Random();
        return random.nextInt(1000);
    }



}
