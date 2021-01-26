/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

import randvar.examplehard.DiceAppear;
import exception.NIFE;
import java.util.Arrays;
import tools.Normal;
import tools.Small;
import tools.PriLev;

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

            PriLev.println(2,1,"I don't manage sample of size less than 5, so i'll increase the smaple Size to 5.");
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
        PriLev.println(1,1,"skRand.estMean: " + skRand.espX(1));
        PriLev.println(1,1,"estVarRand: " + estVarRand);
        PriLev.println(1,1,"skAnal.estMean: " + skAnal.estMean);
        PriLev.println(1,1,"estVarAnal: " + estVarAnal);
        PriLev.println(1,1,"zeeValue(): " + zeeValue(skAnal, skRand));
        PriLev.println(1,1,"chuckValue(): " + chuckValue(skAnal, skRand));
        PriLev.println(1,1,"zeeValue(): " + zeeValue(skAnal, skRand));
        PriLev.println(1,1,"WololoValue(): " + wololoValue(skAnal, skRand));

    }
    
    //TODO: extreme values or small samples, no use of normal
    
    public boolean compareAnalyticCumulativeToSample(double[] ends, int sampleSize,double probType2Err) {
        Arrays.sort(ends);
        double[] sample = this.getSample(sampleSize);
        //double TARGET_PROB = 0.02;
        //double targetProbOfOneEnd = 1 - Math.pow(1 - TARGET_PROB, 1 / (double) ends.length);
        
        //Division by 2 since final result on both edges are 
        double targetProbOfOneEnd = probType2Err/2;
        for (double end : ends) {
            try {
                double vraisemblance = proportionTest(end, sample);

                if (vraisemblance < targetProbOfOneEnd || Double.isNaN(vraisemblance)) {
                    PriLev.println(2, 1, this.getName() + " has very unikely match between cumlative and analytic at value " + end);
                    return false;
                }
            }catch(NIFE ex){
                PriLev.println(2, 1,"Missing implementation for " +this+". Proportion test on end "+ end+" will return true by default");
            }
        }
        PriLev.println(2,1,this.getName() + "has credible match between cumlative and analytic");
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
        double analVar = analEsp * (1 - analEsp);
        //in case of no variance
        if (analEsp==sampleProp){
            
            PriLev.println(2,1,"Precisely identical value ("+analEsp+"), vraisemblance: 1.0");
            return 1.0;
        }
        double coteZ = (sampleProp - analEsp) * Math.sqrt(sampleSize) / Math.sqrt(analVar);
        double normalCumulative = Normal.cumulativeProb(coteZ);
        double normalFurtherFromAverage = (normalCumulative < 0.5) ? normalCumulative : 1 - normalCumulative;
        PriLev.println(2,1,sampleProp + " vs " + analEsp + ", " + "vraisemblance: " + normalFurtherFromAverage);
        return normalFurtherFromAverage;

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
