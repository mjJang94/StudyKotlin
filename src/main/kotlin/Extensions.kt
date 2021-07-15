import ListDataStructure.FunList


fun <T> FunList<T>.addHead(head: T): FunList<T> =
    FunList.Cons(head, this)

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = FunList.Nil): FunList<T> =
    when (this) {
        FunList.Nil -> acc
        is FunList.Cons -> tail.reverse(acc.addHead(head))
    }

fun <T> funListOf(vararg elements: T): FunList<T> = elements.toFunList()

fun <T> Array<out T>.toFunList(): FunList<T> = when {
    this.isEmpty() -> FunList.Nil
    else -> FunList.Cons(this[0], this.copyOfRange(1, this.size).toFunList())
}

//리스트의 마지막 값이 value 가 들어가는 확장함수.
//하지만 이렇게 작성 될 경우 Nil 을 만날때까지 재귀 호출되어 기존 값들은 유지되고,
//마지막 값으로 value 만 추가되고 종료된다.
//이 경우 appenTail 함수는 꼬리 재귀가 아니므로 스택에 영향을 끼친다.
fun <T> FunList<T>.appendTail(value: T): FunList<T> = when (this) {
    FunList.Nil -> FunList.Cons(value, FunList.Nil)
    is FunList.Cons -> FunList.Cons(head, tail.appendTail(value))
}

//꼬리 재귀를 위해 tailRec 키워드를 사용하여 스택에 안전하도록 만든다.
//addHead 를 사용해서 리스트를 거꾸로 만들고, 마지막에 reverse 사용해서 뒤집는 방법은
//스택에도 안전하면서 성능상 손해가 없다.
tailrec fun <T> FunList<T>.appendTail(value: T, acc: FunList<T> = FunList.Nil): FunList<T> = when (this) {
    FunList.Nil -> FunList.Cons(value, acc).reverse()
    is FunList.Cons -> tail.appendTail(value, acc.addHead(head))
}

fun <T> FunList<T>.getTail(): FunList<T> = when (this) {
    FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> tail
}

fun <T> FunList<T>.getHead(): T = when (this) {

    FunList.Nil -> throw NoSuchElementException()
    is FunList.Cons -> head
}


tailrec fun <T> FunList<T>.filter(
    acc: FunList<T> = FunList.Nil,
    p: (T) -> Boolean
): FunList<T> = when (this) {
    FunList.Nil -> acc.reverse()
    is FunList.Cons -> if (p(head)) {
        tail.filter(acc.addHead(head), p)
    } else {
        tail.filter(acc, p)
    }
}

//FunList 의 map 확장 함수로 변환
//리스트에 포함되는 값의 타입이 변경 될 수 있다.
//입력 리스트의 타입은 FunList<T>, 반환하는 리스트의 타입은 FunList<R> 로 일반화.
tailrec fun <T, R> FunList<T>.map(
    acc: FunList<R> = FunList.Nil,
    f: (T) -> R
): FunList<R> = when (this) {
    FunList.Nil -> acc.reverse()
    is FunList.Cons -> tail.map(acc.addHead(f(head)), f)
}