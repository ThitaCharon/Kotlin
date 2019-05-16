import java.util.*

fun main(args: Array<String>){
    var fortune: String
    for (i in 1..2) {
        fortune = getFortune(getBirthday())
        println("\nYour fortune is: $fortune")
        if (fortune.contains("Take it easy")) break;
    }
    println(whatShouldIDoToday(mood = "good"))

}
fun whatShouldIDoToday(mood:String, weather:String = "sunny",temp :Int=24):String {
    return when (weather) {
        //mood == "happy"&& weather ==
        "sunny" -> "go for a walk"
        else -> "Stay home and watch series"
    }
}

fun getBirthday(): Int{
    print("What is your DOB : ")
    return readLine()?.toIntOrNull() ?:1
}
fun getFortune(BOD : Int): String {
    return when (BOD){
        1 -> "You will have a great day!"
        2 -> "Things will go well for you today."
        3 ->"Enjoy a wonderful day of success."
        4 ->"Be humble and all will turn out well."
        in 5..10 ->"Today is a good day for exercising restraint."
        in 16..20 ->"Take it easy and enjoy life!"
        else ->"Treasure your friends because they are your greatest fortune."
    }
}

fun feedTheFish(){
    val day = randomDay()
    val food = "pellets"
    println("Today is $day and feed the fish with $food")
}

fun randomDay():String{
    val week = listOf("Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun")
    return week[Random().nextInt(7)]
}
fun dayOfWeek(){
    println("what day is today ")
    var day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    when(day != null) {
        day == 1-> println("Sunday")
        day == 2->println("Monday")
        day == 3->println("Tuesday")
        day == 4->println("Wednesday")
        day == 5->println("Thursday")
        day == 6->println("Friday")
        day == 7->println("Saturday")
    }

}