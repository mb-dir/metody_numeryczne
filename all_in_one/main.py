from numdifftools import Derivative
from sympy import Symbol, diff
from scipy.interpolate import lagrange
import matplotlib.pyplot as plt
import numpy as np


def horner():
    def schemta_hornera(wspolczynniki_wielomianu, wyraz_dwumianu):
        n = len(wspolczynniki_wielomianu)

        wspolczynniki_ilorazu = [0] * n  # nowy wielomianu
        wspolczynniki_ilorazu[0] = wspolczynniki_wielomianu[0]

        for i in range(1, n):
            wspolczynniki_ilorazu[i] = wspolczynniki_ilorazu[i - 1] * wyraz_dwumianu + wspolczynniki_wielomianu[i]

        reszta = wspolczynniki_ilorazu[-1]  # reszta znajduje się na samym końcu
        return wspolczynniki_ilorazu, reszta

    wspolczynniki_wielomianu = [1, -3, 5, -15]  # Współczynniki wielomianu: x^3 - 3x^2 + 5x -15
    wyraz_dwumianu = 3  # Wyraz dwumianu: (x - 3)

    wspolczynniki_nowego_wielomianu, reszta = schemta_hornera(wspolczynniki_wielomianu, wyraz_dwumianu)

    print("Współczynniki nowego wielomianu:", wspolczynniki_nowego_wielomianu)  # Współczynniki ilorazu: [1, 0, 5, 0]
    print("Reszta:", reszta)  # Reszta: 0 => jest podzielny


# horner()

def sieczne():
    def f(x):
        return x ** 2 - 2 * x - 2

    x0 = -1
    x1 = 0
    f0 = f(x0)
    f1 = f(x1)
    maxIteracje = 100
    ileIteracji = 0
    epsilon = 0.001

    while abs(f1) > epsilon and ileIteracji < maxIteracje:
        x2 = x1 - (f1 * (x1 - x0)) / (f1 - f0)  # wzór
        x0 = x1
        x1 = x2
        f0 = f1
        f1 = f(x1)
        ileIteracji += 1

    if abs(f1) <= epsilon:
        print("Rozwiązanie: x =", x1)
    else:
        print("Metoda siecznych nie zbiega do rozwiązania.")


# sieczne()

def bisekcja():
    def f(x):
        return x ** 2 - 2 * x - 2

    a = 1
    b = 3
    fa = f(a)
    fb = f(b)

    if fa * fb < 0:
        ileIteracji = 0
        epsilon = 0.001
        pivot = 0
        while abs(a - b) > epsilon:
            pivot = (a + b) / 2

            if f(pivot) == 0:  # jeżeli trafimy dokładnie
                print(pivot)
                print("Potrzebne iteracje: ")
                print(ileIteracji)
            elif f(pivot) * f(a) < 0:
                b = pivot
            else:
                a = pivot
            ileIteracji += 1
        print("Iteracje: ")
        print(ileIteracji)
        print(pivot)
    else:
        print("Zalozenia nie sa spelnione")


# bisekcja()

def falsy():
    def f(x):
        return x ** 2 - 2 * x - 2

    a = -1
    b = 1
    epsilon = 0.0001
    fa = f(a)
    fb = f(b)
    maxIteracji = 100
    ileIteracji = 0

    while ileIteracji < maxIteracji:
        c = (a * fb - b * fa) / (fb - fa)  # wzór
        fc = f(c)
        if abs(fc) < epsilon:
            print(c)
            return
        elif fa * fc < 0:
            b = c
            fb = fc
        else:
            a = c
            fa = fc

    ileIteracji += 1


# falsy()

def styczne():  # newton

    def f(x):
        return x ** 2 - 2 * x - 2

    x0 = 2
    x1 = 3
    epsilon = 0.0001
    fx0 = f(x0)
    fx1 = f(x1)

    pochodna = Derivative(f, n=2)

    pochodnax0 = pochodna(x0)
    pochodnax1 = pochodna(x1)

    x2 = 0

    if fx0 * pochodnax0 > 0:
        x2 = x0
    elif fx1 * pochodnax1 > 0:
        x2 = x1
    else:
        print("Zalozenia nie sa spelnione")
        return

    print("Punkt poczatkowy: ")
    print(x2)

    iteracje = 0
    maxIteracje = 100

    while iteracje < maxIteracje:
        dfdx = pochodna(x2)
        x2 = x1 - fx1 / dfdx
        fx2 = f(x2)

        if abs(fx2) < epsilon:
            print(x2)
            print("iteracje: ")
            print(iteracje)
            return
        x1 = x2
        fx1 = fx2
        iteracje += 1


# styczne()

# Dane do funkcji związanych z całkami
def f(x):
    return x ** 2


