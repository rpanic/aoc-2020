import kotlin.math.abs

class Day13 : AdventOfCodePuzzle() {

    override fun puzzleOne(input: List<String>): String {

        val timestamp = input[0].toInt()
        val ids = input[1].split(",").filter { it != "x" }.map { it.toInt() }

        val next = ids.map {
            var res = (it) * (timestamp / it)
            if(res < timestamp)
                res += it
            if(res > timestamp + it){
                res -= it
            }
            println("$it: $res")
            it to res
        }
        val n = next.minBy { it.second }!!
        return "${abs(timestamp - n.second) * n.first}"

    }

    fun euclidExtended(a: Long, b: Long, _x: Long, _y: Long) : Long{

        var x = _x
        var y = _y

        if (a == 0L) {
            x = 0L
            y = 1L
            return b
        }

        val x1 = 1L
        val y1 = 1L // To store results of recursive call
        val gcd = euclidExtended(b % a, a, x1, y1)

        // Update x and y using results of recursive
        // call
        x = y1 - b / a * x1
        y = x1

        return gcd
    }

    /**
     * Finds the coefficients of ax+by=d for a (mod b) where d is the gcd(a,b)
     * @param a is the base of which we want the inverse
     * @param b is the modulo
     * @return the array of coefficients
     */
    fun findInverse(_a: Long, _b: Long): LongArray {
        var a = _a
        var b = _b
        var x = 0L
        var y = 1L
        var lastx = 1L
        var lasty = 0L
        while (b != 0L) {
            val quotient = a / b

            var temp = a
            a = b
            b = temp % b

            temp = x
            x = lastx - quotient * x
            lastx = temp

            temp = y
            y = lasty - quotient * y
            lasty = temp
        }

        return longArrayOf(lastx, lasty, a)
    }

    fun multInv(a: Long, b: Long): Long {
        if (b == 1L) return 1L
        var aa = a
        var bb = b
        var x0 = 0L
        var x1 = 1L
        while (aa > 1L) {
            val q = aa / bb
            var t = bb
            bb = aa % bb
            aa = t
            t = x0
            x0 = x1 - q * x0
            x1 = t
        }
        if (x1 < 0L) x1 += b
        return x1
    }

    fun chineseRemainder2(a: List<Long>, n: List<Long>): Long {
        val prod = n.fold(1L) { acc, i -> acc * i }
        var sum = 0L
        for (i in n.indices) {
            val p = prod / n[i]
            sum += a[i] * multInv(p, n[i]) * p
        }
        return sum % prod
    }
    fun chineseRemainder(a: List<Long>, m: List<Long>) : Long{

        val M = m.fold(1L) { acc, l -> acc * l }
        val ms = m.map { M / it }

        var sum = 0L

        for(i in ms.indices){

            print("${m[i]} ${ms[i]} ")
//            println("${findInverse(ms[i], a[i]).joinToString(",")}")
            println("${multInv(ms[i], m[i]) * ms[i]}")

//            sum += m[i] * findInverse(ms[i], a[i])
            sum += m[i] * multInv(ms[i], m[i]) * ms[i]

        }
        ms.print()

        return sum % M

    }

    override fun puzzleTwo(input: List<String>): String {

        multInv(12, 5).print()
        var moduls = input[1].split(",").withIndex().filter { it.value != "x" }.map { it.index to it.value.toInt() }
        // X = it.first mod it.second
        moduls = listOf(2 to 3, 3 to 4, 4 to 5)
        moduls.forEach { "x = ${it.first} mod ${it.second}".print() }

        val first = chineseRemainder(moduls.map { it.first.toLong() }, moduls.map { it.second.toLong() })

        return "$first"

    }

}