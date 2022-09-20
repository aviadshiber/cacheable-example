package com.aviadshiber.ds.dll.immutable

case object DLEmpty extends DLListReader[Nothing] {
  override def value: Nothing = throw new NoSuchElementException("value of empty list")

  override def prev: DLListReader[Nothing] = throw new NoSuchElementException("prev of empty list")

  override def next: DLListReader[Nothing] = throw new NoSuchElementException("next of empty list")

  override def prepend[S >: Nothing](element: S): DLListReader[S] = new DLConsReader(element, DLEmpty, DLEmpty)

  override def append[S >: Nothing](element: S): DLListReader[S] = new DLConsReader(element, DLEmpty, DLEmpty)

  override def updatePrev[S >: Nothing](newPrev: => DLListReader[S]): DLListReader[S] = this

  override def updateNext[S >: Nothing](newNext: => DLListReader[S]): DLListReader[S] = this
}
