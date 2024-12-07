
import controllers.GameAPI
import ie.setu.models.Game
import ie.setu.models.Savefile
//import org.fusesource.jansi.Ansi.ansi
import persistence.JSONSerializer
import java.io.File
import kotlin.system.exitProcess
import utils.ScanInput.readNextChar
import utils.ScanInput.readNextInt
import utils.ScanInput.readNextLine

private val gameAPI = GameAPI(JSONSerializer(File("Games.json")))



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


fun mainMenu(): Int {
    return readNextInt(
        ansi().render(
            """
        > |------------------------------------------------------------|
        > |                     Game tracker app                       |
        > |------------------------------------------------------------|
        > | Game Menu                                                  |
        > | 1) Add a game                                              |
        > | 2) List games                                              |
        > | 3) Update a game                                           |
        > | 4) Delete a game                                           |
        > | 5) Don't forget to save your game!                         |
        > |------------------------------------------------------------|
        > | Save progress menu                                         |
        > | 6) Add a save to your game                                 |
        > | 7) Update your save file                                   |
        > | 8) Delete your save file                                   |
        > | 9) Mark game as saved/yet to be saved                      |
        > |------------------------------------------------------------|
        > | 0) Exit app                                                |
        > |                                                            |
        > |                                                            |
        > |                                                            |
        > |                                                            |
        > |------------------------------------------------------------|
        > ==>> """.trimMargin(">")
        ).toString()
    )

}

fun addGame() {
    val gameName = readNextLine("Enter the name of your game!:")
    val gameRating = readNextInt("Give the game a rating!(1,2,3,4,5):")
    val gameCategory = readNextLine("Enter your games category: ")
    val isAdded = gameAPI.add(Game(gameName = gameName, gameRating = gameRating, gameCategory = gameCategory))

    if (isAdded) {
        println("Game has been successfully added! :)")
    } else {
        println("Failed to add game :(")
    }
}

