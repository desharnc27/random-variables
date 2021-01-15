/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

/**
 *
 * @author desharnc27
 */


public abstract class RandomLaw {
    
    
    public abstract double randomExec();
    
    public abstract AnalyticSummary analyticEval();
    
    public double [] getSample(int num){
        double [] sample=new double[num];
        for (int i=0;i<num;i++){
            sample[i] = randomExec(); 
        }
        return sample;
    }
    /**
     * 
     * @param sampleSize
     * @return 
     */
    public SampleStatSummary makeRandStats(int sampleSize){
        
        if (sampleSize <5){
            
            System.out.println("I don't manage sample of size less than 5, so i'll increase the smaple Size to 5.");
            sampleSize =5;
        }
        
        double [] sample=getSample(sampleSize);
        
        double moment [] =new double [4];
        //double moment[0]=0;
        //double moment[1]=0;
        //double moment[2]=0;
        //double moment[3]=0;
        
        for (int i=0;i<sampleSize;i++){
            double val = sample[i];           
            moment[0]+=val;
            val*=sample[i];
            moment[1]+=val;
            val*=sample[i];
            moment[2]+=val;
            val*=sample[i];
            moment[3]+=val;
        }
        for (int i=0;i<moment.length;i++){
            moment[i]/=sampleSize;
        }
        //double mom1 = moment[0]/sampleSize;
        //double mom2 = moment[1]/sampleSize;
        //double mom3 = moment[2]/sampleSize;
        //double mom4 = moment[3]/sampleSize;
        //double estVar = estMeanSq - estMean*estMean;
        
        SampleStatSummary sk = new SampleStatSummary(moment,sampleSize);
        
        return sk;
    }
    public void compareAnalyticToSample(int sampleSize){
        AnalyticSummary skAnal=analyticEval();
        SampleStatSummary skRand=makeRandStats(sampleSize);
        
        double estVarRand = skRand.estSampVar();
        double estVarAnal = skAnal.getVar();
                
        //double coteZ = (skRand.estMean-skAnal.estMean)*Math.sqrt(sampleSize/estVarRand);
        System.out.println("skRand.estMean: "+skRand.espX(1));
        System.out.println("estVarRand: "+estVarRand);
        System.out.println("skAnal.estMean: "+skAnal.estMean);
        System.out.println("estVarAnal: "+estVarAnal);
        System.out.println("zeeValue(): "+zeeValue(skAnal,skRand));
        System.out.println("chuckValue(): "+chuckValue(skAnal,skRand));
        System.out.println("zeeValue(): "+zeeValue(skAnal,skRand));
        System.out.println("WololoValue(): "+wololoValue(skAnal,skRand));
    
        
    }
    public double zeeValue(AnalyticSummary anal, SampleStatSummary rans){
        double ans = (rans.espX(1)-anal.estMean);
        ans *= Math.sqrt(rans.sampleSize/rans.estSampVar());
        return ans;
    }
    public double chuckValue(AnalyticSummary anal, SampleStatSummary rans){
        double ans = rans.estSampVar() - anal.getVar() ;
        ans /= (rans.estSampVar()*Math.sqrt(2.0/rans.sampleSize));
        return ans;
    }
    public double wololoValue(AnalyticSummary anal, SampleStatSummary rans){
        double ans = rans.estSampVar() - anal.getVar() ;
        ans /= Math.sqrt(rans.getWololoCoeff(anal.estMean));
        return ans;
    }
    public static void main (String [] args){
        DiceAppear d = new DiceAppear(44);
        for (int i=0;i<1;i++)
            d.compareAnalyticToSample(10000);
    }
}
