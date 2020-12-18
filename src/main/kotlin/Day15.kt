class Day15 : AdventOfCodePuzzle() {

    override fun puzzleOne(input: List<String>): String {

        val numbers = "16,12,1,0,15,7,11".split(",").map{ it.toInt() }.withIndex().map { it.value to it.index }.toMap().toMutableMap()

        var lastNumber = 0
        var counter = 8

        while(counter < 30000000){

            if(counter < 100){
                print("$lastNumber,")
            }

            val occ = numbers[lastNumber]
            var result =
            if(occ != null){
                counter - occ - 1
            }else{
                0
            }
            numbers[lastNumber] = counter - 1
            lastNumber = result
            counter++

        }


        return "${lastNumber}"

    }

    override fun puzzleTwo(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}