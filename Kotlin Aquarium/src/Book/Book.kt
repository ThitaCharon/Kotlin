package Book

fun main(args:Array<String>){
    var b1 = Book("GAME OF THRONES", "George R. R. Martin",2012)
    println(b1.getTitleAuthor())
    val bookTitleAuthor = b1.getTitleAuthor()
    val bookTitleAuthorYear = b1.getTitleAutherYear()
    println("Here is your book ${bookTitleAuthor.first} by ${bookTitleAuthor.second}")

    println("Here is your book ${bookTitleAuthorYear.first} " +
            "by ${bookTitleAuthorYear.second} written in ${bookTitleAuthorYear.third}")

    val allBooks = setOf("Macbeth", "Romeo and Juliet", "Hamlet", "A Midsummer Night's Dream")
    val library = mapOf("Shakespeare" to allBooks)
    println("test on containsKey : " + library.any { library.containsKey("Shakespeare") })
    println("test on any.{it.value} : " + library.any { it.value.contains("Romeo and Juliet") })
    val moreBooks = mutableMapOf("Wilhelm" to "Schiller")
    println(moreBooks)
    moreBooks.getOrPut("Jungle book") {"kipling"}
    println(moreBooks)
    moreBooks.getOrPut("Hamlet"){"Shakespeare"}
    println(moreBooks)
    println(b1.printUrl())
}

class Book (val tittle:String="N/A" , val author:String="N/A" , val year:Int){

    companion object{
        const val MAX_NUM_BOOKS = 10
    }

    fun getTitleAuthor() : Pair<String,String>{
        return (tittle to author)
    }

    fun getTitleAutherYear() : Triple<String,String,Int>{
        return Triple(tittle,author,year)
    }

    fun canBorrow(hasBooks:Int):Boolean{
        return (hasBooks < MAX_NUM_BOOKS)
    }
    fun printUrl(){println(Constants.BASE_URL+tittle+".html")}

    object Constants {
        const val BASE_URL = "https://www.mylibrary."
    }
}