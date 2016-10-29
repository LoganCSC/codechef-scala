/** Copyright by Barry G. Becker, 2016. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package common.search

import java.util
import java.util.Comparator

import scala.collection.mutable

/**
  * A priority queue implementation based on a binary heap.
  * It is pretty much the same as PriorityQueue provided by java
  * with two modifications
  *  - It allows updating the priority of nodes (needed by A*)
  *  - Does not implement Queue interface since that has more methods than needed
  *
  * @author Josh Bloch, Doug Lea, modified by Barry Becker as indicated above.
  */
object HeapPriorityQueue {

  private val DEFAULT_INITIAL_CAPACITY: Int = 256

  /**
    * The maximum size of array to allocate.
    * Some VMs reserve some header words in an array.
    * Attempts to allocate larger arrays may result in
    * OutOfMemoryError: Requested array size exceeds VM limit
    */
  private val MAX_ARRAY_SIZE: Int = Integer.MAX_VALUE - 8

  private def hugeCapacity(minCapacity: Int): Int = {
    if (minCapacity < 0) // overflow
      throw new OutOfMemoryError
    if (minCapacity > MAX_ARRAY_SIZE) Integer.MAX_VALUE
    else MAX_ARRAY_SIZE
  }
}

/**
  * Creates a PriorityQueue with the specified initial capacity
  * that orders its elements according to the specified comparator.
  * @param initialCapacity the initial capacity for this priority queue
  * @param comparator The comparator, or null if priority queue uses elements' natural ordering.
  */
