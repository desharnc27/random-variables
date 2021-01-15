/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

import exception.IPVE;

/**
 *
 * @author desha
 */
public class Geometric extends RandomLaw {

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

}
