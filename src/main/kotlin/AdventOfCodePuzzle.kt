abstract class AdventOfCodePuzzle{

    abstract fun puzzleOne(input: List<String>) : String

    abstract fun puzzleTwo(input: List<String>) : String

}

fun List<String>.readGroups(): List<List<String>> {
    val groups = mutableListOf<MutableList<String>>(mutableListOf())
    this.forEach {
        if (it.isEmpty()) {
            groups.add(mutableListOf())
        } else {
            groups.last().add(it)
        }
    }
    return groups
}