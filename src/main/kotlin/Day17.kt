class Day17 : AdventOfCodePuzzle(){

    override fun puzzleOne(input: List<String>): String {

        val init = input.map { it.split("").toList() }

        var activated = mutableListOf<Vector3>()

        init.forEachIndexed { index, list ->
            list.forEachIndexed { index2, s ->
                if(init[index][index2] == "#") {
                    activated.add(Vector3(index, index2, 0))
                }
            }
        }


        repeat(6){

            val counts = mutableMapOf<Vector3, Int>()
            activated.forEach {
                it.neighboursL2.forEach {
                    counts.merge(it, 1, Int::plus)
                }
            }

            val newActivated = counts.filter { it.value == 3 }.keys
            val deactivated = activated.filter { counts[it] != 2 }

            activated = (activated - deactivated + newActivated).distinct().toMutableList()


        }
        return "${activated.size}"

//        val maxDimensions = listOf(13, init.size + 12, init[0].size + 12)
//        var state = IntArray(12)

    }

    override fun puzzleTwo(input: List<String>): String {

        val init = input.map { it.split("").toList() }

        var activated = mutableListOf<Vector4>()

        init.forEachIndexed { index, list ->
            list.forEachIndexed { index2, s ->
                if(init[index][index2] == "#") {
                    activated.add(Vector4(index, index2, 0, 0))
                }
            }
        }


        repeat(6){

            val counts = mutableMapOf<Vector4, Int>()
            activated.forEach {
                it.neighboursL2.forEach {
                    counts.merge(it, 1, Int::plus)
                }
            }

            val newActivated = counts.filter { it.value == 3 }.keys
            val deactivated = activated.filter { counts[it] != 2 }

            activated = (activated - deactivated + newActivated).distinct().toMutableList()


        }
        return "${activated.size}"

    }

}