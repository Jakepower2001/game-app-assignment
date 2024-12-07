
import controllers.GameAPI
import ie.setu.models.Game
import ie.setu.models.Savefile
//import org.fusesource.jansi.Ansi.ansi
import persistence.JSONSerializer
import java.io.File
import kotlin.system.exitProcess
//import utils.ScanInput.readNextChar
//import utils.ScanInput.readNextInt
//import utils.ScanInput.readNextLine

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