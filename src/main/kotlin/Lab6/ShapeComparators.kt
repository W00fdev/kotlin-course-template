import java.util.Comparator

object ShapeComparators {
    fun <T : Shape> compareArea() : Comparator<T> {
        return java.util.Comparator { shape1, shape2 ->
            val area1 = shape1.calcArea()
            val area2 = shape2.calcArea()
            if (area1 > area2) 1
            else if (area1 == area2 ) 0
            else -1
        }
    }

    fun <T : Shape> compareAreaDescending() : Comparator<T> {
        return java.util.Comparator { shape1, shape2 ->
            val area1 = shape1.calcArea()
            val area2 = shape2.calcArea()
            if (area1 > area2) -1
            else if (area1 == area2 ) 0
            else 1
        }
    }

    fun <T : Shape> comparePerimeter() : Comparator<T> {
        return java.util.Comparator { shape1, shape2 ->
            val perimeter1 = shape1.calcPerimeter()
            val perimeter2 = shape2.calcPerimeter()
            if (perimeter1 > perimeter2) 1
            else if (perimeter1 == perimeter2 ) 0
            else -1
        }
    }

    fun <T : Shape> comparePerimeterDescending() : Comparator<T> {
        return java.util.Comparator { shape1, shape2 ->
            val perimeter1 = shape1.calcPerimeter()
            val perimeter2 = shape2.calcPerimeter()
            if (perimeter1 > perimeter2) -1
            else if (perimeter1 == perimeter2 ) 0
            else 1
        }
    }

    fun compareRadius() : Comparator<Circle> {
        return java.util.Comparator { circle1, circle2 ->
            val radius1 = circle1.radius
            val radius2 = circle2.radius
            if (radius1 > radius2) 1
            else if (radius1 == radius2 ) 0
            else -1
        }
    }

    fun compareRadiusDescending() : Comparator<Circle> {
        return java.util.Comparator { circle1, circle2 ->
            val radius1 = circle1.radius
            val radius2 = circle2.radius
            if (radius1 > radius2) -1
            else if (radius1 == radius2 ) 0
            else 1
        }
    }

}