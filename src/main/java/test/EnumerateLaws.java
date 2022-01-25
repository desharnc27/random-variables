package test;

import java.util.ArrayList;
import randvar.NNIRandomLaw;
import randvar.example.Bernoulli;
import randvar.example.BinomNeg;
import randvar.example.Binomial;
import randvar.example.Crescendo;
import randvar.example.Dice;
import randvar.examplehard.DiceAppear;
import randvar.examplehard.DiceProduct;
import randvar.example.ExponentialLaw;
import randvar.example.Geometric;
import randvar.example.Khi2;
import randvar.examplehard.NGTW;
import randvar.example.NormalDist;
import randvar.example.Poisson;
import randvar.RandomLaw;
import randvar.example.Hypergeometric;

/**
 *
 * @author desha
 */
public class EnumerateLaws {

    public static ArrayList<RandomLaw> lawList() {

        double p = 0.4;
        double p1 = 0.3;
        double lambda = 1.7;
        int n = 5;
        int nbFaces = 7;

        ArrayList<RandomLaw> list = new ArrayList<>();

        list.add(new Bernoulli(p));
        list.add(new BinomNeg(n, p));
        list.add(new Binomial(n, p));
        list.add(new Crescendo());
        list.add(new Dice(nbFaces));
        list.add(new DiceProduct(n, nbFaces));
        list.add(new DiceAppear(n, nbFaces));
        list.add(new ExponentialLaw(lambda));
        list.add(new Geometric(p));
        list.add(new Khi2(14));
        list.add(new NGTW(p, p1, n));
        list.add(new NormalDist(4, 4));
        list.add(new Hypergeometric(7, 10, 11));
        list.add(new Poisson(lambda));

        return list;

    }

    public static void main(String[] args) {
        //double [] ends  = new double []{0,1,3,5,7,10,15,23,47};
        double[] ends = new double[]{3};
        ArrayList<RandomLaw> lawList = lawList();
        for (int i = 0; i < lawList.size(); i++) {
            RandomLaw rl = lawList.get(i);
            //System.out.println(rl.getName());
            if (rl instanceof NNIRandomLaw) {
                NNIRandomLaw irl = (NNIRandomLaw) rl;
                System.out.println(irl + "_excumu: " + irl.verifyCumulativeVsExact());
                System.out.println(irl + "_exmean: " + irl.verifyStatSummaryVsExact());
                System.out.println(irl + "_exyoyo: " + irl.compareAnalyticCumulativeToSample(ends, 50, 0.02));
            }
        }
    }
}
