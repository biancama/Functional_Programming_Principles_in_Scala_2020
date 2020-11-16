# Liskov principle
Let Φ(x) be a property provable about objects x of type T. Then Φ(y) should be true for objects y of type S where S is a subtype of T.


The principle defines that objects of a superclass shall be replaceable with objects of its subclasses without breaking the application.



# Variance rule for Functions

If `A2 <: A1` AND `B1 <: B2`  then

`A1 => B1 <: A2 <: B2`

Fuction are contravariant in thei argument types and covariant in their result type

“Be conservative in what you do, be liberal in what you accept from others”  --- robustness principle

```
trait Function[-T, +U] {
	def apply(x:T): U
} 

```



why covariant can be a problem for Array .

It's a problem if there is a method that can update AND is taking a generic argument

```
class Array[+T] {
	def update(x: T) ...
}
```

Scala compiler will simply check 

- covariant type parameters appear only in result 

- contravariant type parameters appear only in input parameters

Example of simple list 
```
trait List[+T] {
	def isEmpty: Boolean
	def head: T
	def tail: List [T]
}

class Cons[T] (val head: T, val tail: List[T]) extends List[T] {
	def isEmpty: Boolean = false

}

object Nil extends List[Nothing] {
	def isEmpty: Boolean = true
	def head = throw new NoSuchElementException("")
	def tail = throw new NoSuchElementException("")
}


object test {
	val x: List[String] = Nil // I can do this because List is covariant 
}
```


For example we cannot add  a method `prepend [T] (elem: T): List[T] = new Cons(elem, this)` 
If we had `Empty <: IntSet` and `NonEmpty <: IntSet` 
So
```
val xs: List[IntSet] = ....
xs.prepend(Empty)

```

But if we do the same 
```
val ys: List[NonEmpty] = ....
ys.prepend(Empty)

```
it will throw an exception . so if `List[NonEmpty] <: List[IntSet]` it will violate Liskov principle.

so the method prepend should be `prepend [U >: T] (elem: U): List[U] = new Cons(elem, this)`


# pattern matching on a pair
```
{ case p1 => e1...   case pn => en }
```
is equivalent to 
```
x => x match { case p1 => e1...   case pn => en }
```
