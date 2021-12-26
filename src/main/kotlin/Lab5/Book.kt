import java.time.Year

enum class Genre{
    DETECTIVE,
    FAIRYTAIL,
    ROMANCE,
    FANTASY
}

class User(val name: String) {
    var books: Int = 0

    override fun toString(): String {
        return "$name with number of books: $books"
    }
}

class Author(_name: String) {
    val name: String

    init{
        name = _name
    }

    override fun toString(): String {
        return name
    }
}

sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()
}



class Book(
    val name: String,
    val authors: List<Author>,
    val genre: Genre,
    val year: Year,
) {
    var status: Status = Status.Available
    // Empty user
    var user: User = User("")


    fun changeStatus(newStatus: Status) {
        if (status is Status.UsedBy) {
            user.books--
        }

        if (newStatus is Status.UsedBy) {
            user = newStatus.user
            user.books++
        }

        status = newStatus
    }

    fun genreToString(): String {
        return when (genre) {
            Genre.DETECTIVE -> "Detective"
            Genre.FAIRYTAIL -> "Fairytail"
            Genre.ROMANCE -> "Romance"
            Genre.FANTASY -> "Fantasy"
        }
    }

    override fun toString(): String {
        return "$name by $authors of $year of ${genreToString()}"
    }
}