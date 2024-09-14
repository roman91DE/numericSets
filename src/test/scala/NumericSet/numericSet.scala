import munit.FunSuite
import NumericSet._


class NumericSetSuite extends FunSuite {

  // Add this at the beginning of the class
  def time[R](block: => R): Long = {
    val start = System.nanoTime()
    block
    val end = System.nanoTime()
    end - start
  }

  test("empty set should not contain any elements") {
    val set = NumericSet.empty[Int]
    assert(!set.contains(1))
  }

  test("inserted element should be contained in the set") {
    val set = NumericSet.empty[Int].insert(1)
    assert(set.contains(1))
  }

  test("set should not contain elements that were not inserted") {
    val set = NumericSet.empty[Int].insert(1)
    assert(!set.contains(2))
  }

  test("multiple inserted elements should be contained in the set") {
    val set = NumericSet.empty[Int].insert(1).insert(2).insert(3)
    assert(set.contains(1))
    assert(set.contains(2))
    assert(set.contains(3))
  }

  test("inserting duplicate elements should not affect set") {
    val set = NumericSet.empty[Int].insert(1).insert(1)
    assert(set.contains(1))
    // Assuming the set does not allow duplicates, the size should be 1
    // This would require a size method in NumericSet to verify
  }

  test("set should handle negative numbers") {
    val set = NumericSet.empty[Int].insert(-1).insert(-2).insert(-3)
    assert(set.contains(-1))
    assert(set.contains(-2))
    assert(set.contains(-3))
  }

  test("set should handle mixed positive and negative numbers") {
    val set = NumericSet.empty[Int].insert(-1).insert(2).insert(-3).insert(4)
    assert(set.contains(-1))
    assert(set.contains(2))
    assert(set.contains(-3))
    assert(set.contains(4))
  }

  // New tests for the length method
  test("length of empty set should be 0") {
    val set = NumericSet.empty[Int]
    assertEquals(set.length(), 0)
  }

  test("length of set with one element should be 1") {
    val set = NumericSet.empty[Int].insert(1)
    assertEquals(set.length(), 1)
  }

  test("length of set with multiple elements should be correct") {
    val set = NumericSet.empty[Int].insert(1).insert(2).insert(3)
    assertEquals(set.length(), 3)
  }

  test("length of set with duplicate elements should count unique elements only") {
    val set = NumericSet.empty[Int].insert(1).insert(1)
    assertEquals(set.length(), 1)
  }

  test("build NumericSet from List of Ints") {
    val list = List(5, 2, 8, 1, 9, 3)
    val set = NumericSet.fromList(list)
    assertEquals(set.toList(), List(1, 2, 3, 5, 8, 9))
  }

  test("build NumericSet from List of Doubles") {
    val list = List(5.5, 2.2, 8.8, 1.1, 9.9, 3.3)
    val set = NumericSet.fromList(list)
    assertEquals(set.toList(), List(1.1, 2.2, 3.3, 5.5, 8.8, 9.9))
  }

  test("union of two NumericSets with Ints") {
    val set1 = NumericSet.fromList(List(1, 3, 5))
    val set2 = NumericSet.fromList(List(2, 4, 6))
    val unionSet = set1.union(set2)
    assertEquals(unionSet.toList(), List(1, 2, 3, 4, 5, 6))
  }

  test("union of two NumericSets with Doubles") {
    val set1 = NumericSet.fromList(List(1.1, 3.3, 5.5))
    val set2 = NumericSet.fromList(List(2.2, 4.4, 6.6))
    val unionSet = set1.union(set2)
    assertEquals(unionSet.toList(), List(1.1, 2.2, 3.3, 4.4, 5.5, 6.6))
  }

  test("union with overlapping elements") {
    val set1 = NumericSet.fromList(List(1, 2, 3, 4))
    val set2 = NumericSet.fromList(List(3, 4, 5, 6))
    val unionSet = set1.union(set2)
    assertEquals(unionSet.toList(), List(1, 2, 3, 4, 5, 6))
  }

