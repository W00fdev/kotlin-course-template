package lab4

class Matrix(matrixContents: Array<Array<Double>>) {
    private var matrix: Array<Array<Double>>

    init {
        val length = matrixContents[0].size
        matrix = Array(matrixContents.size) { i ->
            if (matrixContents[i].size != length) throw IllegalArgumentException("Matrix is wrong-sized")
            matrixContents[i].clone()
        }
    }

    operator fun get(i: Int, j: Int): Double {
        if (i >= matrix.size) throw ArrayIndexOutOfBoundsException("Matrix index out of bounds. Index i is $i and size is ${matrix.size}")
        if (j >= matrix[0].size) throw ArrayIndexOutOfBoundsException("Matrix index out of bounds. Index j is $j and size is ${matrix[0].size}")
        return matrix[i][j]
    }

    operator fun set(i: Int, j: Int, value: Double) {
        if (i >= matrix.size) throw ArrayIndexOutOfBoundsException("Matrix index out of bounds. Index i is $i and size is ${matrix.size}")
        if (j >= matrix[0].size) throw ArrayIndexOutOfBoundsException("Matrix index out of bounds. Index j is $j and size is ${matrix[0].size}")
        matrix[i][j] = value
    }

    fun getDimensions(): Pair<Int, Int> = Pair(matrix.size, matrix[0].size)

    //matrices (+, +=, -, -=, *, *=)
    operator fun plus(other: Matrix): Matrix {
        //also works as plusAssign
        if (matrix.size != other.matrix.size || matrix[0].size != other.matrix[0].size) throw IllegalArgumentException("Dimensions are different")
        val newMatrix = Matrix(Array(matrix.size) { Array(matrix[0].size) { 0.0 } })
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                newMatrix[i, j] = this[i, j] + other[i, j]
        return newMatrix
    }

    operator fun plusAssign(other: Matrix) {
        //also works as plusAssign
        if (matrix.size != other.matrix.size || matrix[0].size != other.matrix[0].size) throw IllegalArgumentException("Dimensions are different")
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                this[i, j] += other[i, j]
    }

    operator fun minus(other: Matrix): Matrix {
        if (matrix.size != other.matrix.size || matrix[0].size != other.matrix[0].size) throw IllegalArgumentException("Dimensions are different")
        val newMatrix = Matrix(Array(matrix.size) { Array(matrix[0].size) { 0.0 } })
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                newMatrix[i, j] = this[i, j] - other[i, j]
        return newMatrix
    }

    operator fun minusAssign(other: Matrix) {
        //also works as plusAssign
        if (matrix.size != other.matrix.size || matrix[0].size != other.matrix[0].size) throw IllegalArgumentException("Dimensions are different")
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                this[i, j] -= other[i, j]
    }

    operator fun times(other: Matrix): Matrix {
        return multiply(other)
    }

    operator fun timesAssign(other: Matrix) {
        //creating new matrix in case user wants to multiply as A *= A
        matrix = multiply(other).matrix
    }

    private fun multiply(other: Matrix): Matrix {
        //to multiply dimensions should be NxM and MxK
        if (matrix[0].size != other.matrix.size) throw IllegalArgumentException("Dimensions are different")
        val newMatrix = Matrix(Array(matrix.size) { Array(other.matrix[0].size) { 0.0 } })

        for (i in matrix.indices)
            for (j in other.matrix[i].indices)
                for (k in other.matrix.indices)
                    newMatrix[i, j] += this[i, k] * other[k, j]
        return newMatrix
    }

    //working with scalars (*, *=, /, /=)
    operator fun times(scalar: Double): Matrix {
        val newMatrix = Matrix(Array(matrix.size) { Array(matrix[0].size) { 0.0 } })
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                newMatrix[i, j] = this[i, j] * scalar
        return newMatrix
    }

    operator fun timesAssign(scalar: Double) {
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                this[i, j] *= scalar
    }

    operator fun div(scalar: Double): Matrix {
        val newMatrix = Matrix(Array(matrix.size) { Array(matrix[0].size) { 0.0 } })
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                newMatrix[i, j] = this[i, j] / scalar
        return newMatrix
    }

    operator fun divAssign(scalar: Double) {
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                this[i, j] /= scalar
    }

    operator fun unaryMinus(): Matrix {
        val newMatrix = Matrix(Array(matrix.size) { Array(matrix[0].size) { 0.0 } })
        for (i in matrix.indices)
            for (j in matrix[i].indices)
                newMatrix[i, j] = -this[i, j]
        return newMatrix
    }

    operator fun unaryPlus(): Matrix = this

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Matrix || other.getDimensions() != this.getDimensions()) return false

        return matrix.contentDeepEquals(other.matrix)
    }

    override fun toString(): String {
        val outStr = StringBuilder()
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                outStr.append(this[i, j])
                outStr.append(" ")
            }
            outStr.append(System.lineSeparator())
        }
        return outStr.toString()
    }

    override fun hashCode(): Int {
        //automatically generated so there's no warning
        return matrix.contentDeepHashCode()
    }

}