enum class Operations{
    ADD,
    MINUS,
    MULTIPLY,
    DIVIDE
}

class Matrix (
    initialArray: Array<Array<Int>>
) {
    private var data: Array<Array<Int>> = emptyArray()
    private var size1: Int = 0
    private var size2: Int = 0

    init {
        if (initialArray.isEmpty())
            error("Matrix is empty.")
        if (initialArray[0].isEmpty())
            error("Matrix is empty")

        size1 = initialArray.size
        size2 = initialArray[0].size

        data = Array(size1) {
                i ->
            if (size2 != initialArray[i].size)
                error("Matrix has incorrect size.")
            initialArray[i].copyOf()
        }
    }

    // copy function
    fun setMatrix(other: Matrix) {
        if (other.getSize().first == 0 || other.getSize().second == 0)
            error("Matrix is empty.")

        size1 = other.getSize().first
        size2 = other.getSize().second

        data = Array(size1) {
                i ->
            if (size2 != other.data[i].size)
                error("Matrix has incorrect size.")
            other.data[i].copyOf()
        }
    }

    fun getSize(): Pair<Int, Int> {
        return Pair(size1, size2)
    }

    private fun operationMatrix(operator: Operations, other: Matrix): Matrix {
        if (getSize() != other.getSize())
            error("Can't operate with matrices of different sizes.")

        val resultMatrix = Matrix(data)
        for (i in data.indices) {
            for (j in data[i].indices) {
                when (operator) {
                    Operations.ADD -> resultMatrix[i, j] += other[i, j]
                    Operations.MINUS -> resultMatrix[i, j] -= other[i, j]
                    else -> error("operationMatrix() hasnt the options of * and /")
                }
            }
        }

        return resultMatrix
    }

    private fun operationScalar(operator: Operations, scalar: Int): Matrix {
        val resultMatrix = Matrix(data)
        for (i in data.indices) {
            for (j in data[i].indices) {
                when (operator) {
                    Operations.ADD -> resultMatrix[i, j] += scalar
                    Operations.MINUS -> resultMatrix[i, j] -= scalar
                    Operations.MULTIPLY -> resultMatrix[i, j] *= scalar
                    Operations.DIVIDE -> resultMatrix[i, j] /= scalar
                }
            }
        }
        return resultMatrix
    }

    operator fun plus(other: Matrix): Matrix {
        return operationMatrix(Operations.ADD, other)
    }

    operator fun plusAssign(other: Matrix) {
        setMatrix(this + other)
    }

    operator fun plus(scalar: Int): Matrix {
        return operationScalar(Operations.ADD, scalar)
    }

    operator fun plusAssign(scalar: Int) {
        setMatrix(this + scalar)
    }

    operator fun times(scalar: Int): Matrix {
        return operationScalar(Operations.MULTIPLY, scalar)
    }

    operator fun times(other: Matrix): Matrix {
        // N x M ; M x K
        if (size2 != other.size1)
            error("Can't time matrices because of their size.")
        val resultMatrix = Matrix(Array(size1) {Array(other.size2) { 0 }})

        for (n in data.indices)
            for (k in other.data[n].indices)
                for (m in other.data.indices)
                    resultMatrix[n, k] = this[n, m] * other[m, k]

        return resultMatrix
    }

    operator fun timesAssign(scalar: Int) {
        setMatrix(this * scalar)
    }

    operator fun timesAssign(other: Matrix) {
        setMatrix(this * other)
    }

    operator fun div(scalar: Int): Matrix {
        return operationScalar(Operations.DIVIDE, scalar)
    }

    operator fun divAssign(scalar: Int) {
        setMatrix(this / scalar)
    }

    operator fun minus(scalar: Int): Matrix {
        return operationScalar(Operations.MINUS, scalar)
    }

    operator fun minusAssign(scalar: Int) {
        setMatrix(this - scalar)
    }

    operator fun minus(other: Matrix): Matrix {
        return operationMatrix(Operations.MINUS, other)
    }

    operator fun minusAssign(other: Matrix) {
        setMatrix(this - other)
    }

    operator fun set(i: Int, j: Int, value: Int) {
        if (i < 0 || j < 0 || i >= size1 || j >= size2)
            error("Set's index are out of bounds.")
        data[i][j] = value
    }

    operator fun get(i: Int, j: Int): Int {
        if (i < 0 || j < 0 || i >= size1 || j >= size2)
            error("Set's index are out of bounds.")
        return data[i][j]
    }
    operator fun unaryMinus(): Matrix {
        return operationScalar(Operations.MULTIPLY, -1)
    }

    operator fun unaryPlus(): Matrix {
        return this
    }

    override fun toString(): String {
        val resultString = StringBuilder()
        for (i in data.indices) {
            for (j in data[i].indices) {
                resultString.append(this[i, j])
                if (j != size2 - 1)
                    resultString.append(" ")
            }
            resultString.append("\n")
        }
        return resultString.toString()
    }

    override fun equals(other: Any?): Boolean {
        if ( other !is Matrix || other.getSize() != getSize())
            return false
        println("CORRECT EQUALS")
        return data.contentDeepEquals(other.data)
    }

    override fun hashCode(): Int {
        var result = data.contentDeepHashCode()
        result = 31 * result + size1
        result = 31 * result + size2
        return result
    }
}