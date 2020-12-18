class Day8 : AdventOfCodePuzzle() {

    override fun puzzleOne(input: List<String>): String {

        var currentLine = 0
        var acc = 0
        val usedLines = mutableSetOf<Int>()
        while(currentLine < input.size){

            if(currentLine in usedLines){
                return "$acc"
            }

            val (operation, param) = input[currentLine].split(" ")

            usedLines += currentLine

            if(operation == "acc"){
                acc += param.toInt()
                currentLine++
            }else if(operation == "jmp"){
                currentLine += param.toInt()
            }else{
                currentLine++
            }
        }
        return "-1"

    }

    override fun puzzleTwo(input: List<String>): String {


        for (line in input.indices) {

            if (input[line].startsWith("acc")) continue

            val mutatedLines = input.toMutableList()
            if (mutatedLines[line].startsWith("nop")) {
                mutatedLines[line] = mutatedLines[line].replace("nop", "jmp")
            } else {
                mutatedLines[line] = mutatedLines[line].replace("jmp", "nop")
            }
            val (acc, result) = runCode(mutatedLines)
            if (result) {
                return "$acc"
                break
            }
        }
        return "-"
    }

    fun runCode(input: List<String>) : Pair<Int, Boolean>{

        var acc = 0
        val usedLines = mutableSetOf<Int>()
        var currentLine = 0

        while(currentLine !in usedLines){

            if(currentLine > input.lastIndex) return acc to true

            val (operation, param) = input[currentLine].split(" ")

            usedLines += currentLine

            if(operation == "acc"){
                acc += param.toInt()
                currentLine++
            }else if(operation == "jmp"){
                currentLine += param.toInt()
            }else{
                currentLine++
            }
        }
        return acc to false

    }

}