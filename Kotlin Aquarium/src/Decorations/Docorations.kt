package Decorations

fun main(args:Array<String>){
    makeDecorations()
}

fun makeDecorations(){
    val d1 = Decorations("granite")
    val d2 = Decorations("slate")
    val d3 = d2.copy()
    println(d1)
    println(d2)
    println(d3)
    println(d1.equals(d3))

    val d4 = Decorations2("crystal", "wood", "diver")
    val (rock:String,wood:String,diver:String) = d4
    println(rock)
    println(wood)
    println(diver)
}

data class Decorations (val rock:String){}

data class Decorations2 (val rock:String, val wood:String , val diver:String){}