  // Tests for doubles
  test("empty set should not contain any elements (Double)") {
    val set = NumericSet.empty[Double]
    assert(!set.contains(1.0))
  }

  test("inserted element should be contained in the set (Double)") {
    val set = NumericSet.empty[Double].insert(1.5)
    assert(set.contains(1.5))
  }

  test("set should not contain elements that were not inserted (Double)") {
    val set = NumericSet.empty[Double].insert(1.5)
    assert(!set.contains(2.5))
  }

  test("multiple inserted elements should be contained in the set (Double)") {
    val set = NumericSet.empty[Double].insert(1.1).insert(2.2).insert(3.3)
    assert(set.contains(1.1))
    assert(set.contains(2.2))
    assert(set.contains(3.3))
  }

  test("inserting duplicate elements should not affect set (Double)") {
    val set = NumericSet.empty[Double].insert(1.5).insert(1.5)
    assert(set.contains(1.5))
  }

  test("set should handle negative numbers (Double)") {
    val set = NumericSet.empty[Double].insert(-1.1).insert(-2.2).insert(-3.3)
    assert(set.contains(-1.1))
    assert(set.contains(-2.2))
    assert(set.contains(-3.3))
  }

  test("set should handle mixed positive and negative numbers (Double)") {
    val set = NumericSet.empty[Double].insert(-1.1).insert(2.2).insert(-3.3).insert(4.4)
    assert(set.contains(-1.1))
    assert(set.contains(2.2))
    assert(set.contains(-3.3))
    assert(set.contains(4.4))
  }

  // New tests for the length method with doubles
  test("length of empty set should be 0 (Double)") {
    val set = NumericSet.empty[Double]
    assertEquals(set.length(), 0)
  }

  test("length of set with one element should be 1 (Double)") {
    val set = NumericSet.empty[Double].insert(1.5)
    assertEquals(set.length(), 1)
  }

  test("length of set with multiple elements should be correct (Double)") {
    val set = NumericSet.empty[Double].insert(1.1).insert(2.2).insert(3.3)
    assertEquals(set.length(), 3)
  }

  test("length of set with duplicate elements should count unique elements only (Double)") {
    val set = NumericSet.empty[Double].insert(1.5).insert(1.5)
    assertEquals(set.length(), 1)
  }

  test("rebalancing should not change set contents (Int)") {
    val numbers = (1 to 1000).toList
    val set = NumericSet.fromList(numbers)
    assertEquals(set.toList(), set.rebalance().toList())
  }

  test("rebalancing should not change set contents (Double)") {
    val numbers = (1 to 1000).map(_.toDouble / 10).toList
    val set = NumericSet.fromList(numbers)
    assertEquals(set.toList(), set.rebalance().toList())
  }


  test("rebalancing improves lookup time") {
    val numbers = (1 to 10000).toList
    var unbalancedSet = NumericSet.empty[Int]
    for (i <- numbers) {
      unbalancedSet = unbalancedSet.insert(i)
    }
    val rebalancedSet = unbalancedSet.rebalance()
    
    val lookupTimeUnbalanced = time {unbalancedSet.contains(10000)}
    val lookupTimeRebalanced = time {rebalancedSet.contains(10000)}
    assert(lookupTimeUnbalanced > lookupTimeRebalanced)
  }

  test("intersect of two NumericSets with Ints") {
    val set1 = NumericSet.fromList(List(1,2, 3, 5))
    val set2 = NumericSet.fromList(List(2,3, 4, 6))
    val unionSet = set1.intersect(set2)
    assertEquals(unionSet.toList(), List(2,3))
  }

  test("diff of two NumericSets with Ints") {
    val set1 = NumericSet.fromList(List(1,2, 3, 5))
    val set2 = NumericSet.fromList(List(2,3, 4, 6))
    val unionSet = set1.diff(set2)
    assertEquals(unionSet.toList(), List(1, 5))
  }


}
