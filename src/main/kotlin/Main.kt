fun main() {

    // LAB 3
    run{
        println("LAB3 ++++++++++++++++")
        val shapeFactory = ShapeFactoryImpl(10.0)

        val circle1: Circle = shapeFactory.createRandomCircle()
        val circle2: Circle = shapeFactory.createCircle(5.0)

        val square1: Square = shapeFactory.createRandomSquare()
        val square2: Square = shapeFactory.createSquare(5.0)

        val rectangle1: Rectangle = shapeFactory.createRandomRectangle()
        val rectangle2: Rectangle = shapeFactory.createRectangle(5.0, 10.0)

        val triangle1: Triangle = shapeFactory.createRandomTriangle()
        val triangle2: Triangle = shapeFactory.createTriangle(3.0, 4.0, 5.0)

        val shapes: List<Shape> = listOf(circle1, circle2, square1, square2,
            rectangle1, rectangle2, triangle1, triangle2)

        var sumArea = 0.0
        var sumPerimeter = 0.0
        var minArea: Shape = shapes.first()
        var minPerimeter: Shape = shapes.first()
        var maxArea: Shape = shapes.first()
        var maxPerimeter: Shape = shapes.first()

        shapes.forEach {
            val area = it.calcArea()
            val perimeter = it.calcPerimeter()

            if (minArea.calcArea() > area)
                minArea = it
            if (minPerimeter.calcPerimeter() > perimeter)
                minPerimeter = it

            if (maxArea.calcArea() < area)
                maxArea = it
            if (maxPerimeter.calcPerimeter() < perimeter)
                maxPerimeter = it

            sumArea += area
            sumPerimeter += perimeter
        }
        println("Min P = ${minPerimeter.calcPerimeter()} as ${minPerimeter.javaClass}")
        println("Max P = ${maxPerimeter.calcPerimeter()} as ${maxPerimeter.javaClass}")
        println("Min S = ${minArea.calcArea()} as ${minArea.javaClass}")
        println("Max S = ${maxArea.calcPerimeter()} as ${maxArea.javaClass}")
        println("Sum P = $sumPerimeter")
        println("Max S = $sumArea")

        println("LAB3 END --------------")
    }


    // LAB 4
    run{
        println("LAB4 ++++++++++++++++")

        val matrixA = Matrix(arrayOf
            (arrayOf(1, 2),
            arrayOf(3, 4))
        )

        val matrixB = Matrix(arrayOf
            (arrayOf(10, 9),
            arrayOf(8, 7))
        )

        println("Matrix A: \n$matrixA")
        println("Matrix B: \n$matrixB")
        println("Matrix A + matrix B: \n${matrixA + matrixB}")
        println("Matrix B - matrix A: \n${matrixB - matrixA}")
        println("Matrix A x matrix B: \n${matrixA * matrixB}")
        println("Matrix A + 100: \n${matrixA + 100}")
        println("Matrix B - 20: \n${matrixB - 20}")
        println("Matrix A x 10: \n${matrixA * 10}")
        println("Matrix B / 2: \n${matrixB / 2}")
        matrixA *= 2
        println("Matrix A *= 2: \n$matrixA")
        matrixB -= 2
        println("Matrix B -= 2: \n$matrixB")
        println("Matrix A == B: \n${matrixA == matrixB}")

        val matrixC = Matrix(arrayOf(
            arrayOf(2, 4),
            arrayOf(6, 8)
        ))
        println("+Matrix C: \n${+matrixC}")
        println("Matrix A == C: \n${matrixA == matrixC}")
        println("-Matrix B: \n${-matrixB}")

        println("LAB4 END --------------")
    }

}