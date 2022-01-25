package randvar.example;

import exception.IPVE;

import randvar.NNIRandomLaw;
import tools.SomeFunctions;
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

    public final void setProb(double p) {
        this.p = p;
        if (p < 0 || p > 1) {
            throw (IPVE.proba("p", p));
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
    public String getName() {
        return "Geometric" + SomeFunctions.paramStr(p);
    }

    @Override
    public double exactProb(int i) {
        if (i < 1) {
            return 0;
        }
        return Math.pow(1 - p, i - 1) * p;
    }

    @Override
    public double cumulative(double d) {
        int i = (int) (d + Small.EPSILON);
        if (i < 1) {
            return 0;
        }
        return 1 - Math.pow(1 - p, i);
    }

    @Override
    public double getMean() {
        return 1 / p;
    }

    @Override
    public double getVar() {
        return (1 - p) / (p * p);
    }

}
