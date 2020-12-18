import kotlin.math.abs

class Utils {
}

fun String.splitAt(index: Int) : Pair<String, String>{
    return this.substring(0, index) to this.substring(index, this.length)
}

fun Any.print(){
    println(this)
}

data class Vector3(val x: Int, val y: Int, val z: Int) {
    operator fun plus(vector3: Vector3) = Vector3(x + vector3.x, y + vector3.y, z + vector3.z)
    operator fun minus(vector3: Vector3) = Vector3(x - vector3.x, y - vector3.y, z - vector3.z)
    operator fun times(scale: Int) = Vector3(x * scale, y * scale, z * scale)
    operator fun div(scale: Int) = Vector3(x / scale, y / scale, z / scale)


    val neighboursL1 get() = listOf(this + LEFT, this + UP, this + RIGHT, this + DOWN, this + FRONT, this + BACK)

    val neighboursL2
        get() = (-1..1).flatMap { dx ->
            (-1..1).flatMap { dy ->
                (-1..1).map { dz ->
                    Vector3(dx, dy, dz)
                }
            }
        }.filter { it.manhattanScale != 0 }.map { this + it }

    val manhattanScale get() = abs(x) + abs(y) + abs(z)

    fun inBounds(topLeft: Vector3, bottomRight: Vector3): Boolean {
        return x in topLeft.x until bottomRight.x && y in topLeft.y until bottomRight.y && x in topLeft.z until bottomRight.z
    }

    fun inBounds(bottomRight: Vector3): Boolean {
        return inBounds(Vector3(0, 0, 0), bottomRight)
    }

    companion object {
        val LEFT = Vector3(-1, 0, 0)
        val RIGHT = Vector3(1, 0, 0)
        val FRONT = Vector3(0, 1, 0)
        val BACK = Vector3(0, -1, 0)
        val UP = Vector3(0, 0, 1)
        val DOWN = Vector3(0, 0, -1)
    }
}


data class Vector4(val x: Int, val y: Int, val z: Int, val w: Int) {
    operator fun plus(vector3: Vector4) = Vector4(x + vector3.x, y + vector3.y, z + vector3.z, w + vector3.w)

    val neighboursL2
        get() = (-1..1).flatMap { dx ->
            (-1..1).flatMap { dy ->
                (-1..1).flatMap { dz ->
                    (-1..1).map { dw ->
                        Vector4(dx, dy, dz, dw);
                    }
                }
            }
        }.filter { it.manhattanScale != 0 }.map { this + it }

    val manhattanScale get() = abs(x) + abs(y) + abs(z) + abs(w)
}