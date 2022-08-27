package test;

import exception.NIFE;
import multirandvar.FullCrescendo;
import randvar.example.Binomial;
import randvar.example.Geometric;

import randvar.example.Crescendo;
import randvar.examplehard.DiceScope;

import tools.SomeFunctions;

/**
 *
 * @author desha
 */
public class Test {

    public static void main0() {
        int n = 70;
        double p = 2.0 / n;

        int[] vals = new int[]{0, 1, 3, 5, 8};
        int[] vals2 = new int[vals.length];
        for (int i = 0; i < vals.length; i++) {
            vals2[i] = n - 1 - vals[i];
        }
        Binomial el = new Binomial(n, p);
        Binomial e2 = new Binomial(n, 1 - p);
        for (int i = 0; i < vals.length; i++) {
            int val = vals[i];
            int val2 = vals2[i];
            System.out.println("B" + SomeFunctions.paramStr(n, p) + " at " + val + ": " + el.cumulative(val));
            System.out.println("B" + SomeFunctions.paramStr(n, 1 - p) + " at " + val2 + ": " + e2.cumulative(val2));
        }

    }

    public static void main1() {
        DiceScope ds = new DiceScope(3, 34);
        //el.compareAnalyticToSample(10000);
        System.out.println(ds.getName());
        //double [] ends = new double []{1,4,6,7,8.1,15.3,19.5,20,20.5,25,28.8,34,37,44,53,70}; 
        double[] ends = new double[]{1, 2, 3, 4};

        System.out.println(ds.compareAnalyticCumulativeToSample(ends,10000,0.02));
        ds.compareAnalyticToSample(1000);
        /*for (int i = 0;i<10;i++){
            System.out.println(i+": "+ds.exactProb(i)+", "+ds.cumulative(i));
        }*/
    }

    public static void main(String[] args) {
        main1();
    }

}
