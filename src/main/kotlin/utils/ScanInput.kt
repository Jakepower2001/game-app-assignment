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
}