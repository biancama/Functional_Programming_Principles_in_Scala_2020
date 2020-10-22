class Rational(x: Int, y:Int) {
  def numer = x / g
  def denom = y / g

  private def gdc(a:Int, b: Int): Int = if (b == 0) a else gdc(b, a % b)
  private val g = gdc(x, y)

  def add (that: Rational): Rational =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def neg(): Rational =
     new Rational(-numer, denom)

  def sub (that: Rational) = add(that.neg())
  override def toString: String = numer + "/" + denom
}


// this definition introduce 2 entities. A new type name Rational and a constructor Rational created in 2 different namespaces

val rat1 = new Rational(1, 2)
rat1.numer

val rat2 = new Rational(3, 4)

val rat3 = rat1.add(rat2)

val rat4 = rat2.sub(rat1)