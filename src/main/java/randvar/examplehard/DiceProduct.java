package randvar.examplehard;

import randvar.example.Dice;
import randvar.example.DiceTypeLaw;
import tools.SomeFunctions;

/**
 *
 * @author desha
 */
/**
 *
 * X is obtained by throwing nbDice dice and multiplying all obtained numbers
 * together. Every dice has nbFaces faces, numbered from 1 to nbFaces
 * inclusively.
 *
 * Example (nbDice=4,nbFaces=7): throw 4 dice, get (5,1,7,2), therefore X =
 * 1*2*5*7 = 70
 */
public class DiceProduct extends DiceTypeLaw {

    public DiceProduct(int nbDice) {
        super(nbDice, 6);
    }

    public DiceProduct(int nbDice, int nbFaces) {
        super(nbDice, nbFaces);
    }

    @Override
    public String getName() {
        return "DiceProduct" + SomeFunctions.paramStr(nbFaces, nbDice);
    }

    @Override
    public double randomExec() {
        double res = 1;
        for (int i = 0; i < nbDice; i++) {
            res *= Math.ceil(nbFaces * Math.random());
        }
        return res;
    }

    public static void main(String[] args) {
        DiceProduct d = new DiceProduct(2);
        for (int i = 0; i < 1; i++) {
            d.compareAnalyticToSample(10000);
        }
    }

    @Override
    public double getMean() {
        Dice dice = new Dice(nbFaces);
        return Math.pow(dice.getMean(), nbDice);
    }

    @Override
    public double getVar() {
        Dice dice = new Dice(nbFaces);
        double diceEsp = dice.getMean();
        double diceVar = dice.getVar();
        double diceEsp2 = diceEsp * diceEsp + diceVar;
        double diceProductEsp2 = Math.pow(diceEsp2, nbDice);
        double diceProductEsp = getMean();
        return diceProductEsp2 - diceProductEsp * diceProductEsp;
    }

}
