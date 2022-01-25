package randvar.example;

import exception.IPVE;

import randvar.RandomLaw;
import tools.SomeFunctions;
import tools.Small;

/**
 *
 * @author desha
 */
public class ExponentialLaw extends RandomLaw {

    private double lambda;

    public ExponentialLaw(double lambda) {
        setLambda(lambda);
    }

    @Override
    public String getName() {
        return "Exponential" + SomeFunctions.paramStr(lambda);
    }

    public final void setLambda(double lambda) {
        this.lambda = lambda;
        if (lambda <= 0) {
            throw (IPVE.positive("lambda", lambda));
        }
    }

    @Override
    public double randomExec() {
        double ran = Math.random();
        return Math.log(ran) / (-lambda);
    }

    @Override
    public double cumulative(double d) {
        if (d < 0) {
            return 0;
        }
        return 1 - Math.exp(-lambda * d);
    }

    @Override
    public double getMean() {
        return 1 / lambda;
    }

    @Override
    public double getVar() {
        return 1 / (lambda * lambda);
    }

}
