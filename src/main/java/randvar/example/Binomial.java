package randvar.example;

import exception.IPVE;
import exception.TLVE;

import randvar.NNIRandomLaw;
import randvar.Prints;
import tools.SomeFunctions;
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
        return "Binomial" + SomeFunctions.paramStr(n, p);
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
    public double cumulative(double d) {

        if (d > n) {
            return 1;
        }
        try {
            Pascal.comb(n, n / 2);
            //No TLV exception thrown -> Exact Computation will work
            return super.cumulative(d);
        } catch (TLVE ex) {
        }

        int i = (int) (d + Small.EPSILON);

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

        NormalDist nd = NormalDist.buildByVar(n * p, n * p * (1 - p));
        Prints.distApproxLn(4, "Note: " + this.getName() + " approximated to " + nd.getName() + "for cumulative calculation");
        return nd.cumulative(i);

    }

    @Override
    public double exactProb(int i) {
        if (i < 0) {
            return 0;
        }
        return Pascal.comb(n, i) * Math.pow(p, i) * Math.pow(1 - p, n - i);
    }

    @Override
    public double getMean() {
        return n * p;
    }

    @Override
    public double getVar() {
        return getMean() * (1 - p);
    }

}
