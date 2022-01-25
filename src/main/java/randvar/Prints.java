package randvar;

/**
 *
 * @author desha
 */
public class Prints {

    public static int cumulHypoTestLevel = 2;
    public static int distributionApproxLevel = 3;
    public static int noImplementationLevel = 4;
    public static int wrongSumsLevel = 4;
    public static int summaryValues = 4;

    public static void cumulHypo(int level, String message) {
        if (cumulHypoTestLevel >= level) {
            System.out.print(message);
        }
    }

    public static void cumulHypoLn(int level, String message) {
        if (cumulHypoTestLevel >= level) {
            System.out.println(message);
        }
    }

    public static void distApprox(int level, String message) {
        if (distributionApproxLevel >= level) {
            System.out.print(message);
        }
    }

    public static void distApproxLn(int level, String message) {
        if (distributionApproxLevel >= level) {
            System.out.println(message);
        }
    }

    public static void noImplem(int level, String message) {
        if (noImplementationLevel >= level) {
            System.out.print(message);
        }
    }

    public static void noImplemLn(int level, String message) {
        if (noImplementationLevel >= level) {
            System.out.println(message);
        }
    }

    public static void wrongSums(int level, String message) {
        if (wrongSumsLevel >= level) {
            System.out.print(message);
        }
    }

    public static void wrongSumsLn(int level, String message) {
        if (wrongSumsLevel >= level) {
            System.out.println(message);
        }
    }

    public static void summaryValues(int level, String message) {
        if (summaryValues >= level) {
            System.out.print(message);
        }
    }

    public static void summaryValuesLn(int level, String message) {
        if (summaryValues >= level) {
            System.out.println(message);
        }
    }
}
