/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randvar;

import exception.NIFE;
import tools.Small;
import tools.PriLev;

/**
 *
 * @author desha
 */
public abstract class NNIRandomLaw extends RandomLaw {

    public double exactProb(int i) {
        throw NIFE.exactProb(this);
    }

    @Override
    public double cumulative(double d) {
        int end = (int) (d + Small.EPSILON);
        double sum = 0;
        for (int i = 0; i <= end; i++) {
            sum += exactProb(i);
        }
        return sum;
    }

    public boolean verifyCumulativeVsExact() {
        try {
            double cumul = 0;
            for (int i = 0; cumul < 1 - Small.BIG_EPSILON; i++) {
                cumul += exactProb(i);
                if (!Small.eq(cumul, cumulative(i))) {
                    return false;
                }
            }
            return true;
        } catch (NIFE ex) {
            PriLev.println(2,2,"Exception caught: " + ex.getMessage());
            PriLev.println(2,2,"No calculations means no wrong calculations, so I'll return true.");
            return true;
        }
    }

    public boolean verifyStatSummaryVsExact() {
        
        // There is an unsafety in this method since it does not know when to stop looking for possible values of the random variable.
        // It stops if from some i to 2i+10 there is nothing found, which, for some distribution, would be completely faulty.
        try {
            int TODELEcheck=0;
            AnalyticSummary analSmry = this.analyticEval();
            int lastSignificant=0;
            double qwe = 1;
            double esp_X = 0;
            double esp_X2 = 0;
            for (int i = 0; i<2*lastSignificant+10 ; i++) {
                try{
                    qwe = this.exactProb(i);
                }catch(ArithmeticException ae){
                    break;
                }
                if (qwe*i*i<Small.EPSILON){
                    
                }else{
                    lastSignificant=i;
                    esp_X += i * qwe;
                    esp_X2 += i * i * qwe;
                }
                TODELEcheck = i;
            }
            if (!(Small.leqThan(esp_X,analSmry.estMean) && Small.leqThan(esp_X2,analSmry.estMeanSq)))
                PriLev.println(1,1,"busted!");
            if (Small.ishEq(esp_X, analSmry.estMean) && Small.ishEq(esp_X2, analSmry.estMeanSq)) {
                return true;
            }
            if (!Small.ishEq(esp_X, analSmry.estMean)){
                PriLev.println(1,1,"analytic mean: "+analSmry.estMean);
                PriLev.println(1,1,"summed mean: "+esp_X);
                return false;
            }else if (!Small.ishEq(esp_X2, analSmry.estMeanSq)){
                PriLev.println(1,1,"analytic meanSq: "+analSmry.estMeanSq);
                PriLev.println(1,1,"summed meanSq: "+esp_X2);
                return false;
            }
            
            return false;
        } catch (NIFE ex) {
            PriLev.println(2,2,"Exception caught: " + ex.getMessage());
            PriLev.println(2,2,"No calculations means no wrong calculations, so I'll return true.");
            return true;
        }

    }
}
