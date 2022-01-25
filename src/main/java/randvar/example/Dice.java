package randvar.example;

import tools.SomeFunctions;
import tools.Small;

/**
 *
 * @author desha
 *
 * X is the number obtained by throwing a dice. Every dice has nbFaces faces,
 * numbered from 1 to nbFaces inclusively.
 *
 */
public class Dice extends DiceTypeLaw {

    public Dice() {
        super(1, 6);
    }

    public Dice(int nbFaces) {
        super(1, nbFaces);
    }

    @Override
    public String getName() {
        return "Dice" + SomeFunctions.paramStr(nbFaces);
    }

    @Override
    public double randomExec() {
        return Math.ceil(nbFaces * Math.random());
    }

    @Override
    public double exactProb(int i) {

        if (i < 1 || i > nbFaces) {
            return 0;
        }
        return 1 / (double) nbFaces;
    }

    @Override
    public double cumulative(double d) {
        int i = (int) (d + Small.EPSILON);
        if (i < 1) {
            return 0;
        }
        if (i > nbFaces) {
            return 1;
        }
        return i / (double) nbFaces;
    }

    @Override
    public double getMean() {
        return nbFaces * (nbFaces + 1) / 2;
    }

    @Override
    public double getVar() {
        double mean = getMean();
        double meanSq = (nbFaces + 1) * (2 * nbFaces + 1) / 6;
        return meanSq - mean * mean;
    }

}
