import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph
import java.util.*
import kotlin.math.max

class Day7 : AdventOfCodePuzzle() {

    override fun puzzleOne(input: List<String>): String {

        val rules = input.map { it.split(" contain ").map { it.split(", ") }.flatten().map { it.removeSuffix(".").removeSuffix("s").removeSuffix("bag").trim() } }

        val graph = SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)

        rules.forEach {

            val first = it.first()
            if(!graph.containsVertex(first)){
                graph.addVertex(first)
            }

            it.drop(1).forEach { n ->
                if(!n.startsWith("no")) {
                    val (count, color) = n.splitCounted()

                    if (!graph.containsVertex(color)) {
                        graph.addVertex(color)
                    }
                    val edge = graph.addEdge(first, color)
//                    println("$first -> $color")
                    graph.setEdgeWeight(edge, count.toDouble())
                }
            }
        }

        val found = mutableSetOf<String>()
        val nodesToProcess = mutableSetOf("shiny gold")

        while(nodesToProcess.isNotEmpty()){

            val node = nodesToProcess.first()

            graph.edgesOf(node).forEach {
                val source = graph.getEdgeSource(it)
                if(source != node) {
                    nodesToProcess.add(source)
                    found.add(source)
                }
            }

            nodesToProcess.remove(node)
        }

        return ""+ found.size
    }

    fun String.splitCounted() : Pair<Int, String>{
        val color = this.substring(this.indexOf(' ') + 1)
        val count = this.substring(0, this.indexOf(' ')).toInt()
        return count to color
    }

    override fun puzzleTwo(input: List<String>): String {

//        listOf(readDay(7, 1)).forEach {
//            val graph = mutableMapOf<String, MutableList<Pair<String, Int>>>()
//            it.map {
//                val (outerColor, interior) = it.split("bags contain")
//                val innerColors = interior.split(",").map {
//                    val words = it.split(" ")
//                    if (words[1] == "no") return@map "" to 0
//                    val count = words[1].toInt()
//                    val color = words.subList(2, words.size - 1).joinToString(" ")
//                    color to count
//                }
//                outerColor.trim() to innerColors
//            }.forEach { node ->
//                node.second.filter { it.second != 0 }.forEach {
//                    if (node.first !in graph) {
//                        graph[node.first] = mutableListOf()
//                    }
//                    graph[node.first]!!.add(it)
//                }
//            }
//
//            val stack = Stack<Pair<String, Int>>()
//            stack.add("shiny gold" to 1)
//            var count = -1
//
//            while (stack.isNotEmpty()) {
//                val (current, currentNumber) = stack.pop()
//                count += currentNumber
//                for (next in graph[current] ?: mutableListOf()) {
//                    stack.add(next.first to currentNumber * next.second)
//                }
//            }
//            println(count)
//        }
//        return ""

        val rules = input.map { it.split(" contain ").map { it.split(", ") }.flatten().map { it.removeSuffix(".").removeSuffix("s").removeSuffix("bag").trim() } }

        val graph = SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)

        rules.forEach {

            val first = it.first()
            if(!graph.containsVertex(first)){
                graph.addVertex(first)
            }

            it.drop(1).forEach { n ->
                if(!n.startsWith("no")) {
                    val (count, color) = n.splitCounted()

                    if (!graph.containsVertex(color)) {
                        graph.addVertex(color)
                    }
                    val edge = graph.addEdge(first, color)

                    graph.setEdgeWeight(edge, count.toDouble())
                }
            }
        }

        val nodesToProcess = mutableListOf("shiny gold")
        val quantities = mutableMapOf<String, Double>()

        var nodes = getCount("shiny gold", graph) - 1

//        while(nodesToProcess.isNotEmpty()){
//
//            val node = nodesToProcess.first()
//
//            graph.edgesOf(node).forEach {
//                val target = graph.getEdgeTarget(it)
//                val edgeValue = graph.getEdgeWeight(it)
//                if(target != node) {
//                    quantities[target] = edgeValue
//                    val q = quantities[graph.getEdgeTarget(it)]!!
//                    println(q)
//                    nodes += (edgeValue * q)
//                    nodesToProcess.add(target)
//                }
//            }
//
//            nodesToProcess.removeAt(0)
//        }

        return ""+ nodes
    }

    private fun getCount(s: String, graph: SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>): Double {

        if(graph.edgesOf(s).size == 0){
            return 1.toDouble()
        }

        return graph.edgesOf(s).map {
            val target = graph.getEdgeTarget(it)
            val edgeValue = graph.getEdgeWeight(it)
            if(target != s) {
                val count = getCount(target, graph)
                println("$target: $count")
                edgeValue * count
            }else{
                0.toDouble()
            }
        }.sum() + 1

    }
}