package Aquarium

import Spices.Spice


fun main(args: Array<String>){
    buildAquarium()
}

fun buildAquarium(){
    val myAquarium = Aquarium()
    println("width : ${myAquarium.width} height : ${myAquarium.height} length : ${myAquarium.length}" )
    println("Volume : ${myAquarium.volume} litter")
    myAquarium.height = 2
    println("height : ${myAquarium.height}")
    println("Volume : ${myAquarium.volume} litter")

    val mySpicy = SimpleSpice()
    println("Menu : ${mySpicy.menu} , Level of Heat : ${mySpicy.heat}")

    val smallAquarium = Aquarium(width = 20,height = 15,length = 30)
    println("SmallAquarium\n width : ${smallAquarium.width} height : ${smallAquarium.height} length : ${smallAquarium.length}" )

    val myAquar2 = Aquarium(9)
    println("myAquar2 -> ${myAquar2.width}" )



}