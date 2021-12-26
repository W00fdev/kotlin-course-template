import java.time.Year



class Library : LibraryService {

    private var userList: ArrayList<User> = arrayListOf()
    private val bookList: ArrayList<Book> = arrayListOf()

    override fun findBooks(substring: String): List<Book> {
        if (substring.isEmpty())
            error("Book's name is empty.")
        return bookList.filter { it.name == substring }
    }

    override fun findBooks(author: Author): List<Book> {
        return bookList.filter { it.authors.contains(author) }
    }

    override fun findBooks(year: Year): List<Book> {
        return bookList.filter { it.year == year }
    }

    override fun findBooks(genre: Genre): List<Book> {
        return bookList.filter { it.genre == genre }
    }

    override fun getAllBooks(): List<Book> {
        return bookList
    }

    override fun getAllAvailableBooks(): List<Book> {
        return bookList.filter { it.status == Status.Available }
    }

    override fun getBookStatus(book: Book): Status {
        val foundBook: Book = bookList.find { it == book } ?: error("Can't find the book")
        return foundBook.status
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        val resultBookStatuses: Map<Book, Status> = mutableMapOf()
        bookList.forEach {
            resultBookStatuses.plus(Pair(it, it.status))
        }
        return resultBookStatuses
    }

    override fun setBookStatus(book: Book, status: Status) {
        val foundBook = bookList.find { it == book } ?: error("Can't find the book")
        foundBook.changeStatus(status)
    }

    override fun addBook(book: Book, status: Status) {
        book.status = status
        if (!bookList.contains(book))
            bookList.add(book)
    }

    override fun registerUser(user: User) {
        if (userList.contains(user))
            error("The user already exist")
        userList.add(user)
    }

    override fun unregisterUser(user: User) {
        if (!userList.contains(user))
            error("The user doesnt exist")
        userList.remove(user)
    }

    override fun takeBook(user: User, book: Book) {
        if (!bookList.contains(book))
            error("The book doesnt exist")
        if (!userList.contains(user))
            error("The user doesnt exist")
        if (user.books >= 3)
            error("The user cant take more than 3 books")
        if (book.status !is Status.Available)
            error("The book doesnt exist")

        book.changeStatus(Status.UsedBy(user))
    }

    override fun returnBook(book: Book) {
        if (book.status is Status.Available)
            error("The book already returned.")
        if (!bookList.contains(book))
            error("The book doesnt exist.")

        book.changeStatus(Status.Available)
    }
}
