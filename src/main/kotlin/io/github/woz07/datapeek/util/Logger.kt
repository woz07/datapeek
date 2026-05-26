package io.github.woz07.datapeek.util

object Logger {

    enum class Severity {
        SUCCESS,
        FAILURE,
        WARNING,
        INFORMATION
    }

    private const val RESET  = "\u001b[0m"
    private const val GREEN  = "\u001b[32m"
    private const val BLUE   = "\u001B[34m"
    private const val RED    = "\u001B[31m"
    private const val YELLOW = "\u001B[33m"

    fun log(severity: Severity, message: String) {
        when (severity) {
            Severity.SUCCESS ->
                println("${GREEN}[SUCCESS] => $message$RESET")

            Severity.FAILURE ->
                println("${RED}[FAILURE] => $message$RESET")

            Severity.WARNING ->
                println("${YELLOW}[WARNING] => $message$RESET")

            Severity.INFORMATION ->
                println("${BLUE}[INFO] => $message$RESET")
        }
    }

    @JvmStatic
    fun success(message: String) = log(Severity.SUCCESS, message)

    @JvmStatic
    fun failure(message: String) = log(Severity.FAILURE, message)

    @JvmStatic
    fun warning(message: String) = log(Severity.WARNING, message)

    @JvmStatic
    fun info(message: String) = log(Severity.INFORMATION, message)
}