package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        sieczne();
//        bisekcja();
//        falsy();
        styczne();
    }

    public static void sieczne(){
        Scanner scanner = new Scanner(System.in);
        int maxIteracje = 100;

        System.out.println("Podaj stopien wielomianu");
        int n = scanner.nextInt();

        double wspolczynniki[] = new double[n+1];
        for(int i = wspolczynniki.length-1; i >=0; i--){
            System.out.print("x^" + (i)+ ": ");
            wspolczynniki[i] = scanner.nextDouble();
        }

        System.out.println("Podaj x0");
        double x0 = scanner.nextDouble();

        System.out.println("Podaj x1");
        double x1 = scanner.nextDouble();

        System.out.println("Podaj dokladnosc");
        double epsilon = scanner.nextDouble();

        double f0 = wartoscFunkcji(wspolczynniki,x0);
        double f1 = wartoscFunkcji(wspolczynniki,x1);

        int ileItercji = 0;
        while(Math.abs(f1) > epsilon && ileItercji < maxIteracje){
            double x2 = x1 - (f1 * (x1 - x0)) / (f1 - f0);//wzor
            x0 = x1;
            x1 = x2;
            f0 = f1;
            f1 = wartoscFunkcji(wspolczynniki, x1);
            ileItercji++;
        }

        if (Math.abs(f1) <= epsilon) {
            System.out.println("Rozwiazanie: x = " + x1);
        } else {
            System.out.println("Metoda siecznych nie zbiega do rozwiazania.");
        }
    }

    public static void bisekcja(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj stopien wielomianu");
        int n = scanner.nextInt();

        double wspolczynniki[] = new double[n+1];
        for(int i = wspolczynniki.length-1; i >=0; i--){
            System.out.print("x^" + (i)+ ": ");
            wspolczynniki[i] = scanner.nextDouble();
        }

        System.out.println("Podaj a");
        double a = scanner.nextDouble();

        System.out.println("Podaj b");
        double b = scanner.nextDouble();

        System.out.println("Podaj dokladnosc");
        double epsilon = scanner.nextDouble();

        double pivot = 0;

        while(Math.abs(a-b)>epsilon){
            pivot = (a+b)/2;
            if(wartoscFunkcji(wspolczynniki,pivot)==0){
                System.out.println(pivot);
                break;
            }else if(wartoscFunkcji(wspolczynniki, pivot) * wartoscFunkcji(wspolczynniki, a) < 0) {
                b = pivot;
            } else {
                a = pivot;
            }
        }
        System.out.println(pivot);
    }

    public static void falsy(){
        Scanner scanner = new Scanner(System.in);
        int maxIteracje = 100;

        System.out.println("Podaj stopien wielomianu");
        int n = scanner.nextInt();

        double wspolczynniki[] = new double[n+1];
        for(int i = wspolczynniki.length-1; i >=0; i--){
            System.out.print("x^" + (i)+ ": ");
            wspolczynniki[i] = scanner.nextDouble();
        }

        System.out.println("Podaj a");
        double a = scanner.nextDouble();
        double fa = wartoscFunkcji(wspolczynniki,a);

        System.out.println("Podaj b");
        double b = scanner.nextDouble();
        double fb = wartoscFunkcji(wspolczynniki,b);

        System.out.println("Podaj dokladnosc");
        double epsilon = scanner.nextDouble();

        int iteracje = 0;
        while (iteracje < maxIteracje){
            double c = (a * fb - b * fa) / (fb - fa);
            double fc = wartoscFunkcji(wspolczynniki,c);
            if(Math.abs(fc) < epsilon){
                System.out.println(c);
                return;
            }else if(fa * fc < 0){
                b = c;
                fb = fc;
            }else{
                a = c;
                fa = fc;
            }
            iteracje++;
        }
    }

    public static void styczne(){//newton
        Scanner scanner = new Scanner(System.in);
        int maxIteracje = 100;

        System.out.println("Podaj stopien wielomianu");
        int n = scanner.nextInt();

        double wspolczynniki[] = new double[n+1];
        for(int i = wspolczynniki.length-1; i >=0; i--){
            System.out.print("x^" + (i)+ ": ");
            wspolczynniki[i] = scanner.nextDouble();
        }

        System.out.println("Podaj a");
        double a = scanner.nextDouble();

        System.out.println("Podaj b");
        double b = scanner.nextDouble();

        System.out.println("Podaj dokladnosc");
        double epsilon = scanner.nextDouble();

        double x0 = a;
        double x1 = b;

        double fx0 = wartoscFunkcji(wspolczynniki,x0);
        double fx1 = wartoscFunkcji(wspolczynniki,x1);
        double x2 = 0;


        int iteracje = 0;
        while (iteracje < maxIteracje){
            double dfdx = (fx1 - fx0) / (x1 - x0);
            x2 = x1 - fx1 / dfdx;
            double fx2 = wartoscFunkcji(wspolczynniki, x2);

            if (Math.abs(fx2) < epsilon) {
                System.out.println(x2);
                return;
            }

            x0 = x1;
            fx0 = fx1;
            x1 = x2;
            fx1 = fx2;
            iteracje++;
        }
    }

    public static double wartoscFunkcji(double wspolczynniki[], double x){
        double value = 0;
        for(int i = wspolczynniki.length-1; i >=0; i--){
            value+=Math.pow(x,i)*wspolczynniki[i];
        }
        return value;
    }

    public static double obliczWartoscPochodnej(double[] wspolczynniki, double x) {
        double value = 0;
        for (int i = 0; i < wspolczynniki.length-1; i++) {
            value += (wspolczynniki.length-1 - i) * wspolczynniki[i] * Math.pow(x, wspolczynniki.length-1 - i - 1);
        }
        return value;
    }


    //Przekazuj wspolczynniki w odwrotnej kolejności - tzn 3x^2 + x + 2 ==> {2,1,3}
    public static double obliczWartosc2giejPochodnej(double[] wspolczynniki, double x) {
        double result = 0.0;
        int n = wspolczynniki.length - 1; // stopień wielomianu
        for (int i = 2; i <= n; i++) {
            result += i * (i - 1) * wspolczynniki[i] * Math.pow(x, i - 2);
        }

        return result;
    }
}
