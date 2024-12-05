package ie.setu.utils

import java.lang.NumberFormatException
import java.util.Scanner






object ScanInput {



    @JvmStatic
    fun readNextInt(prompt: String?): Int {
        do {
            try {
                print(prompt)
                return Scanner(System. `in`).next().toInt()
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please.e")
            }
        } while (true)
    }

    fun readNextDouble(prompt: String?): Double {
        do{
            try {
                print(prompt)
                return Scanner(System.`in`).next().toDouble()
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please!")
            }
        } while (true)
    }

    @JvmStatic
    fun readNextLine(prompt: String?): String {
        print(prompt)
        return Scanner(System.`in`).nextLine()
    }
}