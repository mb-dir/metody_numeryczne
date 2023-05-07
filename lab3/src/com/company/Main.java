package com.company;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.FiniteDifferencesDifferentiator;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        trapez_prosty();
    }
    public static void trapez_prosty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj przedział <a,b>");

        double a = scanner.nextDouble();
        double b = scanner.nextDouble();

        System.out.println("Podaj stopien wielomianu");
        int n = scanner.nextInt();

        double wspolczynniki[] = new double[n+1];
        for(int i = wspolczynniki.length-1; i >=0; i--){
            System.out.print("x^" + (i)+ ": ");
            wspolczynniki[i] = scanner.nextDouble();
        }

        double fa = wartoscFunkcji(wspolczynniki, a);
        double fb = wartoscFunkcji(wspolczynniki, b);

        double s = ((b-a)/2)*(fa+fb);

        UnivariateFunction f = new UnivariateFunction() {
            @Override
            public double value(double x) {
                // zdefiniuj funkcję, dla której chcesz obliczyć pochodną
                return x*x;
            }
        };

        FiniteDifferencesDifferentiator differentiator = new FiniteDifferencesDifferentiator(2, 0.0000001);
        UnivariateFunction fPrime = differentiator.differentiate(f);
        UnivariateSolver solver = new BrentSolver(0.0000001);
        double max = solver.solve(100, fPrime, a, b);
        double secondDerivative = fPrime.value(max);

        double B = (Math.pow((b-a),3)*secondDerivative)/12;
        System.out.println(s);
        System.out.println("Blad wynosi +/- " + B);
    }

    private static double f(double x) {
        return x * x;
    }

    public static double wartoscFunkcji(double wspolczynniki[], double x){
        double value = 0;
        for(int i = wspolczynniki.length-1; i >=0; i--){
            value+=Math.pow(x,i)*wspolczynniki[i];
        }
        return value;
    }
}
//Metody trapezów i simpsona(złożone) + interpolacja i aproksymacja(srednio-kwadratowa)