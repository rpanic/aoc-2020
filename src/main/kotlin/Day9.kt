class Day9 : AdventOfCodePuzzle(){

    override fun puzzleOne(input: List<String>): String {

        val list = input.map { it.toLong() }
        for(i in 26 until list.size){

            val element = list[i]
            val valid = list.subList(i-25, i).run {
                var hasOne = false
                for(e in this){
                    if(!hasOne){
                        for(e2 in this){
                            if(e != e2){
                                if(e + e2 == element){
                                    hasOne = true
                                }
                            }
                        }
                    }
                }
                hasOne
            }
            if(!valid)
                return "$element"

        }
        return "-1"

    }

    override fun puzzleTwo(input: List<String>): String {

        val suspect = puzzleOne(input).toLong()

        val list = input.map { it.toLong() }
        var collector: MutableList<Long> = mutableListOf()
        for(i in list.indices){
            collector = mutableListOf(list[i])
            var found = false

            for(e in i+1 until list.size){
                if(collector.sum() == suspect){
                    found = true
                    break
                }
                collector.add(list[e])
            }
            if(found){
                break
            }
        }
        return "${collector.max()!! + collector.min()!!}"

    }

}