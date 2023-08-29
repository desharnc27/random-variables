package randvar;

import java.util.Arrays;

/**
 *
 * @author desha
 */
public class SampleStatSummary {

    int sampleSize;
    double[] moment;

    public double espX(int i) {
        return moment[i - 1];
    }

    public SampleStatSummary(double[] mom, int sampleSize) {
        moment = Arrays.copyOf(mom, mom.length);
        this.sampleSize = sampleSize;

    }

    public double estPopVar() {
        return espX(2) - espX(1) * espX(1);
    }

    public double estSampVar() {
        double estVar = estPopVar();

        //sample adjustment
        estVar *= sampleSize / (sampleSize - 1);
        return estVar;
    }

    public double estVarByKnownMean(double mu) {
        return espX(2) - 2 * espX(1) * mu + mu * mu;
    }

    public double getUnnamedMetric1Coeff(double mu) {
        //double mom=this.espX(1);
        //double espX(2)=this.espX(2);
        double momom = espX(1) * espX(1);
        double res = espX(4) - 4 * espX(3) * mu - espX(2) * espX(2) + 8 * espX(2) * momom - 4 * momom * momom;
        return res / this.sampleSize;
    }

    @Override
    public String toString() {
        return "sampleSummary-" + sampleSize + "-" + Arrays.toString(this.moment);
    }
}
