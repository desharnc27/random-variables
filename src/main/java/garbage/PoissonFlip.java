package garbage;

import exception.IPVE;

import randvar.NNIRandomLaw;
import randvar.example.Poisson;
import tools.SomeFunctions;
import tools.Pascal;
import tools.Small;

/**
 * This distribution has nothing exact in its calculations and has therefore no
 * theoretical purpose. It's an approximation of binomial(n,p) with p close to
 * 1. Binomial(n,p) is converted to PoissonFlip(lambda,roof) with lambda=np and
 * roof = n and becomes enough accurate when p is close to 1.
 *
 * @author desha
 */
public class PoissonFlip extends NNIRandomLaw {

    private double lambda;
    private int roof;

    public PoissonFlip(double lambda, int roof) {
        setLambda(lambda);
        setRoof(roof);
    }

    @Override
    public String getName() {
        return "PoissonFlip" + SomeFunctions.paramStr(lambda);
    }

    public final void setLambda(double lambda) {
        this.lambda = lambda;
        if (lambda <= 0) {
            throw (IPVE.positive("lambda", lambda));
        }
    }

    public final void setRoof(int roof) {
        this.roof = roof;
        if (roof <= 0) {
            throw (IPVE.positive("roof", roof));
        }
    }

    @Override
    public double randomExec() {
        Poisson pd = new Poisson(lambda);
        double res = roof - pd.randomExec();
        if (res < 0) {
            res = 0;
        }
        return res;

    }

    /*@Override
    public AnalyticSummary analyticEval() {
        return AnalyticSummary.buildByVar(roof-lambda, lambda);
    }*/
    //These 2 methods of calculation are slower than optimal but avoid overflow risk of calling a factorial
    /*@Override
    public double cumulative(double d){
        int i = (int)(d+Small.EPSILON);
        if (i<0)
            return 0;
        double term= 1;
        int act =1;
        double sum =term;
        while (act<=i){
            term *= lambda/act;
            sum +=term;
            act++;
        }
        return sum*Math.exp(-lambda);
    }*/
    @Override
    public double exactProb(int i) {
        Poisson pd = new Poisson(lambda);
        return pd.exactProb(roof - i);
    }

    @Override
    public double getMean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getVar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
