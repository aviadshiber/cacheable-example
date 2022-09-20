import scala.collection.mutable

trait Cacheable[K,V]{
  def get(key:K):V
}


class LRUCache[K,V](fetcher : K => V,capacity:Int) extends Cacheable[K,V]{
  case class Node(key:K,data:V)

  val dll :mutable.ArrayDeque[Node] = mutable.ArrayDeque() //TODO: replace impl with my own mutable ds
  val map : mutable.Map[K,Node] = mutable.Map()

  def moveToFront(node: Node): V = {
    ???
  }

  override def get(key: K): V = {
    map.get(key) match {
      case Some(node) =>
        moveToFront(node)
      case None =>
        val newNode = Node(key,fetcher(key))
        dll.insert(0,newNode)
        map.put(key,newNode)
    }
    dll.head.data
  }
}