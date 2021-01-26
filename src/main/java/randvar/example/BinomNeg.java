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
public class BinomNeg extends NNIRandomLaw {

    private double p;
    private int n;

    public BinomNeg( int n, double p ) {
        setProb(p);
        setN(n);
    }
    @Override
    public String getName(){
        return "NegativeBinomial"+Funcs.paramStr(n,p);
    }
    public final void setProb(double p){
        this.p = p;
        if (p<0 || p>1){
            throw  (IPVE.proba("p",p));
        }
    }
    public final void setN(int i){
        n=i;
        if (n<1){
            throw  (IPVE.positive("n", n));
        }
    }
    @Override
    public double randomExec() {
        double res =0;
        Geometric gl = new Geometric(p);
        for (int i=0;i<n;i++)
            res += gl.randomExec();
        return res;
    }

    @Override
    public AnalyticSummary analyticEval() {
        double esp = n / p;
        double vari = n* (1 - p) / (p * p);
        return AnalyticSummary.buildByVar(esp, vari);
    }
    
    @Override
    public double exactProb(int i){
        if (i<n)
            return 0;
        double res = Math.pow(p,n)*Math.pow(1-p,i-n);
        res *= Pascal.get(i-1,n-1);
        return res;
    }

}
