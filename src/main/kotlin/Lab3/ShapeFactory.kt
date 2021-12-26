
import kotlin.math.abs
import kotlin.random.Random
import kotlin.math.sqrt

interface ShapeFactory {
    fun createCircle(radius: Double): Circle

    fun createSquare(side: Double): Square

    fun createRectangle(sideA: Double, sideB: Double): Rectangle

    fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle

    fun createRandomCircle(): Circle

    fun createRandomSquare(): Square;
    fun createRandomRectangle(): Rectangle;
    fun createRandomTriangle(): Triangle;

    fun createRandomShape(): Shape;
}

// The square of randomMax can reach out of max_value.
class ShapeFactoryImpl(_randomMax: Double = sqrt(Double.MAX_VALUE) - 1): ShapeFactory {

    private val randomMax: Double

    init {
        if (_randomMax <= 0 || _randomMax > sqrt(Double.MAX_VALUE) - 1)
            error("Random")

        randomMax = _randomMax
    }

    override fun createCircle(radius: Double): Circle {
        return Circle(radius)
    }

    override fun createSquare(side: Double): Square {
        return Square(side)
    }

    override fun createRectangle(sideA: Double, sideB: Double): Rectangle {
        return Rectangle(sideA, sideB)
    }

    override fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle {
        return Triangle(sideA, sideB, sideC)
    }

    override fun createRandomCircle(): Circle {
        return Circle(1 + Random.nextDouble(until = randomMax))
    }

    override fun createRandomSquare(): Square {
        return Square(1 + Random.nextDouble(until = randomMax))
    }

    override fun createRandomRectangle(): Rectangle {
        return Rectangle(1 + Random.nextDouble(until = randomMax), 1 + Random.nextDouble(until = randomMax))
    }

    override fun createRandomTriangle(): Triangle {
        val sideA = 1 + Random.nextDouble(until = randomMax)
        val sideB = 1 + Random.nextDouble(until = randomMax)
        val sideC = Random.nextDouble(abs(sideA - sideB), sideA + sideB)

        return Triangle(sideA, sideB, sideC)
    }

    override fun createRandomShape(): Shape {
        return when(Random.nextInt(1, 4)) {
            1 -> createRandomCircle()
            2 -> createRandomSquare()
            3 -> createRandomRectangle()
            4 -> createRandomTriangle()
            else -> error("Some magic error in random range")
        }
    }
}