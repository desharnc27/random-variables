/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

import exception.IPVE;
import tools.Funcs;

/**
 *
 * @author desha
 * 
 * X is obtained by throwing nDices dice and counting the number of different faces obtained. Every dice has nFaces faces,
 * numbered from 1 to nFaces inclusively.
 * 
 * Example (nDices=8,nFaces=6): throw 8 dice, get (6,3,4,1,1,5,1,3), containing all possibilities except 2, therefore X = 5 
 */
public class DiceAppear extends RandomLaw {
    private int nFaces;
    private int nDices;
    
    public DiceAppear(int nDices){
        this(nDices,6);
    }
    public DiceAppear(int nDices, int nFaces){
        setNbFaces(nFaces);
        setNbDices(nDices);
    }
    @Override
    public String getName(){
        return "DiceAppear"+Funcs.paramStr(nFaces,nDices);
    }
    public final void setNbDices(int i){
        nDices=i;
        if (nDices<1){
            throw  (IPVE.positive("nDices", nDices));
        }
    }
    public final void setNbFaces(int i){
        nFaces=i;
        if (nFaces<1){
            throw  (IPVE.positive("nFaces", nFaces));
        }
    }
    
    @Override
    public double randomExec() {
        int [] found = new int [nFaces];
	for(int i = 0; i < nFaces; i++) {
		found[i] = 0;
	}
	for(int i = 0; i < nDices; i++) {
		found[(int)(Math.random()*nFaces)] = 1;
	}
	int count = 0;
	for(int i = 0; i < nFaces; i++) {
		if (found[i] == 1)
			count++;
	}
	return count;
    }

    @Override
    public AnalyticSummary analyticEval() {
        double valA = Math.pow((nFaces - 1.0) / nFaces, nDices);
	double valB = Math.pow((nFaces - 2.0) / nFaces, nDices);

	double esp_X = nFaces * (1 - valA);
	double var_X = nFaces * (valA - valA * valA) - nFaces * (nFaces - 1) * (valA * valA - valB);

	return AnalyticSummary.buildByVar(esp_X, var_X);
    }
    
}
