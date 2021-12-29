import java.io.File
import java.io.FileReader
import java.io.FileWriter

class FileManager(
    private val readPath: String,
    private val writePath: String
) {
    init {
        if (!File(readPath).canRead() || !File(writePath).canWrite())
            error("Can't process with files")
    }

    fun readFromFile() : String =
        FileReader(readPath).readText()

    fun writeOnFile(text: String) {
        File(writePath).writeText(text)
    }
}