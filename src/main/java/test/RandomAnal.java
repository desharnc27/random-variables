/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import randvar.RandomLaw;
import randvar.SampleStatSummary;
import randvar.Standardizer;
import randvar.example.Bernoulli;
import randvar.example.Dice;
import randvar.example.ExponentialLaw;
import randvar.example.Khi2;
import randvar.example.NormalDist;
import randvar.examplehard.MaxOf;
import tools.Student;

/**
 *
 * @author desharnc27
 */
public class RandomAnal {
    public static void main0(){
        RandomLaw raw = new Bernoulli(0.5);
        RandomLaw raw2 = new Bernoulli(0.01);
        //RandomLaw raw = new ExponentialLaw(6);
        //RandomLaw raw2 = new Bernoulli(0.5);
        // RandomLaw raw2 = new NormalDist()
        System.out.println(raw);
        RandomLaw u = new Standardizer(raw);
        RandomLaw u2 = new Standardizer(raw2);
        MaxOf mo = new MaxOf(u,u2);
        SampleStatSummary sssRaw = raw.makeRandSampleStats(10000);
        SampleStatSummary sssU = u.makeRandSampleStats(10000);
        SampleStatSummary sssU2 = u2.makeRandSampleStats(10000);
        SampleStatSummary sssMo = mo.makeRandSampleStats(10000);
        System.out.println(sssRaw);
        System.out.println(sssU);
        System.out.println(sssMo);
        
    }
    public static void main (String [] args){
        main0();
    }
}
