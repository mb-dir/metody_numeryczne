from numdifftools import Derivative


# metoda trapezów prosta
def f(x):
    return x * x


a = -1
b = 1

fa = f(a)
fb = f(b)

df = Derivative(f, n=2)

result = ((b - a) / 2) * (fa + fb)
blad = (((b - a) ** 3) * df(b)) / 12
print(result)
print(blad)

# metoda simpsona prosta

result2 = ((b-a)/6)*(fa+4*f((a+b)/2)+fb)
print(result2)