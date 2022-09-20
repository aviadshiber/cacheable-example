package com.aviadshiber.ds.dll.immutable

/**
 * immutable double linked list.
 * not very practical since every operation is O(n) - since we need to recursively build up the entire chain
 * when creating new node, we need to update prev by creating new node.. and etc...
 */
class DLConsReader[+T](override val value: T, p: => DLListReader[T], n: => DLListReader[T]) extends DLListReader[T] {
  override lazy val prev: DLListReader[T] = p
  override lazy val next: DLListReader[T] = n

  override def updatePrev[S >: T](newPrev: => DLListReader[S]): DLConsReader[S] = {
    lazy val result: DLConsReader[S] = new DLConsReader(value, newPrev, n.updatePrev(result))
    result
  }

  override def updateNext[S >: T](newTail: => DLListReader[S]): DLListReader[S] = {
    lazy val result: DLConsReader[S] = new DLConsReader(value, p.updateNext(result), newTail)
    result
  }

  def append[S >: T](element: S): DLListReader[S] = {
    lazy val result: DLListReader[S] = new DLConsReader(value, p.updateNext(result), n.append(element).updatePrev(result))
    result
  }

  def prepend[S >: T](element: S): DLListReader[S] = {
    lazy val result: DLListReader[S] = new DLConsReader(value, p.prepend(element).updateNext(result), n.updatePrev(result))
    result
  }
}
