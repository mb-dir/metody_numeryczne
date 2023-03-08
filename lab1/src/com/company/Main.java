package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj stopien wielomianu: ");
        int n = scanner.nextInt();
        float[] wielomian = new float[n+1];

        for(int i = 0; i < wielomian.length; i++){
            System.out.println("Podaj wspolczynnik");
            wielomian[i] = scanner.nextInt();
        }
        System.out.println();

        System.out.println("Podaj pkt dla ktorego ma byc wyliczona wartosc");
        float d = scanner.nextFloat();
        float w = 0;
        for(int i = 0; i < wielomian.length; i++){
            w+=Math.pow(d, wielomian.length-1-i)*wielomian[i];;
        }
        System.out.println(w);

        System.out.println("---");
        System.out.println("HORNER");
        System.out.println("---");

        System.out.print("Podaj stopien wielomianu: ");
        int stopienWielomianu = scanner.nextInt();

        double[] wspolczynniki = new double[stopienWielomianu+1];

        for (int i = 0; i < wspolczynniki.length; i++) {
            System.out.print("Podaj wspolczynnik dla x^" + (wspolczynniki.length-i-1) + ": ");
            wspolczynniki[i] = scanner.nextDouble();
        }

        System.out.print("Podaj dzielnik dwumianowy: ");
        double dzielnik = scanner.nextDouble();
        double wynik = wspolczynniki[0];
        for (int i = 1; i < wspolczynniki.length; i++){
            System.out.println(wynik);
            wynik = (wynik*dzielnik) + wspolczynniki[i];
        }
        System.out.println(wynik);
    }
}

//rozwiązywania równan nieliniowe - stycznie/bisekcji/newton/falsy