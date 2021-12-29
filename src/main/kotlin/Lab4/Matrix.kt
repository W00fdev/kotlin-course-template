enum class Operations {
    ADD,
    MINUS,
    MULTIPLY,
    DIVIDE
}

class Matrix(
    initialArray: Array<Array<Int>>
) {
    private var data: Array<Array<Int>>

    private val rows
        get() = data.size
    private val columns
        get() = data[0].size

    init {
        if (initialArray.isEmpty() || initialArray[0].isEmpty())
            error("Matrix is empty.")

        data = Array(rows) { i ->
            if (columns != initialArray[i].size)
                error("Matrix has incorrect size.")
            initialArray[i].copyOf()
        }
    }

    // copy function
    private fun setMatrix(other: Matrix) {
        if (other.rows == 0 || other.columns == 0)
            error("Matrix is empty.")

        data = Array(rows) { i ->
            if (columns != other.data[i].size)
                error("Matrix has incorrect size.")
            other.data[i].copyOf()
        }
    }

    private fun operationMatrix(operator: Operations, other: Matrix): Matrix {
        if (rows != other.rows || columns != other.columns)
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
        if (columns != other.rows)
            error("Can't time matrices because of their size.")
        val resultMatrix = Matrix(Array(rows) { Array(other.columns) { 0 } })

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
        if (i < 0 || j < 0 || i >= rows || j >= columns)
            error("Set's index are out of bounds.")
        data[i][j] = value
    }

    operator fun get(i: Int, j: Int): Int {
        if (i < 0 || j < 0 || i >= rows || j >= columns)
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
                if (j != columns - 1)
                    resultString.append(" ")
            }
            resultString.append("\n")
        }
        return resultString.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Matrix || rows != other.rows || columns != other.columns)
            return false
        return data.contentDeepEquals(other.data)
    }

    override fun hashCode(): Int {
        var result = data.contentDeepHashCode()
        result = 31 * result + rows
        result = 31 * result + columns
        return result
    }
}