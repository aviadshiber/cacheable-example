package com.aviadshiber.ds

trait DLList[+T] {
  def value:T
  def prev:DLList[T]
  def next:DLList[T]
  def prepend[S >: T](element:S):DLList[S]
  def append[S >: T](element: S): DLList[S]

  def updatePrev[S >: T](newPrev: => DLList[S]): DLList[S]

  def updateNext[S >: T](newNext: => DLList[S]): DLList[S]
}
