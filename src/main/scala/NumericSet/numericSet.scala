package NumericSet

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello, NumericSet!")
  }
}

import scala.math.Ordering.Implicits._

class NumericSet[T: Numeric: Ordering] private (
    private val root: Option[NumericSet.Tree[T]]
) {
  import NumericSet._

  def isEmpty: Boolean = root.isEmpty

  def contains(x: T): Boolean = {
    def loop(tree: Option[Tree[T]]): Boolean = tree match {
      case Some(Tree(value, left, right)) =>
        if (implicitly[Numeric[T]].lt(x, value)) loop(left)
        else if (implicitly[Numeric[T]].gt(x, value)) loop(right)
        else true
      case None => false
    }
    loop(root)
  }

  def insert(x: T): NumericSet[T] = {
    def loop(tree: Option[Tree[T]]): Option[Tree[T]] = tree match {
      case Some(Tree(value, left, right)) =>
        if (implicitly[Numeric[T]].lt(x, value))
          Some(Tree(value, loop(left), right))
        else if (implicitly[Numeric[T]].gt(x, value))
          Some(Tree(value, left, loop(right)))
        else tree
      case None => Some(Tree(x, None, None))
    }
    new NumericSet(loop(root))
  }

  def rebalance(): NumericSet[T] = {
    new NumericSet(NumericSet.rebalanceTree(this.toList()))
  }

  def union(that: NumericSet[T]): NumericSet[T] = {
    NumericSet.fromList(this.toList() ++ that.toList())
  }

  def intersect(that: NumericSet[T]): NumericSet[T] = {
    NumericSet.fromList(this.toList().filter(that.contains))
  }
  def diff(that: NumericSet[T]): NumericSet[T] = {
    NumericSet.fromList(this.toList().filter(x => !that.contains(x)))
  }

  def length(): Int = {
    def loop(tree: Option[Tree[T]]): Int = tree match {
      case Some(Tree(_, left, right)) => 1 + loop(left) + loop(right)
      case None                       => 0
    }
    loop(root)
  }

  def toList(): List[T] = {
    def loop(tree: Option[Tree[T]]): List[T] = tree match {
      case Some(Tree(value, left, right)) => loop(left) ::: value :: loop(right)
      case None                           => List.empty[T]
    }
    loop(root)
  }

  override def toString(): String = {
    this.toList().toString()
  }

}

object NumericSet {
  private case class Tree[T](
      value: T,
      left: Option[Tree[T]],
      right: Option[Tree[T]]
  )

  def empty[T: Numeric: Ordering]: NumericSet[T] = new NumericSet[T](None)

  def fromList[T: Numeric: Ordering](l: List[T]): NumericSet[T] = {
    new NumericSet(rebalanceTree(l.distinct.sorted))
  }

  private def rebalanceTree[T: Numeric: Ordering](
      sortedList: List[T]
  ): Option[Tree[T]] = {
    if (sortedList.isEmpty) None
    else {
      val mid = sortedList.length / 2
      Some(
        Tree(
          sortedList(mid),
          rebalanceTree(sortedList.take(mid)),
          rebalanceTree(sortedList.drop(mid + 1))
        )
      )
    }
  }
}
