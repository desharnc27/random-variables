package tools;

/**
 *
 * @author desha
 */
//TODO: bring to myTools
public class Normal {

    /**
     * Computes P(x>X), where X follows normal standard distribution
     *
     * @param x
     * @return P(x>X), where X follows normal standard distribution
     */
    public static double cumulativeProb(double x) {

        //Method: Computes the MacLaurin serie of  the integral of the standard normal density.
        //Stops when accuracy is sufficient.
        double toler = Small.EPSILON;
        double val = 0;

        double bobo = -x * x / 2;
        double curTerm = x;

        int i = 0;
        do {
            val += curTerm;
            curTerm *= bobo;
            curTerm *= (2 * i + 1);
            i++;
            curTerm /= (2 * i + 1);
            curTerm /= i;
            //System.out.println(curTerm);

        } while (curTerm > toler || curTerm < -toler);

        double coeff = Math.sqrt(1 / (2 * Math.PI));
        return val * coeff + 0.5;
    }

    /**
     * Computes z such that P(z>X)=alpha, where X follows normal standard
     * distribution
     *
     * @param alpha
     * @return z
     */
    public static double endPoint2(double alpha) {

        //Method: approximative binary search
        double zDown = -4;
        double zUp = 4;
        double alphaDown = 0;
        double alphaUp = 1;
        double zMid = 0;
        double alphaMid = 0.5;
        while (alphaUp - alphaDown > Small.BIG_EPSILON) {
            zMid = (zDown + zUp) / 2;
            alphaMid = cumulativeProb(zMid);
            if (alpha > alphaMid) {
                zDown = zMid;
                alphaDown = alphaMid;
            } else {
                zUp = zMid;
                alphaUp = alphaMid;
            }
        }
        return zMid;
    }

    /**
     * Computes z such that P(z>X)=alpha, where X follows normal standard
     * distribution
     *
     * @param alpha
     * @return z
     */
    public static double endPoint(double alpha) {

        //Method: Newton convergence
        double coeff = Math.sqrt(1 / (2 * Math.PI));
        double x;
        double nextX = 0;
        do {
            x = nextX;
            double denom = coeff * Math.exp(-x * x / 2);
            nextX = x - (cumulativeProb(x) - alpha) / denom;
        } while (Math.abs(nextX - x) > Small.BIG_EPSILON);
        return nextX;
    }

    public static double randNormal() {
        double ran = Math.random();
        return endPoint(ran);
    }

    public static void main(String[] args) {
        double alpha = Math.random();
        System.out.println("alpha: " + alpha);
        double ansBin = endPoint2(alpha);
        double ansNewt = endPoint(alpha);
        System.out.println("ansBin: " + ansBin);
        System.out.println("ansNewt: " + ansNewt);

        double inBin = cumulativeProb(ansBin);
        double inNewt = cumulativeProb(ansNewt);

        System.out.println("inBin: " + inBin);
        System.out.println("inNewt: " + inNewt);

    }
}
