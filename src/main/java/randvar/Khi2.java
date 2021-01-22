/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

import exception.IPVE;
import tools.Funcs;

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
    public String getName(){
        return "KhiSquare"+Funcs.paramStr(v);
    }

    public void setV(int v) {
        this.v = v;
        if (v<=0)
            throw IPVE.positive("v", v);
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
    public AnalyticSummary analyticEval() {
        return AnalyticSummary.buildByVar(v, 2 * v);
    }

}
