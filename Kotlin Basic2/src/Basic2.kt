import java.lang.Math.random
import java.util.*

fun main(args:Array<String>){
    println("Sad day" + whatShouldIDoToday(mood = "sad"))
    println("Happy day" + whatShouldIDoToday(mood = "good"))
    println("In Bed day" + whatShouldIDoToday("sad","rainy",0))
    println("Okay day" + whatShouldIDoToday(temperature = 35))

    /*print("How do you feel? ")
    println(whatShouldIDoToday(readLine()!!, readLine()!!, readLine()!!.toInt()))
*/
    eagerExample()
    val rollDice2: (Int) -> Int = { sides ->
        if (sides == 0) 0
        else Random().nextInt(sides) + 1
    }
    gamePlay(rollDice2(6)) 

}

fun gamePlay(diceRoll: Int){
    // do something with the dice roll
    println(diceRoll)
}

fun eagerExample(){
    val deco = listOf("nickle","penny","coins", "bills")
    val eager = deco.filter { it[1]=='i' }
    println(eager)
}


fun whatShouldIDoToday(mood:String = "okay", weather:String = "sunny",temperature :Int=24):String{
    return when{
        isHot(temperature) -> "go swimming"
        isHappyDay(mood,weather) -> "go for a walk in $temperature Celsius"
        isSadRainyCold(mood,weather,temperature) -> "stay in bed"
        else -> "Stay home and read."
    }
}

fun isHot(temperature: Int) = temperature > 30
fun isSadRainyCold(mood:String , weather: String,temperature: Int) =
        mood == "sad" && weather == "rainy" && temperature == 0
fun isHappyDay(mood: String,weather: String) = mood == "good" && weather == "sunny"