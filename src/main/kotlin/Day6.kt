class Day6 : AdventOfCodePuzzle(){

    override fun puzzleOne(input: List<String>): String {

        val list = mutableListOf<MutableList<Char>>()
        list.add(mutableListOf())

        for(i in input.indices){

            val line = input[i]
            if(line == ""){
                list.add(mutableListOf())
            }else{
                list.last().addAll(line.toCharArray().toList())
            }

        }
        return "" + list.map { it.distinct().count() }.sum()

    }

    override fun puzzleTwo(input: List<String>): String {

        return "" + input.readGroups().map {
            it.map { it.toSet() }.reduceRight{ set, acc -> set.intersect(acc)}.size
        }.sum()

    }

}