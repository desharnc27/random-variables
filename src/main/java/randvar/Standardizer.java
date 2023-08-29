/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

import exception.IPVE;

/**
 *
 * Represents another distribution, but centered to mean 0 and reduced to variance 1
 * 
 * R = (X - E[X]) / sqrt(Var[X])
 */
public class Standardizer extends RandomLaw{
    private RandomLaw randLaw;
    private double rawMean;
    private double rawVar;

    public Standardizer(RandomLaw randLaw) {
        this.randLaw = randLaw;
        rawMean = randLaw.getMean();
        rawVar = randLaw.getVar();
        if (rawVar == 0 ){
            throw IPVE.create("randLaw", "randLaw","a distribution with some variance");
        }
    }

    @Override
    public String getName() {
        return "standardizer[" + randLaw.toString()+"]";
    }

    @Override
    public double randomExec() {
        return (randLaw.randomExec() - rawMean) / Math.sqrt(rawVar);
    }

    @Override
    public double getMean() {
        return 0.0;
    }

    @Override
    public double getVar() {
        return 1.0;
    }
    
    
    
    
}
