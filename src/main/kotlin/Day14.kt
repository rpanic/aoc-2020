class Day14 : AdventOfCodePuzzle(){

    @ExperimentalUnsignedTypes
    override fun puzzleOne(input: List<String>): String {

        var mask = ""
        var map = mutableMapOf<ULong, ULong>()

        input.forEach {line ->

            if(line.startsWith("mask")){
                mask = line.split(" = ")[1].trim()
            }else{
                val split = line.split(" = ")
                var value = split[1].trim().toULong()
                val address = split[0].removePrefix("mem[").removeSuffix("]").toULong()

                mask.toCharArray().withIndex().filter { it.value != 'X' }.forEach {
                    val index = (mask.length - it.index - 1)
                    val value2 = (1).toULong().shl(index)
                    if(it.value == '0'){
                        value = value.and(ULong.MAX_VALUE.xor(value2))
                    }else if(it.value == '1'){
                        value = value.or(value2)
                    }
                }
                map[address] = value
            }
        }

        return "${map.values.sum()}"

    }

    @ExperimentalUnsignedTypes
    override fun puzzleTwo(input: List<String>): String {

        var mask = ""
        var map = mutableMapOf<ULong, ULong>()

        input.forEach {line ->

            if(line.startsWith("mask")){
                mask = line.split(" = ")[1].trim()
            }else{
                val split = line.split(" = ")
                var value = split[1].trim().toULong()
                var address = split[0].removePrefix("mem[").removeSuffix("]").toULong()

                val wildcards = mutableListOf<ULong>()

                mask.toCharArray().withIndex().filter { it.value != '0' }.forEach {

                    val index = (mask.length - it.index - 1)
                    val value2 = (1).toULong().shl(index)

                    if(it.value == 'X'){
                        wildcards += value2
                        address = address.and(ULong.MAX_VALUE.xor(value2))
                    }else if(it.value == '1'){
                        address = address.or(value2)
                    }
                }

                val values = mutableListOf<ULong>()
                for(i in wildcards.indices){
                    val range = values.indices
                    values += wildcards[i]
                    for(j in range){
                        values += values[j] + wildcards[i]
                    }
                }

                map[address] = value
                values.forEach {
                    map[address + it] = value
                }
            }

        }

        return "${map.values.sum()}"

    }
}