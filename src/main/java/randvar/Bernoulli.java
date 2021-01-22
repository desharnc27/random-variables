/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

import exception.IPVE;
import tools.Funcs;

/**
 *
 * @author desha
 */
public class Bernoulli extends RandomLaw {

    private double p;

    public Bernoulli(double p) {
        setProb(p);
    }
    @Override
    public String getName(){
        return "Geometric"+Funcs.paramStr(p);
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
}
