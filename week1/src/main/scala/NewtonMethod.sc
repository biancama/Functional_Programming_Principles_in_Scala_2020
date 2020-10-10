
def sqrt(x: Double) = {
  def abs(x: Double) = if (x < 0) -x else x

  def isGoodEnough(guess: Double) = if (abs(guess*guess - x)/ x <= 0.01) true else false

  def improveGuess(guess: Double) = ( guess + x / guess) / 2

  def sqrtIter(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrtIter(improveGuess(guess))

  sqrtIter(1.0)
}

def fact(n: Int) = {
  def fact(n: Int, acc: Int): Int = {
    if (n < 2) acc else fact(n -1, n * acc)
  }
  fact(n, 1)
}

sqrt(4)

fact(4)
