class FileUtil {

    fun readLines(filename: String): List<String> {
        return this::javaClass::class.java.getResourceAsStream(filename)?.bufferedReader()!!.readLines()
    }
}