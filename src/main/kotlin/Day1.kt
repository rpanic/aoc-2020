class Day1 : AdventOfCodePuzzle(){

    override fun puzzleOne(input: List<String>) : String {

        val intlist = input.map { it.toInt() }
        intlist.forEachIndexed { index, i ->
            intlist.drop(index).forEachIndexed { index2, j ->
                if(i + j == 2020){
                    return "${i * j}"
                }
            }
        }
        return "not found"

    }

    override fun puzzleTwo(input: List<String>) : String {

        val intlist = input.map { it.toInt() }
        intlist.forEachIndexed { index, i ->
            intlist.drop(index).forEachIndexed { index2, j ->
                intlist.drop(index).forEachIndexed { index3, k ->
                    if (i + j + k == 2020) {
                        return "${i * j * k}"
                    }
                }
            }
        }
        return "not found"

    }

}