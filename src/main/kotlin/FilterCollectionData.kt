class FilterCollectionData {

    //명령형 방식의 filter
    fun imperativeFilter(numList: List<Int>): List<Int> {
        val newList = mutableListOf<Int>()
        for (num in numList) {
            if (num % 2 == 0) {
                newList.add(num)
            }
        }
        return newList
    }

    //명령형 방식의 리스트 값 변경 map
    fun imperativeMap(numList: List<Int>): List<Int> {
        val newList = mutableListOf<Int>()
        for (num in numList) {
            newList.add(num)
        }

        return newList
    }

    //함수형 방식의 filter
    fun functionalFilter(numList: List<Int>): List<Int> = numList.filter { it % 2 == 0 }

    //함수형 방식의 리스트 값 변경 map
    fun functionalMap(numList: List<Int>): List<Int> {
        return numList.map { it + 2 }
    }


    //ListDataStructure 의 FunList<Int> 컬렉션에 3을 더하는 map 함수로 일반화하는 메소드
    fun add3(list: ListDataStructure.FunList<Int>): ListDataStructure.FunList<Int> = when (list) {
        ListDataStructure.FunList.Nil -> ListDataStructure.FunList.Nil
        is ListDataStructure.FunList.Cons -> ListDataStructure.FunList.Cons(list.head + 3, add3(list.tail))
    }

    //ListDataStructure 의 FunList<Double> 컬렉션에 3을 더하는 map 함수로 일반화하는 메소드
    fun product3(list: ListDataStructure.FunList<Double>): ListDataStructure.FunList<Double> = when (list) {
        ListDataStructure.FunList.Nil -> ListDataStructure.FunList.Nil
        is ListDataStructure.FunList.Cons -> ListDataStructure.FunList.Cons(list.head * 3, product3(list.tail))
    }

}