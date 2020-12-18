class Day18 : AdventOfCodePuzzle() {

    override fun puzzleOne(input: List<String>): String {

        return input.map { _line ->

            val ops = _line.replace("(", "( ").replace(")", " )").split(" ")

            val res = doOperation(ops)
            println("$_line = $res")
            res

        }.sum().toString()

    }

    fun doOperation(ops: List<String>) : Int{

        var currentOp = 0
        var operator = ""
        var first = -1
        var second = -1

        while(currentOp < ops.size){

            if(ops[currentOp] == "("){

                val x = doOperation(ops.subList(currentOp, ops.size))
                println("${ops.subList(currentOp, ops.size).joinToString(" ")} = $x")
                if(first == -1){
                    first = x
                }else{
                    second = x
                }
                currentOp = ops.drop(currentOp).withIndex().find { it.value == ")" }!!.index

            }else if(ops[currentOp] in listOf("+", "*")){

                operator = ops[currentOp]

            }else if(ops[currentOp] == ")"){

                break

            }else{

                if(first == -1)
                    first = ops[currentOp].toInt()
                else
                    second = ops[currentOp].toInt()

            }

            if(second != -1){
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