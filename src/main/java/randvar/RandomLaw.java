/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

import randvar.examplehard.DiceAppear;
import exception.NIFE;
import java.util.Arrays;
import randvar.example.Binomial;
import tools.Normal;
import tools.Small;

/**
 *
 * @author desharnc27
 */
public abstract class RandomLaw {

    //Following functions must be overriden
    public abstract String getName();

    public abstract double randomExec();

    public abstract AnalyticSummary analyticEval();

    //Following functions without "final" attribute may be overriden, though it is not mandatory
    //In fact some distibutions are to complicated to fully calculate, but their mean and variance can still be computed.
    public double cumulative(double d) {
        throw NIFE.cumulative(this);
    }

    public final double[] getSample(int num) {
        double[] sample = new double[num];
        for (int i = 0; i < num; i++) {
            sample[i] = randomExec();
        }
        return sample;
    }

    public final SampleStatSummary makeRandSampleStats(int sampleSize) {
        if (sampleSize < 5) {

            System.out.println("Note(autoAdjust):sampleSize raised to 5 because less is not supported.");
            sampleSize = 5;
        }

        double[] sample = getSample(sampleSize);
        return makeSampleStats(sample);
    }

    /**
     *
     * @return
     */
    public final SampleStatSummary makeSampleStats(double sample[]) {

        int sampleSize = sample.length;

        double moment[] = new double[4];
        //double moment[0]=0;
        //double moment[1]=0;
        //double moment[2]=0;
        //double moment[3]=0;

        for (int i = 0; i < sampleSize; i++) {
            double val = sample[i];
            moment[0] += val;
            val *= sample[i];
            moment[1] += val;
            val *= sample[i];
            moment[2] += val;
            val *= sample[i];
            moment[3] += val;
        }
        for (int i = 0; i < moment.length; i++) {
            moment[i] /= sampleSize;
        }
        //double mom1 = moment[0]/sampleSize;
        //double mom2 = moment[1]/sampleSize;
        //double mom3 = moment[2]/sampleSize;
        //double mom4 = moment[3]/sampleSize;
        //double estVar = estMeanSq - estMean*estMean;

        SampleStatSummary sk = new SampleStatSummary(moment, sampleSize);

        return sk;
    }

    public final void compareAnalyticToSample(int sampleSize) {
        AnalyticSummary skAnal = analyticEval();
        SampleStatSummary skRand = makeRandSampleStats(sampleSize);

        double estVarRand = skRand.estSampVar();
        double estVarAnal = skAnal.getVar();

        //double coteZ = (skRand.estMean-skAnal.estMean)*Math.sqrt(sampleSize/estVarRand);
        Prints.summaryValuesLn(2,"skRand.estMean: " + skRand.espX(1));
        Prints.summaryValuesLn(2,"estVarRand: " + estVarRand);
        Prints.summaryValuesLn(2,"skAnal.estMean: " + skAnal.estMean);
        Prints.summaryValuesLn(2,"estVarAnal: " + estVarAnal);
        Prints.summaryValuesLn(2,"zeeValue(): " + zeeValue(skAnal, skRand));
        Prints.summaryValuesLn(2,"chuckValue(): " + chuckValue(skAnal, skRand));
        Prints.summaryValuesLn(2,"zeeValue(): " + zeeValue(skAnal, skRand));
        Prints.summaryValuesLn(2,"WololoValue(): " + wololoValue(skAnal, skRand));

    }
    
    //TODO: extreme values or small samples, no use of normal
    
    public boolean compareAnalyticCumulativeToSample(double[] ends, int sampleSize,double probType2Err) {
        Arrays.sort(ends);
        double[] sample = this.getSample(sampleSize);
        //double TARGET_PROB = 0.02;
        //double targetProbOfOneEnd = 1 - Math.pow(1 - TARGET_PROB, 1 / (double) ends.length);
        
        //Division by 2 since final result on both edges are 
        double targetProbOfOneEnd = probType2Err / 2;
        for (double end : ends) {
            try {
                Prints.cumulHypo(3, "A hypothesis test will be performed to determine if the anaytic implementation");
                Prints.cumulHypoLn(3, "of cumulative(end) matches a random sample for end= " + end + ". ");
                Prints.cumulHypoLn(3, "H_0: both cumulative and randomExec functions are well implemented.");
                Prints.cumulHypoLn(3, "H_1: Either cumulative or randomExec is not well implemented.");
                
                Prints.cumulHypoLn(3, "Sample of X distribution denoted as {X_1,X_2...X_n}");
                Prints.cumulHypoLn(3, "Define Y_i = 1 if X_i<end, 0 otherwise ");
                Prints.cumulHypo(3, "Assuming H_0, S=sum[i=1..n] Y_i should have a distribution Binomial(n,p) ");
                Prints.cumulHypoLn(3, " where p = P(X<end)");
                
                Prints.cumulHypoLn(3, "As asked, the probabiliy of falsly rejecting null hypothesis must be "+probType2Err);
                Prints.cumulHypoLn(3, "Therefore we will reject H_0 if S<c_1 or S>c_2 , where c_1 and c_2 are defined such that: ");
                Prints.cumulHypoLn(3, "P(S<c_1)=P(S>c_2)= "+targetProbOfOneEnd);
                
                
                double vraisemblance = proportionTest(end, sample);
                Prints.cumulHypo(3, "Interpretation: assuming H_0 is true, S had "+vraisemblance+ " probability ");
                Prints.cumulHypoLn(3, "of being at least as further from E[S] (on the same side)");
                boolean acceptance = false;
                if (Double.isNaN(vraisemblance)){
                    Prints.cumulHypoLn(3,"calcul generated Nan. TODO: details");
                }else if (vraisemblance < targetProbOfOneEnd ) {
                    Prints.cumulHypoLn(3,vraisemblance+"<"+targetProbOfOneEnd);
                }else{
                    Prints.cumulHypoLn(3,vraisemblance+">"+targetProbOfOneEnd);
                    Prints.cumulHypoLn(3,"H0 will be accepted");
                    return true;
                }
                Prints.cumulHypoLn(3,"H0 will be rejected");
                return false;
            }catch(NIFE ex){
                Prints.noImplemLn(1,"Missing implementation for " +this+". Proportion test on end "+ end+" will return true by default");
            }
        }
        //PriLev.println(2,1,this.getName() + "has credible match between cumlative and analytic");
        return true;

    }

