package randvar.example;

import exception.IPVE;

import randvar.RandomLaw;
import tools.SomeFunctions;
import tools.Normal;

/**
 *
 * @author desha
 */
public class NormalDist extends RandomLaw {

    private double mean;
    private double sd;

    public NormalDist() {
        mean = 0;
        sd = 1;
    }

    public NormalDist(double mean, double sd) {
        setMean(mean);
        setSD(sd);
    }

    public static NormalDist buildByVar(double mean, double vari) {
        return new NormalDist(mean, Math.sqrt(vari));
    }

    @Override
    public String getName() {
        return "Normal" + SomeFunctions.paramStr(mean, sd * sd);
    }

    @Override
    public double randomExec() {
        double z = Normal.randNormal();
        return z * sd + mean;
    }

    public final void setMean(double mean) {
        this.mean = mean;
    }

    public final void setSD(double sd) {
        this.sd = sd;
        if (sd <= 0) {
            throw IPVE.positive("sd", sd);
        }
    }

    @Override
    public double cumulative(double d) {
        double z = (d - mean) / sd;
        return Normal.cumulativeProb(z);
    }

    @Override
    public double getMean() {
        return mean;
    }

    @Override
    public double getVar() {
        return sd * sd;
    }

}
