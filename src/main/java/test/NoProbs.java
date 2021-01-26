/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import tools.Pascal;

/**
 *
 * @author desha
 */
public class NoProbs {
    public static void main(String [] args){
        int n=300;
        //System.out.println(Pascal.doubleFact(n));
        try{
            System.out.println(Pascal.get(56,22));
            System.out.println(Pascal.get(74,22));
            System.out.println(Pascal.get(5,3));
            System.out.println(Pascal.get(2040,5));
            //System.out.println(Pascal.get(16672,4));
        }catch(ArithmeticException e){
            throw e;
        }
        
    }
}
