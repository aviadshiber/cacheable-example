package com.aviadshiber.ds.immutable

import com.aviadshiber.ds.DLList


case object DLEmpty extends DLList[Nothing]{
  override def value: Nothing = throw new NoSuchElementException("value of empty list")

  override def prev: DLList[Nothing] = throw new NoSuchElementException("prev of empty list")

  override def next: DLList[Nothing] = throw new NoSuchElementException("next of empty list")

  override def prepend[S >: Nothing](element: S): DLList[S] = new DLCons(element, DLEmpty, DLEmpty)

  override def append[S >: Nothing](element: S): DLList[S] = new DLCons(element, DLEmpty, DLEmpty)

  override def updatePrev[S >: Nothing](newPrev: => DLList[S]): DLList[S] = this

  override def updateNext[S >: Nothing](newNext: => DLList[S]): DLList[S] = this
}



