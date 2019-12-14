import com.sun.tools.corba.se.idl.toJavaPortable.Helper;

import java.util.*;
import java.util.stream.Collectors;

public class BucketSort <X extends Comparable<X>> {

    /**
     * Constructor for BucketSort
     *
     * @param k the number of buckets.
     * @param helper an explicit instance of Helper to be used.
     */
    public BucketSort(int k, Helper helper) {
        this.k = k;
        this.helper = helper;
    }

    /**
     * Constructor for ShellSort
     *
     * @param k the number of buckets
     */
    public BucketSort(int k) {
        this(k, new Helper());
    }

    /**
     * Method to sort a sub-array of an array of Xs.
     * <p>
     * TODO check that the treatment of from and to is correct. It seems to be according to the unit tests.
     *
     * @param xs an array of Xs to be sorted in place.
     */
    public void sort(Integer[]  xs, int from, int to) {
        // TODO ....
        int minValue=xs[0];
        int maxValue=xs[0];
        for(int i = from;i<to;i++)
        {
            maxValue=Math.max(maxValue,xs[i]);
            minValue=Math.min(minValue,xs[i]);
        }
        int mediumValue=(maxValue+minValue)/2+1;
        ArrayList<ArrayList<Integer>> buckets=new ArrayList<>(this.k);
        for(int i = 0 ;i< this.k;i++)
            buckets.add(new ArrayList<Integer>());
        for(int i=from;i<to;i++)
        {
            int index=(xs[i] )/mediumValue; //put elements into 2 buckets
            buckets.get(index).add(xs[i]);
        }
        for(int i=0;i<buckets.size();i++)
            Collections.sort(buckets.get(i));
        List<Integer> result = buckets.stream().flatMap(numbers->numbers.stream())
                .collect(Collectors.toList());
        result.toArray(xs);
    }


    public Helper getHelper() {
        return helper;
    }

    @Override
    public String toString() {
        return helper.toString();
    }
    private final int k;
    private final Helper helper;

    /**
     * An example main program.
     * @param args the command-line args (ignored).
     */

    public static void main(String[] args) {

        /**
         * Have a try.
         */
         BucketSort s = new BucketSort(2);
         Integer[] array = {5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4};
         System.out.println("Before:\t\t\t\t\t" + Arrays.toString(array));
         s.sort(array, 0,array.length);
         System.out.println("After:\t\t\t\t\t" + Arrays.toString(array));

        /**
         *
         * We analyze the effect of array size on time complexity
         */
//        BucketSort s = new BucketSort(512);
//        Random random = new Random();
//        for(int size = 10000;size<=1280000;size*=2){
//        Integer[] array = new Integer[size];
//        long time;
//        long startTime = System.currentTimeMillis();
//        for (int t = 0; t < 10; t++)
//        {
//            for (int i = 0; i < array.length; i++) array[i] = random.nextInt(size);
//            s.sort(array, 0, array.length);
//        }
//        long endTime = System.currentTimeMillis();
//        time = (endTime - startTime);
//        System.out.println("Time complexity is "+time+"; Arrary is "+size+";  ^_^");}


            /**
            *
            * We analyze the effect of bucket size on time complexity.
            */

//            for (int bucket = 2; bucket <= 1024; bucket *= 2) {
//            BucketSort s = new BucketSort(bucket);
//            Random random = new Random();
//            Integer[] array = new Integer[320000];
//            long time;
//            long startTime = System.currentTimeMillis();
//            for (int t = 0; t < 10; t++) {
//                for (int i = 0; i < array.length; i++) array[i] = random.nextInt(1280000);
//                s.sort(array, 0, array.length);
//            }
//            long endTime = System.currentTimeMillis();
//            time = (endTime - startTime);
//            System.out.println("Time complexity is " + time + "; Bucket is "+bucket+";  ^_^");
//            }



    }
}
