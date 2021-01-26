/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import randvar.RandomLaw;

/**
 *
 * @author desha
 */
public class NIFE extends UnsupportedOperationException{
    public static NIFE cumulative(RandomLaw rl){
        return new NIFE("Cumulative function of "+rl.getName()+ " is not implemented");
    }
    public static NIFE exactProb(RandomLaw rl){
        return new NIFE("Exact probability function of "+rl.getName()+ " is not implemented");
    }
    /*public static NIFE exact(){
        return new NIFE("This random law's cumulative operation is not implemented");
    }*/
    public NIFE(String message){
        super(message);
    }
}
