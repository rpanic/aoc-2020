import kotlin.math.min

class Day10 : AdventOfCodePuzzle(){

    override fun puzzleOne(input: List<String>): String {

        var list = input.map { it.toLong() }

        val builtIn = list.max()!! + 3

        list = list.plus(listOf(0L, builtIn))


        val groups = list.sorted().zipWithNext().map { it.second - it.first }.groupBy { it }.map { it.key to it.value.size }.toMap()

        return "${groups[1]!! * groups[3]!!}"

    }

    override fun puzzleTwo(input: List<String>): String {

        var list = input.map { it.toLong() }
        val builtIn = list.max()!! + 3
        list = list.plus(listOf(0L, builtIn)).sorted()
//
//        val possibilities = mutableListOf<Long>()
//
//        list.withIndex().drop(1).forEach {
//            val lookahead = min(3, list.size - it.index)
//            val sublist = list.subList(it.index + 1, it.index + lookahead)
//            val num = sublist.count { x -> x - list[it.index - 1] <= 3 } + 1
//            possibilities += num.toLong()
//        }
//
//        val res = possibilities.reduce { acc, l -> acc * l }


        val memo = mutableMapOf<Int, Long>(0 to 1)

        list.withIndex().drop(1).forEach {
            val possibleIndices = mutableListOf<Int>()
            if (it.index >= 1 && it.value - list[it.index - 1] <= 3) possibleIndices.add(it.index - 1)
            if (it.index >= 2 && it.value - list[it.index - 2] <= 3) possibleIndices.add(it.index - 2)
            if (it.index >= 3 && it.value - list[it.index - 3] <= 3) possibleIndices.add(it.index - 3)
            memo[it.index] = possibleIndices.map { memo[it]!! }.sum()
        }
        return memo[list.lastIndex]!!.toString()

//        return "$res"

    }

}

/*
16
10
15
5
1
11
7
19
6
12
4
 */