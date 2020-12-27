import java.util.*
import kotlin.math.max
import kotlin.math.min

class Day22 : AdventOfCodePuzzle() {

    override fun puzzleOne(input: List<String>): String {

        var secondStart = (input.size - 1) / 2
        val deck1: Queue<Int> = LinkedList<Int>()
        val deck2: Queue<Int> = LinkedList<Int>()

        deck1.addAll(input.subList(1, secondStart).map { it.toInt() })
        deck2.addAll(input.subList(secondStart + 2, input.size).map { it.toInt() })

//        repeat(20){
        while(deck1.isNotEmpty() && deck2.isNotEmpty()){
            val pair = deck1.poll() to deck2.poll()

            (if(pair.first > pair.second) deck1 else deck2).apply {
                offer(max(pair.first, pair.second))
                offer(min(pair.first, pair.second))
            }
        }
        return "" + (if(deck1.isNotEmpty()) deck1 else deck2)
            .toList()
            .reversed()
            .withIndex()
            .map { (it.index + 1) * it.value }
            .sum()
    }

    override fun puzzleTwo(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}