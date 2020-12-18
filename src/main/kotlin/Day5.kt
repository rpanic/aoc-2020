import kotlin.math.pow

class Day5 : AdventOfCodePuzzle() {

    override fun puzzleOne(input: List<String>): String {

        val max = input.map { seat ->

            val chars = seat.toCharArray()

            val row = Integer.parseInt(seat.substring(0, 7).replace("F", "0").replace("B", "1"), 2)
            val seat = Integer.parseInt(seat.substring(7, 10).replace("L", "0").replace("R", "1"), 2)

            val id = row * 8 + seat
            id

        }.max()

        return "$max";

    }

    override fun puzzleTwo(input: List<String>): String {

        val max = input.map { seat ->

            val chars = seat.toCharArray()

            val row = Integer.parseInt(seat.substring(0, 7).replace("F", "0").replace("B", "1"), 2)
            val seat = Integer.parseInt(seat.substring(7, 10).replace("L", "0").replace("R", "1"), 2)

            val id = row * 8 + seat
            id

        }.sorted()
            .reduce { acc, i ->
                if (acc + 1 == i || acc == i)
                    i
                else
                    acc
            } + 1

        return "$max";
    }

}