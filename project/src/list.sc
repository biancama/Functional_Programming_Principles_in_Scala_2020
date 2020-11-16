def length[T](l: List[T]): Int = {
  def length[T](l: List[T], acc: Int):Int = l match {
    case Nil => acc
    case _::xs => length(xs, acc + 1)
  }
  length(l, 0)
}

def last[T](l: List[T]): T  = {
  if (l.isEmpty) throw new UnsupportedOperationException("last on an empty list")
  else if (l.size == 1) l(0)
  else last(l.tail)
 }

def concat1[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case Nil => ys
  case z::zs => z::concat1(zs, ys)
}

def reverse1[T](l: List[T]): List[T] = l match {
  case Nil => List()
  case x::xs => concat1(reverse1(xs), List(x))
}

def reverse2[T](l: List[T]): List[T] = {
  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case Nil => ys
    case z::zs => concat(zs, z::ys)
  }
  concat(l, List())
}

def flatten(l: List[Any]): List[Any] = l match {
  case Nil => Nil
  case (x: List[_])::xs => flatten(x) ++ flatten(xs)
  case x::xs => x::flatten(xs)
}

def merge(xs: List[Int], ys: List[Int]): List[Int] =
  (xs, ys) match {
    case (z, Nil) => z
    case (Nil, z) => z
    case(z::zs, h::hs) => if (z <= h) z::merge(zs, ys) else h::merge(xs, hs)
  }


def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x:: _ => {
    val (sameElement, rest) = xs.span(e => e == x)
    sameElement :: pack(rest)
  }
}

def encode1[T] (xs: List[T]): List[(T, Int)] = xs match {
  case Nil => Nil
  case x:: _ => {
    val (sameElement, rest) = xs.span(e => e == x)
    (sameElement.head, sameElement.length) :: encode1(rest)
  }
}

def encode[T] (xs: List[T]): List[(T, Int)] = pack(xs) map (ys => (ys.head, ys.length))

def concat[T](xs: List[T], ys: List[T]): List[T] = (xs foldRight ys)(_ :: _)
/*
  this is fold right
      op
    /    \
  x1      op
         /  \
        x2    op

        ..........

               op
              /  \
             xn   ys    so prepend will be ok

this is fold left
                      op
                   /     \
                          xn

              .........
            op
          /   \
       op      x2
     /   \
   ys     x1   /// so prepend not working
 */

def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]())( (x, l) => f(x)::l)

def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0)((_, x) => x + 1)

length(List(1,2,3,4,5))
last(List(1,2,3,4,5, 6))
concat1(List(1,2,3,4,5),List(1,2,3,4,5, 6))

reverse1(List(1,2,3,4,5, 6))
reverse2(List(1,2,3,4,5, 6))

flatten(List(List(1,1),2,List(3,List(5,8))))

merge(List(1, 3, 5, 7), List(2, 4, 6, 8))

pack(List("a", "a", "a", "b", "c", "c", "a"))

encode(List("a", "a", "a", "b", "c", "c", "a"))

concat(List(1,2,3,4,5),List(1,2,3,4,5, 6))


mapFun(List(1,2,3,4,5, 6), (x: Int) => x * x)

