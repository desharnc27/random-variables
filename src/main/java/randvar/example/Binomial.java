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
import tools.Pascal;

/**
 *
 * @author desha
 */
public class Binomial extends NNIRandomLaw {

    private double p;
    private int n;

    public Binomial(int n,double p) {
        setProb(p);
        setN(n);
    }
    @Override
    public String getName(){
        return "Binomial"+Funcs.paramStr(n,p);
    }
    public final void setN(int n){
        this.n=n;
        if (n<1){
            throw (IPVE.positive("n", n));
        }
    }
    public final void setProb(double p) {
        this.p = p;
        if (p < 0 || p > 1) {
            throw (IPVE.proba("p", p));
        }
    }

    @Override
    public double randomExec() {
        Bernoulli b = new Bernoulli(p);
        int res = 0;
        for (int i=0;i<n;i++)
            res += b.randomExec();
        return res;
    }

    @Override
    public AnalyticSummary analyticEval() {
        double esp = p*n;
        double vari = (1 - p)*esp;
        return AnalyticSummary.buildByVar(esp, vari);
    }
    
    @Override
    public double cumulative(double d){
        if (d>n)
            return 1;
        return super.cumulative(d);
    }
    @Override
    public double exactProb(int i){
        if (i<0)
            return 0;
        return Pascal.get(n,i)*Math.pow(p,i)*Math.pow(1-p,n-i);
    }
    
    
}
