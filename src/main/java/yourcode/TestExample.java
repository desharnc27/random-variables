/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yourcode;

import randvar.examplehard.DiceScope;

/**
 *
 * @author desharnc27
 */
public class TestExample {
    public static void main(){
        // First complete the code for ExOfRandLaw or ExOfRandNNILaw with the law you want to study
        
        
        //Replace DiceScope (another law already implemented in this project) by your law and initialize it        
        DiceScope rLaw = new DiceScope(3, 34);//You have to change this line!!
        System.out.println(rLaw.getName());
        
        //After that, you can test if your implementations of getMean(),getVar(),randomExec()... match each other
        
        rLaw.compareAnalyticToSample(10000);//Checks if randomExec() produces likely results according to getMean(),getVar()
        
        //If you also overrided cumulative() or exactProb(), you can also verify they match  randomExec() results
        double[] ends = new double[]{1, 2, 3, 4};
        
        //For every value v in ends, the following checks if the cumulative() function you implement matches the proportion of 
        // a sample (obtained with randomExec()) that is under v.

        System.out.println(rLaw.compareAnalyticCumulativeToSample(ends,10000,0.02));

    }
    public static void main(String [] args){
        main();
    }
}
