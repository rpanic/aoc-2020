import java.lang.IllegalStateException

class Day18 : AdventOfCodePuzzle() {

    override fun puzzleOne(input: List<String>): String {

        return input.map { _line ->

            val ops = _line.replace("(", "( ").replace(")", " )").split(" ")

            val res = doOperation(ops)
            println("$_line = $res")
            res

        }.sum().toString()

    }

    fun doOperation(ops: List<String>) : Long{

        var currentOp = 0
        var operator = ""
        var first = -1L
        var second = -1L

        while(currentOp < ops.size){

            if(ops[currentOp] == "("){

                val x = doOperation(ops.subList(currentOp + 1, ops.size))
                println("${ops.subList(currentOp, ops.withIndex().drop(currentOp).first { it.value == ")" }.index + 1).joinToString(" ")} = $x")
                if(first == -1L){
                    first = x
                }else{
                    second = x
                }

                val closingBracket = ops.withIndex().drop(currentOp).fold(0 to -1) { acc, indexedValue ->
                    if(indexedValue.value == "("){
                        acc.first + 1 to acc.second
                    }else if(indexedValue.value == ")"){
                        if(acc.first == 1 && acc.second == -1){
                            acc.first - 1 to indexedValue.index
                        }else{
                            acc.first - 1 to acc.second
                        }
                    }else{
                        acc
                    }
                }
                currentOp = closingBracket.second

            }else if(ops[currentOp] in listOf("+", "*")){

                operator = ops[currentOp]

            }else if(ops[currentOp] == ")"){

                break

            }else{

                if(first == -1L)
                    first = ops[currentOp].toLong()
                else
                    second = ops[currentOp].toLong()

            }

            if(second != -1L){
                if(operator == "+"){
                    first += second
                }else if(operator == "*"){
                    first *= second
                }
                second = -1
                operator = ""
            }

            currentOp++

        }
        return first

    }

    override fun puzzleTwo(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}