class HeapPriorityQueue[S, T](val initialCapacity: Int, val comparator: Comparator[_ >: Node[S, T]])
  extends UpdatablePriorityQueue[S, T] {

  // Note: This restriction of at least one is not actually needed,
  // but continues for 1.5 compatibility
  if (initialCapacity < 1) throw new IllegalArgumentException
  this.queue = new Array[AnyRef](initialCapacity)
  this.indexMap = mutable.Map[Node[S, T], Integer]() //HeapPriorityQueue.DEFAULT_INITIAL_CAPACITY)

  /**
    * Priority queue represented as a balanced binary heap: the two
    * children of queue[n] are queue[2*n+1] and queue[2*(n+1)].  The
    * priority queue is ordered by comparator, or by the elements'
    * natural ordering, if comparator is null: For each node n in the
    * heap and each descendant d of n, n <= d.  The element with the
    * lowest value is in queue[0], assuming the queue is nonempty.
    */
  private var queue: Array[AnyRef] = _

  /** allows for quick lookup of a nodes position in the heap. Required for updating priority of nodes */
  private var indexMap: mutable.Map[Node[S, T], Integer] = _

  /**
    * The number of elements in the priority queue.
    */
  private var size1: Int = 0

  /**
    * Creates a PriorityQueue with the default initial
    * capacity (11) that orders its elements according to their Comparable natural ordering.
    */
  def this() {
    this(HeapPriorityQueue.DEFAULT_INITIAL_CAPACITY, null)
  }

  /**
    * Creates a PriorityQueue with the specified initial
    * capacity that orders its elements according to their Comparable natural ordering.
    *
    * @param initialCapacity the initial capacity for this priority queue
    */
  def this(initialCapacity: Int) {
    this(initialCapacity, null)
  }

  /**
    * Increases the capacity of the array.
    *
    * @param minCapacity the desired minimum capacity
    */
  private def grow(minCapacity: Int) {
    val oldCapacity: Int = queue.length
    // Double size if small; else grow by 50%
    var newCapacity: Int = oldCapacity + (if (oldCapacity < 4096) oldCapacity + 2
    else oldCapacity >> 1)
    // overflow-conscious code
    if (newCapacity - HeapPriorityQueue.MAX_ARRAY_SIZE > 0) newCapacity = HeapPriorityQueue.hugeCapacity(minCapacity)
    queue = util.Arrays.copyOf(queue, newCapacity)
  }

  def pop: Node[S, T] = {
    val minNode: Node[S, T] = this.peek
    this.removeAt(0)
    minNode
  }

  def addOrUpdate(node: Node[S, T]): Boolean = if (indexMap.contains(node)) {
    val index: Int = indexMap(node)
    this.siftUp(index, node)
    true
  }
  else {
    add(node)
    false
  }

  /**
    * Removes the ith element from queue.
    * First remove the last put where the removed one was.
    * Then shift it up.
    */
  private def removeAt(i: Int) {
    assert(i >= 0 && i < this.size1)
    val s: Int = {
      size1 -= 1; size1
    }
    val removed: Node[S, T] = queue(i).asInstanceOf[Node[S, T]]
    queue(i) = queue(s)
    queue(s) = null
    if (s != i) {
      // removing other than last
      val node: Node[S, T] = queue(i).asInstanceOf[Node[S, T]]
      siftDown(i, node)
    }
    indexMap.remove(removed)
  }

  /**
    * Inserts the specified element into this priority queue.
    *
    * @return { @code true} (as specified by { @link Collection#add})
    * @throws ClassCastException   if the specified element cannot be
    *                              compared with elements currently in this priority queue
    *                              according to the priority queue's ordering
    * @throws NullPointerException if the specified element is null
    */
  def add(e: Node[S, T]): Boolean = offer(e)

  /**
    * Inserts the specified element into this priority queue.
    *
    * @return { @code true} if the specified node is added
    * @throws ClassCastException   if the specified element cannot be
    *                              compared with elements currently in this priority queue
    *                              according to the priority queue's ordering
    * @throws NullPointerException if the specified element is null
    */
  def offer(node: Node[S, T]): Boolean = {
    if (node == null) throw new NullPointerException
    val i: Int = size1
    if (i >= queue.length) grow(i + 1)
    size1 = i + 1
    queue(i) = node
    indexMap.put(node, i)
    if (i != 0) siftUp(i, node)
    true
  }

  def peek: Node[S, T] = {
    if (size1 == 0) return null
    queue(0).asInstanceOf[Node[S, T]]
  }

  def size: Int = size1

  def isEmpty: Boolean = size1 == 0

  /**
    * Removes all of the elements from this priority queue.
    * The queue will be empty after this call returns.
    */
  def clear() {
    var i: Int = 0
    while (i < size1) {
      {
        indexMap.remove(queue(i).asInstanceOf[Node[S, T]])
        queue(i) = null
      }
      {
        i += 1; i - 1
      }
    }
    size1 = 0
  }

  /**
    * Inserts item x at position k, maintaining heap invariant by
    * promoting x up the tree until it is greater than or equal to
    * its parent, or is the root.
    *
    * To simplify and speed up coercions and comparisons. the
    * Comparable and Comparator versions are separated into different
    * methods that are otherwise identical. (Similarly for siftDown.)
    *
    * @param k the position to fill
    * @param x the item to insert
    */
  private def siftUp(k: Int, x: Node[S, T]) {
    if (comparator != null) siftUpUsingComparator(k, x)
    else siftUpComparable(k, x)
  }

  private def siftUpComparable(index: Int, key: Node[S, T]) {
    var k = index
    var done = false
    while (k > 0 && !done) {
      val parent: Int = (k - 1) >>> 1
      val element: AnyRef = queue(parent)
      if (key.compareTo(element.asInstanceOf[Node[S, T]]) < 0)  {
        queue(k) = element
        indexMap.put(element.asInstanceOf[Node[S, T]], k)
        k = parent
      } else done = true
    }
    queue(k) = key
    indexMap.put(key, k)
  }

  private def siftUpUsingComparator(index: Int, x: Node[S, T]) {
    var k = index
    var done = false
    while (k > 0 && !done) {
      val parent: Int = (k - 1) >>> 1
      val e: AnyRef = queue(parent)
      if (comparator.compare(x, e.asInstanceOf[Node[S, T]]) < 0) {
        queue(k) = e
        indexMap.put(e.asInstanceOf[Node[S, T]], k)
        k = parent
      } else done = true

    }
    queue(k) = x
    indexMap.put(x, k)
  }

  /**
    * Inserts item x at position k, maintaining heap invariant by
    * demoting x down the tree repeatedly until it is less than or
    * equal to its children or is a leaf.
    *
    * @param k the position to fill
    * @param x the item to insert
    */
  private def siftDown(k: Int, x: Node[S, T]) {
    if (comparator != null) siftDownUsingComparator(k, x)
    else siftDownComparable(k, x)
  }

  private def siftDownComparable(index: Int, key: Node[S, T]) {
    var k = index
    var done = false
    val half: Int = size1 >>> 1 // loop while a non-leaf
    while (k < half && !done) {
      var child: Int = (k << 1) + 1 // assume left child is least
      var c: AnyRef = queue(child)
      val right: Int = child + 1
      if (right < size1 && c.asInstanceOf[Comparable[_ >: Node[S, T]]].compareTo(queue(right).asInstanceOf[Node[S, T]]) > 0)
        child = right
        c = queue(child)
      if (key.compareTo(c.asInstanceOf[Node[S, T]]) > 0) {
        queue(k) = c
        indexMap.put(c.asInstanceOf[Node[S, T]], k)
        k = child
      } else done = true
    }
    queue(k) = key
    indexMap.put(key, k)
  }

  private def siftDownUsingComparator(index: Int, x: Node[S, T]) {
    var k = index
    var done = false
    val half: Int = size1 >>> 1
    while (k < half && !done) {
      var child: Int = (k << 1) + 1
      var c: AnyRef = queue(child)
      val right: Int = child + 1
      if (right < size1 && comparator.compare(c.asInstanceOf[Node[S, T]], queue(right).asInstanceOf[Node[S, T]]) > 0)
        child = right
        c = queue(child)
      if (comparator.compare(x, c.asInstanceOf[Node[S, T]]) > 0) {
        queue(k) = c
        indexMap.put(c.asInstanceOf[Node[S, T]], k)
        k = child
      } else done = true
    }
    queue(k) = x
    indexMap.put(x, k)
  }
}
