/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar.example;

import randvar.AnalyticSummary;
import randvar.NNIRandomLaw;
import tools.Funcs;
import tools.Pascal;
import tools.Small;

/**
 *
 * @author desha
 */
public class Crescendo extends NNIRandomLaw {
    @Override
    public String getName(){
        return "Crescendo"+Funcs.paramStr();
    }
    @Override
    public double randomExec() {
        double count = 0;
        double val = 0;
        double previousVal = 0;
        do {
            previousVal = val;
            val = Math.random();
            count += 1;
        } while (val > previousVal);
        return count;
    }

    @Override
    public AnalyticSummary analyticEval() {
        double e =Math.E;
        return new AnalyticSummary(e,3*e);
    }
    
    @Override
    public double exactProb(int i){
        if (i<2)
            return 0;
        double res = (i-1)/(double)Pascal.doubleFact(i);
        return res;
    }
    @Override
    public double cumulative(double d){
        int i = (int)(d+Small.EPSILON);
        if (i<2)
            return 0;
        double res = 1-1/(double)Pascal.doubleFact(i);
        return res;
    }

}
