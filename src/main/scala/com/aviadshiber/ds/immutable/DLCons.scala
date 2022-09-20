package com.aviadshiber.ds.immutable

import com.aviadshiber.ds.DLList

/**
 * immutable double linked list.
 * not very practical since every operation is O(n) - since we need to recursively build up the entire chain
 * when creating new node, we need to update prev by creating new node.. and etc...
 */
class DLCons[+T](override val value: T, p: => DLList[T], n: => DLList[T]) extends DLList[T] {
  override lazy val prev: DLList[T] = p
  override lazy val next: DLList[T] = n

  override def updatePrev[S >: T](newPrev: => DLList[S]): DLCons[S] = {
    lazy val result: DLCons[S] = new DLCons(value, newPrev, n.updatePrev(result))
    result
  }

  override def updateNext[S >: T](newTail: => DLList[S]): DLList[S] = {
    lazy val result: DLCons[S] = new DLCons(value, p.updateNext(result), newTail)
    result
  }

  def append[S >: T](element: S): DLList[S] = {
    lazy val result: DLList[S] = new DLCons(value, p.updateNext(result), n.append(element).updatePrev(result))
    result
  }

  def prepend[S >:T](element: S): DLList[S] = {
    lazy val result: DLList[S] = new DLCons(value, p.prepend(element).updateNext(result), n.updatePrev(result))
    result
  }
}
