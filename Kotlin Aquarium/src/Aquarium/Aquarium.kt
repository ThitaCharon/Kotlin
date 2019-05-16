package Aquarium

import java.lang.Math.PI

/* Created the constructor by parameter and by default */
open class Aquarium (var width : Int = 20,
                var height: Int = 40,
                var length: Int = 100){

    /* Syntax for get and set method -> stored in a variable */
    open var volume : Int
        get() = width*height*length/1000
        set(value) {height= (value*1000)/(width*length)}

    open var water = volume * 0.9

    constructor(numofFish : Int) : this(){
        val water : Int = numofFish * 2000 //cm3
        val tank : Double = water + water * 0.9
        height = (tank / (length * width)).toInt()
    }
}

class TowerTank() : Aquarium(){
    override var water = volume * 0.8
    override var volume: Int
        get()= (width * height * length/1000 *PI).toInt()
        set(value) {
            height = ( value * 1000) / (width*length)
        }

}