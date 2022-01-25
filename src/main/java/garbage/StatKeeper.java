package garbage;

/**
 *
 * @author desha
 */
public class StatKeeper {

    double estMean;
    double estMeanSq;

    //int sampleSize;
    public StatKeeper(double estMean, double estMeanSq) {
        this.estMean = estMean;
        this.estMeanSq = estMeanSq;

        //this.sampleSize=sampleSize;
    }

    public double estPopVar() {
        return estMeanSq - estMean * estMean;
    }

    public double estSampVar(double sampleSize) {
        double estVar = estPopVar();

        //sample adjustment
        estVar *= sampleSize / (sampleSize - 1);
        return estVar;
    }

    public double estVarByKnownMean(double mu) {
        return estMeanSq - 2 * estMean * mu + mu * mu;
    }
}
