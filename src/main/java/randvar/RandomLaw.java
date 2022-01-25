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
    /**
     * Returns a name to represent this law and its parameters
     *
     * @return a name to represent this law and its parameters
     */
    public abstract String getName();

    /**
     * Returns X, a random execution of this law
     *
     * @return X, a random execution of this law
     */
    public abstract double randomExec();

    /**
     * Returns E[X], also called analytic mean in this project
     *
     * @return E[X]
     */
    public abstract double getMean();

    /**
     * Returns Var[X], also called analytic mean in this project
     *
     * @return Var[X]
     */
    public abstract double getVar();

    //Following functions without "final" attribute may be overriden, though it is not mandatory
    //In fact some distibutions are to complicated to fully calculate, but their mean and variance can still be computed.
    /**
     * Returns E[X^2].
     *
     * Should be overridden if and only if getVar()'s implementation calls it
     *
     * @return E[X^2]
     */
    public double getMeanSq() {
        double mean = getMean();
        return getVar() + mean * mean;
    }

    /**
     * returns P(X leq d)
     *
     * @param d any numeric value
     * @return P(X leq d)
     */
    public double cumulative(double d) {
        throw NIFE.cumulative(this);
    }

    /**
     * Returns a sample of IID random variables having the distribution of this
     * law.
     *
     * @param num size of sample
     * @return a sample of IID random variables having the distribution of this
     * law.
     */
    public final double[] getSample(int num) {
        double[] sample = new double[num];
        for (int i = 0; i < num; i++) {
            sample[i] = randomExec();
        }
        return sample;
    }

    /**
     * Makes a sample of IID variables having this law, then calls
     * makeRandSampleStats(sample) on it
     *
     * @see makeRandSampleStats(double [] sample)
     * @param num size of sample
     * @return .....
     */
    public final SampleStatSummary makeRandSampleStats(int sampleSize) {
        if (sampleSize < 5) {

            System.out.println("Note(autoAdjust):sampleSize raised to 5 because less is not supported.");
            sampleSize = 5;
        }

        double[] sample = getSample(sampleSize);
        return makeSampleStats(sample);
    }

    /**
     * Returns the SampleStatSummary of a sample
     *
     * @return the SampleStatSummary of a sample
     * @ SampleStatSummary
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
        SampleStatSummary sk = new SampleStatSummary(moment, sampleSize);

        return sk;
    }

    //TODO: extreme values or small samples, no use of normal
    /**
     * Generates a sample of IID variables having this law, then compares to the
     * analytic values returned by getMean() and getVar()
     *
     * Used Metrics: Z value, and TODO
     *
     * @param sampleSize sample size
     */
    public final void compareAnalyticToSample(int sampleSize) {
        //AnalyticSummary skAnal = analyticEval();
        SampleStatSummary skRand = makeRandSampleStats(sampleSize);
        double analMean = this.getMean();
        double analVar = this.getVar();

        double estVarRand = skRand.estSampVar();
        double estVarAnal = getVar();

        //double coteZ = (skRand.estMean-skAnal.estMean)*Math.sqrt(sampleSize/estVarRand);
        Prints.summaryValuesLn(2, "skRand.estMean: " + skRand.espX(1));
        Prints.summaryValuesLn(2, "estVarRand: " + estVarRand);
        Prints.summaryValuesLn(2, "skAnal.estMean: " + analMean);
        Prints.summaryValuesLn(2, "estVarAnal: " + estVarAnal);
        Prints.summaryValuesLn(2, "zScore(): " + zScore(analMean, analVar, skRand));
        Prints.summaryValuesLn(2, "chuckValue(): " + chuckValue(analMean, analVar, skRand));
        Prints.summaryValuesLn(2, "zScore(): " + zScore(analMean, analVar, skRand));
        Prints.summaryValuesLn(2, "WololoValue(): " + wololoValue(analMean, analVar, skRand));

    }

    //TODO: extreme values or small samples, no use of normal
    /**
     * Generates a sample of IID variables having this law, then compares it the
     * analytic probabilities of X being under some threshold.
     *
     * Used Metrics: Z value, and TODO
     *
     * @param ends every threshold to: compare cumulative(threshold) to the
     * proportion of sample being under that threshold
     * @param sampleSize sample size
     * @param probType2Err the probability of likelihood of sample according to
     * analytics, under which we reject the hypothesis that analytic
     * evalutations are correct
     * @return true if, for every threshold, null hypothesis is accepted.
     */
    public boolean compareAnalyticCumulativeToSample(double[] ends, int sampleSize, double probType2Err) {
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

                Prints.cumulHypoLn(3, "As asked, the probabiliy of falsly rejecting null hypothesis must be " + probType2Err);
                Prints.cumulHypoLn(3, "Therefore we will reject H_0 if S<c_1 or S>c_2 , where c_1 and c_2 are defined such that: ");
                Prints.cumulHypoLn(3, "P(S<c_1)=P(S>c_2)= " + targetProbOfOneEnd);

                double vraisemblance = proportionTest(end, sample);
                Prints.cumulHypo(3, "Interpretation: assuming H_0 is true, S had " + vraisemblance + " probability ");
                Prints.cumulHypoLn(3, "of being at least as further from E[S] (on the same side)");
                if (Double.isNaN(vraisemblance)) {
                    Prints.cumulHypoLn(3, "calcul generated Nan. TODO: details");
                } else if (vraisemblance < targetProbOfOneEnd) {
                    Prints.cumulHypoLn(3, vraisemblance + "<" + targetProbOfOneEnd);
                } else {
                    Prints.cumulHypoLn(3, vraisemblance + ">" + targetProbOfOneEnd);
                    Prints.cumulHypoLn(3, "H0 will be accepted");
                    Prints.cumulHypoLn(2, "Analytic and sample seem to match on end " + end);
                    continue;
                }
                Prints.cumulHypoLn(3, "H0 will be rejected");
                Prints.cumulHypoLn(2, "Analytic and sample don't match on end " + end);
                return false;
            } catch (NIFE ex) {
                Prints.noImplemLn(1, "Missing implementation for " + this + ". Proportion test on end " + end + " will return true by default");
            }

        }
        Prints.cumulHypoLn(2, "Analytic and sample seem to match on all ends ");
        return true;
        //PriLev.println(2,1,this.getName() + "has credible match between cumlative and analytic");

    }

    /**
     *
     * Returns probability of getting at least as extreme proportion in sample,
     * assuming analytic values (esp and var) are good.
     *
     * @param end The threshold for which the proportion of data below it is
     * evaluated.
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

        Prints.cumulHypoLn(3, "Expectation: E[S]= " + sampleSize * analEsp);
        Prints.cumulHypo(3, "Sample results: ");
        Prints.cumulHypoLn(3, "S = " + inferCount);
        //Prints.cumulHypoLn(3, "S/n = "+sampleProp);

        //in case of no differnce between analytic mean and sample mean
        if (analEsp == sampleProp) {

            Prints.cumulHypoLn(3, "S = " + inferCount);
            return 0.5;
        }
        Binomial bin = new Binomial(sampleSize, analEsp);

        double binCumulative = bin.cumulative(inferCount);
        boolean sampleUnderAverage = sampleProp < analEsp;
        double binFurtherFromAverage = (sampleUnderAverage) ? binCumulative : 1 - binCumulative;
        if (sampleUnderAverage) {
            Prints.cumulHypoLn(3, "P(S<=" + inferCount + ")= " + binFurtherFromAverage);
        } else {
            Prints.cumulHypoLn(3, "P(S>=" + inferCount + ")= " + binFurtherFromAverage);
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

    /**
     * Returns the z-score of sample summary relatively to analytic mean and
     * variance values
     *
     * @param analMean
     * @param analVar
     * @param rans
     * @return
     */
    public final static double zScore(double analMean, double analVar, SampleStatSummary rans) {
        double ans = (rans.espX(1) - analMean);
        ans *= Math.sqrt(rans.sampleSize / rans.estSampVar());
        return ans;
    }

    /**
     * TODO some exotic metric to compare analytic and sample variances
     *
     * @param analMean analytic mean
     * @param analVar analytic variance
     * @param rans SampleStatSummary of some sample
     * @return
     */
    public final static double chuckValue(double analMean, double analVar, SampleStatSummary rans) {
        double ans = rans.estSampVar() - analVar;
        ans /= (rans.estSampVar() * Math.sqrt(2.0 / rans.sampleSize));
        return ans;
    }

    /**
     * TODO some exotic metric to compare analytic and sample variances
     *
     * @param analMean analytic mean
     * @param analVar analytic variance
     * @param rans SampleStatSummary of some sample
     * @return
     */
    public final static double wololoValue(double analMean, double analVar, SampleStatSummary rans) {
        double ans = rans.estSampVar() - analVar;
        ans /= Math.sqrt(rans.getWololoCoeff(analMean));
        return ans;
    }

    @Override
    public String toString() {
        return getName();
    }

    //Not useful functions that might be used later
    /*public final void meanTestWithKnownVar(int sampleSize,double probType2Err){
       SampleStatSummary sss = this.makeRandSampleStats(sampleSize);
       zeeTest(sss,probType2Err,true); 
    }
    public final void meanTestWithUnknownVar(int sampleSize, double probType2Err){
        SampleStatSummary sss = this.makeRandSampleStats(sampleSize);
        zeeTest(sss,probType2Err,false);
    }

    public final void zeeTest(SampleStatSummary rans, double probType2Err, boolean assumeVar) {
        int n =rans.sampleSize;
        double targetProb = probType2Err/2;
        Prints.cumulHypoLn(3, "A hypothesis test will be performed to determine if the implementations of randomExec() and getMean() match.");
        Prints.cumulHypoLn(3, "Option [assume getVar() is good]: "+assumeVar);
        Prints.cumulHypoLn(3, "H_0: both getMean and randomExec functions are well implemented.");
        Prints.cumulHypoLn(3, "H_1: Either getMean or randomExec does calculation errors.");
        Prints.cumulHypoLn(3, "Sample of distribution X denoted as {X_1,X_2...X_n}");
        Prints.cumulHypoLn(3, "With sample mean Xb");
        if (assumeVar){
            Prints.cumulHypoLn(3,"Let Z = (Xb - E[X]) /sqrt(Var[X]/n)");
            Prints.cumulHypoLn(3,"By CLT, Z can be approximated to normal(0,1) distribution");
            
            double z = (rans.espX(1)-getMean())*Math.sqrt(n/getVar());
            double alpha= Normal.cumulativeProb(z);
            if (z>0)
                alpha=1-alpha;
            Prints.cumulHypoLn(3,"Result:");
            Prints.cumulHypoLn(3,"Xb="+rans.espX(1));
            Prints.cumulHypoLn(3,"Z="+z);
            Prints.cumulHypoLn(3,"We had P(|Z|>|"+z+"|)="+alpha);
            vraiCompa(alpha,targetProb);
            
        }else{
            Prints.cumulHypoLn(3,"TODO: Student not implemented");
        }
        
        
    }*/
    /**
     * TODO
     *
     * @param vraisemblance
     * @param targetProbOfOneEnd
     * @return
     */
    /*private boolean vraiCompa(double vraisemblance, double targetProbOfOneEnd) {
        if (Double.isNaN(vraisemblance)) {
            Prints.cumulHypoLn(3, "calcul generated Nan. TODO: details");
            Prints.cumulHypoLn(3,"H0 will be rejected");
            return false;
        } else if (vraisemblance < targetProbOfOneEnd) {
            Prints.cumulHypoLn(3, vraisemblance + "<" + targetProbOfOneEnd);
            Prints.cumulHypoLn(3,"H0 will be rejected");
            return false;
        } else {
            Prints.cumulHypoLn(3, vraisemblance + ">" + targetProbOfOneEnd);
            Prints.cumulHypoLn(3, "H0 will be accepted");
            return true;
        }
    }*/
}
