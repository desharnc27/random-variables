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
import tools.Small;

/**
 *
 * @author desha
 */
public class Bernoulli extends NNIRandomLaw {

    private double p;

    public Bernoulli(double p) {
        setProb(p);
    }
    @Override
    public String getName(){
        return "Bernoulli"+Funcs.paramStr(p);
    }

    public final void setProb(double p) {
        this.p = p;
        if (p < 0 || p > 1) {
            throw (IPVE.proba("p", p));
        }
    }

    @Override
    public double randomExec() {
        return (Math.random() < p) ? 1 : 0;
    }

    @Override
    public AnalyticSummary analyticEval() {
        double esp = p;
        double vari = (1 - p)*p;
        return AnalyticSummary.buildByVar(esp, vari);
    }
    
    
    @Override
    public double cumulative(double d){
        if (d>1)
            return 1;
        return super.cumulative(d);
    }
    @Override
    public double exactProb(int i){
        switch(i){
            case 0:
                return 1-p;
            case 1: 
                return p;
            default:
                return 0;
        }
    }
}
