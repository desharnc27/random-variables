package randvar.example;

import exception.IPVE;

import randvar.NNIRandomLaw;
import tools.SomeFunctions;
import tools.Pascal;

/**
 * TODO
 *
 * Hypergeometric law. Suppose you have kYes red balls and kNo black balls in an
 * urn. You randomly draw n balls from it, without replacement. X is the number
 * of red balls you draw.
 *
 * @author desha
 */
public class Hypergeometric extends NNIRandomLaw {

    //Number 
    private int kYes;
    private int kNo;
    private int n;

    public Hypergeometric(int kYes, int kNo, int n) {
        fastSetN(n);
        fastSetKYes(kYes);
        setKNo(kNo);
    }

    @Override
    public String getName() {
        return "Hypergeometric" + SomeFunctions.paramStr(kYes, kNo, n);
    }

    private void fastSetN(int i) {
        n = i;
        if (n < 1) {
            throw (IPVE.positive("n", n));
        }
    }

    public final void setN(int i) {
        fastSetN(i);
        checkSum();
    }

    private void fastSetKYes(int i) {
        kYes = i;
        if (n < 1) {
            throw (IPVE.positive("kYes", kYes));
        }
    }

    public final void setKYes(int i) {
        fastSetKYes(i);
        checkSum();
    }

    private void fastSetKNo(int i) {
        kNo = i;
        if (n < 1) {
            throw (IPVE.positive("kNo", kNo));
        }
    }

    public final void setKNo(int i) {
        fastSetKNo(i);
        checkSum();
    }

    public final void checkSum() {
        if (kNo + kYes < n) {
            throw IPVE.create("n", String.valueOf(n), "(0,kYes+kNo]");
        }
    }

    @Override
    public double randomExec() {
        int actYes = kYes;
        int actTot = kNo + kYes;
        double res = 0.0;
        for (int i = 0; i < n; i++) {
            boolean success = Math.random() < (actYes / (actTot + 0.0));
            if (success) {
                actYes--;
                res += 1.0;
            }
            actTot--;

        }
        return res;
    }

    @Override
    public double cumulative(double d) {
        if (d > n || d > kYes) {
            return 1;
        }
        return super.cumulative(d);
    }

    @Override
    public double exactProb(int i) {
        if (i < 0 || i > n || i > kYes || n - i > kNo) {
            return 0;
        }

        int kTot = kNo + kYes;
        return Pascal.comb(kYes, i) * Pascal.comb(kNo, n - i) / (double) Pascal.comb(kTot, n);
    }

    @Override
    public double getMean() {
        double kTot = kYes + kNo;
        double p = kYes / kTot;
        return (n * kYes) / (kTot + 0.0);
    }

    @Override
    public double getVar() {
        double kTot = kYes + kNo;
        double p = kYes / kTot;
        return n * (1 - (n - 1) / (kTot - 1 + 0.0)) * p * (1 - p);
    }

}
