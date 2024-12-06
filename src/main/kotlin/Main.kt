//package ie.setu

import controllers.GameAPI
import models.Game
import models.Savefile
}


fun main() = startMenu()

fun startMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> addGame()
            2 -> listGames()
            3 -> updateGame()
            4 -> deleteGame()
            0 -> exitApp()
            else -> println("Invalid choice given, try again!: $option")
        }
    } while (true)
}