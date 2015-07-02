import sys

a, b, n = [int(x) for x in sys.stdin.readline().split()]
c = b*b + a

for i in range(3, n):
    a, b, c = b, c, c*c+b

print(c)