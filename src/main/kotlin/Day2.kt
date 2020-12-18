class Day2 : AdventOfCodePuzzle() {

    override fun puzzleOne(input: List<String>): String {

        var valid = 0
        input.forEach { line ->
            val (policy, pwd) = line.split(": ")
            val (min, max, letter) = policy.split("-").map { it.split(" ") }.flatten()
            val count = pwd.count { it == letter.toCharArray()[0] }
            if(count <= max.toInt() && count >= min.toInt()){
                valid++
            }
        }

        return valid.toString()

    }

    override fun puzzleTwo(input: List<String>): String {

        var valid = 0
        input.forEach { line ->
            val (policy, pwd) = line.split(": ")
            val (pos1, pos2, letter) = policy.split("-").map { it.split(" ") }.flatten()
            val letterc = letter.toCharArray()[0];
            if((pwd.toCharArray()[pos1.toInt() - 1] == letterc).xor(pwd.toCharArray()[pos2.toInt() - 1] == letterc)){
                valid++
            }
        }

        return valid.toString()

    }

}