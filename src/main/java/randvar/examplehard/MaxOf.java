/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar.examplehard;

import randvar.RandomLaw;

/**
 *
 * @author desharnc27
 */
public class MaxOf extends RandomLaw{
    private RandomLaw []  randVars;
    public MaxOf(RandomLaw... randVars){
        this.randVars = randVars;
    }
    @Override
    public String getName() {
        String [] arr = new String[randVars.length];
        for (int i=0;i<randVars.length;i++){
            arr [i] = randVars[i].getName();
        }
        return "maxOf["+String.join(",", arr)+"]";
    }

    @Override
    public double randomExec() {
        double max = randVars[0].randomExec();
        for (int i =1;i<randVars.length;i++){
            double result = randVars[i].randomExec();
            if (max<result)
                max = result;
        }
        return max;
    }

    @Override
    public double getMean() {
        //There is no general formula for it
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getVar() {
        //There is no general formula for it
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
