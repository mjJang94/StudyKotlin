import ListDataStructure.FunList

class FoldFunction {

    /**
     * list 왼쪽의 head에서 부터 마지막까지 하나씩 더하고 마지막에 0을 더해서 최종 합을 구하는 메소드
     */
    private fun sum(list: FunList<Int>): Int = when (list) {
        FunList.Nil -> 0
        is FunList.Cons -> list.head + sum(list.tail)
    }

    /**
     * foldLeft 함수 만들기
     *
     * 위에서 정의한 sum 함수와 같이 컬렉션의 값들을 왼쪽에서부터 오른쪽으로 줄여나가는 함수를 foldLeft 라 한다.
     *
     * foldLeft 함수는 리스트의 값들을 줄여 나가는 방법을 기술한 함수 f 를 입력받고, 하나의 값으로 줄어든 결괏값 R을 반환한다.
     */
    tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R, T) -> R) : R = when(this){
        FunList.Nil -> acc
        is FunList.Cons -> tail.foldLeft(f(acc, head), f)
    }

    private fun sumByFoldLeft(list: FunList<Int>): Int = list.foldLeft(0){acc, x -> acc + x}

    /**
     * foldLeft 함수로 toUpper 함수 만들기
     */
    fun toUpper(list: FunList<Char>): FunList<Char> = list.foldLeft(FunList.Nil){
        acc: FunList<Char>, char: Char -> acc.appendTail(char.toUpperCase())
    }

    /**
     * foldLeft 함수로 작성한 map 함수
     */
    fun<T, R> FunList<T>.mapByFoldLeft(f: (T) -> R): FunList<R> = this.foldLeft(FunList.Nil){
        acc: FunList<R>, x -> acc.appendTail(f(x))
    }

    fun onMain(){
        val intList = funListOf(1, 2, 3)

        println(sum(intList))
        println(sumByFoldLeft(intList))
    }
}
