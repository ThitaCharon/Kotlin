package Spices

fun main (args:Array<String>){
    println("Spices Package")
    var mydish = Curry("Panang", "spicy")
    println("My order is -> ${mydish.name} ${mydish.heat} ${mydish.spiciness} ${mydish.color}")
  /*  val spices = listOf(
            Spice("curry", "mild"),
            Spice("pepper", "medium"),
            Spice("cayenne", "spicy"),
            Spice("ginger", "mild"),
            Spice("red curry", "medium"),
            Spice("green curry", "mild"),
            Spice("hot pepper", "extremely spicy")
    )

    val salt = Spice("salt")
    println("Salt : ${salt.name}")*/
    val spiceCabinet = listOf(SpiceContainer(Curry("Masamun", "spicy",RedSpiceColor)),
            SpiceContainer(Curry("Panang","very spicy", GreenSpiceColor)))
    for(item in spiceCabinet){
        println(item.label + " -> " + item.spice.spiciness + " -> " + item.spice.color + " -> heat of " + item.spice.heat )
    }
}

sealed class Spice(val name : String = "N/A",
                val spiciness : String = "mild", color: SpiceColor)
                : SpiceColor by color {

    val heat : Int
        get(){
            return when(spiciness){
                "mild" -> 1
                "medium" -> 3
                "spicy" -> 5
                "very spicy" -> 7
                "extremely spicy" -> 10
                else -> 0
            }
        }
    //constructor(salt:String): this(name=salt){}

    abstract fun prepareSpice()
}

class Curry(name: String, spiciness: String,
            color : SpiceColor = RedSpiceColor) // inheritance Spice and interface Grinder
            : Spice (name,spiciness,color), Grinder{
    override fun grind(){}
    override fun prepareSpice() { grind() }
}

object RedSpiceColor : SpiceColor{
    override val color = Color.RED
}

object GreenSpiceColor : SpiceColor{
    override val color = Color.GREEN
}
interface Grinder{
    fun grind()
}

interface SpiceColor{
    val color : Color
}

data class SpiceContainer(var spice : Spice){
    val label = spice.name
}

enum class Color(val rgb: Int) {
    RED(0xFF0000), GREEN(0x00FF00), BLUE(0x0000FF);
}