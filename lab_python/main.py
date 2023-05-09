from numdifftools import Derivative
# NAPRAWA TABELI PRZESTAWNEJ
# -zliczamy wiersze i kolumny(ILE.NIEPUSTYCH)
# =PRZESUNIĘCIE($A$8(początek wierszy);ZAOKR.DO.CAŁK((WIERSZ()-WIERSZ($A$19(pierwszy wiersz do wypełniania)))/$H$9(ilość kolumn));0)
# =PRZESUNIĘCIE($B$7(początek kolumn);0;MOD((WIERSZ()-WIERSZ($B$19(pierwszy wiersz do wypełniania)));$H$9(ilość kolumn)))
# =WYSZUKAJ.PIONOWO(A19(pierwszy wiersz do wypełniania);$A$7:$E$13(zakres całej tabeli); PODAJ.POZYCJĘ(B19(pierwszy wiersz do wypełniania);$B$7:$E$7(zakres nagłówków kolumn);0)+1;0)
# SourceData:=Range("A4:L200").SpecialCells(xlCellTypeConstants)
def f(x):
    return x * x


a = -1
b = 1


def metoda_trapezow_prosta():
    fa = f(a)
    fb = f(b)

    df2 = Derivative(f, n=2)

    result = ((b - a) / 2) * (fa + fb)
    blad = (((b - a) ** 3) * df2(b)) / 12
    print("Metoda trapezów prosta")
    print(result)
    print(blad)


# metoda simpsona prosta

def metoda_simpsona_prosta():
    df4 = Derivative(f, n=4)
    fa = f(a)
    fb = f(b)
    result = ((b - a) / 6) * (fa + 4 * f((a + b) / 2) + fb)
    print("Metoda simpsona prosta")
    print(result)


def metoda_trapezow_zlozona():
    n = 4  # - ilość podprzedziałów
    h = (b - a) / n  # - szerokość podprzedziału
    suma = 0
    x = a + h

    i = 1
    while i < n:
        suma += f(x)
        x += h
        i += 1

    suma = h * (0.5 * f(a) + 0.5 * f(b) + suma)
    df2 = Derivative(f, n=2)
    R = (-((b - a) * h ** 2) / 12) * df2((a + b) / 2)
    print("Metoda trapezów złożona")
    print(suma)
    print(R)


def metoda_simpsona_zlozona():
    n = 4  # - ilość podprzedziałów
    if n % 2 == 1:
        print("n musi być parzyste")
        return

    h = (b - a) / n
    suma = 0
    i = 0

    while i < n:
        x = a + i * h
        if i % 2 == 0:
            suma += 2 * f(x)
        else:
            suma += 4 * f(x)
        i += 1
    suma *= h / 3

    df4 = Derivative(f, n=4)
    R=(-((b-a)*h**4)/180)*df4((a+b))
    # sympy, bo ta biblioteka to zamiast 0 daje przybliżenie zera

    print("metoda simpsona złożona")
    print(suma)
    print(R)


# metoda_simpsona_prosta()
# metoda_trapezow_prosta()
# metoda_trapezow_zlozona()
metoda_simpsona_zlozona()
# interpolacja i aproksymacja(sredniokwadratowa)
