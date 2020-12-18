import java.io.File

fun readDay(day: Int, puzzle: Int) : List<String>{
    return File("src/main/resources/day$day.${if(puzzle > 2 ) "example" else puzzle}.txt").readLines()
}

//val puzzles = mutableListOf<AdventOfCodePuzzle>()

fun main(){

    val day = 17
    val puzzleNum = 2

    val input = readDay(day, 1)

    val puzzle: AdventOfCodePuzzle = Day17()
    val output: String

    if(puzzleNum == 1){
        output = puzzle.puzzleOne(input)
    }else{
        output = puzzle.puzzleTwo(input)
    }

    println("Output for Day $day #$puzzleNum:")
    println(output)

}

