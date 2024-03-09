import java.util.Arrays;

public class ValueCalculator {



    public static float[] divide_array(float[] a) throws InterruptedException {

        int half = a.length/2;
        float[] a1 = new float[half];
        float[] a2 = new float[half];
        long timeP;
        long start = System.currentTimeMillis();

        Arrays.fill(a, 1);

        System.arraycopy(a, 0 , a1, 0, half);

        System.arraycopy(a, half, a2, 0, half);

        Thread t1 =  new Thread(()->{
            for (int i = 0; i < half; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < half; i++) {
                a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2)) ;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();



        System.arraycopy(a1, 0, a, 0, half);

        System.arraycopy(a2, 0, a, half, half);



        timeP=System.currentTimeMillis()-start;


        System.out.println(String.format("Time work separation and connection %02d:%02d:%02d", timeP / 1000 / 60 % 60, timeP / 1000 % 60, timeP % 1000 ));

        return a;



    }





}
