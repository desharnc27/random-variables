package randvar.example;

import exception.IPVE;

import randvar.NNIRandomLaw;
import tools.SomeFunctions;
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
    public String getName() {
        return "Bernoulli" + SomeFunctions.paramStr(p);
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
    public double cumulative(double d) {
        if (d > 1) {
            return 1;
        }
        return super.cumulative(d);
    }

    @Override
    public double exactProb(int i) {
        switch (i) {
            case 0:
                return 1 - p;
            case 1:
                return p;
            default:
                return 0;
        }
    }

    @Override
    public double getVar() {
        return p * (1 - p);
    }

    @Override
    public double getMean() {
        return p;
    }
}
