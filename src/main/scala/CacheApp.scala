import com.aviadshiber.ds.immutable.DLEmpty

object CacheApp {


  def main(args: Array[String]): Unit = {

    // 1 <-> 2 <-> 3 <-> 4
    val list = DLEmpty.prepend(1).append(2).prepend(3).append(4)
    println(list.value) // 1
    println(list.next.value) // 2
    println(list.next.prev == list) // true
    println(list.prev.value) // 3
    println(list.prev.next == list) // true
    println(list.next.next.value) // 4
    println(list.next.next.prev.prev == list) // true
  }

}
