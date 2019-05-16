package Aquarium

open class Book(val tittle : String , val auther : String ){
    private var currentPage = 1
    open fun readPage() {currentPage++}
}

class eBook(tittle: String, author:String, var format: String = "text") : Book(tittle,author){
    private var wordsRead = 0
    override fun readPage(){
        wordsRead += 250
    }
}