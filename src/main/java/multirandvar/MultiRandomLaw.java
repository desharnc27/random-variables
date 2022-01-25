package multirandvar;

import tools.SomeFunctions;
import tools.PrintMeths;

/**
 *
 * @author desha
 */
public abstract class MultiRandomLaw {

    public final int dim;

    public MultiRandomLaw(int dim) {
        this.dim = dim;
    }

    public abstract double[] randomExec();

    public abstract MultiAnalyticSummary analyticEval();

    public double[][] getSample(int num) {
        double[][] sample = new double[num][];
        for (int i = 0; i < num; i++) {
            sample[i] = randomExec();
        }
        return sample;
    }

    /**
     *
     * @param sampleSize
     * @return
     */
    public MultiSampleStatSummary makeRandStats(int sampleSize) {

        if (sampleSize < 5) {

            System.out.println("Note(autoAdjust):sampleSize raised to 5 because less is not supported.");
            sampleSize = 5;
        }

        double[][] sample = getSample(sampleSize);
        //int dim =sample[0].length;
        double[][] covar = new double[dim][dim];
        double[] esp = new double[dim];
        //double moment [] =new double [4];
        //double moment[0]=0;
        //double moment[1]=0;
        //double moment[2]=0;
        //double moment[3]=0;

        for (int k = 0; k < sampleSize; k++) {
            for (int i = 0; i < dim; i++) {
                esp[i] += sample[k][i];
                for (int j = 0; j < dim; j++) {
                    covar[i][j] += sample[k][i] * sample[k][j];
                }
            }
        }
        for (int i = 0; i < dim; i++) {
            esp[i] /= sampleSize;
            for (int j = 0; j < dim; j++) {
                covar[i][j] /= sampleSize;
            }
        }
        //double mom1 = moment[0]/sampleSize;
        //double mom2 = moment[1]/sampleSize;
        //double mom3 = moment[2]/sampleSize;
        //double mom4 = moment[3]/sampleSize;
        //double estVar = estMeanSq - estMean*estMean;

        MultiSampleStatSummary sk = new MultiSampleStatSummary(esp, covar, sampleSize);

        return sk;
    }

    public void compareAnalyticToSample(int sampleSize) {
        MultiAnalyticSummary skAnal = analyticEval();
        MultiSampleStatSummary skRand = makeRandStats(sampleSize);

        double[] estEspRand = skRand.esp;
        double[] estEspAnal = skAnal.esp;
        double[][] estCovarRand = skRand.estSampCovar();
        double[][] estCovarAnal = skAnal.getCovar();

        double[] espDiff = SomeFunctions.diffArray(estEspRand, estEspAnal);
        double[][] covarDiff = SomeFunctions.diffArray(estCovarRand, estCovarAnal);

        System.out.println("estEspRand: ");
        PrintMeths.print(estEspRand);
        System.out.println("estEspAnal: ");
        PrintMeths.print(estEspAnal);
        System.out.println("espDiff: ");
        PrintMeths.print(espDiff);
        System.out.println("espCovarRand: ");
        PrintMeths.print(estCovarRand);
        System.out.println("estCovarAnal: ");
        PrintMeths.print(estCovarAnal);
        System.out.println("covarDiff: ");
        PrintMeths.print(covarDiff);

        //double coteZ = (skRand.estMean-skAnal.estMean)*Math.sqrt(sampleSize/estVarRand);
        /*System.out.println("skRand.estMean: "+skRand.espX(1));
        System.out.println("estVarRand: "+estVarRand);
        System.out.println("skAnal.estMean: "+skAnal.estMean);
        System.out.println("estVarAnal: "+estVarAnal);
        System.out.println("zeeValue(): "+zeeValue(skAnal,skRand));
        System.out.println("chuckValue(): "+chuckValue(skAnal,skRand));
        System.out.println("zeeValue(): "+zeeValue(skAnal,skRand));
        System.out.println("WololoValue(): "+wololoValue(skAnal,skRand));*/
    }
    /*public double zeeValue(MultiAnalyticSummary anal, MultiSampleStatSummary rans){
        double ans = (rans.espX(1)-anal.estMean);
        ans *= Math.sqrt(rans.sampleSize/rans.estSampVar());
        return ans;
    }*/
}
