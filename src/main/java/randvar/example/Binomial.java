/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar.example;

import exception.IPVE;
import exception.TLVE;
import randvar.AnalyticSummary;
import randvar.NNIRandomLaw;
import randvar.Prints;
import tools.Funcs;
import tools.Pascal;
import tools.Small;

/**
 *
 * @author desha
 */
public class Binomial extends NNIRandomLaw {

    //private final static double NORMAL_THRESHOLD = 10;
    private final static double POISSON_THRESHOLD = 5;
    private double p;
    private int n;

    public Binomial(int n, double p) {
        setProb(p);
        setN(n);
    }

    @Override
    public String getName() {
        return "Binomial" + Funcs.paramStr(n, p);
    }

    public final void setN(int n) {
        this.n = n;
        if (n < 1) {
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
        for (int i = 0; i < n; i++) {
            res += b.randomExec();
        }
        return res;
    }

    @Override
    public AnalyticSummary analyticEval() {
        double esp = p * n;
        double vari = (1 - p) * esp;
        return AnalyticSummary.buildByVar(esp, vari);
    }

    @Override
    public double cumulative(double d) {

        if (d > n) {
            return 1;
        }
        try {
            Pascal.get(n, n / 2);
            //No TLV exception thrown -> Exact Computation will work
            return super.cumulative(d);
        } catch (TLVE ex) {
        }

        int i = (int) (d + Small.EPSILON);

        /*try {
            Pascal.get(n, i);
            //No TLV exception thrown -> Exact Computation will work
            if (2 * i <= n) {
                return super.cumulative(d);
            }
            //Here since i is closer from n than from 0, to avoid TLVE,
            //we must calculate 1-P(X>d) instead of P(X<=d)
            double antires = 0;
            for (int j = n; j > i; j--) {
                
                antires += this.exactProb(j);
            }
            PriLev.println(0,4,"Binomial cumulative() used exact (reverse calcs)");
            return 1 - antires;
        } catch (TLVE ex) {
        }*/
        if (n * p < Binomial.POISSON_THRESHOLD) {

            Poisson pd = new Poisson(n * p);
            Prints.distApproxLn(4, "Note: " + this.getName() + " approximated to " + pd.getName() + "for cumulative calculation");
            return pd.cumulative(d);
        }
        if (n * (1 - p) < Binomial.POISSON_THRESHOLD) {
            Poisson pd = new Poisson(n * (1 - p));
            Prints.distApproxLn(4, "Note: " + this.getName() + " approximated to reversed " + pd.getName() + "for cumulative calculation");
            return 1 - pd.cumulative(n - d);
            //PoissonFlip pld = new PoissonFlip(n*p,n);
            //return pld.cumulative(d);
        }

        /*if (n*p>Binomial.NORMAL_THRESHOLD && n*(1-p)>Binomial.NORMAL_THRESHOLD){
            //Normal approximation will be ok
            NormalDist nd = NormalDist.buildByVar(n*p,n*(1-p));
            return nd.cumulative(i+0.5);
        }*/
        //No choice, normal approximation is the only way
        NormalDist nd = NormalDist.buildByVar(n * p, n * p * (1 - p));
        Prints.distApproxLn(4, "Note: " + this.getName() + " approximated to " + nd.getName() + "for cumulative calculation");
        return nd.cumulative(i);

    }

    @Override
    public double exactProb(int i) {
        if (i < 0) {
            return 0;
        }
        return Pascal.get(n, i) * Math.pow(p, i) * Math.pow(1 - p, n - i);
    }

}