a = -1
b = 1

# Tworzenie symbolicznej zmiennej x
x = Symbol('x')

# Tworzenie symbolicznej funkcji f(x)
f_expr = x ** 2


def metoda_trapezow_prosta():
    fa = f(a)
    fb = f(b)

    # Obliczanie 2-tej pochodnej
    df2_expr = diff(f_expr, x, 2)
    # Obliczanie wartości 2-tej pochodnej w punkcie b
    df2_value = df2_expr.subs(x, b)

    result = ((b - a) / 2) * (fa + fb)
    blad = (((b - a) ** 3) * df2_value) / 12

    print("Metoda trapezów prosta")
    print("Wynik:")
    print(result)
    print("Błąd:")
    print(blad)
    print("\n")


# metoda simpsona prosta
def metoda_simpsona_prosta():
    fa = f(a)
    fb = f(b)
    result = ((b - a) / 6) * (fa + 4 * f((a + b) / 2) + fb)
    print("Metoda simpsona prosta")
    print("Wynik:")
    print(result)
    print("\n")


def metoda_trapezow_zlozona():
    n = 4  # - ilość podprzedziałów
    h = (b - a) / n  # - szerokość podprzedziału
    suma = 0
    x1 = a + h

    i = 1
    while i < n:
        suma += f(x1)
        x1 += h
        i += 1

    suma = h * (0.5 * f(a) + 0.5 * f(b) + suma)

    # Obliczanie 2-tej pochodnej
    df2_expr = diff(f_expr, x, 2)
    # Obliczanie wartości 2-tej pochodnej w punkcie (a+b)/2
    df2_value = df2_expr.subs(x, (a + b) / 2)

    R = (-((b - a) * h ** 2) / 12) * df2_value
    print("Metoda trapezów złożona")
    print("Wynik:")
    print(suma)
    print("Błąd:")
    print(R)
    print("\n")


def metoda_simpsona_zlozona():
    n = 4  # - ilość podprzedziałów
    if n % 2 == 1:
        print("n musi być parzyste")
        return

    h = (b - a) / n
    suma = 0
    i = 0

    while i < n:
        x1 = a + i * h
        if i % 2 == 0:
            suma += 2 * f(x1)
        else:
            suma += 4 * f(x1)
        i += 1
    suma *= h / 3

    # Obliczanie 4-tej pochodnej
    df4_expr = diff(f_expr, x, 4)
    # Obliczanie wartości 4-tej pochodnej w punkcie (a+b)
    df4_value = df4_expr.subs(x, a + b)

    R = (-((b - a) * h ** 4) / 180) * df4_value

    print("metoda simpsona złożona")
    print("Wynik:")
    print(suma)
    print("Błąd:")
    print(R)
    print("\n")


metoda_trapezow_prosta()
metoda_simpsona_prosta()
metoda_trapezow_zlozona()
metoda_simpsona_zlozona()

# Lagrange(interpolacja) własny
# Obliczają tyko wartości, nie zwracają wielomianu
def lagrange1(x_data, y_data, a):
    n = len(x_data)
    y_interp = 0

    for i in range(n):
        L = 1
        for j in range(n):
            # Obliczamy wartość "pojedyńczego" węzła
            if i != j:
                L *= (a - x_data[j]) / (x_data[i] - x_data[j])
        # Dodajemy ją do tego co już jest
        y_interp += y_data[i] * L
    return y_interp


x1 = [-1, 0, 1, 2]
y1 = [3, 1, 2, -6]
# print(lagrange1(x1, y1, 1))

# lagrange z pythona
x = np.array([-1, 0, 1, 2])
y = np.array([3, 1, 2, -6])
poly = lagrange(x, y)
# print(poly(1))

# aproksymacja średniokwadratowa
def aproksymacja():
    # Dane wejściowe
    x = np.array([-1, 0, 1, 2])
    y = np.array([3, 1, 2, -6])

    # Stopień wielomianu
    degree = 3

    # Przygotowanie macierzy Vandermonde'a
    X = np.vander(x, degree + 1, increasing=True)

    # Obliczenie współczynników aproksymacji średniokwadratowej
    coefficients = np.linalg.lstsq(X, y, rcond=None)[0]

    # Generowanie punktów na wykresie do wizualizacji
    x_plot = np.linspace(x.min(), x.max(), 100)
    X_plot = np.vander(x_plot, degree + 1, increasing=True)
    y_plot = np.dot(X_plot, coefficients)

    # Wykres danych i dopasowanej krzywej
    plt.scatter(x, y, label='Dane')
    plt.plot(x_plot, y_plot, color='red', label='Aproksymacja')
    plt.xlabel('x')
    plt.ylabel('y')
    plt.legend()
    plt.show()

# aproksymacja()
