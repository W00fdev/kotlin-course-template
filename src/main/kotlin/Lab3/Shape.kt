
import kotlin.math.sqrt

interface Shape {
    fun calcPerimeter(): Double
    fun calcArea(): Double
}

class Circle(
    _radius: Double
) : Shape {
    private val radius : Double

    init {
        if (_radius <= 0)
            error("Wrong radius entered (r > 0).")

        radius = _radius
    }

    override fun calcPerimeter() : Double {
        return radius * 2 * Math.PI
    }

    override fun calcArea(): Double {
        return Math.PI * radius * radius
    }
}

class Square(
    _side: Double
) : Shape {
    private val side : Double

    init {
        if (_side <= 0)
            error("Wrong side entered (side >= 0")

        side = _side
    }

    override fun calcPerimeter() : Double {
        return side * 4
    }

    override fun calcArea(): Double {
        return side * side
    }
}

class Rectangle(
    _sideA: Double,
    _sideB: Double
) : Shape {

    private val sideA: Double
    private val sideB: Double

    init{
        if (_sideA <= 0 || _sideB <= 0)
            error("Wrong side entered (side >= 0).")

        sideA = _sideA
        sideB = _sideB
    }

    override fun calcPerimeter() : Double {
        return sideA * 2 + sideB * 2
    }

    override fun calcArea(): Double {
        return sideA * sideB
    }
}
class Triangle (
     _sideA: Double,
     _sideB: Double,
     _sideC: Double
) : Shape {

    private val sideA: Double
    private val sideB: Double
    private val sideC: Double

    init {
        if (_sideA <= 0 || _sideB <= 0 || _sideC <= 0)
            error("Wrong side entered (side >= 0).")

        if (_sideA >= _sideB + _sideC || _sideB >= _sideA + _sideC || _sideC >= _sideA + _sideB)
            error("Wrong values of sides. They must be: a + b < c")

        sideA = _sideA
        sideB = _sideB
        sideC = _sideC
    }

    override fun calcPerimeter() : Double {
        return sideA + sideB + sideC
    }

    override fun calcArea(): Double {
        val halfPerimeter = calcPerimeter() / 2
        return sqrt(halfPerimeter * (halfPerimeter - sideA) * (halfPerimeter - sideB) * (halfPerimeter - sideC))
    }
}