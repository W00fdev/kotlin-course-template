class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) /* ??? */ {}
    fun addAll(new: List<T> ) /* ??? */ {}

    fun getAll(): List<T> { return mutableListOf() }
    fun getAllSorted(comparator: ShapeComparators): List<T> /* ??? */ { return mutableListOf() }
}
