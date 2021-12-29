import java.time.Year

fun main() {

    // LAB 1
    run {
        println("LAB1 ++++++++++++++++")

        val text = "abcd abcd abc abc ab ab ab a a a"
        println("Text origin:\n $text")
        println("Text with align left(9):\n ${changeAlignText(text, 9, Alignment.LEFT)}")
        println("Text with align center(10):\n ${changeAlignText(text, 10, Alignment.CENTER)}")
        println("Text with align right(5):\n ${changeAlignText(text, 5, Alignment.RIGHT)}")

        println("LAB1 END --------------")
    }

    println()

    // LAB 2
    run {
        println("LAB2 ++++++++++++++++")

        val rpn1 = RPNManager("50+30")
        rpn1.parseRPN()
        println("50+30")
        println(rpn1.outputExpressionRPN)
        println(rpn1.computeRPN())

        println()

        val rpn2 = RPNManager("3 + 4 * 2 / (1 - 5)^2")
        rpn2.parseRPN()
        println("3 + 4 * 2 / (1 - 5)^2")
        println(rpn2.outputExpressionRPN)
        println(rpn2.computeRPN())

        println()

        val rpn3 = RPNManager("5 + (3 * (-3 * 10) / 3) ^ 2")
        rpn3.parseRPN()
        println("5 + (3 * (-3 * 10) / 3) ^ 2")
        println(rpn3.outputExpressionRPN)
        println(rpn3.computeRPN())

        println("LAB2 END --------------")
    }

    println()

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

    println()

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

    println()

    // LAB 5
    run{
        println("LAB5 ++++++++++++++++")

        val authorList1 = listOf(
            Author("Dmitry Kulikov"),
            Author("Alexander Mikhaylov")
        )

        val authorList2 = listOf(
            Author("Lev Tolstoy")
        )

        val bookList = listOf(
            Book("Lab3", authorList1, Genre.FAIRYTAIL, Year.of(2021)),
            Book("Lab2", authorList1, Genre.FANTASY, Year.of(2021)),
            Book("The war and the peace", authorList2, Genre.ROMANCE, Year.of(1956)),
        )

        val library = Library()

        bookList.forEach { library.addBook(it) }

        println("All books list: ${library.getAllBooks()}")
        library.setBookStatus(bookList[1], Status.ComingSoon)

        println()

        println("All books' statuses: ")
        library.getAllBookStatuses().forEach { println("${it.key.name} on ${it.value}") }

        println()

        println("${library.findBooks("Lab2")}")
        library.setBookStatus(library.findBooks("Lab2")[0], Status.Available)

        val newUser = User("Senior Programmer")
        library.registerUser(newUser)
        library.takeBook(newUser, bookList[1])
        println()

        println("All books' statuses: ")
        library.getAllBookStatuses().forEach { println("${it.key.name} is ${it.value}") }
        println()

        println("Books stored by Senior Programmer number: ${newUser.books}")

        println("Available books:\n ${library.getAllAvailableBooks()}")
        library.returnBook(bookList[1])
        println()

        println("Books stored by Senior Programmer number: ${newUser.books}")


        println("Available books:\n ${library.getAllAvailableBooks()}")

        println("LAB5 END --------------")
    }

    println()

    // LAB 6
    run{
        println("LAB6 ++++++++++++++++")

        val shapeFactory = ShapeFactoryImpl(10.0)
        val shapeCollector = ShapeCollector<Shape>()

        val circle1: Circle = shapeFactory.createRandomCircle()
        val circle2: Circle = shapeFactory.createCircle(5.0)

        val square1: Square = shapeFactory.createRandomSquare()
        val square2: Square = shapeFactory.createSquare(5.0)

        val rectangle1: Rectangle = shapeFactory.createRandomRectangle()
        val rectangle2: Rectangle = shapeFactory.createRectangle(5.0, 10.0)

        val triangle1: Triangle = shapeFactory.createRandomTriangle()
        val triangle2: Triangle = shapeFactory.createTriangle(3.0, 4.0, 5.0)

        val shapesRandom: List<Shape> = listOf(circle1, square1, rectangle1, triangle1)

        shapeCollector.add(circle2)
        shapeCollector.add(square2)
        shapeCollector.add(rectangle2)
        shapeCollector.add(triangle2)

        println("Shape collector all:\n\t${shapeCollector.getAll()}")
        println("Shape collector sorted area:\n\t${shapeCollector.getAllSorted(ShapeComparators.compareArea())}")
        println("Shape collector sorted perimeter descending:\n\t" +
                "${shapeCollector.getAllSorted(ShapeComparators.comparePerimeterDescending())}")

        shapeCollector.addAll(shapesRandom)

        println("Shape collector sorted area descending:\n\t${shapeCollector.getAllSorted
            (ShapeComparators.compareAreaDescending())}")
        println("Shape collector sorted perimeter:\n\t" +
                "${shapeCollector.getAllSorted(ShapeComparators.comparePerimeter())}")

        println()

        val shapeCollectorCircles = ShapeCollector<Circle>()

        shapeCollectorCircles.add(Circle(1.0))
        shapeCollectorCircles.add(shapeFactory.createRandomCircle())
        shapeCollectorCircles.add(shapeFactory.createRandomCircle())
        shapeCollectorCircles.add(shapeFactory.createRandomCircle())

        println("Shape collector circles all:\n\t${shapeCollectorCircles.getAll()}")
        println("All circles sorted by radius:\n\t${shapeCollectorCircles.getAllSorted
            (ShapeComparators.compareRadius())}")
        println("All circles sorted by radius descending:\n\t${shapeCollectorCircles.getAllSorted
            (ShapeComparators.compareRadiusDescending())}")

        println("LAB6 END --------------")
    }

    println()

    run {
        println("LAB7 ++++++++++++++++")
        val inPath = "D:\\in.json"
        val outPath = "D:\\out.json"
        val fileManager = FileManager(inPath, outPath)
        val managerJSON = JSONManager()
        val shapesList = mutableListOf<Shape>()
        shapesList.addAll(managerJSON.decodeJSON<MutableList<Shape>>(fileManager.readFromFile()))

        println("Original shapes list:\n$shapesList")

        shapesList.addAll(listOf(
                Circle(42.0),
                Triangle(3.0, 4.0, 5.0),
                Rectangle(1.0, 2.0),
                Square(3.0)
            )
        )

        println("Changed shapes list:\n$shapesList")

        fileManager.writeOnFile(managerJSON.encodeJSON(shapesList))

        println("In JSON:\n${fileManager.readFromFile()}")

        val fileManagerDebug = FileManager(outPath, inPath)
        println("Out JSON:\n${fileManagerDebug.readFromFile()}")

        println("LAB7 END --------------")
    }
}