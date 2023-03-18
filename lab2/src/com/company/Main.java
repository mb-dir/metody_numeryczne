package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        metodaSiecznych();
//        bisekcja();
//        newton();
    }

    //równanie nieliniowe f(x)=0

    public static void metodaSiecznych(){
        //        Metoda siecznych to jedna z popularnych metod numerycznych używanych do rozwiązywania równań nieliniowych. Polega ona na iteracyjnym wyznaczaniu kolejnych przybliżeń rozwiązania równania na podstawie wartości funkcji w dwóch poprzednich punktach. Jest to metoda iteracyjna, która polega na przybliżaniu miejsca zerowego funkcji poprzez ciąg punktów znajdujących się na secantach przechodzących przez dwa poprzednie punkty. Jeśli funkcja jest ciągła i różniczkowalna, to metoda ta zbiega do rozwiązania równania.
//
//        Algorytm metody siecznych jest następujący:
//
//        Wybrać dwa punkty startowe x0 i x1.
//        Obliczyć wartości funkcji f w punktach x0 i x1.
//        Obliczyć wartość x2, korzystając ze wzoru:
//        x2 = x1 - (f(x1) * (x1 - x0)) / (f(x1) - f(x0))
//        Jeśli |f(x2)| < ε, gdzie ε to zadana dokładność, to zwrócić x2 jako przybliżenie rozwiązania równania.
//        W przeciwnym wypadku, powtarzać kroki 2-4, zastępując x0 przez x1, a x1 przez x2

        Scanner input = new Scanner(System.in);

        System.out.print("Podaj stopien funkcji f(x): ");
        int stopien = input.nextInt();

        double[] wspolczynniki = new double[stopien+1];

        System.out.println("Podaj wspolczynniki funkcji f(x) (w kolejnosci od najwyzszej potegi):");
        for (int i = stopien; i >= 0; i--) {
            System.out.print("a" + i + ": ");
            wspolczynniki[i] = input.nextDouble();
        }

        System.out.print("Podaj dokladnosc obliczen (ε): ");
        double epsilon = input.nextDouble();

        System.out.print("Podaj punkt startowy x0: ");
        double x0 = input.nextDouble();

        System.out.print("Podaj punkt startowy x1: ");
        double x1 = input.nextDouble();

        int maxIteracje = 100; // maksymalna liczba iteracji
        int ileItercji = 0; // liczba wykonanych iteracji

        double f0 = obliczWartoscFunkcji(wspolczynniki, x0);
        double f1 = obliczWartoscFunkcji(wspolczynniki, x1);

        while (Math.abs(f1) > epsilon && ileItercji < maxIteracje) {
            double x2 = x1 - (f1 * (x1 - x0)) / (f1 - f0);//wzor
            x0 = x1;
            x1 = x2;
            f0 = f1;
            f1 = obliczWartoscFunkcji(wspolczynniki, x1);
            ileItercji++;
            System.out.println("Iteracja " + ileItercji + ": x = " + x1);
        }

        if (Math.abs(f1) <= epsilon) {
            System.out.println("Rozwiazanie: x = " + x1);
        } else {
            System.out.println("Metoda siecznych nie zbiega do rozwiazania.");
        }
    }

    public static void bisekcja(){
    //Wybierz przedział początkowy [a,b], w którym szukasz rozwiązania równania nieliniowego f(x) = 0. Upewnij się, że f(a) i f(b) mają różne znaki.
        //
        //Oblicz wartość funkcji w punkcie środkowym przedziału c = (a + b) / 2.
        //
        //Jeśli f(c) = 0, to c jest rozwiązaniem równania nieliniowego. W przeciwnym razie, zastosuj regułę połowienia, aby wybrać nowy przedział, który zawiera rozwiązanie:
        //
        //Jeśli f(a) i f(c) mają różne znaki, to nowy przedział to [a,c].
        //W przeciwnym razie, f(c) i f(b) mają różne znaki, więc nowy przedział to [c,b].
        //Powtarzaj kroki 2-3, aż uzyskasz dostatecznie dokładne przybliżenie rozwiązania.
        Scanner input = new Scanner(System.in);
        System.out.print("Podaj stopień funkcji: ");
        int stopien = input.nextInt();
        double[] wspolczynniki = new double[stopien+1];
        System.out.println("Podaj wspolczynniki funkcji f(x) (w kolejnosci od najwyzszej potegi):");
        for (int i = stopien; i >= 0; i--) {
            System.out.print("a" + i + ": ");
            wspolczynniki[i] = input.nextDouble();
        }

        System.out.print("Podaj lewą stronę przedziału: ");
        double a = input.nextDouble();
        System.out.print("Podaj prawą stronę przedziału: ");
        double b = input.nextDouble();

        System.out.print("Podaj dokładność: ");
        double epsilon = input.nextDouble();

        double c = 0.0;
        while(Math.abs(b-a) >= epsilon) {
            c = (a+b) / 2;
            if(obliczWartoscFunkcji(wspolczynniki, c) == 0) {
                System.out.println(c);
            } else if(obliczWartoscFunkcji(wspolczynniki, c) * obliczWartoscFunkcji(wspolczynniki, a) < 0) {
                b = c;
            } else {
                a = c;
            }
        }
        System.out.println(c);
    }

    public static void newton(){
        //Wybierz początkową wartość x0.
        //Oblicz wartość funkcji f(x0) i jej pochodnej f'(x0) w punkcie x0.
        //Przybliż pierwiastek równania jako x1 = x0 - f(x0)/f'(x0).
        //Oblicz wartość funkcji f(x1) i jej pochodnej f'(x1) w punkcie x1.
        //Przybliż pierwiastek równania jako x2 = x1 - f(x1)/f'(x1).
        //Powtarzaj kroki 4-5 aż do osiągnięcia wymaganej dokładności.

        Scanner scanner = new Scanner(System.in);

        // Wprowadzanie danych przez użytkownika
        System.out.println("Podaj stopień funkcji:");
        int stopien = scanner.nextInt();

        double[] wspolczynniki = new double[stopien + 1];
        System.out.println("Podaj wspolczynniki funkcji f(x) (w kolejnosci od najwyzszej potegi):");
        for (int i = stopien; i >= 0; i--) {
            System.out.print("a" + i + ": ");
            wspolczynniki[i] = scanner.nextDouble();
        }

        System.out.println("Podaj wartość początkową:");
        double wartoscPoczotkowa = scanner.nextDouble();

        System.out.println("Podaj dokładność:");
        double epsilon = scanner.nextDouble();

        double fWartoscPoczotkowa = obliczWartoscFunkcji(wspolczynniki, wartoscPoczotkowa);
        double pochodnaWartoscPoczotkowa = obliczWartoscPochodnej(stopien, wspolczynniki, wartoscPoczotkowa);
        double wynik = wartoscPoczotkowa;
        double delta = Math.abs(fWartoscPoczotkowa/pochodnaWartoscPoczotkowa);

        while(delta < epsilon) {
            delta = obliczWartoscFunkcji(wspolczynniki, wynik) / obliczWartoscPochodnej(stopien, wspolczynniki, wynik);
            wynik -= delta;
        }
        System.out.println(wynik);
    }

    public static void falsy(){
        //Wybierz przedział początkowy [a, b], w którym znajduje się szukany pierwiastek funkcji f(x).
        //Oblicz wartości f(a) i f(b).
        //Oblicz punkt przecięcia linii łączącej punktów (a, f(a)) i (b, f(b)) z osią OX, tzn. punkt (c, 0), gdzie:
        //c = a - (b - a) * f(a) / (f(b) - f(a))
        //Jeśli f(c) = 0, to znaleźliśmy rozwiązanie i kończymy algorytm.
        //Sprawdź, w której połowie przedziału [a, b] znajduje się pierwiastek funkcji f(x).
        //Powtarzaj kroki 2-4, zastępując punkt końcowy przedziału a lub b przez punkt (c, f(c)), w zależności od tego, w której połowie przedziału znajduje się pierwiastek funkcji, aż do uzyskania wymaganej dokładności.


    }

    public static double obliczWartoscPochodnej(int stopien, double[] wspolczynniki, double x) {
        double value = 0;
        for (int i = 0; i < stopien; i++) {
            value += (stopien - i) * wspolczynniki[i] * Math.pow(x, stopien - i - 1);
        }
        return value;
    }

    public static double obliczWartoscFunkcji(double[] wspolczynniki, double x) {
        double wynik = 0;
        for (int i = wspolczynniki.length-1; i >= 0; i--) {
            wynik = wynik * x + wspolczynniki[i];
        }
        return wynik;
    }
}
