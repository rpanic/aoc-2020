import java.math.BigInteger

class Day25 : AdventOfCodePuzzle() {

    fun doLoop(value: BigInteger, subject: BigInteger) : BigInteger{
        return (value.multiply(subject)).mod(20201227.toBigInteger())
    }

    fun findLoopSize(pk: BigInteger) : Int{

        var i = 1
        var r = 7.toBigInteger()
        while(r != pk){
            r = doLoop(r, 7.toBigInteger())
            i++
        }
        return i

    }

    override fun puzzleOne(input: List<String>): String {

        val cardPk = 3248366.toBigInteger()
        val doorPk = 4738476L.toBigInteger()
//        val doorPk = 17807724.toBigInteger()
//        val cardPk = 5764801.toBigInteger()

        val cardLoop = findLoopSize(cardPk)
        val doorLoop = findLoopSize(doorPk)


        val encKey = (1..cardLoop).fold(1.toBigInteger()){ acc, v -> doLoop(acc, doorPk) }

        return "${ encKey }"

    }

    override fun puzzleTwo(input: List<String>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}