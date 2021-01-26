/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar.example;

import exception.IPVE;
import randvar.AnalyticSummary;
import randvar.NNIRandomLaw;
import tools.Funcs;
import static tools.Pascal.fact;
import tools.Small;

/**
 *
 * @author desha
 */
public class Geometric extends NNIRandomLaw {

    private double p;

    public Geometric(double p) {
        setProb(p);
    }
    public final void setProb(double p){
        this.p = p;
        if (p<0 || p>1){
            throw (IPVE.proba("p",p));
        }
    }

    @Override
    public double randomExec() {
        double res = 1;
        while (Math.random() > p) {
            res += 1;
        }
        return res;
    }

    @Override
    public AnalyticSummary analyticEval() {
        double esp = 1 / p;
        double vari = (1 - p) / (p * p);
        return AnalyticSummary.buildByVar(esp, vari);
    }

    @Override
    public String getName(){
        return "Geometric"+Funcs.paramStr(p);
    }
    @Override
    public double exactProb(int i){
        if (i<1)
            return 0;
        return Math.pow(1-p,i-1)*p;
    }
    @Override
    public double cumulative(double d){
        int i = (int)(d+Small.EPSILON);
        if (i<1)
            return 0;
        return 1-Math.pow(1-p,i);
    }

}
