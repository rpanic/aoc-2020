import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class Day12 : AdventOfCodePuzzle() {

    override fun puzzleOne(input: List<String>): String {

        var curX = 0L
        var curY = 0L
        var direction = 0L

        input.forEach { instruction ->
            val (action, value) = instruction.splitAt(1).run { this.first to this.second.toLong() }

            print("$instruction: ")
            when(action){
                "N" -> {
                    curY += value
                }
                "E" -> curX += value
                "W" -> curX -= value
                "S" -> curY -= value
                "L" -> direction -= value
                "R" -> direction += value
                "F" -> {
                    var q = direction / 90
                    while(q < 0){
                        q += 4
                    }
                    val alpha = direction % 90
                    val a = sin(alpha.toDouble()) * value
                    val b = cos(alpha.toDouble()) * value

                    val mappings: Map<Int, (Pair<Double, Double>) -> Pair<Double, Double>> = mapOf(
                        0 to { (a, b) -> b to -a },
                        1 to { (a, b) -> -a to -b },
                        2 to { (a, b) -> -b to a },
                        3 to { (a, b) -> a to b }
                    )

                    val function = mappings[q.toInt() % 4]!!
                    val (x, y) = function.invoke(a to b)
                    curX += x.toInt()
                    curY += y.toInt()
                }
            }
            println("$curX $curY $direction")
        }

        return "${abs(curX) + abs(curY)}"

    }

    override fun puzzleTwo(input: List<String>): String {

        var curX = 0L
        var curY = 0L
        var direction = 0L

        var wayX = 10L
        var wayY = 1L

        input.forEach { instruction ->
            val (action, value) = instruction.splitAt(1).run { this.first to this.second.toLong() }

            print("$instruction: ")
            when(action){
                "N" -> wayY += value
                "E" -> wayX += value
                "W" -> wayX -= value
                "S" -> wayY -= value
                "L" -> {
                    for(i in 0 until (value / 90) % 4) {
                        val waypoint = wayX to wayY
                        wayX = -waypoint.second
                        wayY = waypoint.first
                    }
                }
                "R" -> {
                    for(i in 0 until (value / 90) % 4) {
                        val waypoint = wayX to wayY
                        wayX = waypoint.second
                        wayY = -waypoint.first
                    }
                }
                "F" -> {
                    curX += wayX * value
                    curY += wayY * value
                }
            }
            println("$curX $curY $direction")
            println("$wayX $wayY")
        }

        return "${abs(curX) + abs(curY)}"

    }

}