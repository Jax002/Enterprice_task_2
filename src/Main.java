import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static Map<Float,Integer> map = new HashMap<>();
    static List<Float> endMaxVal=new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        float[] a = new float[1000000];
        float[] flot=ValueCalculator.divide_array(a);
        float mintime = 0;

        List<Float> lisFloat = new ArrayList<>();

        for (Float f1 :flot) {
            lisFloat.add(f1);
        }

        maxInList(lisFloat,2);
        maxInList(lisFloat,5);
        maxInList(lisFloat,10);

        for (Map.Entry<Float, Integer> entry : map.entrySet()){
            if(mintime>entry.getKey()||mintime==0){
                mintime=entry.getKey();
            }
        }

        System.out.println(String.format("%d threads take the least amount of time",map.get(mintime)));



    }



    public static void maxInList (List<Float>  array,int countThread) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Max_com> threads = new ArrayList<>();
        List<Float> subarray;
        float maxVal ;
        int partList = array.size()/countThread;
        int first_el;
        int end_el = 0;

        for (int i = 1; i <= countThread; i++) {

            first_el=(partList*i)-partList;

            if (i != countThread) {
                end_el=partList*i;
            }else if(i == countThread){
                end_el=array.size();
            }
            subarray=array.subList(first_el,end_el);

            threads.add(new Max_com(subarray));
        }

        for (Max_com max : threads) {
            max.start();
        }

        for (Max_com max : threads) {
            max.join();
        }

        maxVal=endMaxVal.get(0);

        for (Float f: endMaxVal) {
            if(maxVal<f){
                maxVal = f;
            }


        }
        map.put((float) (System.currentTimeMillis()-start),countThread);
        System.out.println("MAX VALUE"+maxVal);
    }
}