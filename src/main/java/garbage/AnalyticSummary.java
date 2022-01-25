package garbage;

/**
 *
 * @author desha
 */
public class AnalyticSummary {

    double estMean;
    double estMeanSq;

    //int sampleSize;
    public AnalyticSummary(double estMean, double estMeanSq) {
        this.estMean = estMean;
        this.estMeanSq = estMeanSq;

    }

    public double getVar() {
        return estMeanSq - estMean * estMean;
    }

    public static AnalyticSummary buildByVar(double estMean, double vari) {
        return new AnalyticSummary(estMean, estMean * estMean + vari);
    }

}
