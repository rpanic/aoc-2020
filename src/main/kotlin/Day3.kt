import java.io.File

class Day3 : AdventOfCodePuzzle(){
     fun puzzleTwo2(input: List<String>): String {

        val lines = File("src/main/resources/day3.1.txt").readLines().map { it.toCharArray() }

        var xOffsets = arrayOf(1,3,5,7,1)
        var yOffsets = arrayOf(1,1,1,1,2)
        val treeCounters = mutableListOf<Int>()
        for (i in xOffsets.indices)
        {
            var treeCounter = 0
            var x = 0

            for(line in 0..lines.size -(1+yOffsets[i]) step yOffsets[i]) {
                x+=xOffsets[i]
                if(x >= lines[0].size)
                {
                    x -= lines[0].size
                }
                if(lines[line+yOffsets[i]][x] == '#') treeCounter++
            }
            treeCounters.add(treeCounter)
        }

        return treeCounters.reduce { acc, i -> acc * i }.toString()

    }

    override fun puzzleOne(input: List<String>): String {

        val matrix = input.map { it.toCharArray().toList() }
        var trees = 0;

        for(i in 0 until (input.size)){

            val char = matrix[i][(i * 3) % matrix[i].size]
            if(char == '#') {
                trees++
            }
        }
        return "$trees"
    }

    override fun puzzleTwo(input: List<String>): String {

        val matrix = input.map { it.toCharArray().toList() }

        val rightDown = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)

        val treesum = mutableListOf<Int>()

        for(turn in rightDown) {
            var trees = 0

            for (i in 0 until (input.size) step turn.second) {

                val char = matrix[i][((i / turn.second) * turn.first) % matrix[i].size]
                if (char == '#') {
                    trees++
                }

                println("$i ${((i / turn.second) * turn.first) % matrix[i].size}")

            }
            treesum += trees
        }

        println(treesum)

        var sum = 1L;
        for(i in treesum){
            sum *= i;
        }

        return "$sum ${treesum.reduce { acc, i -> acc * i }}"
    }

}