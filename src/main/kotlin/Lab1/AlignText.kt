enum class Alignment {
    LEFT,
    CENTER,
    RIGHT
}

fun applyAlign(
    text: String,
    lineWidth: Int,
    alignment: Alignment
): String {
    return when (alignment) {
        Alignment.RIGHT -> text.padStart(lineWidth)
        Alignment.LEFT -> text
        Alignment.CENTER -> {
            val padding = text.length + (lineWidth - text.length) / 2
            text.padStart(padding).padEnd(lineWidth)
        }
    }
}

fun changeAlignText(
    text: String,
    lineWidth: Int = 120,
    alignment: Alignment = Alignment.LEFT
): String {
    if (text.isEmpty())
        throw Exception("Can't process an empty text.")

    var resultText = ""
    var currentText = text
    while (currentText.isNotBlank()) {
        if (currentText.length <= lineWidth) {
            resultText = resultText.plus(applyAlign(currentText, lineWidth, alignment))
            break
        }

        val lineBreak = currentText.indexOf('\n')

        // lineBreak can't be > currentText.length
        if (lineBreak <= lineWidth + 1 && lineBreak != -1) {
            resultText = resultText.plus("${applyAlign(currentText.substring(0, lineBreak), lineWidth, alignment)}\n")
            // Skip 1 redundant '\n'
            currentText = currentText.substring(lineBreak + 1)
            continue
        }

        val lastSpaceInLine =
            if (lineWidth < text.length)
                currentText.lastIndexOf(' ', lineWidth + 1) + 1
            else
                currentText.lastIndexOf(' ', currentText.length) + 1

        var moveIndexText = lineWidth
        // 1 + 3 cases.
        if (lastSpaceInLine == -1 ||
            lastSpaceInLine == lineWidth + 1
        ) {
            resultText = resultText.plus("${applyAlign(currentText.substring(0, lineWidth), lineWidth, alignment)}\n")
        }
        // 2 + 4 cases
        else {
            resultText =
                resultText.plus("${applyAlign(currentText.substring(0, lastSpaceInLine), lineWidth, alignment)}\n")
            moveIndexText = if (lastSpaceInLine == 0)
                1
            else
                lastSpaceInLine
        }

        currentText = currentText.substring(moveIndexText)
    }

    return resultText
}