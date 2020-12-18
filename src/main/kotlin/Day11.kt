class Day11 : AdventOfCodePuzzle(){

    enum class GridState{
        FLOOR, EMPTY, TAKEN
    }

    override fun puzzleOne(input: List<String>): String {

        var grid = input.map { it.toCharArray() }

        var changes = 1
        while(changes > 0){
            val (changes2, grid2) = change(grid)
            grid = grid2
            changes = changes2
        }

        return ""+ grid.sumBy { it.count { x -> x == '#' } }

    }

    fun change(grid1: List<CharArray>) : Pair<Int, List<CharArray>>{

        var changes = 0
        var grid = grid1.map { it.copyOf() }
        grid.forEachIndexed { index, row ->
            row.forEachIndexed one@{ index2, e ->

                if(e != '.') {

                    var seats = listOf(
                        index - 1 to index2 - 1, index - 1 to index2, index - 1 to index2 + 1,
                        index to index2 - 1, index to index2 + 1,
                        index + 1 to index2 - 1, index + 1 to index2, index + 1 to index2 + 1
                    )

                    seats =
                        seats.filter { it.first >= 0 && it.first < grid.size && it.second >= 0 && it.second < row.size }
                    var numSeatsTaken = seats.count { grid1[it.first][it.second] == '#' }
                    if (numSeatsTaken >= 4 && e == '#') {
                        grid[index][index2] = 'L'
                        changes++
                    }
                    if (numSeatsTaken == 0 && e == 'L') {
                        grid[index][index2] = '#'
                        changes++
                    }
                }

            }
        }
        return changes to grid
    }

    override fun puzzleTwo(input: List<String>): String {

        var grid = input.map { it.toCharArray() }

        var changes = 1
        while(changes > 0){
            val (changes2, grid2) = change2(grid)
            grid = grid2
            changes = changes2
            println("------")
            println(grid.joinToString("\n") { it.joinToString(" ") })
        }

        return ""+ grid.sumBy { it.count { x -> x == '#' } }

    }

    fun Pair<Int, Int>.seatTaken(x: Int, y: Int, grid: List<CharArray>) : Boolean{
        var x2 = this.first
        var y2 = this.second
        var currentChar = '.'

        while (currentChar !in listOf('#', 'L')){

            x2 += x
            y2 += y
            if(x2 < 0 || x2 >= grid.size || y2 < 0 || y2 >= grid[x2].size){
                return false
            }
            currentChar = grid[x2][y2]

        }
        return currentChar == '#'

    }

    fun change2(grid1: List<CharArray>) : Pair<Int, List<CharArray>>{

        var changes = 0
        var grid = grid1.map { it.copyOf() }
        grid.forEachIndexed { index, row ->
            row.forEachIndexed one@{ index2, e ->

                if(e != '.') {

                    var seats = listOf(
                        -1 to -1, -1 to 0, -1 to 1,
                        0 to -1, 0 to 1,
                        1 to -1, 1 to 0, 1 to 1
                    )

                    val currentPosition = index to index2
                    var numSeatsTaken = seats.count { currentPosition.seatTaken(it.first, it.second, grid1) }

                    if (numSeatsTaken >= 5 && e == '#') {
                        grid[index][index2] = 'L'
                        changes++
                    }
                    if (numSeatsTaken == 0 && e == 'L') {
                        grid[index][index2] = '#'
                        changes++
                    }
                }

            }
        }
        return changes to grid
    }

}