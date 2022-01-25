package randvar.example;

import exception.IPVE;

import randvar.RandomLaw;
import tools.SomeFunctions;

/**
 *
 * @author desha
 */
public class Khi2 extends RandomLaw {

    private int v;

    public Khi2(int v) {
        setV(v);
    }

    @Override
    public String getName() {
        return "KhiSquare" + SomeFunctions.paramStr(v);
    }

    public void setV(int v) {
        this.v = v;
        if (v <= 0) {
            throw IPVE.positive("v", v);
        }
    }

    @Override
    public double randomExec() {
        double res = 0;
        NormalDist nd = new NormalDist();
        for (int i = 0; i < v; i++) {
            double z = nd.randomExec();
            res += z * z;
        }
        return res;
    }

    @Override
    public double getMean() {
        return v;
    }

    @Override
    public double getVar() {
        return 2 * v;
    }

}
