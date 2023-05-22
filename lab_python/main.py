from sympy import Symbol, diff
from scipy.interpolate import lagrange
import numpy as np


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


# metoda_trapezow_prosta()
# metoda_simpsona_prosta()
# metoda_trapezow_zlozona()
# metoda_simpsona_zlozona()
# interpolacja i aproksymacja(sredniokwadratowa)


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
print(lagrange1(x1, y1, 1))

x = np.array([-1, 0, 1, 2])
y = np.array([3, 1, 2, -6])
poly = lagrange(x, y)
print(poly(1))
