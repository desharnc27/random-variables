package randvar.examplehard;

import randvar.example.Dice;
import randvar.example.DiceTypeLaw;
import tools.SomeFunctions;

/**
 * Suppose you throw nbDice conventional dices. X is the difference between max
 * and min obtained numbers
 *
 * @author desharnc27
 */
public class DiceScope extends DiceTypeLaw {

    public DiceScope(int nbDice) {
        super(nbDice, 6);
    }

    public DiceScope(int nbDice, int nbFaces) {
        super(nbDice, nbFaces);
    }

    @Override
    public double exactProb(int i) {
        if (i > nbFaces - 1) {
            return 0;
        }
        if (i < 0) {
            return 0;
        }
        double a = myPow(i + 1, nbDice);
        double b = myPow(i, nbDice);
        double c = myPow(i - 1, nbDice);
        return (nbFaces - i) * (a - 2 * b + c) / myPow(nbFaces, nbDice);
    }

    @Override
    public String getName() {
        return "DiceScope" + SomeFunctions.paramStr(nbFaces, nbDice);
    }

    @Override
    public double randomExec() {
        Dice dice = new Dice(nbFaces);
        double min = nbFaces;
        double max = 1;
        for (int i = 0; i < nbDice; i++) {
            double toss = dice.randomExec();
            if (toss > max) {
                max = toss;
            }
            if (toss < min) {
                min = toss;
            }
        }
        return max - min;

    }

    @Override
    public double getMean() {
        return nbFaces - 1 - 2 * getGeneralizedHarmonic(nbFaces - 1, nbDice) / myPow(nbFaces, nbDice);
    }

    @Override
    public double getVar() {
        double harmonic0 = getGeneralizedHarmonic(nbFaces - 1, nbDice);
        double harmonic1 = getGeneralizedHarmonic(nbFaces - 1, nbDice + 1);
        double denom0 = myPow(nbFaces, nbDice);
        double ans = -4 / denom0 * harmonic0 * harmonic0;
        ans += - 6 * harmonic1;
        ans += (6 * nbFaces - 4) * harmonic0;
        ans /= denom0;
        return ans;

        //double mean = getMean();
        //return getMeanSq() - mean * mean;
    }

    /*@Override
    public double getMeanSq() {
        double v0 = (nbFaces - 1) * (nbFaces - 1);
        double v1 = 2 * nbFaces * getGeneralizedHarmonic(nbFaces - 1, nbDice);
        double v2 = 6 * getGeneralizedHarmonic(nbFaces - 1, nbDice + 1);
        return v0 + (v1 - v2) / myPow(nbFaces, nbDice);
    }*/
    public static double myPow(double base, double exp) {
        if (base <= 0) {
            return 0;
        }
        return Math.pow(base, exp);
    }

    public static double[] getGeneralizedHarmonicArr(int len, double exp) {
        double[] res = new double[len];
        for (int i = 1; i < len; i++) {
            res[i] = res[i - 1] + Math.pow(i, exp);
        }
        return res;
    }

    public static double getGeneralizedHarmonic(int base, double exp) {
        double res = 0;
        for (int i = 1; i <= base; i++) {
            res += Math.pow(i, exp);
        }
        return res;
    }

}
