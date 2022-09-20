import com.aviadshiber.ds.dll.immutable.DLEmpty

object CacheApp {


  def main(args: Array[String]): Unit = {
    //example of immutable linked list:
    // 3 <-> 1 <-> 2 <-> 4
    val list = DLEmpty.prepend(1).append(2).prepend(3).append(4)
    println(list.value) // 1
    println(list.next.value) // 2
    println(list.next.prev == list) // true
    println(list.prev.value) // 3
    println(list.prev.next == list) // true
    println(list.next.next.value) // 4
    println(list.next.next.prev.prev == list) // true


    //example of caching fetcher function
    def fetcher(key:Int) : String = {
      Thread.sleep(1000) //simulate very slow network call
      key.toString //imagine this is fetched from the web
    }

    val fetcherCached: Cacheable[Int,String] = new LRUCache[Int,String](fetcher,10_000)
    println(fetcherCached.get(1))
    println(fetcherCached.get(2))
    println(fetcherCached.get(1)) //will be faster than 3 sec

  }

}