    /**
     * 
     * Returns probability of getting at least as extreme proportion in sample, assuming analytic values (esp and var) are good.
     * @param end The threshold for which the proportion of data below it is evaluated.
     * @param sample a sample of the distribution.
     * @return the likelihood of as least as extreme result (one sided) 
     */
    private double proportionTest(double end, double[] sample) {
        int sampleSize = sample.length;
        int inferCount = 0;
        for (int i = 0; i < sampleSize; i++) {
            if (Small.leqThan(sample[i], end)) {
                inferCount++;
            }
        }
        
        
        double sampleProp = inferCount / (double) sampleSize;
        double analEsp = cumulative(end);
        //double analVar = analEsp * (1 - analEsp);
        
        Prints.cumulHypoLn(3, "Expectation: E[S]= "+ sampleSize*analEsp);
        Prints.cumulHypo(3, "Sample results: ");
        Prints.cumulHypoLn(3, "S = "+inferCount);
        //Prints.cumulHypoLn(3, "S/n = "+sampleProp);
        
        //in case of no variance
        if (analEsp == sampleProp) {

            Prints.cumulHypoLn(3, "S = "+inferCount);
            return 0.5;
        }
        Binomial bin = new Binomial(sampleSize, analEsp);

        double binCumulative = bin.cumulative(inferCount);
        boolean sampleUnderAverage = sampleProp < analEsp;
        double binFurtherFromAverage = (sampleUnderAverage) ? binCumulative : 1 - binCumulative;
        if (sampleUnderAverage) {
            Prints.cumulHypoLn(3,"P(S<="+inferCount+")= "+binFurtherFromAverage);
        }else {
            Prints.cumulHypoLn(3,"P(S>="+inferCount+")= "+binFurtherFromAverage);
        }

        /*
        double coteZ = (sampleProp - analEsp) * Math.sqrt(sampleSize) / Math.sqrt(analVar);
        double normalCumulative = Normal.cumulativeProb(coteZ);
        double normalFurtherFromAverage = (normalCumulative < 0.5) ? normalCumulative : 1 - normalCumulative;
        
        PriLev.println(2,1,sampleProp + " vs " + analEsp + ", " + "vraisemblance: " + normalFurtherFromAverage);
        */
        return binFurtherFromAverage;
        //return normalFurtherFromAverage;

    }

    public final double zeeValue(AnalyticSummary anal, SampleStatSummary rans, boolean assumeVar) {
        Prints.cumulHypo(3, "A hypothesis test will be performed to determine if the implementations of randomExec()");
        Prints.cumulHypoLn(3, "and analyticEval()");
        Prints.cumulHypoLn(3, "H_0: both analytic and randomExec functions are well implemented.");
        Prints.cumulHypoLn(3, "H_1: Either cumulative or randomExec is not well implemented.");
        return 0.0;
    }
    public final double zeeValue(AnalyticSummary anal, SampleStatSummary rans) {
        double ans = (rans.espX(1) - anal.estMean);
        ans *= Math.sqrt(rans.sampleSize / rans.estSampVar());
        return ans;
    }

    public final double chuckValue(AnalyticSummary anal, SampleStatSummary rans) {
        double ans = rans.estSampVar() - anal.getVar();
        ans /= (rans.estSampVar() * Math.sqrt(2.0 / rans.sampleSize));
        return ans;
    }

    public final double wololoValue(AnalyticSummary anal, SampleStatSummary rans) {
        double ans = rans.estSampVar() - anal.getVar();
        ans /= Math.sqrt(rans.getWololoCoeff(anal.estMean));
        return ans;
    }

    public static void main(String[] args) {
        DiceAppear d = new DiceAppear(44);
        for (int i = 0; i < 1; i++) {
            d.compareAnalyticToSample(10000);
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
