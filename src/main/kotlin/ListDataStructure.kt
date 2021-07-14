class ListDataStructure {

    sealed class FunList<out T> {
        object Nil : FunList<Nothing>()
        data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>()
    }

    private val list: FunList<Int> = FunList.Cons(1, FunList.Cons(2, FunList.Nil))

    private fun <T> FunList<T>.addHead(head: T): FunList<T> = FunList.Cons(head, this)

    tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
        FunList.Nil -> acc
        is FunList.Cons -> tail.reverse(acc.addHead(head))
    }

    //리스트의 마지막 값이 value 가 들어가는 확장함수.
    //하지만 이렇게 작성 될 경우 Nil 을 만날때까지 재귀 호출되어 기존 값들은 유지되고,
    //마지막 값으로 value 만 추가되고 종료된다.
    //이 경우 appenTail 함수는 꼬리 재귀가 아니므로 스택에 영향을 끼친다.
    private fun <T> FunList<T>.appendTail(value: T): FunList<T> = when (this) {
        FunList.Nil -> FunList.Cons(value, FunList.Nil)
        is FunList.Cons -> FunList.Cons(head, tail.appendTail(value))
    }

    //꼬리 재귀를 위해 tailRec 키워드를 사용하여 스택에 안전하도록 만든다.
    //addHead 를 사용해서 리스트를 거꾸로 만들고, 마지막에 reverse 사용해서 뒤집는 방법은
    //스택에도 안전하면서 성능상 손해가 없다.
    private tailrec fun <T> FunList<T>.appendTail(value: T, acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
        FunList.Nil -> FunList.Cons(value, acc).reverse()
        is FunList.Cons -> tail.appendTail(value, acc.addHead(head))
    }

    private fun <T> FunList<T>.getTail(): FunList<T> = when (this) {
        FunList.Nil -> throw NoSuchElementException()
        is FunList.Cons -> tail
    }

    private fun <T> FunList<T>.getHead(): T = when (this) {

        FunList.Nil -> throw NoSuchElementException()
        is FunList.Cons -> head
    }

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
}