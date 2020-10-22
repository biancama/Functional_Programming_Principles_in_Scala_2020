def sum(f: Int => Int)(a: Int, b:Int): Int =
  if (a > b ) 0
  else f(a) + sum(f)(a + 1, b)


def product(f: Int => Int)(a: Int, b:Int): Int =
  if (a > b ) 1
  else f(a) * product(f)(a + 1, b)


def mapReduce(f: Int => Int, neutralValue: Int, combine: (Int, Int) => Int)(a: Int, b:Int): Int =
  if (a > b ) neutralValue
  else combine(f(a), mapReduce(f, neutralValue, combine)(a+a, b))

def sum1 (f: Int => Int)(a: Int, b:Int): Int = mapReduce(f, 0, (x, y) => x + y)(a, b)

def product1 (f: Int => Int)(a: Int, b:Int): Int = mapReduce(f, 1, (x, y) => x * y)(a, b)


product(x => x * x)(3, 4)

def factorial(n : Int): Int = product(x => x) (1, n)

factorial(5)


