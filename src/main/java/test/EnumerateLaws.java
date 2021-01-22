/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import randvar.Bernoulli;
import randvar.BinomNeg;
import randvar.Binomial;
import randvar.Crescendo;
import randvar.Dice;
import randvar.DiceAppear;
import randvar.DiceProduct;
import randvar.ExponentialLaw;
import randvar.Geometric;
import randvar.Khi2;
import randvar.NGTW;
import randvar.NormalDist;
import randvar.PigeSR;
import randvar.Poisson;
import randvar.RandomLaw;

/**
 *
 * @author desha
 */
public class EnumerateLaws {
    public static ArrayList<RandomLaw> lawList(){
        
        double p = 0.4;
        double p1 = 0.3;
        double lambda = 1.7;
        int n = 5;
        int nFaces = 7;
        
        ArrayList<RandomLaw> list = new ArrayList<>();
        
        list.add(new Bernoulli(p));
        list.add(new BinomNeg(n,p));
        list.add(new Binomial(n,p));
        list.add(new Crescendo());
        list.add(new Dice(nFaces));
        list.add(new DiceProduct(n,nFaces));
        list.add(new DiceAppear(n,nFaces));
        list.add(new ExponentialLaw(lambda));
        list.add(new Geometric(p));
        list.add(new Khi2(14));
        list.add(new DiceAppear(n,nFaces));
        list.add(new NGTW(p,p1,n));
        list.add(new NormalDist(4,4));
        list.add(new PigeSR(7,10,11));
        list.add(new Poisson(lambda));
        
        return list;
        
        
    }
    public static void main (String [] args){
        ArrayList<RandomLaw> lawList =lawList();
        for (int i=0;i<lawList.size();i++){
            RandomLaw rl = lawList.get(i);
            System.out.println(rl.getName());
        }
    }
}
