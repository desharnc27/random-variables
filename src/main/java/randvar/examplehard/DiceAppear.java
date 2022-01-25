package randvar.examplehard;

import randvar.example.DiceTypeLaw;
import tools.SomeFunctions;

/**
 *
 * @author desha
 *
 * X is obtained by throwing nbDice dice and counting the number of different
 * faces obtained. Every dice has nbFaces faces, numbered from 1 to nbFaces
 * inclusively.
 *
 * Example (nbDice=8,nbFaces=6): throw 8 dice, get (6,3,4,1,1,5,1,3), containing
 * all possibilities except 2, therefore X = 5
 */
public class DiceAppear extends DiceTypeLaw {

    public DiceAppear(int nbDice) {
        super(nbDice, 6);
    }

    public DiceAppear(int nbDice, int nbFaces) {
        super(nbDice, nbFaces);
    }

    @Override
    public String getName() {
        return "DiceAppear" + SomeFunctions.paramStr(nbFaces, nbDice);
    }

    @Override
    public double randomExec() {
        int[] found = new int[nbFaces];
        for (int i = 0; i < nbFaces; i++) {
            found[i] = 0;
        }
        for (int i = 0; i < nbDice; i++) {
            found[(int) (Math.random() * nbFaces)] = 1;
        }
        int count = 0;
        for (int i = 0; i < nbFaces; i++) {
            if (found[i] == 1) {
                count++;
            }
        }
        return count;
    }

    @Override
    public double getMean() {
        double valA = Math.pow((nbFaces - 1.0) / nbFaces, nbDice);
        //double valB = Math.pow((nbFaces - 2.0) / nbFaces, nbDice);

        return nbFaces * (1 - valA);
    }

    @Override
    public double getVar() {
        double valA = Math.pow((nbFaces - 1.0) / nbFaces, nbDice);
        double valB = Math.pow((nbFaces - 2.0) / nbFaces, nbDice);

        return nbFaces * (valA - valA * valA) - nbFaces * (nbFaces - 1) * (valA * valA - valB);
    }

}
