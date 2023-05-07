from numdifftools import Derivative

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
    result= ((b - a) / 6) * (fa + 4 * f((a + b) / 2) + fb)
    print("Metoda simpsona prosta")
    print(result)


def metoda_trapezow_zlozona():
    n = 10  # - ilość podprzedziałów
    h = (abs(a) + abs(b)) / n  # - szerokość podprzedziału
    suma = 0
    lewy = a
    prawy = a + h

    i = 0
    while i < n:
        flewy = f(lewy)
        fprawy = f(prawy)
        suma += ((prawy - lewy) / 2) * (flewy + fprawy)
        lewy = prawy
        prawy = lewy + h
        i += 1
    print("Metoda trapezów złożona")
    print(suma)

def metoda_simpsona_zlozona():
    n = 10  # - ilość podprzedziałów
    h = (b - a) / n
    S = 0
    i = 0
    while i <= n:
        x = a + i * h
        if i == 0 or i == n:
            S += f(x)
        elif i % 2 == 1:
            S += 4 * f(x)
        else:
            S += 2 * f(x)
        i += 1
    S *= h / 3
    print("metoda simpsona złożona")
    print(S)

metoda_simpsona_prosta()
metoda_trapezow_prosta()
metoda_trapezow_zlozona()
metoda_simpsona_zlozona()