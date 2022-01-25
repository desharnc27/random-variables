/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar.example;

import exception.IPVE;
import randvar.NNIRandomLaw;

/**
 * Parent class for all laws using a certain number of identical uniform dice
 * (faces [1..nbFaces]) Purpose of this class: I just wanted to shrink a bit of
 * code.
 *
 * @author desharnc27
 */
public abstract class DiceTypeLaw extends NNIRandomLaw {

    protected int nbFaces;
    protected int nbDice;

    protected DiceTypeLaw(int nbDice) {
        this(nbDice, 6);
    }

    protected DiceTypeLaw(int nbDice, int nbFaces) {
        setNbFaces(nbFaces);
        setNbDice(nbDice);
    }

    protected final void setNbDice(int i) {
        nbDice = i;
        if (nbDice < 1) {
            throw (IPVE.positive("nbDice", nbDice));
        }
    }

    protected final void setNbFaces(int i) {
        nbFaces = i;
        if (nbFaces < 1) {
            throw (IPVE.positive("nbFaces", nbFaces));
        }
    }
}
