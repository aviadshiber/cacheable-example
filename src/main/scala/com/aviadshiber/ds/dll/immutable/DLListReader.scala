package com.aviadshiber.ds.dll.immutable

trait DLListReader[+T] {
  def value: T

  def prev: DLListReader[T]

  def next: DLListReader[T]

  def prepend[S >: T](element: S): DLListReader[S]

  def append[S >: T](element: S): DLListReader[S]

  def updatePrev[S >: T](newPrev: => DLListReader[S]): DLListReader[S]

  def updateNext[S >: T](newNext: => DLListReader[S]): DLListReader[S]
}
