package randvar;

import exception.NIFE;
import tools.Small;

/**
 *
 * NNI stands for non-negative integer Note that even if the possible values for
 * a NNI random variable are mathematically integers, here they are treated as
 * double format since NNIRandomLaw class must match the parent class.
 *
 * @author desha
 */
public abstract class NNIRandomLaw extends RandomLaw {

    /**
     * Return P[X = i]
     *
     * @param i
     * @return P[X = i]
     */
    public double exactProb(int i) {
        throw NIFE.exactProb(this);
    }

    @Override
    public double cumulative(double d) {
        int end = (int) (d + Small.EPSILON);
        double sum = 0;
        for (int i = 0; i <= end; i++) {
            sum += exactProb(i);
        }
        return sum;
    }

    /**
     * Verifies that cumulative(...) and exactProb(...) implementations match
     * together
     *
     * @return true if cumulative(...) and exactProb(...) match together, false
     * otherwise
     */
    public boolean verifyCumulativeVsExact() {
        try {
            double cumul = 0;
            for (int i = 0; cumul < 1 - Small.BIG_EPSILON; i++) {
                cumul += exactProb(i);
                if (!Small.eq(cumul, cumulative(i))) {
                    return false;
                }
            }
            return true;
        } catch (NIFE ex) {
            Prints.noImplem(2, "Exception caught: " + ex.getMessage());
            Prints.noImplem(2, "No calculations means no wrong calculations, so I'll return true.");
            return true;
        }
    }

    /**
     * Verifies that exactProb(...), getVar() and getMean() implementations
     * match together
     *
     * @return true if cumulative(...) and exactProb(...) match together, false
     * otherwise
     *
     * Note :There is an unsafeness in this method since it does not know when
     * to stop looking for possible values of the random variable. It stops if
     * from some i to 2i+10 there is nothing found, which, for some
     * distribution, would be completely faulty.
     *
     * TODO: maybe a bunch of calls to randomExec() would increase safety, since
     * it would give another minimum value to reach
     */
    public boolean verifyStatSummaryVsExact() {
        double analVar = getVar();
        double analMean = getMean();
        double analMeanSq = analVar + analMean * analMean;

        try {
            int lastSignificant = 0;
            double tempProb = 1;
            double esp_X = 0;
            double esp_X2 = 0;
            for (int i = 0; i < 2 * lastSignificant + 10; i++) {
                try {
                    tempProb = this.exactProb(i);
                } catch (ArithmeticException ae) {
                    break;
                }
                if (tempProb * i * i < Small.EPSILON) {

                } else {
                    lastSignificant = i;
                    esp_X += i * tempProb;
                    esp_X2 += i * i * tempProb;
                }
            }
            if (!(Small.leqThan(esp_X, analMean) && Small.leqThan(esp_X2, analMeanSq))) {
                Prints.wrongSums(1, "busted!");
            }
            if (Small.ishEq(esp_X, analMean) && Small.ishEq(esp_X2, analMeanSq)) {
                return true;
            }
            if (!Small.ishEq(esp_X, analMean)) {
                Prints.wrongSums(1, "analytic mean: " + analMean);
                Prints.wrongSums(1, "summed mean: " + esp_X);
                return false;
            } else if (!Small.ishEq(esp_X2, analMeanSq)) {
                Prints.wrongSums(1, "analytic meanSq: " + analMeanSq);
                Prints.wrongSums(1, "summed meanSq: " + esp_X2);
                return false;
            }

            return false;
        } catch (NIFE ex) {
            Prints.noImplem(2, "Exception caught: " + ex.getMessage());
            Prints.noImplem(2, "No calculations means no wrong calculations, so I'll return true.");
            return true;
        }

    }
}
