package utils

import java.lang.NumberFormatException
import java.util.Scanner






object ScanInput {

    /**
     * reads int given by user
     * if data entered isnt actually an int the user is prompted again
     */
    @JvmStatic
    fun readNextInt(prompt: String?): Int {
        do {
            try {
                print(prompt)
                return Scanner(System.`in`).next().toInt()
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please.")
            }
        } while (true)
    }

    /**
     * if data entered isnt a double it reprompts the user
     */
    fun readNextDouble(prompt: String?): Double {
        do {
            try {
                print(prompt)
                return Scanner(System.`in`).next().toDouble()
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please!")
            }
        } while (true)
    }

    /**
     * this reads a line of text from the user.
     * this however does not have validation to re prompt
     */
    @JvmStatic
    fun readNextLine(prompt: String?): String {
        print(prompt)
        return Scanner(System.`in`).nextLine()
    }


    @JvmStatic
    fun readNextChar(prompt: String?): Char {
        print(prompt)
        return Scanner(System.`in`).next()[0]
    }
}