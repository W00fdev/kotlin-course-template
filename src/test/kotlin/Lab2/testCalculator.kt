import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class testCalculator {
    @Test
    fun testEmptyExpression() {
        assertFails { RPNManager("") }
    }

    @Test
    fun testBracersError() {
        assertFails { RPNManager("(3(()51") }
    }

    @Test
    fun testSimpleExpression() {
        val rpn1 = RPNManager("50+30")
        rpn1.parseRPN()
        assertEquals(rpn1.computeRPN(), 80.0)

        val rpn2 = RPNManager("  50   +   30   ")
        rpn2.parseRPN()
        assertEquals(rpn2.computeRPN(), 80.0)

        val rpn3 = RPNManager("3 + 4 * 2 / (1 - 5)^2")
        rpn3.parseRPN()
        assertEquals(rpn3.computeRPN(), 3.5)

        val rpn4 = RPNManager("(3) + ((4 * 2)) / ((1 - 5) ^ 2)")
        rpn4.parseRPN()
        assertEquals(rpn4.computeRPN(), 3.5)
    }

    @Test
    fun testExpressionWithConstants() {
        val rpn1 = RPNManager("pi+e")
        rpn1.parseRPN()
        assertEquals(rpn1.computeRPN(), 5.86008)

        val rpn2 = RPNManager("pi*4")
        rpn2.parseRPN()
        assertEquals(rpn2.computeRPN(), 12.5672)
    }
}