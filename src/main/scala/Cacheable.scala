import scala.collection.mutable

trait Cacheable[K, V >: Null <: AnyRef] {
  def get(key: K): V
  def size(): Int
}

/**
 * an implementation of a generic cache, not thread safe.
 *
 * @param fetcher  FETCH the data which we would like to cache given a key
 * @param capacity the max capacity we limit the cache with
 * @tparam K key
 * @tparam V value
 */
class LRUCache[K, V >: Null <: AnyRef](fetcher: K => V, capacity: Int) extends Cacheable[K, V] {
  private case class Node(var prev: Node = null, data: V, var next: Node = null)

  private var currentSize = 0
  private val map: mutable.Map[K, Node] = mutable.Map()

  private val head: Node = Node(null, null, null)
  private val tail: Node = Node(head, null, null)
  head.next = tail

  override def get(key: K): V = {
    val n = map.get(key) match {
      case Some(node) =>
        if (currentSize == capacity) {
          map.remove(key)
          removeLast()
        }
        moveToFront(node)
      case None =>
        val newNode = Node(data = fetcher(key))
        addFirst(newNode)
        map.put(key, newNode)
        newNode
    }
    n.data
  }

  override def size(): Int = currentSize

  private def addFirst(n: Node): Node = {
    n.next = head.next
    n.prev = head
    val next = head.next
    head.next = n
    next.prev = n
    currentSize = currentSize + 1
    n
  }

  private def removeLast(): Unit = {
    val prev = tail.prev
    if (prev != head) { // we do not remove head, which is a dummy node
      //prev.prev exist since we know we are not head
      tail.prev = prev.prev
      prev.prev.next = tail
      currentSize = currentSize - 1
    }
  }

  private def moveToFront(n: Node): Node = {
    val next = n.next
    val prev = n.prev
    prev.next = next
    next.prev = prev
    addFirst(n)
  }


}