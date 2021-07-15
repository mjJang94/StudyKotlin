class ListDataStructure {

    sealed class FunList<out T> {
        object Nil : FunList<Nothing>()
        data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()
    }

    private val list: FunList<Int> = FunList.Cons(1, FunList.Cons(2, FunList.Nil))


    fun result() {
        println("result: $list")
    }

    /**
     * addHead()
     */
    fun resultAddHead() {
        println("resultAddHead: ${list.addHead(0)}")
    }

    /**
     * appendTail()
     */
    fun resultAppendTail() {
        println("resultAppendTail: ${list.appendTail(value = 3, acc = FunList.Nil)}")
    }

    /**
     * getTail()
     */
    fun resultGetTail() {
        println("resultGetTail: ${list.getTail()}")
    }

    /**
     * getHead()
     */
    fun resultGetHead() {
        println("resultGetHead: ${list.getHead()}")
    }

    /**
     * map()
     */
    fun resultMap(){
        val intList = funListOf(1, 2, 3)
        val doubleList = funListOf(1.0, 2.0, 3.0)

        val filterCollectionData = FilterCollectionData()

        println(filterCollectionData.add3(intList))
        println(filterCollectionData.product3(doubleList))

        println("resultGetHead Plus: " + intList.map{ it + 3})
        println("resultGetHead Multiple: " + intList.map{ it * 3})
    }
}