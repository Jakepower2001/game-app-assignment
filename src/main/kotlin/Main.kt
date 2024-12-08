/**
 * welcome to my game tracker app
 * this class contains the main function which is the focal point of my app
 * the main is responsible for setting up the app, such as the initiziation
 * dependencies and configs synced with gradle
 */
import controllers.GameAPI
import models.Game
import models.Savefile
import org.fusesource.jansi.Ansi.ansi
import persistence.JSONSerializer
import java.io.File
import kotlin.system.exitProcess
import utils.ScanInput.readNextInt
import utils.ScanInput.readNextLine

private val gameAPI = GameAPI(JSONSerializer(File("Games.json")))


/**
 * main entry point for my app
 * initilizes gameApi with jsonserializer and renders a menu for the user
 */
fun main() = startMenu()

/**
 * this method gives all the options to user
 * calls appropriate options from methods
 */
fun startMenu() {
    do {
        when (val option = mainMenu()) {
            1 -> addGame()
            2 -> listGames()
            3 -> updateGame()
            4 -> deleteGame()
            5 -> saveGame()
            6 -> updateSaveFileInGame()
            7 -> deleteSaveFile()
            //8 -> save()
            //7 -> load()
            0 -> exitApp()
            else -> println("Invalid choice given, try again!: $option")
        }
    } while (true)
}


/**
 * this method displays all options to the user in menu format
 * converts it all to a string using my toString() function
 */

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

/**
 * The following are my CRUD methods for the menu
 * a user can create/update/read(search)/delete a game/game savefile
 */
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

fun deleteGame() {
    listGames()
    if (gameAPI.gamesAmount() > 0) {
        val id = readNextInt("Enter a games id to delete that game: ")
        val gameToBeDeleted = gameAPI.delete(id)
        if (gameToBeDeleted) {
            println("Your Delete Request was successful!!")
        }
    }
}

fun updateGame() {
    listGames()
    if (gameAPI.gamesAmount() > 0) {
        //user is only asked to choose the game if said game exists
        val id = readNextInt("Enter your games id to update: ")
        if (gameAPI.gameFind(id) != null) {
            val gameName = readNextLine("Enter your games name: ")
            val gameRating = readNextInt("Enter your games rating (1,2,3,4,5): ")
            val gameCategory = readNextLine("Enter your games category: ")

            if (gameAPI.update(id, Game(0, gameName, gameRating, gameCategory, isGameSaved = false))) {
                println("Update Successful!!")
            } else {
                println("Update wasn't successfully completed!")
            }
        } else {
            println("This index has no associated game entries.....")
        }
    }
}


fun listGames() {
    if (gameAPI.gamesAmount() > 0) {
        val option = readNextInt(
            """"
                > |----------------------------|
                > | 1) View all games          |
                > | 2) View saved games        |
                > |----------------------------|
        > ==>> """.trimMargin(">")
        )
        when (option) {
            1 -> listEveryGame()
            2 -> listSavedGames()
            else -> println("Invalid choice given, try again!: $option")
        }
    } else {
        println("Game is empty!")
    }
}
fun listEveryGame() = println(gameAPI.listEveryGame())

fun listSavedGames() = println(gameAPI.listSavedGames())



fun exitApp() {
    println("See you next time!!")
    exitProcess(0)
}

/**
 * These methods are for the game save segment of the menu
 * A user can save a game, update a save, delete a save,and just save/load
 */

fun saveGame() {
    listSavedGames()
    if (gameAPI.amountOfGameSaves() > 0) {
        val id = readNextInt("Enter the Id of the game you want to save your progress on!!")
        if (gameAPI.saveGame(id)) {
            println("Game saved successfully!")
        } else {
            println("Your game hasn't saved :(")
        }
    }
}


fun deleteSaveFile() {
    val game: Game? = askUserToSelectGame()
    if (game != null) {
        val savefile: Savefile? = askUserToSelectSavefile(game)
        if(savefile != null) {
            val isDeleted = game.delete(savefile.saveId)
            if (isDeleted) {
                println("Save has been deleted!")
            } else {
                println("the delete didnt work...")
            }
        }
    }
}

fun updateSaveFileInGame() {
    val game: Game? = askUserToSelectGame()
    if (game != null) {
        val savefile: Savefile? = askUserToSelectSavefile(game)
        if (savefile != null) {
            val newAdditions = readNextLine("Enter new save progress: ")
            if (game.update(savefile.saveId, Savefile(saveContents = newAdditions)))
                println("Save file progress has been updated!")
            } else {
                println("Save file progress hasnt been updated!")
            }
        } else {
            print("Game id isn't valid....add some games!")
    }
}

/** Extra functions
 * asks a user to select a savefile for a game in a saved list
 * asks for game id (first saved game will always be 0 by default)
 * checks if game is saved first. If a save is found it will return the game object
 */

private fun askUserToSelectGame(): Game? {
    listSavedGames()
    if (gameAPI.amountOfGameSaves() > 0) {
        val game = gameAPI.gameFind(readNextInt("\nEnter the games id: "))
        if (game != null) {
            if (game.isGameSaved) {
                println("Game isnt saved....")
            } else {
                return game
            }
        } else {
            println("That game id isnt here")
        }
    }
    return null
}
private fun askUserToSelectSavefile(game: Game): Savefile? {
    return if (game.amountOfSaves()> 0) {
        print(game.listSaves())
        game.findOne(readNextInt("\n Enter the save files id:"))
    } else {
        println("this game hasnt any saves yet")
        null
    }
}

fun save() {
    try {
        gameAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file")
    }
}

fun load() = try {
    gameAPI.load()
    println("File gather worked, games and savefiles have been added to system")
} catch (e: Exception) {
    system.err.println("Error reading from file")
}