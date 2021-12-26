import java.util.*

// Структура для стека операторов.
class Operator(
    val literal: Char,
    val isUnary: Boolean = false
) {
    val priority: Int

    init {
        when(literal) {
            '+' -> priority = 1
            '-' -> priority = 1
            '*' -> priority = 2
            '/' -> priority = 2
            '^' -> priority = 3
            '(' -> priority = 0
            else -> error("There's no such operator: $literal")
        }
    }
}

// check unary: если предыдущая обработка была оператором и сейчас встретился '-', то унарный. иначе - ошибка.

class RPNManager(
    private val inputExpression: String
) {
    var outputExpressionRPN: String = ""

    private val operatorsCheck: String = "+-*/^()"
    private val digitsCheck: String = "0123456789"

    init{
        if (inputExpression.isEmpty())
            error("Input expression is empty.")
        if (!checkBracers())
            error("Bracers' count isnt right.")
    }

    private fun checkBracers(): Boolean {
        var count = 0
        inputExpression.forEach {
            if (it == '(')
                count++
            else if (it == ')')
                count--
        }
        return count == 0
    }

    // 3 if's wrapped.
    private fun checkConstant(literal: Char, lastReadOperator: Boolean, lastReadLiteral: Char): Boolean {
        return if (lastReadOperator && lastReadLiteral !in "pie" && literal == 'e')
            true
        else if (!lastReadOperator && lastReadLiteral == 'p' && literal == 'i')
            true
        else !lastReadOperator && lastReadLiteral !in "pie" && literal == 'p'
    }

    fun parseRPN() {
        val input = inputExpression.replace(" ", "").lowercase()
        // Для проверки, что мы прочитали два оператора подряд. Это либо ошибка, либо унарный минус.
        var lastReadOperator = false
        var lastReadLiteral = '\n'
        val stackOfOperators: Stack<Operator> = Stack()

        input.forEach {
            if (it in operatorsCheck) {
                outputExpressionRPN += ' '
                // Обрабатываем оператор
                if (it == '(') {
                    stackOfOperators.push(Operator('('))
                } else if (it == ')') {
                    // Выносим, пока не встретим '('
                    while (stackOfOperators.size > 0) {
                        val currentOperator = stackOfOperators.pop()
                        if (currentOperator.literal == '(') {
                            break
                        }
                        // Выводим
                        outputExpressionRPN += currentOperator.literal
                    }
                }
                else {
                    if (lastReadOperator && it == '-') {
                        outputExpressionRPN += "0 "
                        stackOfOperators.push(Operator('-', true))
                    } else {
                        // Priority pops
                        val currentOperator = Operator(it)
                        while (stackOfOperators.size > 0) {
                            if (stackOfOperators.lastElement().priority < currentOperator.priority
                                && stackOfOperators.lastElement().isUnary == false)
                                break

                            outputExpressionRPN += " " + stackOfOperators.pop().literal
                        }
                        stackOfOperators.push(Operator(it))
                    }
                }
                lastReadOperator = true
            } else if (it in digitsCheck) {
                lastReadOperator = false
                // Просто выводим цифру в вывод
                outputExpressionRPN += it

            } else if (it in "pie") {
                if (false == checkConstant(it, lastReadOperator, lastReadLiteral))
                    error("Error parsing pi/e.")
                outputExpressionRPN += it
                lastReadOperator = false

            } else {
                error("Error input string: unaccepted literal.")
            }
            // Запоминаем в конце последний прочитанный символ. Для обработки pi, e
            lastReadLiteral = it
        }

        while (stackOfOperators.size > 0)
            outputExpressionRPN += " " + stackOfOperators.pop().literal
    }

    private fun computeOperator(operator: Operator, b: Double, a: Double) : Double {
        return when(operator.literal) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> a / b
            '^' -> Math.pow(a, b)
            else -> error("Unknown operator")
        }
    }

    fun computeRPN() : Double {
        var readingNumber = false
        var stringNumber = ""

        var stackNumbers: Stack<Double> = Stack()

        outputExpressionRPN.forEach {
            if (it in digitsCheck) {
                readingNumber = true
                stringNumber += it
            } else if (it == ' ') {
                if (readingNumber == true)
                    stackNumbers.push(stringNumber.toDouble())

                if (stringNumber == "e")
                    stackNumbers.push(2.71828)
                else if (stringNumber == "pi")
                    stackNumbers.push(3.1418)

                stringNumber = ""
                readingNumber = false
            } else if (it in "pie") {
                stringNumber += it
            } else if (it in operatorsCheck) {
                stackNumbers.push(computeOperator(Operator(it), stackNumbers.pop(), stackNumbers.pop()))
            }
        }

        return stackNumbers.pop()
    }
}


