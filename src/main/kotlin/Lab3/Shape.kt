import kotlin.math.sqrt
import kotlinx.serialization.Serializable

interface Shape {
    fun calcPerimeter(): Double
    fun calcArea(): Double

    override fun toString(): String
}

@Serializable
class Circle(
    val radius: Double
) : Shape {

    init {
        if (radius <= 0)
            error("Wrong radius entered (r > 0).")
    }

    override fun calcPerimeter(): Double {
        return radius * 2 * Math.PI
    }

    override fun calcArea(): Double {
        return Math.PI * radius * radius
    }

    override fun toString(): String {
        return "Circle(" + "%.3f".format(radius) + ") [P=%.2f]".format(calcPerimeter()) +
                "[S=%.2f]".format(calcArea())
    }
}

@Serializable
class Square(
    val side: Double
) : Shape {
    init {
        if (side <= 0)
            error("Wrong side entered (side >= 0")
    }

    override fun calcPerimeter(): Double {
        return side * 4
    }

    override fun calcArea(): Double {
        return side * side
    }

    override fun toString(): String {
        return "Square(" + "%.3f".format(side) + ") [P=%.2f]".format(calcPerimeter()) +
                "[S=%.2f]".format(calcArea())
    }
}

@Serializable
class Rectangle(
    val sideA: Double,
    val sideB: Double
) : Shape {

    init {
        if (sideA <= 0 || sideB <= 0)
            error("Wrong side entered (side >= 0).")
    }

    override fun calcPerimeter(): Double {
        return sideA * 2 + sideB * 2
    }

    override fun calcArea(): Double {
        return sideA * sideB
    }

    override fun toString(): String {
        return "Rectangle(" + "%.3f".format(sideA) + ", " +
                "%.3f".format(sideB) + ") [P=%.2f]".format(calcPerimeter()) +
                "[S=%.2f]".format(calcArea())
    }
}

@Serializable
class Triangle(
    val sideA: Double,
    val sideB: Double,
    val sideC: Double
) : Shape {

    init {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0)
            error("Wrong side entered (side >= 0).")

        if (sideA >= sideB + sideC || sideB >= sideA + sideC || sideC >= sideA + sideB)
            error("Wrong values of sides. They must be: a + b < c")
    }

    override fun calcPerimeter(): Double {
        return sideA + sideB + sideC
    }

    override fun calcArea(): Double {
        val halfPerimeter = calcPerimeter() / 2
        return sqrt(halfPerimeter * (halfPerimeter - sideA) * (halfPerimeter - sideB) * (halfPerimeter - sideC))
    }

    override fun toString(): String {
        return "Triangle(" + "%.3f".format(sideA) + ", " +
                "%.3f".format(sideB) + ", " + "%.3f".format(sideC) +
                ") [P=%.2f]".format(calcPerimeter()) + "[S=%.2f]".format(calcArea())
    }
}